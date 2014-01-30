package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type represents a mapping from one tag to another, as part of a Stack Exchange sites tag synonym list.
 * Note that even if a tag has been designated a synonym of another tag, that tag may still appear on some older questions in the system.
 * Applications should be able to gracefully handle both the introduction of synonyms and their removal.
 * 
 * @author t0mm13b
 * 
 * @see http://api.stackexchange.com/docs/types/tag-synonym
 */
public class TagSynonym {
	@SerializedName("applied_count") public int appliedCount = -1;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("from_tag") public String fromTag = "";
	@SerializedName("last_applied_date") public long lastAppliedDate = -1;
	@SerializedName("to_tag") public String toTag = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + appliedCount;
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result
				+ ((fromTag == null) ? 0 : fromTag.hashCode());
		result = prime * result
				+ (int) (lastAppliedDate ^ (lastAppliedDate >>> 32));
		result = prime * result + ((toTag == null) ? 0 : toTag.hashCode());
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
		TagSynonym other = (TagSynonym) obj;
		if (appliedCount != other.appliedCount)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (fromTag == null) {
			if (other.fromTag != null)
				return false;
		} else if (!fromTag.equals(other.fromTag))
			return false;
		if (lastAppliedDate != other.lastAppliedDate)
			return false;
		if (toTag == null) {
			if (other.toTag != null)
				return false;
		} else if (!toTag.equals(other.toTag))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TagSynonym [appliedCount=");
		builder.append(appliedCount);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", fromTag=");
		builder.append(fromTag);
		builder.append(", lastAppliedDate=");
		builder.append(lastAppliedDate);
		builder.append(", toTag=");
		builder.append(toTag);
		builder.append("]");
		return builder.toString();
	}
	
}
