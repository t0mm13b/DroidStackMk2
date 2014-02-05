package ie.t0mm13b.droidstackmk2.drawer;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.helpers.Utils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

/***
 * The fragment embedded in the Navigation Drawer. 
 * <br/>
 * 
 * @author t0mm13b
 *
 */
public class DrawerFragment extends Fragment{
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
		mDrawerList.setEmptyView(mDrawerListEmpty);
		//
		mDrawerList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> argAdapter, View argView,	int argPosition, long argId) {
				Utils.LogIt(TAG, String.format("mDrawerList::onItemLongClick(...) - position = %d", argPosition));
				return true;
			}
			
		});
		mDrawerList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> argViewAdapter, View argView, int argPosition, long argId) {
				if (mDrawerListItemListener != null){
					mDrawerListItemListener.cbDrawerListItemClick(argPosition, mDrawerAdapter.getItem(argPosition));
				}
			}
			
		});
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
}
