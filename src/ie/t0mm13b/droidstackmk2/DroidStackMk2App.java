package ie.t0mm13b.droidstackmk2;

import android.app.Application;
import android.content.Context;

public class DroidStackMk2App extends Application{
	private static Context mContext;
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
	}
	public static Context getAppContext(){
		return mContext;
	}
}
