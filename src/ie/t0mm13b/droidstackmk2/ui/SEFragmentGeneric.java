package ie.t0mm13b.droidstackmk2.ui;

import java.util.ArrayList;
import java.util.List;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.helpers.BaseFragment;
import ie.t0mm13b.droidstackmk2.helpers.Utils;

/***
 * The Generic fragment that shall incorporate the visuals of teh StackExchange site network depending on what was selected via 
 * Navigation Drawer item that was tapped.
 * 
 * @author t0mm13b
 *
 */
public class SEFragmentGeneric extends BaseFragment{
	private static final String TAG = "SEFragmentGeneric";
	private DrawerRowEntry mDrawerEntry = null;
	private int mDrawerPosition;
	@InjectView(R.id.pager) ViewPager mViewPager;
	@InjectView(R.id.pts) PagerSlidingTabStrip mPageTabStrip;
	@InjectView(R.id.ivSelectedSite) ImageView mIVSelectedSite;
	private PagingTabStripAdapter mPageTabStripAdapter;
	
	public static SEFragmentGeneric newInstance(int position, DrawerRowEntry dre) {
		SEFragmentGeneric frag = new SEFragmentGeneric();
		Bundle args = new Bundle();
		args.putParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY, dre);
		args.putInt(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY, position);
		frag.setArguments(args);
		return frag;
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        //
        Bundle args = getArguments();
        if (args != null){
	        if (args.containsKey(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY)){
	        	mDrawerEntry = args.getParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY);
	        }
	        if (args.containsKey(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY)){
	        	mDrawerPosition = args.getInt(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY);
	        }
			Utils.LogIt(TAG, String.format("onCreate(...) - mDrawerPosition = %d; mDrawerEntry = %s", mDrawerPosition, mDrawerEntry.toString()));
        }
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	Utils.LogIt(TAG,  "onCreateView(...)");
    	View rootView = inflater.inflate(R.layout.fragment_generic, container, false);
        //
        ButterKnife.inject(this, rootView);
        //
        if (mDrawerEntry != null){
        	Picasso.with(getActivity())
	        	.load(mDrawerEntry.getDrawerIcon())
				.resize(96, 96)
				.centerInside()
				.into(mIVSelectedSite);
        	mPageTabStripAdapter = new PagingTabStripAdapter(getChildFragmentManager(), getActivity());
        	mViewPager.setAdapter(mPageTabStripAdapter);
        	mPageTabStrip.setViewPager(mViewPager);
        	//mPageTabStrip.setBackground(new ColorDrawable(R.drawable.ab_stacked_solid_psts));
        	mPageTabStrip.setBackgroundColor(Color.parseColor("#fff3f3f3")); //#ff000000"));
//        	mPageTabStrip.setTextColor(Color.parseColor("#ffffffff")); //#ff000000"));
        	mPageTabStrip.setIndicatorColorResource(R.color.ICSHoloDarkBlue);
        	mPageTabStrip.setDividerColor(0xFF666666);
//        	mPageTabStrip.setTabBackground(R.drawable.tab_indicator_ab_psts);
//        	mPageTabStrip.setOnPageChangeListener(new OnPageChangeListener(){
//
//				@Override
//				public void onPageScrollStateChanged(int arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void onPageScrolled(int arg0, float arg1, int arg2) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void onPageSelected(int arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//        		
//        	});
        }
        if (getActionBar() != null){
        	getActionBar().setTitle(mDrawerEntry.getDrawerText());
        }
        //
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater mInflater = getActivity().getMenuInflater();
        super.onCreateOptionsMenu(menu, mInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // switch (item.getItemId()) {
        // // Respond to the action bar's Up/Home button
        // default:
        // return true;
        // }
        return super.onOptionsItemSelected(item);
    }


    /*** 
     * Restore the state
     */
	@Override
	public void activityCreated(Bundle savedInstanceState) {
		Utils.LogIt(TAG, "activityCreated(...)");
		if (savedInstanceState != null){
			mDrawerEntry = savedInstanceState.getParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY);
			mDrawerPosition = savedInstanceState.getInt(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY);
			Utils.LogIt(TAG, String.format("activityCreated(...) - position = %d; actionBarText = %s", 
					mDrawerPosition, 
					mDrawerEntry.getDrawerText()));
//			if (getActionBar() != null){
//	        	getActionBar().setTitle(mDrawerEntry.getDrawerText());
//	        }else{
//	        	Utils.LogIt(TAG, "activityCreated(...) - getActionBar() is null");
//	        }
		}else{
			Utils.LogIt(TAG, "activityCreated(...) - savedInstanceState is null");
		}
	}
	
	/***
	 * Save the state of drawer fragment
	 */
	@Override
	public void saveInstanceState(Bundle outState) {
		Utils.LogIt(TAG, "saveInstanceState(...)");
		outState.putParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY, mDrawerEntry);
		outState.putInt(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY, mDrawerPosition);
	}

    public static class PagingTabStripAdapter extends FragmentPagerAdapter{
    	private static final String TAG = "PagingTabStripAdapter";
    	private String[] mTabTitles;
    	private FragmentManager mFragManager;
    	private List<String> mListFrags = new ArrayList<String>();
    	private Context mContext;
    	
		public PagingTabStripAdapter(FragmentManager fm, Context ctxt) {
			super(fm);
			mFragManager = fm;
			mContext = ctxt;
			mTabTitles = mContext.getResources().getStringArray(R.array.se_fragment_titles);
			mListFrags.add(SEFragment_Questions.class.getName());
			mListFrags.add(SEFragment_FeaturedQuestions.class.getName());
			mListFrags.add(SEFragment_UnansweredQuestions.class.getName());
		}
		
		@Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }

		@Override
		public Fragment getItem(int argPosition) {
			String fragId = makeFragmentName(R.id.pager, argPosition);
			Fragment frag = mFragManager.findFragmentByTag(fragId);
			if (frag == null){
//				Utils.LogIt(TAG, "getItem(...) - frag is null...");
				frag = Fragment.instantiate(mContext, mListFrags.get(argPosition));
			}
			return frag;
		}

		@Override
		public int getCount() {
			return mTabTitles.length;
		}
		private static String makeFragmentName(int viewId, int index) {
	        return "android:switcher:" + viewId + ":" + index;
	    }
    }
}
