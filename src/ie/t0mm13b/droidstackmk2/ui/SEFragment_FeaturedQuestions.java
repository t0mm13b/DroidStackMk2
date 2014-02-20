package ie.t0mm13b.droidstackmk2.ui;

import ie.t0mm13b.droidstackmk2.R;
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
public class SEFragment_FeaturedQuestions extends Fragment{
	private static final String TAG = "SEFragment_FeaturedQuestions";
	public static SEFragment_FeaturedQuestions newInstance(Bundle args){
		SEFragment_FeaturedQuestions sefFQ = new SEFragment_FeaturedQuestions();
		if (args != null) sefFQ.setArguments(args);
		return sefFQ;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_generic_fquestions, container, false);

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

}