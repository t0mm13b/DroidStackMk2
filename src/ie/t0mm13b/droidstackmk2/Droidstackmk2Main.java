package ie.t0mm13b.droidstackmk2;

import ie.t0mm13b.droidstackmk2.drawer.DrawerFragment;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.events.DrawerItemClickEvent;
import ie.t0mm13b.droidstackmk2.events.StackExchangeUserDialogEvent;
import ie.t0mm13b.droidstackmk2.events.FragmentFinishedEvent;
import ie.t0mm13b.droidstackmk2.helpers.EventBusProvider;
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

import com.inscription.ChangeLogDialog;
import com.inscription.WhatsNewDialog;
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
	private static final String MDSKEY_EVTBUS_REG = "MDS_EVTBUS_REGD";
	private static final String MDSKEY_FRAGLISTNR_REG = "MDS_FRAGLSTNR_REGD";
	private static final String MDSKEY_USERINFO_OBSERVD = "MDS_USERINFO_OBSERVD";
	private static final String MDSKEY_FRAGDRAWER = "MDS_FRAGDRAWER";
	private static final String MDSKEY_TITLE = "MDS_TITLE";
	private static final String MDSKEY_STACK = "MDS_STACK";
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
	private boolean mRefreshing = false;
	//
	private DrawerFragment mFragmentDrawer;
	private Fragment mFragmentMain;
	private DrawerUserSEInfo mUserInfo = new DrawerUserSEInfo();
	//
	private Map<String, Site> mSiteMap = new HashMap<String, Site>();
	private Stack<DrawerRowEntry> mStackDRE = new Stack<DrawerRowEntry>();
	//
	private boolean mEventBusIsRegistered = false;
	private boolean mFragStackListening = false;
	private boolean mUserInfoObserving = false;
	//
	private FragmentStackListener mFragStackListnr = new FragmentStackListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.supportRequestWindowFeature(Window.FEATURE_PROGRESS);
		//
		setContentView(R.layout.activity_droidstackmk2_main);
		
		mDrawerFrameLayout = (FrameLayout)this.findViewById(R.id.drawer);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		//
		mFragmentManager = getSupportFragmentManager();
		//
		this.initActionBar();
		this.initDrawer();
		//
		if (savedInstanceState != null){
			mEventBusIsRegistered = savedInstanceState.getBoolean(MDSKEY_EVTBUS_REG);
			mUserInfoObserving = savedInstanceState.getBoolean(MDSKEY_USERINFO_OBSERVD);
			mFragStackListening = savedInstanceState.getBoolean(MDSKEY_FRAGLISTNR_REG);
			mFragmentDrawer = (DrawerFragment) mFragmentManager.getFragment(savedInstanceState, MDSKEY_FRAGDRAWER);
			mActionBar.setTitle(savedInstanceState.getString(MDSKEY_TITLE));
			mDrawerToggle.syncState();
			adjustActionBarToggle();
			List<DrawerRowEntry> stackList = savedInstanceState.getParcelableArrayList(MDSKEY_STACK);
			if (mStackDRE.size() > 0) mStackDRE.clear();
			for (DrawerRowEntry dre : stackList){
				mStackDRE.push(dre);
			}
			// NEed to resstore text via top of stack!
			if (mStackDRE.size() > 0){
				mActionBar.setTitle(mStackDRE.peek().getDrawerText());
			}
		}else{
			mFragmentDrawer = (DrawerFragment) mFragmentManager.findFragmentById(R.id.drawerFragment);
			//
			if (!RetrofitClient.IsClientReady()){
				RetrofitClient.getInstance().Initialize(null); //new FakeStackExchange());
				RetrofitClient.getInstance().SetLogging(LogLevel.FULL);
				//
				new AsyncFetchSites().execute();
			}
			//
			mFragmentMain = DroidStackMk2Fragment.newInstance(null);
			FragmentTransaction transaction = mFragmentManager.beginTransaction();
			transaction.replace(R.id.content_frame, mFragmentMain);
			transaction.commit();
		}
		if (!mUserInfoObserving && mFragmentDrawer != null){
			mUserInfo.addObserver(mFragmentDrawer);
			mUserInfoObserving = true;
		}
		if (!mEventBusIsRegistered){
			EventBusProvider.getInstance().register(this);
			mEventBusIsRegistered = true;
		}
		if (!mFragStackListening){
			mFragmentManager.addOnBackStackChangedListener(mFragStackListnr);
			mFragStackListening = true;
		}
		WhatsNewDialog wnd = new WhatsNewDialog(this);
		wnd.show();
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
		case R.id.menu_changelog:
			final ChangeLogDialog changeLogDialog = new ChangeLogDialog(this);
			changeLogDialog.show();
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
	
	/***
	 * De register any handlers as we're going into pause state
	 */
	@Override
	protected void onPause(){
		super.onPause();
		if (mEventBusIsRegistered){
			EventBusProvider.getInstance().unregister(this);
			mEventBusIsRegistered = false;
		}
		if (mFragStackListening){
			mFragmentManager.removeOnBackStackChangedListener(mFragStackListnr);
			mFragStackListening = false;
		}
		if (mUserInfoObserving && mFragmentDrawer != null){
			mUserInfo.deleteObserver(mFragmentDrawer);
			mUserInfoObserving = false;
		}
	}
	
	/***
	 * Re register the handlers as we're alive n kicking
	 */
	@Override
	protected void onResume(){
		super.onResume();
		if (!mEventBusIsRegistered){
			EventBusProvider.getInstance().register(this);
			mEventBusIsRegistered = true;
		}
		if (!mFragStackListening){
			mFragmentManager.addOnBackStackChangedListener(mFragStackListnr);
			mFragStackListening = true;
		}
		if (!mUserInfoObserving && mFragmentDrawer != null){
			mUserInfo.addObserver(mFragmentDrawer);
			mUserInfoObserving = true;
		}
	}
	
	/***
	 * Save the state of play here, could be for rotation...
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putBoolean(MDSKEY_EVTBUS_REG, mEventBusIsRegistered);
		outState.putBoolean(MDSKEY_FRAGLISTNR_REG, mFragStackListening);
		outState.putBoolean(MDSKEY_USERINFO_OBSERVD, mUserInfoObserving);
		outState.putString(MDSKEY_TITLE, getTitle().toString());
		ArrayList<DrawerRowEntry> listFromStack = new ArrayList<DrawerRowEntry>(mStackDRE);
		outState.putParcelableArrayList(MDSKEY_STACK, listFromStack);
		mFragmentManager.putFragment(outState, MDSKEY_FRAGDRAWER, mFragmentDrawer);
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
		mFragmentManager.addOnBackStackChangedListener(mFragStackListnr);
		//
		mFragStackListening = true;
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
		}
	}
	
	/***
	 * Event raised when the fragment has the chevron tapped to up the parent activity.
	 * This is needed as it does not work proper after rotation
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

	/***
	 * Observable object that notifies the drawer when changes are made to either {@link com.stackexchange.api.objects.User} or
	 * {@link com.stackexchange.api.objects.NetworkUser}
	 * 
	 * @author t0mm13b
	 *
	 */
	class DrawerUserSEInfo extends Observable{
		private User mUserInfo;
		private NetworkUser mNetworkUserInfo;
		public User getUserInfo(){
			return mUserInfo;
		}
		public void setUserInfo(User userInfo){
			mUserInfo = userInfo;
			setChanged();
			notifyObservers(getUserInfo()); // fire the observers (in this case drawer fragment will pick this up 
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
	/***
	 * Simple class to compare {@link ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry} for sorting based on reputation
	 * 
	 * @author t0mm13b
	 *
	 */
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
	
	/***
	 * Simple class to handle the fragment stack events
	 * 
	 * @author t0mm13b
	 *
	 */
	class FragmentStackListener implements FragmentManager.OnBackStackChangedListener{

		@Override
		public void onBackStackChanged() {
			adjustActionBarToggle();
		}
		
	}

}
