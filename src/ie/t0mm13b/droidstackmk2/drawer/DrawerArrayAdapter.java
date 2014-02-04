package ie.t0mm13b.droidstackmk2.drawer;

import ie.t0mm13b.droidstackmk2.Droidstackmk2Main;
import ie.t0mm13b.droidstackmk2.R;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DrawerArrayAdapter extends BaseAdapter {
	private static String TAG = "DrawerArrayAdapter";
	private ArrayList<DrawerRowEntry> mDrawerArrayList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private IDrawerEntry mDrawerEntryCallback;
	
	public DrawerArrayAdapter(Context context){
		super();
		mContext = context;
		mDrawerArrayList = new ArrayList<DrawerRowEntry>();
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Droidstackmk2Main.LAYOUT_INFLATER_SERVICE);
	}
	public DrawerArrayAdapter(Context context, ArrayList<DrawerRowEntry> listRowDrawerEntries) {
		this(context);
		mDrawerArrayList = listRowDrawerEntries;
	}

	public void setDrawerEntryCallback(IDrawerEntry iDrawerEntryCallback){
		mDrawerEntryCallback = iDrawerEntryCallback;
	}
	@Override
	public int getCount() {
		if (mDrawerArrayList != null && mDrawerArrayList.size() > 0) return mDrawerArrayList.size();
		return 0;
	}

	@Override
	public DrawerRowEntry getItem(int position) {
		if (mDrawerArrayList != null && mDrawerArrayList.size() > 0) return mDrawerArrayList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void clearAll(){
		mDrawerArrayList.clear();
	}

	@Override
	public boolean hasStableIds(){
		return true;
	}
	
	public ArrayList<DrawerRowEntry> getList(){
		return mDrawerArrayList;
	}
	
	public void add(DrawerRowEntry dre){
		if (mDrawerArrayList != null){
			if (!mDrawerArrayList.contains(dre)){
				mDrawerArrayList.add(dre);
			}
		}
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		DrawerView dv;
		if (convertView == null){
			dv = (DrawerView)mLayoutInflater.inflate(R.layout.drawer_list_item, null);
		}else{
			dv = (DrawerView)convertView;
		}
		DrawerRowEntry dre = mDrawerArrayList.get(position);
		dv.showDrawerEntry(dre);
		dv.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (mDrawerEntryCallback != null){
					mDrawerEntryCallback.cbDrawerEntryClicked(position);
				}
			}
			
		});
		return dv;
	}
}