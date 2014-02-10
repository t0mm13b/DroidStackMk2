package ie.t0mm13b.droidstackmk2.drawer;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import com.squareup.picasso.Picasso;
import com.stackexchange.api.objects.NetworkUser;
import com.stackexchange.api.objects.User;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.events.DrawerItemClickEvent;
import ie.t0mm13b.droidstackmk2.events.DrawerItemLongClickEvent;
import ie.t0mm13b.droidstackmk2.helpers.EventBusProvider;
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
	//
	private ImageView mIVUserGravatar;
	private TextView mTVUserName;
	private TextView mTVUserRepCount;
	private TextView mTVUserBadgeGold;
	private TextView mTVUserBadgeSilver;
	private TextView mTVUserBadgeBronze;
	//
	private ListView mDrawerList;
	private TextView mDrawerListEmpty;
	//
	private DrawerArrayAdapter mDrawerAdapter;
	private boolean isRegistered = false;
	//
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drawer_fragment, container, false);
        //
        mIVUserGravatar = (ImageView) rootView.findViewById(R.id.ivUserGravatar);
        mTVUserName = (TextView) rootView.findViewById(R.id.tvUserInfoSEId);
        mTVUserRepCount = (TextView) rootView.findViewById(R.id.tvUserInfoRep);
        mTVUserBadgeGold = (TextView) rootView.findViewById(R.id.tvUserInfoBadgesGold);
        mTVUserBadgeSilver = (TextView) rootView.findViewById(R.id.tvUserInfoBadgesSilver);
        mTVUserBadgeBronze = (TextView) rootView.findViewById(R.id.tvUserInfoBadgesBronze);
        //
        mDrawerList = (ListView) rootView.findViewById(R.id.lvDrawerItems);
        mDrawerListEmpty = (TextView) rootView.findViewById(R.id.emptyDrawerList);
		mDrawerAdapter = new DrawerArrayAdapter(this.getActivity().getApplicationContext());
		mDrawerList.setEmptyView(mDrawerListEmpty);
		//
		mDrawerList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> argAdapter, View argView,	int argPosition, long argId) {
				Utils.LogIt(TAG, String.format("mDrawerList::onItemLongClick(...) - position = %d", argPosition));
				// Prompt for user account info then, find associated accounts and re-trim the list in the drawer...
				EventBusProvider.getInstance().post(new DrawerItemLongClickEvent(argPosition, mDrawerAdapter.getItem(argPosition)));
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

	@Override
	public void update(Observable observable, Object data) {
		if (data != null) Utils.LogIt(TAG, "update(...) data = " + data.toString());
		// Here we update the user info in the fragment
		if (data != null && data instanceof User){
			User lUserInfo = (User)data;
			if (lUserInfo != null){
				if (lUserInfo.profileImage != null && lUserInfo.profileImage.length() > 0){
					Picasso.with(getActivity())
						.load(lUserInfo.profileImage)
						.resize(96, 96)
						.centerInside()
						.into(mIVUserGravatar);
				}
				mTVUserName.setText(lUserInfo.displayName);
				String strRep = String.format(getString(R.string.SEUserRep_fmt), NumberFormat.getNumberInstance(Locale.US).format(lUserInfo.reputation));
				mTVUserRepCount.setText(strRep);
				mTVUserBadgeGold.setText(String.valueOf(lUserInfo.badges.gold));
				mTVUserBadgeSilver.setText(String.valueOf(lUserInfo.badges.silver));
				mTVUserBadgeBronze.setText(String.valueOf(lUserInfo.badges.bronze));
			}
		}
		if (data != null && data instanceof NetworkUser){
			NetworkUser nwUserInfo = (NetworkUser)data;
			if (nwUserInfo != null){
				String strRep = String.format(getString(R.string.SEUserRep_fmt), NumberFormat.getNumberInstance(Locale.US).format(nwUserInfo.reputation));
				mTVUserRepCount.setText(strRep);
				mTVUserBadgeGold.setText(String.valueOf(nwUserInfo.badges.gold));
				mTVUserBadgeSilver.setText(String.valueOf(nwUserInfo.badges.silver));
				mTVUserBadgeBronze.setText(String.valueOf(nwUserInfo.badges.bronze));
			}
		}
	}
}
