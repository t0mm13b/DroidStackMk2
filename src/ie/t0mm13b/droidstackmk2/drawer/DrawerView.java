package ie.t0mm13b.droidstackmk2.drawer;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import ie.t0mm13b.droidstackmk2.R;
import ie.t0mm13b.droidstackmk2.helpers.Utils.AnimateFirstDisplayListener;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/***
 * A much neater way of getting around the ugly ViewHolder pattern.
 * 
 * @author t0mm13b
 * @see http://architects.dzone.com/articles/viewholder-considered-harmful<br/>
 * @see {@link DrawerArrayAdapter} for a clean implementation of {@link DrawerArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)}
 * and notice how simple it is, combined with performance gain from the obsoleted ugly ViewHolder pattern!
 */
public class DrawerView extends RelativeLayout{

	private ImageView mDrawerImage;
	private TextView mDrawerTextEntry;
	protected ImageLoader mImageLoader;
	
	private AnimateFirstDisplayListener mAFDListener;
	
	public DrawerView(Context context) {
		super(context);
		internalInit();
	}
	/** Inherited constructor. */
	public DrawerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		internalInit();	
	}
	/** Inherited constructor. */
	public DrawerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		internalInit();
	}
	
	/***
	 * This stops the Design UI tool from blowing up!
	 */
	private void internalInit(){
		if (!isInEditMode()){
			mImageLoader = ImageLoader.getInstance();
			mAFDListener = new AnimateFirstDisplayListener();
		}
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mDrawerImage = (ImageView)findViewById(R.id.ivDrawerEntryIcon);
		mDrawerTextEntry = (TextView)findViewById(R.id.tvDrawerEntryText);
	}
	
	/***
	 * Set up this view's layout in a cleaner manner.
	 * 
	 * @param dre - DrawerRowEntry item
	 * @see {@link DrawerRowEntry}
	 */
	public void showDrawerEntry(DrawerRowEntry dre){
		mDrawerTextEntry.setText(dre.getDrawerText());
		if (!isInEditMode()){
			if (dre.getDrawerId() > -1){
				mDrawerImage.setImageResource(dre.getDrawerId());
			}else{
				if (MemoryCacheUtils.findCachedBitmapsForImageUri(dre.getDrawerIcon(), ImageLoader.getInstance().getMemoryCache()).size() > 0){
					mDrawerImage.setImageBitmap(MemoryCacheUtils.findCachedBitmapsForImageUri(dre.getDrawerIcon(), ImageLoader.getInstance().getMemoryCache()).get(0));
				}else{
					mImageLoader.displayImage(dre.getDrawerIcon(), mDrawerImage, mAFDListener);
				}
			}
		}
	}
}