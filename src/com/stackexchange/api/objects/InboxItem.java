package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.InboxType;


/***
 * Excerpt: This type represents an item in a user's Global Inbox. 
 * Be aware that the types of items returned by this method are subject to change at any time. 
 * In particular, new types may be introduced without warning. Applications should deal with these changes gracefully. 
 * Applications should not publish a user's inbox without their explicit consent, as while most item types are public in 
 * nature there are a few which are (and should remain) private.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/inbox-item
 */
public class InboxItem {
	@SerializedName("answer_id") public int answerId = -1;
	@SerializedName("body") public String body = "";
	@SerializedName("comment_id") public int commentId = -1;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("is_unknown") public boolean isUnread = false;
	@SerializedName("item_type") public InboxType inboxType = InboxType.Unknown;
	@SerializedName("link") public String link = "";
	@SerializedName("question_id") public int questionId = -1;
	@SerializedName("site") public Site site;
	@SerializedName("title") public String title = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isUnread ? 1231 : 1237);
		result = prime * result + answerId;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + commentId;
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result
				+ ((inboxType == null) ? 0 : inboxType.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + questionId;
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		InboxItem other = (InboxItem) obj;
		if (isUnread != other.isUnread)
			return false;
		if (answerId != other.answerId)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (commentId != other.commentId)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (inboxType != other.inboxType)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (questionId != other.questionId)
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InboxItem [answerId=");
		builder.append(answerId);
		builder.append(", body=");
		builder.append(body);
		builder.append(", commentId=");
		builder.append(commentId);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", isUnread=");
		builder.append(isUnread);
		builder.append(", inboxType=");
		builder.append(inboxType);
		builder.append(", link=");
		builder.append(link);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", selectedSite=");
		builder.append(site);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}
	
}
