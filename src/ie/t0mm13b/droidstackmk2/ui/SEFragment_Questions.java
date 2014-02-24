package ie.t0mm13b.droidstackmk2.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.helpers.Utils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/***
 * This deals with and governs questions...
 * Based on the bundle passed in, it can be generic all questions, featured questions or unanswered questions.
 * 
 * @author t0mm13b
 *
 */
public class SEFragment_Questions extends Fragment{
	private static final String TAG = "SEFragment_Questions";
	private DrawerRowEntry mSelectedSite;
	@InjectView(R.id.flGeneric_Progress) FrameLayout mFLProgress;
	@InjectView(R.id.flGeneric_Content) FrameLayout mFLGenericContent;
	@InjectView(R.id.pgGeneric) ProgressBar mPBLoading;
	@InjectView(R.id.tvGenericPg_NoData) TextView mTVNoData;
	@InjectView(R.id.tvFragGenTypeOfQ) TextView mTVTypeOfQuestion;
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
//		mFLProgress = (FrameLayout)rootView.findViewById(R.id.flGeneric_Progress);
//		mFLGenericContent = (FrameLayout)rootView.findViewById(R.id.flGeneric_Content);
//		//
//		mPBLoading = (ProgressBar) mFLProgress.findViewById(R.id.pgGeneric);
//		mTVNoData = (TextView) mFLProgress.findViewById(R.id.tvGenericPg_NoData);
//		//
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
    class AsyncFetchQuestions extends AsyncTask<String, Integer, String>{

    	@Override
		protected void onPreExecute(){
    		mFLGenericContent.setVisibility(View.INVISIBLE);
    		mFLProgress.setVisibility(View.VISIBLE);
    		mPBLoading.setVisibility(View.VISIBLE);
			mTVNoData.setVisibility(View.INVISIBLE);
		}
		@Override
		protected String doInBackground(String... arg0) {
			try{
				for (int nLoopy = 0; nLoopy < 10; nLoopy++){
					Thread.sleep(500);
				}
			}catch(InterruptedException intEx){
			}catch(Exception eX){
				
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... progress){
			
		}
		@Override
		protected void onPostExecute(String result){
			mPBLoading.setVisibility(View.INVISIBLE);
			mTVNoData.setVisibility(View.VISIBLE);
			new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					mFLProgress.setVisibility(View.INVISIBLE);
					mFLGenericContent.setVisibility(View.VISIBLE);
				}
				
			}, 1000);
		}
    }
}
