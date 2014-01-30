package ie.t0mm13b.droidstackmk2.ui;

import java.util.ArrayList;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.drawer.DrawerArrayAdapter;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.interfaces.IDrawerListItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class DrawerFragment extends Fragment{
	private static final String TAG = "DrawerFragment";
	private ListView mDrawerList;
	private ArrayList<DrawerRowEntry> mListDrawerEntries;
	private DrawerArrayAdapter mDrawerAdapter;
	private IDrawerListItem mDrawerListItemListener;
	private boolean isRegistered = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drawer_fragment, container, false);
        mDrawerList = (ListView) rootView.findViewById(R.id.lvDrawerItems);
        mListDrawerEntries = new ArrayList<DrawerRowEntry>();
        String[] mDrawerTitles = getResources().getStringArray(
				R.array.droidstack_drawer_titles_array);
		String[] drawerIcons = getResources().getStringArray(
				R.array.droidstack_drawer_icons_array);
		if (mDrawerTitles.length == drawerIcons.length) {
			int countItemsMax = drawerIcons.length;
			for (int looper = 0; looper < countItemsMax; looper++) {
				mListDrawerEntries.add(new DrawerRowEntry(mDrawerTitles[looper], drawerIcons[looper]));
			}
		} else {
			throw new IllegalStateException(
					"Drawer Titles and icons do not match up!");
		}
		mDrawerAdapter = new DrawerArrayAdapter(this.getActivity().getApplicationContext(), mListDrawerEntries);
		// Set the adapter for the list view
		mDrawerList.setAdapter(this.mDrawerAdapter);
		//
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        return rootView;
	}
	
	public void registerListener(IDrawerListItem drawerListItem){
		if (!isRegistered){
			this.mDrawerListItemListener = drawerListItem;
			isRegistered = true;
		}
	}
	
	public boolean isListenerRegistered(){
		return this.isRegistered;
	}
	/**
	 * A simple list view item listener for taps, go forth and insert a fragment
	 * into our view.
	 * 
	 * @author tombrennan
	 * 
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@SuppressWarnings("rawtypes")
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
//			selectItem(position);
			if (mDrawerListItemListener != null){
				mDrawerListItemListener.cbDrawerListItemClick(position, id, mListDrawerEntries.get(position).getDrawerText());
				mDrawerList.setItemChecked(position, true);
			}
		}
	}
}
