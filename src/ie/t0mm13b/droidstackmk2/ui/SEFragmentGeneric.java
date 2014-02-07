package ie.t0mm13b.droidstackmk2.ui;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
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
	private ImageView ivLogo;
	private DrawerRowEntry mDrawerEntry = null;
	private int mDrawerPosition;
	private ViewPager mViewPager;
	private VPSEGA  mSEViewPagerAdapter;
	private ActionBar mSEFragActionBar;
	
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
	public void onAttach(Activity activity){
		super.onAttach(activity);
		mSEFragActionBar = getActionBar();
		mSEFragActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}
	
	@Override
	public void onDetach(){
		super.onDetach();
		mSEFragActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generic, container, false);
//        ivLogo = (ImageView)rootView.findViewById(R.id.ivSELogo);
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        //
        if (mDrawerEntry != null){
        	mSEViewPagerAdapter = new VPSEGA(getChildFragmentManager());
        	
        	mViewPager.setAdapter(mSEViewPagerAdapter);

//        	Picasso.with(getActivity())
//        	.load(mDrawerEntry.getDrawerIcon())
//        	.resize(96, 96)
//        	.into(ivLogo);
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
    class VPSEGA extends FragmentPagerAdapter{
    	private static final String TAG = "VPSEGA";
		public VPSEGA(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int argPosition) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
    	
    }
}
