package com.stackexchange.api.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.PostType;


/***
 * Excerpt: This type represents the intersection of the Question and Answer types.
 * It's used in cases where it would be beneficial to mix questions and answers in a response.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/post
 * @see Question
 * @see Answer
 */
public class Post {
	@SerializedName("body") public String body = "";
	@SerializedName("comments") public List<Comment> listComments;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("down_vote_count") public int downvoteCount = -1;
	@SerializedName("last_activity_date") public long lastActivityDate = -1;
	@SerializedName("last_edit_date") public long lastEditDate = -1;
	@SerializedName("link") public String link = "";
	@SerializedName("owner") public ShallowUser owner;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("post_type") public PostType postType = PostType.Unknown;
	@SerializedName("score") public int score = -1;
	@SerializedName("up_vote_count") public int upvoteCount = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + downvoteCount;
		result = prime * result
				+ (int) (lastActivityDate ^ (lastActivityDate >>> 32));
		result = prime * result
				+ (int) (lastEditDate ^ (lastEditDate >>> 32));
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((listComments == null) ? 0 : listComments.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + postId;
		result = prime * result
				+ ((postType == null) ? 0 : postType.hashCode());
		result = prime * result + score;
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
		Post other = (Post) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (downvoteCount != other.downvoteCount)
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
		if (listComments == null) {
			if (other.listComments != null)
				return false;
		} else if (!listComments.equals(other.listComments))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (postId != other.postId)
			return false;
		if (postType != other.postType)
			return false;
		if (score != other.score)
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
		builder.append("Post [body=");
		builder.append(body);
		builder.append(", listComments=");
		builder.append(listComments);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", downvoteCount=");
		builder.append(downvoteCount);
		builder.append(", lastActivityDate=");
		builder.append(lastActivityDate);
		builder.append(", lastEditDate=");
		builder.append(lastEditDate);
		builder.append(", link=");
		builder.append(link);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", postType=");
		builder.append(postType);
		builder.append(", score=");
		builder.append(score);
		builder.append(", upvoteCount=");
		builder.append(upvoteCount);
		builder.append("]");
		return builder.toString();
	}
	
}
