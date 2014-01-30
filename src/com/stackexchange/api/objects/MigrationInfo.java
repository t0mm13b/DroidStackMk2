package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * This type represents a question's migration to or from a different site in the Stack Exchange network.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/migration-info
 */
public class MigrationInfo {
	@SerializedName("on_date") public long onDate = -1;
	@SerializedName("other_site") public Site otherSite;
	@SerializedName("question_id") public int questionId = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (onDate ^ (onDate >>> 32));
		result = prime * result
				+ ((otherSite == null) ? 0 : otherSite.hashCode());
		result = prime * result + questionId;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MigrationInfo other = (MigrationInfo) obj;
		if (onDate != other.onDate)
			return false;
		if (otherSite == null) {
			if (other.otherSite != null)
				return false;
		} else if (!otherSite.equals(other.otherSite))
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MigrationInfo [onDate=");
		builder.append(onDate);
		builder.append(", otherSite=");
		builder.append(otherSite);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append("]");
		return builder.toString();
	}
	
}
