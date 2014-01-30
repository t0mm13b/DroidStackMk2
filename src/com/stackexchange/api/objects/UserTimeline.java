package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.PostType;
import com.stackexchange.api.objects.Enums.TimelineType;


/***
 * Excerpt: This type describes public actions a User has taken.
 * 
 * @author t0mm13b
 * 
 * @see http://api.stackexchange.com/docs/types/user-timeline
 * @see User
 */
public class UserTimeline {
	@SerializedName("badge_id") public int badgeId = -1;
	@SerializedName("comment_id") public int commentId = -1;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("detail") public String detail = "";
	@SerializedName("link") public String link = "";
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("post_type") public PostType postType = PostType.Unknown;
	@SerializedName("suggested_edit_id") public int suggestedEditId = -1;
	@SerializedName("timeline_type") public TimelineType timelineType = TimelineType.Unknown;
	@SerializedName("title") public String title;
	@SerializedName("user_id") public int userId = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + badgeId;
		result = prime * result + commentId;
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + postId;
		result = prime * result
				+ ((postType == null) ? 0 : postType.hashCode());
		result = prime * result + suggestedEditId;
		result = prime * result
				+ ((timelineType == null) ? 0 : timelineType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		UserTimeline other = (UserTimeline) obj;
		if (badgeId != other.badgeId)
			return false;
		if (commentId != other.commentId)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (postId != other.postId)
			return false;
		if (postType != other.postType)
			return false;
		if (suggestedEditId != other.suggestedEditId)
			return false;
		if (timelineType != other.timelineType)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		builder.append("UserTimeline [badgeId=");
		builder.append(badgeId);
		builder.append(", commentId=");
		builder.append(commentId);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", link=");
		builder.append(link);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", postType=");
		builder.append(postType);
		builder.append(", suggestedEditId=");
		builder.append(suggestedEditId);
		builder.append(", timelineType=");
		builder.append(timelineType);
		builder.append(", title=");
		builder.append(title);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
}
