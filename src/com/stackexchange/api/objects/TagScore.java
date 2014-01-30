package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type represents a user's statistics within a tag.
 * Note that this data is often heavily cached or normalized, and thus methods return it may lag significantly 
 * behind other methods returning similar data.
 * 
 * @author t0mm13b
 * @see http://api.stackexchange.com/docs/types/tag-score 
 */
public class TagScore {
	@SerializedName("post_count") public int postCount = -1;
	@SerializedName("score") public int score = -1;
	@SerializedName("user") public ShallowUser user;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postCount;
		result = prime * result + score;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		TagScore other = (TagScore) obj;
		if (postCount != other.postCount)
			return false;
		if (score != other.score)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TagScore [postCount=");
		builder.append(postCount);
		builder.append(", score=");
		builder.append(score);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
}
