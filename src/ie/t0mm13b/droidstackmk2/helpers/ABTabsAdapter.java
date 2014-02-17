package ie.t0mm13b.droidstackmk2.helpers;

import ie.t0mm13b.droidstackmk2.DroidStackMk2App;
import ie.t0mm13b.droidstackmk2.interfaces.IFragmentLifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;

// http://stackoverflow.com/questions/9952555/actionbar-on-sub3-0-devices-implementing-actionbarsherlock-with-my-customized
public class ABTabsAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
	private static final String TAG = "ABSTabsAdapter";
	private final Context mContext;
	private final ViewPager mViewPager;
    private final ArrayList<ABTabsAdapter.ABSTabInfo> mListTabs = new ArrayList<ABTabsAdapter.ABSTabInfo>();
    private final Map<String, ABTabsAdapter.ABSTabInfo> mTabsMap = new HashMap<String, ABTabsAdapter.ABSTabInfo>();
    private final ActionBar mActionBar;
    private final FragmentManager mFragMgr;
    private int mCurrentPagePosition = 0;
    private int mCurrentTabPosition = 0;
    //
    /***
     * Helper class to hold the fragment's class, fragment, a tag identifier and text for tab.
     * @author t0mm13b
     *
     */
    static final class ABSTabInfo {
        private final Class<?> clss;
        private final Bundle args;
        private Fragment mFrag;
        private final String mTag;
        private Tab mTab;

        ABSTabInfo(String _tag, Class<?> _class, Bundle _args) {
            clss = _class;
            args = _args;
            mTag = _tag;
            mFrag = null;
        }
        public void setFragment(Fragment frag){
        	mFrag = frag;
        }
        public Fragment getFragment(){
        	return mFrag;
        }
        public void setTab(ActionBar.Tab tab){
        	mTab = tab;
        }
        public String getTag(){
        	return mTag;
        }
        public ActionBar.Tab getTab(){
        	return mTab;
        }
    }
	public ABTabsAdapter(FragmentManager fm, ActionBar actionBar, ViewPager pager) {
		super(fm);
		mFragMgr = fm;
		mContext = DroidStackMk2App.getAppContext();
		mActionBar = actionBar;
		mViewPager = pager;
		mViewPager.setAdapter(this);
		mViewPager.setOnPageChangeListener(this);
	}
	public void addTab(ActionBar.Tab tab, String tag, Class<?> clss, Bundle args) {
        ABTabsAdapter.ABSTabInfo info = new ABSTabInfo(tag, clss, args);
        info.setTab(tab);
        tab.setTag(info);
        tab.setTabListener(this);
        mListTabs.add(info);
        mActionBar.addTab(tab);
        mTabsMap.put(tag, info);
        notifyDataSetChanged();
    }
	public void removeTab(String tag){
		ABTabsAdapter.ABSTabInfo targetTabInfo = null;
		for (ABTabsAdapter.ABSTabInfo tabInfo : this.mListTabs){
			if (tabInfo.getTag().equalsIgnoreCase(tag)){
				targetTabInfo = tabInfo;
				break;
			}
		}
		if (targetTabInfo != null){
			targetTabInfo.getTab().setTabListener(null);
			this.mActionBar.selectTab(this.mListTabs.get(0).getTab());
			this.mActionBar.removeTab(targetTabInfo.getTab());
			boolean blnRmvdOk = this.mListTabs.remove(targetTabInfo);
			if (blnRmvdOk){
				Utils.LogIt(TAG, "removeTab: removed from mListTabs");
				notifyDataSetChanged();
			}
			else Utils.LogIt(TAG, "removeTab: Failed to remove from mListTabs");
		}
	}
	public void clearAll(){
		if (mListTabs != null && mListTabs.size() > 0){
//			FragmentTransaction ft = mFragMgr.beginTransaction();
			mFragMgr.getFragments().clear();
			mListTabs.clear();
			mActionBar.removeAllTabs();
			mTabsMap.clear();
			notifyDataSetChanged();
//			ft.commit();
		}		
	}
	@Override
    public int getCount() {
//		notifyDataSetChanged();
        return mListTabs.size();
    }
    public void setCurrentTabViewPage(int position){
    	try{
    		mActionBar.setSelectedNavigationItem(position);
    		mViewPager.setCurrentItem(position);
    	}finally{
    		
    	}
    	
    }
    public boolean foundTabByTag(String tag){
    	if (mTabsMap.containsKey(tag)) return true;
//    	for(ABTabsAdapter.ABSTabInfo tab : mListTabs){
//    		if (tab.getTag().equalsIgnoreCase(tag)) return true;
//    	}
    	return false;
    }
	@Override
    public Fragment getItem(int position) {
        ABTabsAdapter.ABSTabInfo info = mListTabs.get(position);
        if (info.getFragment() == null){
        	Utils.LogIt(TAG, "TabsAdapter:getItem() is null! INSTANTIATING!");
        	info.setFragment(Fragment.instantiate(mContext, info.clss.getName(), info.args));
        }else{
        	Utils.LogIt(TAG, "TabsAdapter:getItem() is non-null!");
        	assert(info.getFragment() == null);
        }
    	return info.getFragment();
    }
	public String getCurrentTabViewTag(){
		return mActionBar.getSelectedTab().getTag().toString();
	}
    public int getCurrentTabViewPage(){
		return mActionBar.getSelectedTab().getPosition();
    }
    
    @Override
    public void onPageSelected(int position) {
    	IFragmentLifecycle fragShow = (IFragmentLifecycle)getItem(position);
    	fragShow.onResumeFragment();
    	//
    	IFragmentLifecycle fragHide = (IFragmentLifecycle)getItem(mCurrentPagePosition);
    	fragHide.onPauseFragment();
    	//
    	mActionBar.setSelectedNavigationItem(position);
    	//
    	mCurrentPagePosition = position;
    }

    @Override        
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	mViewPager.setCurrentItem(tab.getPosition());
	}
	@Override
	public void onPageScrollStateChanged(int position) { }
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) { }
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) { }
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) { }
	
	
}