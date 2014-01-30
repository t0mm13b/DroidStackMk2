package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Represents a notice on a post.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/notice
 */
public class Notice {
	@SerializedName("body") public String body = "";
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("owner_user_id") public int ownerUserId = -1;

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
		result = prime * result + ownerUserId;
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
		Notice other = (Notice) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (ownerUserId != other.ownerUserId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notice [body=");
		builder.append(body);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", ownerUserId=");
		builder.append(ownerUserId);
		builder.append("]");
		return builder.toString();
	}
	
}
