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
public class BaseFragment extends Fragment {

	private ActionBarActivity mActionBarActivity;
	private ActionBar mActionBar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActionBarActivity = (ActionBarActivity) this.getActivity();
		mActionBar = mActionBarActivity.getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
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
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			getFragmentManager().popBackStack();
			// make sure transactions are finished before reading backstack count
			getFragmentManager().executePendingTransactions();
			EventBusProvider.getInstance().post(new FragmentFinishedEvent());

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
