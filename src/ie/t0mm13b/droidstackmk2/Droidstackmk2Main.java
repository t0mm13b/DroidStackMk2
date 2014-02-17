package ie.t0mm13b.droidstackmk2;

import ie.t0mm13b.droidstackmk2.drawer.DrawerFragment;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.events.DrawerItemClickEvent;
import ie.t0mm13b.droidstackmk2.events.StackExchangeUserDialogEvent;
import ie.t0mm13b.droidstackmk2.events.FragmentFinishedEvent;
import ie.t0mm13b.droidstackmk2.helpers.EventBusProvider;
import ie.t0mm13b.droidstackmk2.helpers.FakeStackExchange;
import ie.t0mm13b.droidstackmk2.helpers.RetrofitClient;
import ie.t0mm13b.droidstackmk2.helpers.Utils;
import ie.t0mm13b.droidstackmk2.ui.SEFragmentGeneric;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

import org.apache.commons.lang3.StringEscapeUtils;

import com.squareup.otto.Subscribe;
import com.stackexchange.api.objects.CommonSEWrapper;
import com.stackexchange.api.objects.Enums.SiteState;
import com.stackexchange.api.objects.Enums.SiteType;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;
import com.stackexchange.api.objects.NetworkUser;
import com.stackexchange.api.objects.Site;
import com.stackexchange.api.objects.User;
import com.stackexchange.api.restapi.ISites;
import com.stackexchange.api.restapi.IUsers;

import retrofit.Callback;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.SearchManager;
import android.content.Context;
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
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

/***
 * Main Fragment Activity class that handles the Navigation Bar and keeping track of fragments.
 * <ul>
 * <li>Implements {@link IDrawerListItem} for Navigation Bar handling</li>
 * <li>Implements {@link IFragmentNotify} for adjusting the ActionBar when fragments are finished</li>
 * </ul>
 * 
 * @author t0mm13b
 * @see IDrawerListItem
 * @see IFragmentNotify
 */
public class Droidstackmk2Main extends ActionBarActivity /*implements  OnQueryTextListener*/{
	private static final String TAG = "Droidstackmk2Main";
	//http://stackapps.com/apps/oauth/view/2563
	private static final String SECLIENTIDP_KEY = "client_id";
	private static final String SECLIENTIDP_VALUE = "2563";
	//
	private static final String SESECRETP_KEY = "client_secret";
	private static final String SESECRETP_VALUE = "jtLcZ6ruFMN2Woif6PKwUw((";
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
	private Stack<DrawerRowEntry> mStackDRE = new Stack<DrawerRowEntry>();
	//
	private DrawerFragment mFragmentDrawer;
	private DrawerUserSEInfo mUserInfo = new DrawerUserSEInfo();
	//
	private Map<String, Site> mSiteMap = new HashMap<String, Site>();
	//
	private boolean mEventBusIsRegistered = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.supportRequestWindowFeature(Window.FEATURE_PROGRESS);
		//
		setContentView(R.layout.activity_droidstackmk2_main);
		
		this.mDrawerFrameLayout = (FrameLayout)this.findViewById(R.id.drawer);
		//
		this.initActionBar();
		this.initDrawer();
		//
		EventBusProvider.getInstance().register(this);
		mEventBusIsRegistered = true;
		//
		mFragmentDrawer = (DrawerFragment) mFragmentManager.findFragmentById(R.id.drawerFragment);
		if (mFragmentDrawer != null){
			Log.d(TAG, "Found our fragment!");
			mUserInfo.addObserver(mFragmentDrawer);
		}
		//
		Fragment frag = DroidStackMk2Fragment.newInstance(null);
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.replace(R.id.content_frame, frag);
		transaction.commit();
		//
		RetrofitClient.getInstance().Initialize(null); //new FakeStackExchange());
		RetrofitClient.getInstance().SetLogging(LogLevel.HEADERS);
		//
		new AsyncFetchSites().execute();
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
        //searchViewAction.setOnQueryTextListener(this);
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
		// Pass the event to ActionBarDrawerToggle, if it returns true, then it has handled the app icon touch event
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
			//mOptionsMenu.setGroupVisible(0, false);
			mOptionsSearch.expandActionView();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void setActionRefreshState(final boolean refreshing) {
		if (mOptionsMenu != null) {
			if (mOptionsRefresh != null) {
				if (refreshing) {
					this.setProgressBarVisibility(true);
					new TestTask().execute("test");
				} else {
					this.setProgressBarVisibility(false);
				}
			}
		}
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		if (mEventBusIsRegistered){
			EventBusProvider.getInstance().unregister(this);
			mEventBusIsRegistered = false;
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if (!mEventBusIsRegistered){
			EventBusProvider.getInstance().register(this);
			mEventBusIsRegistered = true;
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
						adjustActionBarToggle();
					}

				});
		

	}

	
	/**
	 * Handle the ActionBar toggle depending on whether we're at the top-level
	 * view or within a fragment.
	 */
	protected void adjustActionBarToggle() {
		int backStackCount = mFragmentManager.getBackStackEntryCount();
		boolean showDrawerToggle = (backStackCount == 0);
		mDrawerToggle.setDrawerIndicatorEnabled(showDrawerToggle);
		if (showDrawerToggle) mActionBar.setTitle(getString(R.string.default_view_title));
	}

	/***
	 * Event handler when the item in Drawer is tapped
	 * @param event
	 */
	@Subscribe
	public void onDrawerItemClicked(DrawerItemClickEvent event){
		final DrawerRowEntry dre = event.getDREntry();
		final int position = event.getDREntryPosition();
		Utils.LogIt(TAG, String.format("onDrawerItemClicked(...) - position = %d; actionBarText = %s", 
				position, 
				dre.getDrawerText()));
		selectItem(position, dre);		
	}
	
	/***
	 * Pulls in the user id's account from a site that was long-pressed within the drawer.
	 * 
	 * Event receiver for when {@link ie.t0mm13b.droidstackmk2.drawer.StackExchangeUserDialog} is either cancelled 
	 * or user id submitted, {@link ie.t0mm13b.droidstackmk2.events.StackExchangeUserDialogEvent#getIsCancelled()} or
	 * {@link ie.t0mm13b.droidstackmk2.events.StackExchangeUserDialogEvent#getStackExchangeUserId()}
	 * 
	 * @param event
	 * @see {@link ie.t0mm13b.droidstackmk2.events.StackExchangeUserDialogEvent}
	 * @see {@link getAssociatedSites()}
	 */
	@Subscribe
	public void onStackExchangeUserDialog(StackExchangeUserDialogEvent event){
		if (event.getIsCancelled()){
			Utils.LogIt(TAG, String.format("onStackExchangeUserDialog(...) - User cancelled"));
			return;
		}
		final String seUserId = event.getStackExchangeUserId();
		final DrawerRowEntry dre = event.getDrawerItem();
		final int position = event.getDrawerPosition();
		// Now we have the account number....
		Utils.LogIt(TAG, String.format("onStackExchangeUserDialog(...) - seUserId = %s", seUserId));
		MenuItemCompat.setActionView(mOptionsRefresh, R.layout.actionbar_indeterminate_progress);

		// Ok, now the user id is picked and returned back, we need to pull that in!
		final IUsers users = RetrofitClient.getInstance().getRESTfulClient().create(IUsers.class);
		users.getUsersById(seUserId, dre.getSiteInfo().apiSiteParameter, 
				"", 
				"", 
				"", 
				"", 
				SortOrder.Desc, 
				"", 
				"", 
				SortType.Reputation,
				new Callback<CommonSEWrapper<User>>(){

					@Override
					public void failure(RetrofitError arg0) {
						MenuItemCompat.setActionView(mOptionsRefresh, null);
						Toast.makeText(DroidStackMk2App.getAppContext(), "Oops, getUsersById(...) failure!", Toast.LENGTH_SHORT);
					}

					@Override
					public void success(CommonSEWrapper<User> argCSEWrapper, Response argResponse) {
						Utils.LogIt(TAG,  "onStackExchangeUserDialog::success(...) - " + argCSEWrapper.toString());
						if (argCSEWrapper != null && argCSEWrapper.itemsList != null && argCSEWrapper.itemsList.size() == 1){
							User selectdUser = argCSEWrapper.itemsList.get(0);
							Utils.LogIt(TAG,  "onStackExchangeUserDialog(...)::success(...) - User = " + selectdUser.toString());
							mUserInfo.setUserInfo(selectdUser);
							getAssociatedSites(users, mUserInfo.getUserInfo(), position, dre);
						}
						MenuItemCompat.setActionView(mOptionsRefresh, null);	
					}
			
		});
	}

	/***
	 * pulls in teh associated accounts across the StackExchange network and trims the initial excess sites from teh drawer
	 * and re-sorts them based on order of rep points.
	 * 
	 * @param userAPI
	 * @param userInfo
	 * @param position
	 * @param dre
	 */
	private void getAssociatedSites(final IUsers userAPI, final User userInfo, final int position, final DrawerRowEntry dre){
		MenuItemCompat.setActionView(mOptionsRefresh, R.layout.actionbar_indeterminate_progress);
		userAPI.getAssociatedAccountsByAccountId(
				String.valueOf(userInfo.accountId), 
				"", 
				"",
				new Callback<CommonSEWrapper<NetworkUser>>(){

					@Override
					public void failure(RetrofitError argRetrofitError) {
						MenuItemCompat.setActionView(mOptionsRefresh, null);
						Toast.makeText(DroidStackMk2App.getAppContext(), "Oops, getAssociatedAccountsByAccountId(...) failure!", Toast.LENGTH_SHORT);
					}

					@Override
					public void success(CommonSEWrapper<NetworkUser> argCSEWrapper, Response argResponse) {
						DrawerRowEntry newDRE = null;
						List<DrawerRowEntry> dreList = new ArrayList<DrawerRowEntry>();
						if (argCSEWrapper != null && argCSEWrapper.itemsList != null && argCSEWrapper.itemsList.size() > 0){
							mFragmentDrawer.getDrawerAdapter().clearAll();
							for (NetworkUser nw : argCSEWrapper.itemsList){
								if (nw.siteUrl != null){
									Site assocSite = mSiteMap.get(nw.siteUrl);
									if (assocSite != null){
										Utils.LogIt(TAG, "getAssociatedAccountsByAccountId::success(...) assocSite = " + assocSite.toString());
										Utils.LogIt(TAG, "getAssociatedAccountsByAccountId::success(...) nw = " + nw.toString());
										DrawerRowEntry dreAssoc = new DrawerRowEntry(
												StringEscapeUtils.unescapeHtml4(assocSite.name), 
												assocSite.iconUrl, 
												assocSite.apiSiteParameter);
										dreAssoc.setSiteInfo(assocSite);
										dreAssoc.setNetworkUserInfo(nw);
										dreList.add(dreAssoc); // Add to temporary list!
										if (dre.equals(dreAssoc)){
											// Yup! Longpressed on row matches this associated newly recreated entry, which will have the correct
											// rep points etc
											Utils.LogIt(TAG, "getAssociatedAccountsByAccountId::success(...) - Found the intended target!");
											newDRE = dreAssoc;
										}
									}else{
										Utils.LogIt(TAG, "getAssociatedAccountsByAccountId::success(...) assocSite is null! nw = " + nw.toString());
									}
								}
							}
							if (dreList.size() > 0){
								Collections.sort(dreList, new DREComparator()); // Sort by reputation!
								for (DrawerRowEntry dre : dreList){
									mFragmentDrawer.getDrawerAdapter().add(dre);
								}
							}
							mFragmentDrawer.getDrawerAdapter().notifyDataSetChanged();							
						}
						MenuItemCompat.setActionView(mOptionsRefresh, null);
						// If we have not found the target of long-pressed row, fallback
						selectItem(position, (newDRE == null) ? dre : newDRE); 
						
					}
					
				});
	}
	/**
	 * Swap out the fragment depending on which item in teh drawer was tapped
	 * on.
	 * 
	 * @param position
	 */
	private void selectItem(int position, DrawerRowEntry dre) {
		Fragment frag = null;
		//
		frag = SEFragmentGeneric.newInstance(position, dre);
		if (frag != null) {
			// Insert the fragment by replacing any existing fragment
			FragmentTransaction transaction = mFragmentManager.beginTransaction();
			transaction.replace(R.id.content_frame, frag);
			// add the transaction to the back stack so the user can navigate back
			transaction.addToBackStack(frag.getClass().getSimpleName());
			transaction.commit();
			//
			NetworkUser nwUser = dre.getNetworkUserInfo();
			if (nwUser != null){
				mUserInfo.setNetworkUserInfo(nwUser);
			}
			// Highlight the selected item, update the title, and close the drawer
			mActionBar.setTitle(dre.getDrawerText());
			mDrawerLayout.closeDrawer(this.mDrawerFrameLayout);
			mStackDRE.push(dre);
			dumpStack();
		}
	}
	
	private void dumpStack(){
		Utils.LogIt(TAG, "dumpStack *** ENTER ***");
		for (DrawerRowEntry dre : mStackDRE){
			Log.d(TAG, "dumpStack: dre = " + dre);
		}
		Utils.LogIt(TAG, "dumpStack *** LEAVE ***");
	}

	/***
	 * Event raised when the fragment has the chevron tapped to up the parent activity.
	 * 
	 * @param event
	 */
	@Subscribe
	public void onFragmentFinished(FragmentFinishedEvent event){
		Utils.LogIt(TAG, "onFragmentFinished");
		try{
			mStackDRE.pop();
			if (!mStackDRE.isEmpty()){
				DrawerRowEntry dre = mStackDRE.peek();
				if (dre != null){
					mActionBar.setTitle(dre.getDrawerText());
					mUserInfo.setNetworkUserInfo(dre.getNetworkUserInfo());
				}
			}
		}catch(EmptyStackException eseEx){
			mActionBar.setTitle(getString(R.string.default_view_title));
		}
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
				for (int i = 0; i < 100; i++){
					int progress = ((Window.PROGRESS_END - Window.PROGRESS_START) / 100) * i;
					setSupportProgress(progress);
					Thread.sleep(50);
				}
				//Thread.sleep(2000);
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
	
/*
	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		mOptionsMenu.setGroupVisible(0, true);
		mOptionsSearch.collapseActionView();
		// TODO Auto-generated method stub
		return false;
	}*/
	
	/***
	 * Retrieve the known list of sites across the Stack Exchange Network, only those that are normal and non-beta (<b>i.e. main</b>).
	 * Build up the arraylist and pass that into the {@link DrawerArrayAdapter} and that's the Navigation Drawer sorted.
	 * 
	 * @author t0mm13b
	 *
	 */
	class AsyncFetchSites extends AsyncTask <String, Integer, String>{
		private static final String TAG = "AsyncFetchSites";
		@Override
		protected void onPreExecute(){
			setProgressBarVisibility(true);
			mFragmentDrawer.getDrawerAdapter().clearAll();//.clearDrawerList();
		}
		@Override
		protected String doInBackground(String... arg0) {
			boolean hasLoadedSites = false;
			ISites sites = RetrofitClient.getInstance().getRESTfulClient().create(ISites.class);
			CommonSEWrapper<Site> siteWrapper;
			int nPageCount = 1;
			ArrayList<Site> siteList = new ArrayList<Site>();
			boolean hasRetrofitError = false;
			try{
				do{
					siteWrapper = sites.getAllSitesSync(null, 
							String.valueOf(nPageCount), 
							String.valueOf(RetrofitClient.SE_MAX_PAGESIZE));
					if (siteWrapper != null){
						Utils.LogIt(TAG, String.format("doInBackground(...) - Page: %d; Has More: %s", nPageCount, String.valueOf(siteWrapper.hasMore)));
						if (siteWrapper != null && siteWrapper.itemsList != null && siteWrapper.itemsList.size() > 0){
							for (Site siteInfo : siteWrapper.itemsList){
								if (siteInfo.siteType == SiteType.MainSite && siteInfo.siteState == SiteState.Normal){
									siteList.add(siteInfo);
									if (!mSiteMap.containsKey(siteInfo.siteUrl)){
										mSiteMap.put(siteInfo.siteUrl, siteInfo);
									}
								}
							}
						}
						if (siteWrapper.hasMore) nPageCount++;
						else hasLoadedSites = true;
					}else break;
				}while(!hasLoadedSites);
			}catch(RetrofitError rfEx){
				hasRetrofitError = true;
			}
			int nMaxSiteCount = siteList.size();
			Utils.LogIt(TAG, String.format("doInBackground(...) - siteList Count = %d", nMaxSiteCount));
			if (nMaxSiteCount > 0){
				for (int nSiteIndex = 0; nSiteIndex < nMaxSiteCount; nSiteIndex++){
					Site siteInfo = siteList.get(nSiteIndex);
					Utils.LogIt(TAG, String.format("doInBackground(...) - [%d] - siteInfo = %s", nSiteIndex, siteInfo.name));
					final DrawerRowEntry dre = new DrawerRowEntry(
							StringEscapeUtils.unescapeHtml4(siteInfo.name), 
							((hasRetrofitError) ? "ic_error" : siteInfo.iconUrl), 
							siteInfo.apiSiteParameter);
					// need to build a map based on url, site for quick lookups via associated accounts.
					// mSiteMap is cached above!
					dre.setSiteInfo(siteInfo);
					mFragmentDrawer.getDrawerAdapter().add(dre);
					publishProgress(nSiteIndex);
				}
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... progress){
			int nProgress = progress[0];
			Utils.LogIt(TAG, "onProgressUpdate(...) - nProgress = " + nProgress);
			int progressTrack = ((Window.PROGRESS_END - Window.PROGRESS_START) / 100) * nProgress;
			setSupportProgress(progressTrack);
		}
		@Override
		protected void onPostExecute(String result){
			setProgressBarVisibility(false);
			mFragmentDrawer.getDrawerAdapter().notifyDataSetChanged();
		}
	}

	class DrawerUserSEInfo extends Observable{
		private User mUserInfo;
		private NetworkUser mNetworkUserInfo;
		public User getUserInfo(){
			return mUserInfo;
		}
		public void setUserInfo(User userInfo){
			mUserInfo = userInfo;
			setChanged();
			notifyObservers(getUserInfo());
		}
		public NetworkUser getNetworkUser(){
			return mNetworkUserInfo;
		}
		public void setNetworkUserInfo(NetworkUser nwUserInfo){
			mNetworkUserInfo = nwUserInfo;
			setChanged();
			notifyObservers(getNetworkUser());
		}
	}
	class DREComparator implements Comparator<DrawerRowEntry>{

		@Override
		public int compare(DrawerRowEntry lhs, DrawerRowEntry rhs) {
			NetworkUser nwLeft = lhs.getNetworkUserInfo();
			NetworkUser nwRight = rhs.getNetworkUserInfo();
			if (nwLeft != null && nwRight != null){
				return nwRight.reputation - nwLeft.reputation;
			}
			return 0;
		}
		
	}
}
