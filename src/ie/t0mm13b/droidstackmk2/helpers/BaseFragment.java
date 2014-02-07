/**
 * 
 */
package ie.t0mm13b.droidstackmk2.helpers;

import ie.t0mm13b.droidstackmk2.interfaces.IFragmentNotify;
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
	private IFragmentNotify mFragmentNotify;
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
			/***
			 * Call the {@link IFragmentNotify#cbFragmentFinished} which gets caught by the class implementer.
			 */
			if (mFragmentNotify != null){
				mFragmentNotify.cbFragmentFinished();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/***
	 * Register the class implementer of the interface {@link IFragmentNotify}
	 * 
	 * @param fragNotify
	 */
	public void registerFragmentNotify(IFragmentNotify fragNotify){
		mFragmentNotify = fragNotify;
	}
	public void unregisterFragmentNotify(){
		mFragmentNotify = null;
	}
}
