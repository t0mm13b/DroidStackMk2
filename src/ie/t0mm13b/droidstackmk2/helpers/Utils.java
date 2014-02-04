package ie.t0mm13b.droidstackmk2.helpers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Utils {
	public static final String SE_GENERIC_FRAG_ARGS_DRAWER_ROW_ITEM_KEY = "DRE_KEY";
	public static final String SE_GENERIC_FRAG_ARGS_DRAWER_ROW_POSN_KEY = "DRE_POSN";
	//
	public static final boolean DEBUG = true;
	
	public static final void LogIt(final String argTag, final String argMessage){
		if (DEBUG){
			Log.d(argTag, argMessage);
		}
	}
	
	public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 150);
                } else {
                    imageView.setImageBitmap(loadedImage);
                }
                displayedImages.add(imageUri);
            }
        }
    }
}
