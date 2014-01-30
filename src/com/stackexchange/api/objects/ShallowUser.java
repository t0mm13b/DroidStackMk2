package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.UserType;


/***
 * ShallowUser object
 * 
 * Excerpt: This type represents a user, but omits many of the fields found on the full {@link User} type.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/shallow-user
 */
public class ShallowUser {
	@SerializedName("accept_rate") public int acceptRate = -1;
	@SerializedName("display_name") public String displayName = "";
	@SerializedName("link") public String link = "";
	@SerializedName("profile_image") public String profileImage = "";
	@SerializedName("reputation") public int reputation = -1;
	@SerializedName("user_id") public int userId = -1;
	@SerializedName("user_type") public UserType userType = UserType.Unknown;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acceptRate;
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((profileImage == null) ? 0 : profileImage.hashCode());
		result = prime * result + reputation;
		result = prime * result + userId;
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
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
		ShallowUser other = (ShallowUser) obj;
		if (acceptRate != other.acceptRate)
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		if (reputation != other.reputation)
			return false;
		if (userId != other.userId)
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShallowUser [acceptRate=");
		builder.append(acceptRate);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", link=");
		builder.append(link);
		builder.append(", profileImage=");
		builder.append(profileImage);
		builder.append(", reputation=");
		builder.append(reputation);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userType=");
		builder.append(userType);
		builder.append("]");
		return builder.toString();
	}
}
