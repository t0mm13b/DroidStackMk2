package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.EventType;


/***
 * Excerpt: This type describes an event that has recently occurred on a Stack Exchange site.
 * A minimal ammount of information is present in these events for scaling purposes. 
 * It is expected that most applications will make follow up calls to the API to "flesh out" the event objects for their own purposes.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/event
 */
public class Event {
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("event_id") public int eventId = -1;
	@SerializedName("event_type") public EventType eventType = EventType.Unknown;
	@SerializedName("excerpt") public String excerpt = "";
	@SerializedName("link") public String link = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + eventId;
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result
				+ ((excerpt == null) ? 0 : excerpt.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
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
		Event other = (Event) obj;
		if (creationDate != other.creationDate)
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventType != other.eventType)
			return false;
		if (excerpt == null) {
			if (other.excerpt != null)
				return false;
		} else if (!excerpt.equals(other.excerpt))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [creationDate=");
		builder.append(creationDate);
		builder.append(", eventId=");
		builder.append(eventId);
		builder.append(", eventType=");
		builder.append(eventType);
		builder.append(", excerpt=");
		builder.append(excerpt);
		builder.append(", link=");
		builder.append(link);
		builder.append("]");
		return builder.toString();
	}
	
}
