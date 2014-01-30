package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.PostType;


/***
 * All Questions and Answers on a Stack Exchange site can be commented on, and this type represents those comments.
 * Comments can also be optionally directed at users, when this is the case the reply_to_user property is set 
 * (if it is requested in the current filter).
 * 
 * @author t0mm13b
 *
 */
public class Comment {
	@SerializedName("body") public String body = "";
	@SerializedName("body_markdown") public String bodyMarkdown = "";
	@SerializedName("comment_id") public int commentId = -1;
	@SerializedName("creation_date") public int creationDate = -1;
	@SerializedName("edited") public boolean isEdited = false;
	@SerializedName("link") public String link = "";
	@SerializedName("owner") public ShallowUser owner;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("post_type") public PostType typeOfPost;
	@SerializedName("reply_to_user") public ShallowUser replyToUser;
	@SerializedName("score") public int score = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((bodyMarkdown == null) ? 0 : bodyMarkdown.hashCode());
		result = prime * result + commentId;
		result = prime * result + creationDate;
		result = prime * result + (isEdited ? 1231 : 1237);
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + postId;
		result = prime * result
				+ ((replyToUser == null) ? 0 : replyToUser.hashCode());
		result = prime * result + score;
		result = prime * result
				+ ((typeOfPost == null) ? 0 : typeOfPost.hashCode());
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
		Comment other = (Comment) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (bodyMarkdown == null) {
			if (other.bodyMarkdown != null)
				return false;
		} else if (!bodyMarkdown.equals(other.bodyMarkdown))
			return false;
		if (commentId != other.commentId)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (isEdited != other.isEdited)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (postId != other.postId)
			return false;
		if (replyToUser == null) {
			if (other.replyToUser != null)
				return false;
		} else if (!replyToUser.equals(other.replyToUser))
			return false;
		if (score != other.score)
			return false;
		if (typeOfPost != other.typeOfPost)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [body=");
		builder.append(body);
		builder.append(", bodyMarkdown=");
		builder.append(bodyMarkdown);
		builder.append(", commentId=");
		builder.append(commentId);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", isEdited=");
		builder.append(isEdited);
		builder.append(", link=");
		builder.append(link);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", typeOfPost=");
		builder.append(typeOfPost);
		builder.append(", replyToUser=");
		builder.append(replyToUser);
		builder.append(", score=");
		builder.append(score);
		builder.append("]");
		return builder.toString();
	}
	
}
