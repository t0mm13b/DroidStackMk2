package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.TimelineType;


/***
 * Excerpt: This type represents events in the history of a Question.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/question-timeline
 * @see Question
 */
public class QuestionTimeline {
	@SerializedName("comment_id") public int commentId = -1;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("down_vote_count") public int downvoteCount = -1;
	@SerializedName("owner") public ShallowUser owner;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("question_id") public int questionId = -1;
	@SerializedName("revision_guid") public String revisionGuid = "";
	@SerializedName("timeline_type") public TimelineType timelineType = TimelineType.Unknown;
	@SerializedName("up_vote_count") public int upvoteCount = -1;
	@SerializedName("user") public ShallowUser user;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId;
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + downvoteCount;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + postId;
		result = prime * result + questionId;
		result = prime * result
				+ ((revisionGuid == null) ? 0 : revisionGuid.hashCode());
		result = prime * result
				+ ((timelineType == null) ? 0 : timelineType.hashCode());
		result = prime * result + upvoteCount;
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
		QuestionTimeline other = (QuestionTimeline) obj;
		if (commentId != other.commentId)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (downvoteCount != other.downvoteCount)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (postId != other.postId)
			return false;
		if (questionId != other.questionId)
			return false;
		if (revisionGuid == null) {
			if (other.revisionGuid != null)
				return false;
		} else if (!revisionGuid.equals(other.revisionGuid))
			return false;
		if (timelineType != other.timelineType)
			return false;
		if (upvoteCount != other.upvoteCount)
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
		builder.append("QuestionTimeline [commentId=");
		builder.append(commentId);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", downvoteCount=");
		builder.append(downvoteCount);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", revisionGuid=");
		builder.append(revisionGuid);
		builder.append(", timelineType=");
		builder.append(timelineType);
		builder.append(", upvoteCount=");
		builder.append(upvoteCount);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
}
