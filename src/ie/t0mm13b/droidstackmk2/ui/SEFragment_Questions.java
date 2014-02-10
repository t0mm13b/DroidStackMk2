package ie.t0mm13b.droidstackmk2.ui;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.interfaces.IFragmentLifecycle;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/***
 * This deals with and governs questions...
 * 
 * @author t0mm13b
 *
 */
public class SEFragment_Questions extends Fragment implements IFragmentLifecycle{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_generic_questions, container, false);
		return rootView;
	}
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater mInflater = getActivity().getMenuInflater();
        super.onCreateOptionsMenu(menu, mInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onPauseFragment() {
		// TODO Auto-generated method stub
		// Save state here...
	}

	@Override
	public void onResumeFragment() {
		// TODO Auto-generated method stub
		// Restore state here...
		
	}
}
