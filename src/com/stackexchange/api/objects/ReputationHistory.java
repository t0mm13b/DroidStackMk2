package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.ReputationHistoryType;


/***
 * Excerpt: This type describes the events that make up a user's reputation history. 
 * Certain reputation events are private (and types may be reclassified as policy changes), so totaling a user's 
 * reputation history may not match their reported reputation unless you have access to their entire reputation history.
 * Note that new event types may be added at any time, and existing ones may become defunct.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/reputation-history
 */
public class ReputationHistory {
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("reputation_change") public int reputationChange = -1;
	@SerializedName("reputation_history_type") public ReputationHistoryType reputationHistoryType = ReputationHistoryType.Unknown;
	@SerializedName("user_id") public int userId = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + postId;
		result = prime * result + reputationChange;
		result = prime
				* result
				+ ((reputationHistoryType == null) ? 0
						: reputationHistoryType.hashCode());
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
		ReputationHistory other = (ReputationHistory) obj;
		if (creationDate != other.creationDate)
			return false;
		if (postId != other.postId)
			return false;
		if (reputationChange != other.reputationChange)
			return false;
		if (reputationHistoryType != other.reputationHistoryType)
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
		builder.append("ReputationHistory [creationDate=");
		builder.append(creationDate);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", reputationChange=");
		builder.append(reputationChange);
		builder.append(", reputationHistoryType=");
		builder.append(reputationHistoryType);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
}
