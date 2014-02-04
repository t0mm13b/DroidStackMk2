package ie.t0mm13b.droidstackmk2.drawer;

import ie.t0mm13b.droidstackmk2.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/***
 * The fragment embedded in the Navigation Drawer. 
 * <br/>
 * Implements the {@link IDrawerEntry} callback.
 * 
 * @author t0mm13b
 *
 */
public class DrawerFragment extends Fragment implements IDrawerEntry{
	private static final String TAG = "DrawerFragment";
	private ListView mDrawerList;
	private TextView mDrawerListEmpty;
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
        //
        mDrawerList = (ListView) rootView.findViewById(R.id.lvDrawerItems);
        mDrawerListEmpty = (TextView) rootView.findViewById(R.id.emptyDrawerList);
		mDrawerAdapter = new DrawerArrayAdapter(this.getActivity().getApplicationContext());
		mDrawerAdapter.setDrawerEntryCallback(this);
		mDrawerList.setEmptyView(mDrawerListEmpty);
		// Set the adapter for the list view
		mDrawerList.setAdapter(this.mDrawerAdapter);
        return rootView;
	}
	
	public void registerListener(IDrawerListItem drawerListItem){
		if (!isRegistered){
			mDrawerListItemListener = drawerListItem;
			isRegistered = true;
		}
	}
	
	/***
	 * Get the Drawer's Adapter
	 * 
	 * @return {@link DrawerArrayAdapter}
	 */
	public synchronized DrawerArrayAdapter getDrawerAdapter(){
		return mDrawerAdapter;
	}
	public boolean isListenerRegistered(){
		return isRegistered;
	}
//	
//	public void clearDrawerList(){
//		mDrawerAdapter.clearAll();
//		mDrawerAdapter.notifyDataSetChanged();
//	}
//	
//	public void addDrawerEntry(DrawerRowEntry dre){
//		mDrawerAdapter.add(dre);
//	}
//	public void notifyDrawer(){
//		mDrawerAdapter.notifyDataSetChanged();
//	}

	/***
	 * The callback from the {@link DrawerArrayAdapter} where the tap on the row was made.
	 * We fire the external interface's callback and pass it back out to whoever implements {@link IDrawerListItem}
	 */
	@Override
	public void cbDrawerEntryClicked(int position) {
		if (mDrawerListItemListener != null){
			mDrawerListItemListener.cbDrawerListItemClick(position, mDrawerAdapter.getItem(position));
		}
	}

}
