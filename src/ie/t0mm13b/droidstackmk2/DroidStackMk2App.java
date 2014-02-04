package ie.t0mm13b.droidstackmk2;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import android.app.Application;
import android.content.Context;

public class DroidStackMk2App extends Application{
	private static Context mContext;
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		//
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_stub)
			.showImageForEmptyUri(R.drawable.ic_empty)
			.showImageOnFail(R.drawable.ic_error)
			.displayer(new RoundedBitmapDisplayer(20))
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
			.defaultDisplayImageOptions(defaultOptions)
			.build();
		ImageLoader.getInstance().init(config);
	}
	public static Context getAppContext(){
		return mContext;
	}
}
