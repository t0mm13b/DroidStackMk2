package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * This type is used to describe the errors that can be returned by the Stack Exchange API.
 * It is not expected that many applications will concern themselves with this type. 
 * It is made available for development and testing purposes.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/error
 */
public class Error {
	@SerializedName("description") public String description = "";
	@SerializedName("error_id") public int errorId = -1;
	@SerializedName("error_name") public String errorName = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + errorId;
		result = prime * result
				+ ((errorName == null) ? 0 : errorName.hashCode());
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
		Error other = (Error) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (errorId != other.errorId)
			return false;
		if (errorName == null) {
			if (other.errorName != null)
				return false;
		} else if (!errorName.equals(other.errorName))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Error [description=");
		builder.append(description);
		builder.append(", errorId=");
		builder.append(errorId);
		builder.append(", errorName=");
		builder.append(errorName);
		builder.append("]");
		return builder.toString();
	}
	
}
