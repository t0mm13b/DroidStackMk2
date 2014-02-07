package ie.t0mm13b.droidstackmk2.ui;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;
import ie.t0mm13b.droidstackmk2.helpers.BaseFragment;
import ie.t0mm13b.droidstackmk2.helpers.Utils;
import ie.t0mm13b.droidstackmk2.helpers.Utils.AnimateFirstDisplayListener;

/***
 * The Generic fragment that shall incorporate the visuals of teh StackExchange site network depending on what was selected via 
 * Navigation Drawer item that was tapped.
 * 
 * @author t0mm13b
 *
 */
public class SEFragmentGeneric extends BaseFragment{
	private static final String TAG = "SEFragmentGeneric";
	private ImageView ivLogo;
//	protected ImageLoader mImageLoader;
//	private AnimateFirstDisplayListener mAFDListener = new AnimateFirstDisplayListener();
	private DrawerRowEntry mDrawerEntry = null;
	private int mDrawerPosition;
	
	public static SEFragmentGeneric newInstance(int position, DrawerRowEntry dre) {
		SEFragmentGeneric frag = new SEFragmentGeneric();
		Bundle args = new Bundle();
		args.putParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY, dre);
		args.putInt(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY, position);
		frag.setArguments(args);
		return frag;
	}
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
        Bundle args = getArguments();
        if (args != null){
	        if (args.containsKey(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY)){
	        	mDrawerEntry = args.getParcelable(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY);
	        }
	        if (args.containsKey(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY)){
	        	mDrawerPosition = args.getInt(Utils.SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY);
	        }
			Utils.LogIt(TAG, String.format("onCreate(...) - mDrawerPosition = %d; mDrawerEntry = %s", mDrawerPosition, mDrawerEntry.toString()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generic, container, false);
        ivLogo = (ImageView)rootView.findViewById(R.id.ivSELogo);
        //
        if (mDrawerEntry != null){
        	Picasso.with(getActivity()).load(mDrawerEntry.getDrawerIcon()).into(ivLogo);
//			if (MemoryCacheUtils.findCachedBitmapsForImageUri(mDrawerEntry.getDrawerIcon(), ImageLoader.getInstance().getMemoryCache()).size() > 0){
//				ivLogo.setImageBitmap(MemoryCacheUtils.findCachedBitmapsForImageUri(mDrawerEntry.getDrawerIcon(), ImageLoader.getInstance().getMemoryCache()).get(0));
//			}else{
//				mImageLoader.displayImage(mDrawerEntry.getDrawerIcon(), ivLogo, mAFDListener);
//			}
        }
        //
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater mInflater = getActivity().getMenuInflater();
        super.onCreateOptionsMenu(menu, mInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // switch (item.getItemId()) {
        // // Respond to the action bar's Up/Home button
        // default:
        // return true;
        // }
        return super.onOptionsItemSelected(item);
    }
}
