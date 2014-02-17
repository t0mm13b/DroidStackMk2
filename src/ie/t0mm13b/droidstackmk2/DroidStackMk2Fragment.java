package ie.t0mm13b.droidstackmk2;

import ie.t0mm13b.droidstackmk2.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DroidStackMk2Fragment extends Fragment{
	
	public static DroidStackMk2Fragment newInstance(Bundle argsBundle){
		DroidStackMk2Fragment frag = new DroidStackMk2Fragment();
		if (argsBundle != null){
			frag.setArguments(argsBundle);
		}
		return frag;
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_droidstackmk2, container, false);
        //
        if (getArguments() != null){
        	
        }
        return rootView;
    }

}
