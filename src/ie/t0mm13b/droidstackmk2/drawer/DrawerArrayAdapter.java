package ie.t0mm13b.droidstackmk2.drawer;

import ie.t0mm13b.droidstackmk2.Droidstackmk2Main;
import ie.t0mm13b.droidstackmk2.R;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DrawerArrayAdapter extends BaseAdapter {
	private static String TAG = "DrawerArrayAdapter";
	private ArrayList<DrawerRowEntry> mDrawerArrayList;
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public DrawerArrayAdapter(Context context,
			ArrayList<DrawerRowEntry> listRowDrawerEntries) {
		super();
		this.mContext = context;
		this.mDrawerArrayList = listRowDrawerEntries;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Droidstackmk2Main.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mDrawerArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.mDrawerArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		DrawerViewHolder dvh = null;
		if (convertView == null) {
			dvh = new DrawerViewHolder();
			convertView = this.mLayoutInflater.inflate(
					R.layout.drawer_list_item, null);
			dvh.drawerTextEntry = (TextView) convertView
					.findViewById(R.id.tvDrawer);
			convertView.setTag(dvh);
		} else {
			dvh = (DrawerViewHolder) convertView.getTag();
		}
		DrawerRowEntry dre = this.mDrawerArrayList.get(position);
		try {
			dvh.drawerTextEntry.setCompoundDrawablesWithIntrinsicBounds(
					dre.getDrawerId(), 0, 0, 0);
		} catch (NullPointerException npe) {
			Log.d(TAG, "getView b0rk3d :( " + npe);
		}
		dvh.drawerTextEntry.setText(dre.getDrawerText());
		return convertView;
	}

	public static class DrawerViewHolder {
		TextView drawerTextEntry;
	}
}