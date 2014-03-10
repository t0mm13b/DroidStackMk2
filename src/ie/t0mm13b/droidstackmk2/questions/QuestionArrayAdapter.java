package ie.t0mm13b.droidstackmk2.questions;

import ie.t0mm13b.droidstackmk2.Droidstackmk2Main;
import ie.t0mm13b.droidstackmk2.R;

import java.util.ArrayList;

import com.stackexchange.api.objects.Question;
import com.stackexchange.api.objects.Site;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/***
 * The adapter for the list in the drawer aka Navigation Drawer.
 * 
 * @author t0mm13b
 *
 */
public class QuestionArrayAdapter extends BaseAdapter implements Parcelable{
	private static String TAG = "QuestionArrayAdapter";
	private ArrayList<Question> mQuestionArrayList;
	private Site mSelectedSite;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	private QuestionArrayAdapter(){
		mQuestionArrayList = new ArrayList<Question>();
	}
	private QuestionArrayAdapter(Parcel in){
		this();
		readFromParcel(in);
		notifyDataSetChanged();
	}
	public QuestionArrayAdapter(Context context, Site selectedSite){
		super();
		mQuestionArrayList = new ArrayList<Question>();
		mContext = context;
		mSelectedSite = selectedSite;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Droidstackmk2Main.LAYOUT_INFLATER_SERVICE);
	}
	public QuestionArrayAdapter(Context context, Site selectedSite, ArrayList<Question> listQuestionEntries) {
		this(context, selectedSite);
		mQuestionArrayList = listQuestionEntries;
	}

	@Override
	public int getCount() {
		if (mQuestionArrayList != null) return mQuestionArrayList.size();
		return 0;
	}

	@Override
	public Question getItem(int position) {
		if (mQuestionArrayList != null && mQuestionArrayList.size() > 0) return mQuestionArrayList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void clearAll(){
		mQuestionArrayList.clear();
	}

	public boolean remove(Question selectedQuestion){
		if (mQuestionArrayList != null){
			if (mQuestionArrayList.contains(selectedQuestion)){
				return mQuestionArrayList.remove(selectedQuestion);
			}
		}
		return false;
	}
	@Override
	public boolean hasStableIds(){
		return true;
	}
	
	public ArrayList<Question> getList(){
		return mQuestionArrayList;
	}
	
	public void setList(ArrayList<Question> list){
		if (list != null){
			this.mQuestionArrayList = list;
			notifyDataSetChanged();
		}
		
	}
	
	public void add(Question selectedQuestion){
		if (mQuestionArrayList != null){
			if (!mQuestionArrayList.contains(selectedQuestion)){
				mQuestionArrayList.add(selectedQuestion);
			}
		}
	}
	/***
	 * Note how much cleaner this {@link BaseAdapter#getView(int, View, ViewGroup)} is, using custom view and viewholder pattern 
	 * hidden away inside the custom view.
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		QuestionView qv;
		if (convertView == null){
			qv = (QuestionView)mLayoutInflater.inflate(R.layout.question_list_item, null);
		}else{
			qv = (QuestionView)convertView;
		}
		Question selectedQuestion = mQuestionArrayList.get(position);
		qv.showQuestionEntry(selectedQuestion, mSelectedSite);
		return qv;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mQuestionArrayList.size());
		dest.writeTypedList(mQuestionArrayList);
		dest.writeParcelable(mSelectedSite, flags);
	}
	
	private void readFromParcel(Parcel src){
		int nEntries = src.readInt();
		src.readTypedList(mQuestionArrayList, Question.CREATOR);
		if (nEntries != mQuestionArrayList.size()){
			throw new IllegalStateException("Inconsistent list size in QuestionArrayAdapter");
		}
		mSelectedSite = src.readParcelable(Site.class.getClassLoader());
	}
	public static final Parcelable.Creator<QuestionArrayAdapter> CREATOR = new Parcelable.Creator<QuestionArrayAdapter>() {

		@Override
		public QuestionArrayAdapter createFromParcel(Parcel source) {
			return new QuestionArrayAdapter(source);
		}

		@Override
		public QuestionArrayAdapter[] newArray(int size) {
			return new QuestionArrayAdapter[size];
		}
	};
}