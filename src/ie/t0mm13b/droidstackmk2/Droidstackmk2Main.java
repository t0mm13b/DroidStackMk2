package ie.t0mm13b.droidstackmk2;

import ie.t0mm13b.droidstackmk2.interfaces.IDrawerListItem;
import ie.t0mm13b.droidstackmk2.interfaces.IFragmentNotify;
import ie.t0mm13b.droidstackmk2.ui.CareersFragment;
import ie.t0mm13b.droidstackmk2.ui.DrawerFragment;
import ie.t0mm13b.droidstackmk2.ui.SFFragment;
import ie.t0mm13b.droidstackmk2.ui.SOFragment;
import ie.t0mm13b.droidstackmk2.ui.SUFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class Droidstackmk2Main extends ActionBarActivity implements IDrawerListItem, IFragmentNotify{
	private static final String TAG = "Droidstackmk2Main";
	//
	private static final String SECLIENTIDP_KEY = "client_id";
	private static final String SECLIENTIDP_VALUE = "2492";
	//
	private static final String SESECRETP_KEY = "client_secret";
	private static final String SESECRETP_VALUE = "ofXllBxWXNMuvcEaDI3RIg((";
	//
	private ActionBar mActionBar;
	private FragmentManager mFragmentManager;
	//
	private DrawerLayout mDrawerLayout;
	private FrameLayout mDrawerFrameLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	//
	private Menu mOptionsMenu;
	private MenuItem mOptionsRefresh;
	private MenuItem mOptionsSearch;
	//
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	//
	private boolean mRefreshing = false;
	//
	private Stack<String> mStackActionTitles = new Stack<String>();
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_droidstackmk2_main);
		this.mDrawerFrameLayout = (FrameLayout)this.findViewById(R.id.drawer);
		//
		this.initActionBar();
		this.initDrawer();
		//
		Fragment frag = new DroidStackMk2Fragment();
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.replace(R.id.content_frame, frag);
		transaction.commit();
		mStackActionTitles.push(getString(R.string.default_view_title));
		
/*		RetrofitClient.getInstance().Initialize(new FakeStackExchange());
//		RestAdapter ra = new RestAdapter.Builder()
//		.setServer(SEURL)
//		.setClient(new FakeStackExchange())
//		.setLogLevel(LogLevel.FULL)
//		.build();
		
		ISites sites = RetrofitClient.getInstance().getRESTfulClient().create(ISites.class);
		sites.getAllSites("", "", "", new Callback<CommonSEWrapper<Site>> (){

			@Override
			public void failure(RetrofitError argRetrofitError) {
				// TODO Auto-generated method stub
				if (argRetrofitError.isNetworkError()){
					Log.d(TAG, "sites.getAllSites.failure() - Network error!");
				}else{
					Log.d(TAG, "sites.getAllSites.failure() - " + argRetrofitError);
				}
			}

			@Override
			public void success(CommonSEWrapper<Site> argCSEWrapper, Response argResponse) {
				// TODO Auto-generated method stub
		        Log.d(TAG, "sites.getAllSites.success() - cseWrapper = " + argCSEWrapper.toString());
			}
		});*/
	}

	@SuppressWarnings("unused")
	private String cvtRespToJson(InputStream in){
		// TODO Auto-generated method stub
		InputStream inStream = in; 
		BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
		StringBuilder sb = new StringBuilder();
		String line = null;
		boolean isClosedOk = false;
		boolean hasIOEx = false;
        try {
            while ((line = br.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            hasIOEx = true;
        } finally {
            try {
            	inStream.close();
            	isClosedOk = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (hasIOEx) return null;
        if (isClosedOk) return sb.toString();
        return "";
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		mOptionsMenu = menu;
		getMenuInflater().inflate(R.menu.droidstackmk2_main, menu);
		mOptionsRefresh = menu.findItem(R.id.menu_refresh);
		mOptionsSearch = menu.findItem(R.id.menu_search);
		SearchView searchViewAction = (SearchView)MenuItemCompat.getActionView(mOptionsSearch);
		SearchManager sManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
		searchViewAction.setSearchableInfo(sManager.getSearchableInfo(getComponentName()));
        searchViewAction.setIconifiedByDefault(true);		
		return true;
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerFrameLayout);
		mOptionsMenu.setGroupVisible(0, !drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			// handle All activity refresh here...
			// When ready, show the indeterminate progress bar
			if (!mRefreshing) {
				mRefreshing = true;
				setActionRefreshState(true);
			}
			return true;
		case R.id.menu_search:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void setActionRefreshState(final boolean refreshing) {
		if (mOptionsMenu != null) {
			if (mOptionsRefresh != null) {
				if (refreshing) {
					MenuItemCompat.setActionView(mOptionsRefresh,
							R.layout.actionbar_indeterminate_progress);
					new TestTask().execute("test");
				} else {
					MenuItemCompat.setActionView(mOptionsRefresh, null);
				}
			}
		}
	}
	
	private void handleDrawerOpenClose() {
		ActivityCompat.invalidateOptionsMenu(Droidstackmk2Main.this); // creates
																	// call to
																	// onPrepareOptionsMenu()
	}

	/**
	 * Initialise the action bar.
	 */
	private void initActionBar() {
		mActionBar = getSupportActionBar();
		mActionBar.setTitle(getString(R.string.default_view_title));
		mActionBar.setSubtitle(getString(R.string.app_name));
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
	}
	
	/**
	 * Initialise the sliding drawer navigation
	 */
	private void initDrawer() {
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		//
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				//getActionBar().setTitle(mTitle);
				handleDrawerOpenClose();
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				//getActionBar().setTitle(mDrawerTitle);
				handleDrawerOpenClose();
			}
		};
		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		//
		mFragmentManager = getSupportFragmentManager();
		mFragmentManager
				.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

					@Override
					public void onBackStackChanged() {
						// TODO Auto-generated method stub
						adjustActionBarToggle();
					}

				});
		DrawerFragment fragmentDrawer = (DrawerFragment) mFragmentManager.findFragmentById(R.id.drawerFragment);
		if (fragmentDrawer != null){
			Log.d(TAG, "Found our fragment!");
			if (!fragmentDrawer.isListenerRegistered()){
				fragmentDrawer.registerListener(this);
			}
		}

	}

	
	/**
	 * Handle the ActionBar toggle depending on whether we're at the top-level
	 * view or within a fragment.
	 */
	protected void adjustActionBarToggle() {
		// TODO Auto-generated method stub
		int backStackCount = mFragmentManager.getBackStackEntryCount();
		boolean showDrawerToggle = (backStackCount == 0);
		mDrawerToggle.setDrawerIndicatorEnabled(showDrawerToggle);
		if (showDrawerToggle) mActionBar.setTitle(getString(R.string.default_view_title));
	}


	@Override
	public void cbDrawerListItemClick(int position, long id, String actionBarText) {
		// TODO Auto-generated method stub
		Log.d(TAG, "cbDrawerListItemClick");
		selectItem(position, actionBarText);
	}
	/**
	 * Swap out the fragment depending on which item in teh drawer was tapped
	 * on.
	 * 
	 * @param position
	 */
	private void selectItem(int position, String actionBarText) {
		Fragment frag = null;
		switch (position) {
		case 0:
			frag = new SOFragment();
			((SOFragment)frag).registerFragmentNotify(this);
			break;
		case 1:
			frag = new CareersFragment();
			((CareersFragment)frag).registerFragmentNotify(this);
			break;
		case 2:
			frag = new SFFragment();
			((SFFragment)frag).registerFragmentNotify(this);
			break;
		case 3:
			frag = new SUFragment();
			((SUFragment)frag).registerFragmentNotify(this);
			break;
		default:
			break;
		}
		if (frag != null) {
			// Bundle args = new Bundle();
			// args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
			// frag.setArguments(args);

			// Insert the fragment by replacing any existing fragment
			FragmentTransaction transaction = mFragmentManager
					.beginTransaction();
			transaction.replace(R.id.content_frame, frag);
			// add the transaction to the back stack so the user can navigate back
			transaction.addToBackStack(frag.getClass().getSimpleName());
			transaction.commit();
			// Highlight the selected item, update the title, and close the drawer
			mActionBar.setTitle(actionBarText);
			mDrawerLayout.closeDrawer(this.mDrawerFrameLayout);
			mStackActionTitles.push(actionBarText);
			dumpStack();
		}
	}
	
	private void dumpStack(){
		Log.d(TAG, "dumpStack *** ENTER ***");
		for (String s : mStackActionTitles){
			Log.d(TAG, "dumpStack: s = " + s);
		}
		Log.d(TAG, "dumpStack *** LEAVE ***");
	}

	/**
	 * Test to show the refresh spinner on action bar.
	 * 
	 * @author tombrennan
	 * 
	 */
	private class TestTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// Simulate something long running
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			mRefreshing = false;
			setActionRefreshState(false);
		}
	};
	
	/***
	 * Fake StackExchange Server Client
	 * 
	 * @author t0mm13b
	 *
	 */
	public class FakeStackExchange implements Client{
		private static final String TAG = "FakeStackExchange";
		// Could have hashmap of each json, some NOT all of them!
		@Override
		public Response execute(Request argRequest) throws IOException {
			// TODO Auto-generated method stub
			Uri uri = Uri.parse(argRequest.getUrl());

	        Log.d(TAG, "fetching uri: " + uri.toString());

	        String responseString = "";

	        if(uri.getPath().equals("/path/of/interest")) {
	            responseString = "JSON STRING HERE";
	        } else {
	            responseString = "OTHER JSON RESPONSE STRING";
	        }

	        return new Response(200, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));

		}
		
	}

	@Override
	public void cbFragmentFinished() {
		// TODO Auto-generated method stub
		Log.d(TAG, "cbFragmentFinished");
		dumpStack();
		try{
			mStackActionTitles.pop();
			if (!mStackActionTitles.isEmpty()){
				mActionBar.setTitle(mStackActionTitles.peek());
			}
		}catch(EmptyStackException eseEx){
			mActionBar.setTitle(getString(R.string.default_view_title));
		}

	}

}