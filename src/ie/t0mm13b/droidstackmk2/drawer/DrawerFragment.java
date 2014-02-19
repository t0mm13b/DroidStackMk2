package ie.t0mm13b.droidstackmk2.drawer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.squareup.picasso.Picasso;
import com.stackexchange.api.objects.NetworkUser;
import com.stackexchange.api.objects.User;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.events.DrawerItemClickEvent;
import ie.t0mm13b.droidstackmk2.events.DrawerItemLongClickEvent;
import ie.t0mm13b.droidstackmk2.helpers.EventBusProvider;
import ie.t0mm13b.droidstackmk2.helpers.RoundedTransformation;
import ie.t0mm13b.droidstackmk2.helpers.Utils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/***
 * The fragment embedded in the Navigation Drawer. 
 * <br/>
 * 
 * @author t0mm13b
 *
 */
public class DrawerFragment extends Fragment implements Observer{
	private static final String TAG = "DrawerFragment";
	private static final String DFKEY_ADAPTER_LIST = "DF_ADAPTER_LIST";
	private static final String DFKEY_USERINFO = "DF_USERINFO";
	private static final String DFKEY_NWUSERINFO = "DF_NWUSERINFO";
	//
	@InjectView(R.id.ivUserGravatar) ImageView mIVUserGravatar;
	@InjectView(R.id.tvUserInfoSEId) TextView mTVUserName;
	@InjectView(R.id.tvUserInfoRep) TextView mTVUserRepCount;
	@InjectView(R.id.tvUserInfoBadgesGold) TextView mTVUserBadgeGold;
	@InjectView(R.id.tvUserInfoBadgesSilver) TextView mTVUserBadgeSilver;
	@InjectView(R.id.tvUserInfoBadgesBronze) TextView mTVUserBadgeBronze;
	//
	@InjectView(R.id.lvDrawerItems) ListView mDrawerList;
	@InjectView(R.id.emptyDrawerList) TextView mDrawerListEmpty;
	//
	private DrawerArrayAdapter mDrawerAdapter;
	private boolean isRegistered = false;
	private User aUserInfo;
	private NetworkUser aNetworkUserInfo;
	//
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drawer_fragment, container, false);
        //
        ButterKnife.inject(this, rootView);
        //
		mDrawerAdapter = new DrawerArrayAdapter(this.getActivity().getApplicationContext());
		mDrawerList.setEmptyView(mDrawerListEmpty);
		//
		mDrawerList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> argAdapter, View argView,	int argPosition, long argId) {
				Utils.LogIt(TAG, String.format("mDrawerList::onItemLongClick(...) - position = %d", argPosition));
				// Prompt for user account info then, find associated accounts and re-trim the list in the drawer...
				final DrawerRowEntry dre = mDrawerAdapter.getItem(argPosition);
				final int position = argPosition;
				Utils.LogIt(TAG, String.format("onDrawerItemLongClicked: dre = " + dre.toString()));
				// Check against the shared prefs for that site info?
				StackExchangeUserDialog dlg = StackExchangeUserDialog.newInstance(dre, position);
				dlg.show(getActivity().getSupportFragmentManager(), "userPickrDialog");
				return true;
			}
			
		});
		mDrawerList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> argViewAdapter, View argView, int argPosition, long argId) {
				EventBusProvider.getInstance().post(new DrawerItemClickEvent(argPosition, mDrawerAdapter.getItem(argPosition)));
			}
			
		});
		// Set the adapter for the list view
		mDrawerList.setAdapter(this.mDrawerAdapter);
        return rootView;
	}
		
	/***
	 * Get the Drawer's Adapter
	 * 
	 * @return {@link DrawerArrayAdapter}
	 */
	public synchronized DrawerArrayAdapter getDrawerAdapter(){
		return mDrawerAdapter;
	}

	/***
	 * Handle the rotation scenario!
	 */
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(DFKEY_ADAPTER_LIST, mDrawerAdapter.getList());
		outState.putParcelable(DFKEY_USERINFO, aUserInfo);
		outState.putParcelable(DFKEY_NWUSERINFO, aNetworkUserInfo);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null){
			aNetworkUserInfo = savedInstanceState.getParcelable(DFKEY_NWUSERINFO);
			aUserInfo = savedInstanceState.getParcelable(DFKEY_USERINFO);
			ArrayList<DrawerRowEntry> listDRE = new ArrayList<DrawerRowEntry>();
			listDRE = savedInstanceState.getParcelableArrayList(DFKEY_ADAPTER_LIST);
			mDrawerAdapter.setList(listDRE);
			if (aUserInfo != null) setUserInfo(aUserInfo);
			if (aNetworkUserInfo != null) setNetworkUserInfo(aNetworkUserInfo);
		}
	}
	private void setUserInfo(User usr){
		RoundedTransformation rt = new RoundedTransformation(10, 1);
		if (usr.profileImage != null && usr.profileImage.length() > 0){
			Picasso.with(getActivity())
				.load(usr.profileImage)
				.resize(96, 96)
				.centerInside()
				.transform(rt)
				.into(mIVUserGravatar);
		}
		mTVUserName.setText(usr.displayName);
		String strRep = String.format(getString(R.string.SEUserRep_fmt), NumberFormat.getNumberInstance(Locale.US).format(usr.reputation));
		mTVUserRepCount.setText(strRep);
		mTVUserBadgeGold.setText(String.valueOf(usr.badges.gold));
		mTVUserBadgeSilver.setText(String.valueOf(usr.badges.silver));
		mTVUserBadgeBronze.setText(String.valueOf(usr.badges.bronze));
	}
	private void setNetworkUserInfo(NetworkUser nwUsr){
		String strRep = String.format(getString(R.string.SEUserRep_fmt), NumberFormat.getNumberInstance(Locale.US).format(nwUsr.reputation));
		mTVUserRepCount.setText(strRep);
		mTVUserBadgeGold.setText(String.valueOf(nwUsr.badges.gold));
		mTVUserBadgeSilver.setText(String.valueOf(nwUsr.badges.silver));
		mTVUserBadgeBronze.setText(String.valueOf(nwUsr.badges.bronze));
	}
	/***
	 * A callback based on the Observable object in which, we then update our drawer to reflect it.
	 * (When changes are made to an object that implements Observable and listener is added, this gets fired.)
	 * Could do it another way, using the Otto's bus... 
	 */
	@Override
	public void update(Observable observable, Object data) {
		if (data != null) Utils.LogIt(TAG, "update(...) data = " + data.toString());
		// Here we update the user info in the fragment
		if (data != null && data instanceof User){
			User lUserInfo = (User)data;
			if (lUserInfo != null){
				aUserInfo = lUserInfo;
				setUserInfo(aUserInfo);
			}
		}
		if (data != null && data instanceof NetworkUser){
			NetworkUser nwUserInfo = (NetworkUser)data;
			if (nwUserInfo != null){
				aNetworkUserInfo = nwUserInfo;
				setNetworkUserInfo(aNetworkUserInfo);
			}
		}
	}
}
