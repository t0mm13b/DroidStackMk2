package ie.t0mm13b.droidstackmk2.events;

import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;

public class StackExchangeUserDialogEvent {
	private static final String TAG = "StackExchangeUserDialogEvent";
	private String mSEUserId;
	private DrawerRowEntry mDrawerRowEntry;
	private int mPosition;
	private boolean mIsCancelled;
	
	public StackExchangeUserDialogEvent(String seUserId, DrawerRowEntry dre, int position, boolean isCancelled){
		mSEUserId = seUserId;
		mDrawerRowEntry = dre;
		mPosition = position;
		mIsCancelled = isCancelled;
	}
	
	public String getStackExchangeUserId(){
		return mSEUserId;
	}
	
	public DrawerRowEntry getDrawerItem(){
		return mDrawerRowEntry;
	}

	public int getDrawerPosition(){
		return mPosition;
	}
	
	public boolean getIsCancelled(){
		return mIsCancelled;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StackExchangeUserDialogEvent [mSEUserId=");
		builder.append(mSEUserId);
		builder.append(", mDrawerRowEntry=");
		builder.append(mDrawerRowEntry);
		builder.append(", mPosition=");
		builder.append(mPosition);
		builder.append(", mIsCancelled=");
		builder.append(mIsCancelled);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mDrawerRowEntry == null) ? 0 : mDrawerRowEntry.hashCode());
		result = prime * result + (mIsCancelled ? 1231 : 1237);
		result = prime * result + mPosition;
		result = prime * result
				+ ((mSEUserId == null) ? 0 : mSEUserId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof StackExchangeUserDialogEvent){
			StackExchangeUserDialogEvent other = (StackExchangeUserDialogEvent) obj;
			if (mSEUserId.equalsIgnoreCase(other.mSEUserId) &&
					mPosition == other.getDrawerPosition() && 
					mDrawerRowEntry.equals(other.getDrawerItem()) &&
					mIsCancelled == other.getIsCancelled()) 
				return true;
		}
		return false;
	}
	
}
