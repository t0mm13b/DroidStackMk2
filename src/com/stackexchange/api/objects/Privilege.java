package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: Represents a privilege a user may have on a Stack Exchange site.
 * Applications should be aware of, and be able to deal with, the possibility of new privileges being introduced and 
 * old ones being removed.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/privilege
 */
public class Privilege {
	@SerializedName("description") public String description = "";
	@SerializedName("reputation") public int reputation = -1;
	@SerializedName("short_description") public String shortDescription = "";
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + reputation;
		result = prime
				* result
				+ ((shortDescription == null) ? 0 : shortDescription
						.hashCode());
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
		Privilege other = (Privilege) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reputation != other.reputation)
			return false;
		if (shortDescription == null) {
			if (other.shortDescription != null)
				return false;
		} else if (!shortDescription.equals(other.shortDescription))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Privilege [description=");
		builder.append(description);
		builder.append(", reputation=");
		builder.append(reputation);
		builder.append(", shortDescription=");
		builder.append(shortDescription);
		builder.append("]");
		return builder.toString();
	}
	
}
