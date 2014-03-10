package ie.t0mm13b.droidstackmk2.questions;

import org.apache.commons.lang3.StringEscapeUtils;

import com.stackexchange.api.objects.Question;
import com.stackexchange.api.objects.Site;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ie.t0mm13b.droidstackmk2.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionView extends RelativeLayout{
	private static final String TAG = "QuestionView";
	private static final int MAX_TAGS = 5;
	
	@InjectView(R.id.tvNbrVotes) TextView mTvNumberOfVotes;
	@InjectView(R.id.tvNbrAnswers) TextView mTvNumberOfAnswers;
	@InjectView(R.id.tvQuestion) TextView mTvQuestion;
	@InjectView(R.id.tvTagOne) TextView mTvTagOne;
	@InjectView(R.id.tvTagTwo) TextView mTvTagTwo;
	@InjectView(R.id.tvTagThree) TextView mTvTagThree;
	@InjectView(R.id.tvTagFour) TextView mTvTagFour;
	@InjectView(R.id.tvTagFive) TextView mTvTagFive;
	private TextView[] mTvTags;
	
	public QuestionView(Context context){
		super(context);
		internalInit();
	}
	public QuestionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		internalInit();
	}
	public QuestionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		internalInit();
	}
	/***
	 * This stops the Design UI tool from blowing up!
	 */
	private void internalInit(){
		if (!isInEditMode()){
		}
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.inject(this, this);
		mTvTags = new TextView[] { mTvTagOne, mTvTagTwo, mTvTagThree, mTvTagFour, mTvTagFive };
	}
	
	/***
	 * Set up this view's layout in a cleaner manner.
	 * 
	 * @param dre - Question item
	 * @see {@link com.stackexchange.api.objects#Question}
	 */
	public void showQuestionEntry(Question questionItem, Site siteInfo){
		mTvNumberOfVotes.setText(String.valueOf(questionItem.score));
		//
		mTvNumberOfAnswers.setText(String.valueOf(questionItem.answerCount));
		if (questionItem.isAnswered){
			mTvNumberOfAnswers.setBackgroundColor(Color.GREEN);
		}
		//
		mTvQuestion.setText(StringEscapeUtils.unescapeHtml4(questionItem.title));
		// Use the siteInfo's tag colours....
		int nSizeTags = questionItem.listTags.size();
		for (int nLoop = 0; nLoop < nSizeTags; nLoop++){
			mTvTags[nLoop].setText(questionItem.listTags.get(nLoop).toString());
			mTvTags[nLoop].setBackgroundColor(Color.parseColor(siteInfo.styling.tagBackgroundColor));
			mTvTags[nLoop].setTextColor(Color.parseColor(siteInfo.styling.tagForegroundColor));
			mTvTags[nLoop].setVisibility(View.VISIBLE);
		}
	}
}
