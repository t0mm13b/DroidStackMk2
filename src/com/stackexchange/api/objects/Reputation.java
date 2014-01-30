package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.PostType;
import com.stackexchange.api.objects.Enums.VoteType;


/***
 * Excerpt: This type represents a change in reputation for a User.
 * All methods that return this data will scrub it to a degree, to increase the difficulty of correlating reputation changes 
 * with down voting.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/reputation
 * @see User
 */
public class Reputation {
	@SerializedName("link") public String link = "";
	@SerializedName("on_date") public long onDate = -1;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("post_type") public PostType postType = PostType.Unknown;
	@SerializedName("reputation_change") public int reputationChange = -1;
	@SerializedName("title") public String title = "";
	@SerializedName("user_id") public int userId = -1;
	@SerializedName("vote_type") public VoteType voteType = VoteType.Unknown;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + (int) (onDate ^ (onDate >>> 32));
		result = prime * result + postId;
		result = prime * result
				+ ((postType == null) ? 0 : postType.hashCode());
		result = prime * result + reputationChange;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userId;
		result = prime * result
				+ ((voteType == null) ? 0 : voteType.hashCode());
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
		Reputation other = (Reputation) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (onDate != other.onDate)
			return false;
		if (postId != other.postId)
			return false;
		if (postType != other.postType)
			return false;
		if (reputationChange != other.reputationChange)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId != other.userId)
			return false;
		if (voteType != other.voteType)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reputation [link=");
		builder.append(link);
		builder.append(", onDate=");
		builder.append(onDate);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", postType=");
		builder.append(postType);
		builder.append(", reputationChange=");
		builder.append(reputationChange);
		builder.append(", title=");
		builder.append(title);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", voteType=");
		builder.append(voteType);
		builder.append("]");
		return builder.toString();
	}
	
}
