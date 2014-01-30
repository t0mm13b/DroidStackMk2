package ie.t0mm13b.droidstackmk2.drawer;

import ie.t0mm13b.droidstackmk2.DroidStackMk2App;
import ie.t0mm13b.droidstackmk2.Droidstackmk2Main;
import ie.t0mm13b.droidstackmk2.ui.DrawerFragment;

public class DrawerRowEntry {
	/**
	 * 
	 */
	private String mDrawerText;
	private String mDrawerNameOfIcon;
	private int mDrawerIconId;

	public DrawerRowEntry(String drawerText, String drawerNameOfIcon){
		mDrawerText = drawerText;
		mDrawerNameOfIcon = drawerNameOfIcon;
		mDrawerIconId = DroidStackMk2App.getAppContext().getResources().getIdentifier(
				drawerNameOfIcon, "drawable",
				DroidStackMk2App.getAppContext().getPackageName());
	}

	public String getDrawerText() {
		return mDrawerText;
	}

	public int getDrawerId() {
		return mDrawerIconId;
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
}