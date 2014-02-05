package ie.t0mm13b.droidstackmk2.drawer;

import com.stackexchange.api.objects.Site;

import android.os.Parcel;
import android.os.Parcelable;
import ie.t0mm13b.droidstackmk2.DroidStackMk2App;
import ie.t0mm13b.droidstackmk2.Droidstackmk2Main;

/***
 * The entry of the item in the navigation drawer.
 * <br/>
 * Implements the {@link Parcelable} interface for ease of passing this around in bundles.
 * 
 * @author t0mm13b
 *
 */
public class DrawerRowEntry implements Parcelable{
	/**
	 * 
	 */
	private static final String TAG = "DrawerRowEntry";
	private String mDrawerText;
	private String mDrawerNameOfIcon;
	private int mDrawerIconId;
	private String mKey;
	private Site mSiteInfo;

	public DrawerRowEntry(Parcel in){
		readFromParcel(in);
	}
	public DrawerRowEntry(String drawerText, String drawerNameOfIcon, String keyExtra){
		mDrawerText = drawerText;
		mDrawerNameOfIcon = drawerNameOfIcon;
		mKey = keyExtra;
		if (!mDrawerNameOfIcon.startsWith("http://")){
			mDrawerIconId = DroidStackMk2App.getAppContext().getResources().getIdentifier(
					drawerNameOfIcon, "drawable",
					DroidStackMk2App.getAppContext().getPackageName());
		}else{
			mDrawerIconId = -1;
		}
	}

	public String getDrawerText() {
		return mDrawerText;
	}

	public int getDrawerId() {
		return mDrawerIconId;
	}

	public String getDrawerIcon(){
		return mDrawerNameOfIcon;
	}
	
	public String getExtraKey(){
		return mKey;
	}
	public void setSiteInfo(Site siteInfo){
		mSiteInfo = siteInfo;
	}
	public Site getSiteInfo(){
		return mSiteInfo;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		result = prime * result + mDrawerIconId;
		result = prime
				* result
				+ ((mDrawerNameOfIcon == null) ? 0 : mDrawerNameOfIcon
						.hashCode());
		result = prime * result
				+ ((mDrawerText == null) ? 0 : mDrawerText.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DrawerRowEntry)) {
			return false;
		}
		DrawerRowEntry other = (DrawerRowEntry) obj;
		if (mDrawerIconId != other.mDrawerIconId) {
			return false;
		}
		if (mDrawerNameOfIcon == null) {
			if (other.mDrawerNameOfIcon != null) {
				return false;
			}
		} else if (!mDrawerNameOfIcon.equals(other.mDrawerNameOfIcon)) {
			return false;
		}
		if (mDrawerText == null) {
			if (other.mDrawerText != null) {
				return false;
			}
		} else if (!mDrawerText.equals(other.mDrawerText)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DrawerRowEntry [mDrawerText=" + mDrawerText
				+ ", mDrawerNameOfIcon=" + mDrawerNameOfIcon
				+ ", mDrawerIconId=" + mDrawerIconId + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel argDestParcel, int argFlags) {
		// TODO Auto-generated method stub
		argDestParcel.writeString(mDrawerText);
		argDestParcel.writeString(mDrawerNameOfIcon);
		argDestParcel.writeString(mKey);
		argDestParcel.writeInt(mDrawerIconId);
		argDestParcel.writeParcelable(mSiteInfo, argFlags);
	}
	private void readFromParcel(Parcel in){
		mDrawerText = in.readString();
		mDrawerNameOfIcon = in.readString();
		mKey = in.readString();
		mDrawerIconId = in.readInt();
		mSiteInfo = in.readParcelable(Site.class.getClassLoader());
	}
	
	public static final Parcelable.Creator<DrawerRowEntry> CREATOR = new Parcelable.Creator<DrawerRowEntry>(){
		@Override
		public DrawerRowEntry createFromParcel(Parcel source) {
			return new DrawerRowEntry(source);
		}
		@Override
		public DrawerRowEntry[] newArray(int size){
			return new DrawerRowEntry[size];
		}
	};
}