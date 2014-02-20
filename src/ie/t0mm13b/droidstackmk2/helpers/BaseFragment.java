/**
 * 
 */
package ie.t0mm13b.droidstackmk2.helpers;

import ie.t0mm13b.droidstackmk2.events.FragmentFinishedEvent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author tombrennan
 * 
 */
public abstract class BaseFragment extends Fragment {
	private static final String TAG = "BaseFragment";
	private ActionBarActivity mActionBarActivity;
	private ActionBar mActionBar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBarActivity = (ActionBarActivity) BaseFragment.this.getActivity();
		if (mActionBarActivity != null){
			mActionBar = mActionBarActivity.getSupportActionBar();
			if (mActionBar != null){
				mActionBar.setDisplayHomeAsUpEnabled(true);
				mActionBar.setHomeButtonEnabled(true);
			}else{
				Utils.LogIt(TAG,  "onCreate(...) - mActionBar is null");
			}
		}else{
			Utils.LogIt(TAG,  "onCreate(...) - mActionBarActivity is null");
		}
	}

	protected ActionBar getActionBar(){
		return mActionBar;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuInflater mInflater = getActivity().getMenuInflater();
		super.onCreateOptionsMenu(menu, mInflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int nStackCount = getFragmentManager().getBackStackEntryCount();
//		Utils.LogIt(TAG, "getBackStackEntryCount = " + nStackCount);
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			if (nStackCount > 0){
				getFragmentManager().popBackStack();
				// make sure transactions are finished before reading backstack count
				boolean ptExecd = getFragmentManager().executePendingTransactions();
//				Utils.LogIt(TAG, "ptExecd = " + String.valueOf(ptExecd));
			}
			EventBusProvider.getInstance().post(new FragmentFinishedEvent());

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Utils.LogIt(TAG, "onActivityCreated(...)");
		activityCreated(savedInstanceState);
	}
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Utils.LogIt(TAG, "onSaveInstanceState(...)");
		saveInstanceState(outState);		
	}
	
	public abstract void activityCreated(Bundle savedInstanceState);
	public abstract void saveInstanceState(Bundle outState);
}
