package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type represents a tag on a Stack Exchange site.
 * Applications should be prepared for the destruction of tags, though this tends to be a rare event.
 * 
 * @author t0mm13b
 * @see http://api.stackexchange.com/docs/types/tag
 */
public class Tag {
	@SerializedName("count") public int count = -1;
	@SerializedName("has_synonyms") public boolean hasSynonyms = false;
	@SerializedName("is_moderator_only") public boolean isModeratorOnly = false;
	@SerializedName("is_required") public boolean isRequired = false;
	@SerializedName("last_activity_date") public long lastActivityDate = -1;
	@SerializedName("name") public String name = "";
	@SerializedName("user_id") public int userId = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasSynonyms ? 1231 : 1237);
		result = prime * result + (isModeratorOnly ? 1231 : 1237);
		result = prime * result + (isRequired ? 1231 : 1237);
		result = prime * result + count;
		result = prime * result
				+ (int) (lastActivityDate ^ (lastActivityDate >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + userId;
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
		Tag other = (Tag) obj;
		if (hasSynonyms != other.hasSynonyms)
			return false;
		if (isModeratorOnly != other.isModeratorOnly)
			return false;
		if (isRequired != other.isRequired)
			return false;
		if (count != other.count)
			return false;
		if (lastActivityDate != other.lastActivityDate)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tag [count=");
		builder.append(count);
		builder.append(", hasSynonyms=");
		builder.append(hasSynonyms);
		builder.append(", isModeratorOnly=");
		builder.append(isModeratorOnly);
		builder.append(", isRequired=");
		builder.append(isRequired);
		builder.append(", lastActivityDate=");
		builder.append(lastActivityDate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
}
