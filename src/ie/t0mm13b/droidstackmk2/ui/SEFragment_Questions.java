package ie.t0mm13b.droidstackmk2.ui;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.stackexchange.api.objects.CommonSEWrapper;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;
import com.stackexchange.api.objects.Question;
import com.stackexchange.api.restapi.IQuestions;
import butterknife.ButterKnife;
import butterknife.InjectView;
import ie.t0mm13b.droidstackmk2.DroidStackMk2App;
import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.helpers.RetrofitClient;
import ie.t0mm13b.droidstackmk2.helpers.Utils;
import ie.t0mm13b.droidstackmk2.questions.QuestionArrayAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/***
 * This deals with and governs questions...
 * Based on the bundle passed in, it can be generic all questions, featured questions or unanswered questions.
 * 
 * @author t0mm13b
 *
 */
public class SEFragment_Questions extends Fragment{
	private static final String TAG = "SEFragment_Questions";
	private static final int DEF_MSG_BUILD_ALL_QUESTIONS = 0x101;
	private static final int DEF_MSG_CLEAR_LISTVIEW = 0x102;
	private static final int DEF_MSG_ADD_TO_ADAPTER = 0x103;
	//
	private DrawerRowEntry mSelectedSite;
	private int mTypeOfQuestions;
	private QuestionArrayAdapter mQuestionAdapter;
	private boolean mQuestionLoadedOk = false;
	//
	@InjectView(R.id.flGeneric_Progress) FrameLayout mFLProgress;
	@InjectView(R.id.flGeneric_Content) FrameLayout mFLGenericContent;
	@InjectView(R.id.pgGeneric) ProgressBar mPBLoading;
	@InjectView(R.id.tvGenericPg_NoData) TextView mTVNoData;
	@InjectView(R.id.tvFragGenTypeOfQ) TextView mTVTypeOfQuestion;
	@InjectView(R.id.lvFragGenQuestions) ListView mLVQuestions;
	@InjectView(R.id.tvListViewEmpty) TextView mTVListEmpty;
	//
	private int MSG_BUILD_ALL_QUESTIONS = -1;
	private int MSG_CLEAR_LISTVIEW = -1;
	private int MSG_ADD_TO_ADAPTER = -1;
	
	public static SEFragment_Questions newInstance(Bundle args){
		SEFragment_Questions sefQ = new SEFragment_Questions();
		if (args != null) sefQ.setArguments(args);
		return sefQ;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_generic_questions, container, false);
		ButterKnife.inject(this, rootView);
		//
		Bundle args = getArguments();
		if (args != null){
			if (args.containsKey(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY)){
				mSelectedSite = args.getParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY);
				Utils.LogIt(TAG, "onCreateView(...) - Got the DRE - " + mSelectedSite.toString());
			}else{
				Utils.LogIt(TAG, "onCreateView(...) - Failed to obtain the DRE!");
			}
			if (args.containsKey(Utils.SE_GENERIC_FRAG_ARGS_TYPEOFQUESTIONS_KEY)){
				int typeOfQuestion = args.getInt(Utils.SE_GENERIC_FRAG_ARGS_TYPEOFQUESTIONS_KEY);
				switch(typeOfQuestion){
				case 0 :
					mTVTypeOfQuestion.setText(getActivity().getString(R.string.fragAllQuestionsTitle));
					break;
				case 1 :
					mTVTypeOfQuestion.setText(getActivity().getString(R.string.fragFeaturedQuestionsTitle));
					break;
				case 2 :
					mTVTypeOfQuestion.setText(getActivity().getString(R.string.fragUnansweredQuestionsTitle));
					break;
				}
				mTypeOfQuestions = typeOfQuestion;
				mQuestionAdapter = new QuestionArrayAdapter(this.getActivity(), mSelectedSite.getSiteInfo());
				mLVQuestions.setEmptyView(mTVListEmpty);
				mLVQuestions.setAdapter(mQuestionAdapter);
				//
				MSG_BUILD_ALL_QUESTIONS = DEF_MSG_BUILD_ALL_QUESTIONS * (mTypeOfQuestions + 1);
				MSG_CLEAR_LISTVIEW = DEF_MSG_CLEAR_LISTVIEW * (mTypeOfQuestions + 1);
				MSG_ADD_TO_ADAPTER = DEF_MSG_ADD_TO_ADAPTER * (mTypeOfQuestions + 1);
				
			}
		}else{
			Utils.LogIt(TAG, "onCreateView(...) - args is null!");
		}
		if (mSelectedSite != null){
			new AsyncFetchQuestions().execute();
		}
		return rootView;
	}
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater mInflater = getActivity().getMenuInflater();
        super.onCreateOptionsMenu(menu, mInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    final Callback<CommonSEWrapper<Question>> getQuestionsCb = new Callback<CommonSEWrapper<Question>>(){
    	@Override
		public void failure(RetrofitError argRetrofitError) {
			Toast.makeText(DroidStackMk2App.getAppContext(), "Oops, getQuestionsCb(...) failure!", Toast.LENGTH_SHORT);
		}

		@Override
		public void success(CommonSEWrapper<Question> argCSEWrapper, Response argResponse) {
			Utils.LogIt(TAG, "getQuestionsCb::success(...)");
			try{
				Utils.LogIt(TAG,  "getQuestionsCb::success(...) - mTypeOfQuestions = " + String.valueOf(mTypeOfQuestions));
				if (argCSEWrapper != null && argCSEWrapper.itemsList != null && argCSEWrapper.itemsList.size() > 0){
					mQuestionsHandler.obtainMessage(MSG_CLEAR_LISTVIEW).sendToTarget();
					for (Question quest : argCSEWrapper.itemsList){
						mQuestionsHandler.obtainMessage(MSG_ADD_TO_ADAPTER, quest).sendToTarget();
					}
					mQuestionsHandler.obtainMessage(MSG_BUILD_ALL_QUESTIONS).sendToTarget();
					mQuestionLoadedOk = true;
				}else{
					Utils.LogIt(TAG, "Uhhhh, getQuestionsCb(...) - could not parse!");
				}
			}catch(Exception eX){
				Utils.LogIt(TAG, "Exception = " + eX.toString());
			}finally{
			}
		}
    };

    class AsyncFetchQuestions extends AsyncTask<String, Integer, String>{
    	private static final String TAG = "AsyncFetchQuestions";
    	@Override
		protected void onPreExecute(){
    		mFLGenericContent.setVisibility(View.INVISIBLE);
    		mFLProgress.setVisibility(View.VISIBLE);
    		mPBLoading.setVisibility(View.VISIBLE);
			mTVNoData.setVisibility(View.INVISIBLE);
		}
		@Override
		protected String doInBackground(String... arg0) {
			IQuestions questions =  RetrofitClient.getInstance().getRESTfulClient().create(IQuestions.class);
			int nPageCount = 1;
			try{
				switch(mTypeOfQuestions){
				case 0 :
					questions.getAllQuestions(
							mSelectedSite.getSiteInfo().apiSiteParameter, // siteId
							String.valueOf(nPageCount), // pageNumber
							String.valueOf(RetrofitClient.SE_MAX_PAGESIZE), // pageSize
							"", // fromDate
							"", // toDate
							SortOrder.Desc.toString(), // orderOfSort
							"", // minDate
							"", // maxDate
							SortType.Activity.toString(), // typeOfSort
							"", // tagged
							getQuestionsCb);
					break;
				case 1 :
					questions.getFeaturedQuestions(
							mSelectedSite.getSiteInfo().apiSiteParameter, // siteId
							String.valueOf(nPageCount), // pageNumber
							String.valueOf(RetrofitClient.SE_MAX_PAGESIZE), // pageSize
							"", // fromDate
							"", // toDate
							SortOrder.Desc.toString(), // orderOfSort
							"", // minDate
							"", // maxDate
							SortType.Activity.toString(), // typeOfSort
							getQuestionsCb);
					break;
				case 2 :
					questions.getUnansweredQuestions(
							mSelectedSite.getSiteInfo().apiSiteParameter, // siteId
							String.valueOf(nPageCount), // pageNumber
							String.valueOf(RetrofitClient.SE_MAX_PAGESIZE), // pageSize
							"", // fromDate
							"", // toDate
							SortOrder.Desc.toString(), // orderOfSort
							"", // minDate
							"", // maxDate
							SortType.Activity.toString(), // typeOfSort
							"", // tagged
							getQuestionsCb);
					break;
				}
			}catch(Exception eX){
				Utils.LogIt(TAG, "doInBackground(...) Exception = " + eX.toString());
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... progress){
			
		}
		@Override
		protected void onPostExecute(String result){
			mPBLoading.setVisibility(View.INVISIBLE);
			if (!mQuestionLoadedOk) mTVNoData.setVisibility(View.VISIBLE);
			new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					if (mQuestionLoadedOk) {
						mFLProgress.setVisibility(View.INVISIBLE);
						mFLGenericContent.setVisibility(View.VISIBLE);
					}
				}
				
			}, 1000);
		}
    }
    private final Handler mQuestionsHandler = new Handler(){
    	private static final String TAG = "mQuestionsHandler";
    	@Override
        public void handleMessage(Message msg) {
    		if (msg.what == MSG_BUILD_ALL_QUESTIONS){
    			Utils.LogIt(TAG, "MSG_BUILD_ALL_QUESTIONS, count is: " + mQuestionAdapter.getCount());
            	mTVTypeOfQuestion.setVisibility(View.INVISIBLE);
            	mLVQuestions.setVisibility(View.VISIBLE);
				mQuestionAdapter.notifyDataSetChanged();
				Utils.LogIt(TAG, "MSG_BUILD_ALL_QUESTIONS, count is: " + mLVQuestions.getCount());
    		}
    		if (msg.what == MSG_CLEAR_LISTVIEW){
    			Utils.LogIt(TAG, "MSG_CLEAR_LISTVIEW");
            	mQuestionAdapter.clearAll();
            	mQuestionAdapter.notifyDataSetChanged();
    		}
    		if (msg.what == MSG_ADD_TO_ADAPTER){
    			Utils.LogIt(TAG, "MSG_ADD_TO_ADAPTER");
            	if (msg.obj != null && msg.obj instanceof Question){
            		Question questObj = (Question) msg.obj;
            		mQuestionAdapter.add(questObj);
            	}
    		}
    	}
    };
}
