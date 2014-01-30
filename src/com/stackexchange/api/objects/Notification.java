package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.NotificationType;


/***
 * Excerpt: This type represents an item in a user's Notification Tab.
 * Be aware that the types of items returned by this method are subject to change at any time. 
 * In particular, new types may be introduced without warning. Applications should deal with these changes gracefully.
 * Applications should not publish a user's notification tab without their explicit consent, as while most item types are 
 * public in nature there are a few which are (and should remain) private.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/notification
 */
public class Notification {
	@SerializedName("body") public String body = "";
	@SerializedName("creation_date") public long creationDate;
	@SerializedName("is_unread") public boolean isUnread = false;
	@SerializedName("notification_type") public NotificationType notificationType = NotificationType.Unknown;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("site") public Site site;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isUnread ? 1231 : 1237);
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime
				* result
				+ ((notificationType == null) ? 0 : notificationType
						.hashCode());
		result = prime * result + postId;
		result = prime * result + ((site == null) ? 0 : site.hashCode());
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
		Notification other = (Notification) obj;
		if (isUnread != other.isUnread)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (notificationType != other.notificationType)
			return false;
		if (postId != other.postId)
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [body=");
		builder.append(body);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", isUnread=");
		builder.append(isUnread);
		builder.append(", notificationType=");
		builder.append(notificationType);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", selectedSite=");
		builder.append(site);
		builder.append("]");
		return builder.toString();
	}
	
}
