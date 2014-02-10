package ie.t0mm13b.droidstackmk2.events;

public class FragmentFinishedEvent{
	private static final String TAG = "FragmentFinishedEvent";
	private final long mCurrTime = System.currentTimeMillis();
	
	
	/**
	 * @return the mCurrTime
	 */
	public long getFinishedEventCurrTime() {
		return mCurrTime;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (mCurrTime ^ (mCurrTime >>> 32));
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof FragmentFinishedEvent){
			FragmentFinishedEvent ffeOther = (FragmentFinishedEvent)obj;
			if (mCurrTime == ffeOther.getFinishedEventCurrTime()) return true;
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FragmentFinishedEvent [");
		builder.append(mCurrTime);
		builder.append("]");
		return builder.toString();
	}
}
