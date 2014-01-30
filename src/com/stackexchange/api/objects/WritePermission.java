package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * This type describes a user's ability to perform a certain write operation against a type via the Stack Exchange API.
 * 
 * @author t0mm13b
 *
 */
public class WritePermission {
	@SerializedName("can_add") public boolean canAdd = false;
	@SerializedName("can_delete") public boolean canDelete = false;
	@SerializedName("can_edit") public boolean canEdit = false;
	@SerializedName("max_daily_actions") public int maxDailyActions = -1;
	@SerializedName("min_seconds_between_actions") public int minSecsBetweenActions = -1;
	@SerializedName("object_type") public String typeOfObject = "";
	@SerializedName("user_id") public int userId = -1;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (canAdd ? 1231 : 1237);
		result = prime * result + (canDelete ? 1231 : 1237);
		result = prime * result + (canEdit ? 1231 : 1237);
		result = prime * result + maxDailyActions;
		result = prime * result + minSecsBetweenActions;
		result = prime * result
				+ ((typeOfObject == null) ? 0 : typeOfObject.hashCode());
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
		WritePermission other = (WritePermission) obj;
		if (canAdd != other.canAdd)
			return false;
		if (canDelete != other.canDelete)
			return false;
		if (canEdit != other.canEdit)
			return false;
		if (maxDailyActions != other.maxDailyActions)
			return false;
		if (minSecsBetweenActions != other.minSecsBetweenActions)
			return false;
		if (typeOfObject == null) {
			if (other.typeOfObject != null)
				return false;
		} else if (!typeOfObject.equals(other.typeOfObject))
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
		builder.append("WritePermission [canAdd=");
		builder.append(canAdd);
		builder.append(", canDelete=");
		builder.append(canDelete);
		builder.append(", canEdit=");
		builder.append(canEdit);
		builder.append(", maxDailyActions=");
		builder.append(maxDailyActions);
		builder.append(", minSecsBetweenActions=");
		builder.append(minSecsBetweenActions);
		builder.append(", typeOfObject=");
		builder.append(typeOfObject);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
}
