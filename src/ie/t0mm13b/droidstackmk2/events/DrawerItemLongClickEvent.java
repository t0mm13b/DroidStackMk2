package ie.t0mm13b.droidstackmk2.events;

import ie.t0mm13b.droidstackmk2.drawer.DrawerRowEntry;

public class DrawerItemLongClickEvent {
	private static final String TAG = "DrawerItemClickEvent";
	private DrawerRowEntry mDREntry;
	private int mDREntryPosition;
	public DrawerItemLongClickEvent(int position, DrawerRowEntry dre){
		mDREntryPosition = position;
		mDREntry = dre;
	}
	/**
	 * @return the mDREntry
	 */
	public DrawerRowEntry getDREntry() {
		return mDREntry;
	}
	/**
	 * @return the mDREntryPosition
	 */
	public int getDREntryPosition() {
		return mDREntryPosition;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DrawerItemLongClickEvent [mDREntry=");
		builder.append(mDREntry);
		builder.append(", mDREntryPosition=");
		builder.append(mDREntryPosition);
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
				+ ((mDREntry == null) ? 0 : mDREntry.hashCode());
		result = prime * result + mDREntryPosition;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof DrawerItemLongClickEvent){
			DrawerItemClickEvent other = (DrawerItemClickEvent) obj;
			if (mDREntryPosition == other.getDREntryPosition()){
				if (mDREntry.equals(other.getDREntry())) return true;
			}
		}
		return false;
	}
	
}
