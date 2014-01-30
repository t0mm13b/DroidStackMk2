package com.stackexchange.api.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/***
 * This type represents an answer to a question on one of the Stack Exchange sites
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/answer
 */
public class Answer {
	@SerializedName("answer_id") public int answerId = -1;
	@SerializedName("body") public String body = "";
	@SerializedName("comments") public List<Comment> listComments;
	@SerializedName("community_owned_date") public long communityOwnedDate = -1;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("down_vote_count") public int downvoteCount = -1;
	@SerializedName("is_accepted") public boolean isAccepted = false;
	@SerializedName("last_activity_date") public long lastActivityDate = -1;
	@SerializedName("last_edit_date") public long lastEditDate = -1;
	@SerializedName("link") public String link = "";
	@SerializedName("locked_date") public long lockedDate = -1;
	@SerializedName("owner") public ShallowUser owner;
	@SerializedName("question_id") public int questionId = -1;
	@SerializedName("score") public int score = -1;
	@SerializedName("tags") public List<String> listTags;
	@SerializedName("title") public String title = "";
	@SerializedName("up_vote_count") public int upvoteCount = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerId;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((listComments == null) ? 0 : listComments.hashCode());
		result = prime * result
				+ (int) (communityOwnedDate ^ (communityOwnedDate >>> 32));
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + downvoteCount;
		result = prime * result + (isAccepted ? 1231 : 1237);
		result = prime * result
				+ (int) (lastActivityDate ^ (lastActivityDate >>> 32));
		result = prime * result
				+ (int) (lastEditDate ^ (lastEditDate >>> 32));
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + (int) (lockedDate ^ (lockedDate >>> 32));
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + questionId;
		result = prime * result + score;
		result = prime * result
				+ ((listTags == null) ? 0 : listTags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + upvoteCount;
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
		Answer other = (Answer) obj;
		if (answerId != other.answerId)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (listComments == null) {
			if (other.listComments != null)
				return false;
		} else if (!listComments.equals(other.listComments))
			return false;
		if (communityOwnedDate != other.communityOwnedDate)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (downvoteCount != other.downvoteCount)
			return false;
		if (isAccepted != other.isAccepted)
			return false;
		if (lastActivityDate != other.lastActivityDate)
			return false;
		if (lastEditDate != other.lastEditDate)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (lockedDate != other.lockedDate)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (questionId != other.questionId)
			return false;
		if (score != other.score)
			return false;
		if (listTags == null) {
			if (other.listTags != null)
				return false;
		} else if (!listTags.equals(other.listTags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (upvoteCount != other.upvoteCount)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Answer [answerId=");
		builder.append(answerId);
		builder.append(", body=");
		builder.append(body);
		builder.append(", listComments=");
		builder.append(listComments);
		builder.append(", communityOwnedDate=");
		builder.append(communityOwnedDate);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", downvoteCount=");
		builder.append(downvoteCount);
		builder.append(", isAccepted=");
		builder.append(isAccepted);
		builder.append(", lastActivityDate=");
		builder.append(lastActivityDate);
		builder.append(", lastEditDate=");
		builder.append(lastEditDate);
		builder.append(", link=");
		builder.append(link);
		builder.append(", lockedDate=");
		builder.append(lockedDate);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", score=");
		builder.append(score);
		builder.append(", listTags=");
		builder.append(listTags);
		builder.append(", title=");
		builder.append(title);
		builder.append(", upvoteCount=");
		builder.append(upvoteCount);
		builder.append("]");
		return builder.toString();
	}
	
}
