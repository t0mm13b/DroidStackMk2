package com.stackexchange.api.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/***
 * All responses in the Stack Exchange API share a common format, so as to make parsing these responses simpler.
 * 
 * The error_* fields, while technically elligible for filtering, will not actually be excluded in an error case. 
 * 
 * This is by design.
 * 
 * page and page_size are whatever was passed into the method.
 * 
 * If you're looking to just select total, exclude the items field in favor of excluding all the properties on the returned type.
 * 
 * When building filters, this common wrapper object has no name. Refer to it with a leading ., so the items field would be refered to via .items.
 * 
 * The backoff field is only set when the API detects the request took an unusually long time to run. 
 * 
 * When it is set an application must wait that number of seconds before calling that method again. 
 * 
 * Note that for the purposes of throttling and backoff, the /me routes are considered the same as their /users/{ids} equivalent.

 * @author t0mm13b
 * @param <T>
 * @see https://api.stackexchange.com/docs/wrapper
 */
public class CommonSEWrapper<T>{
	@SerializedName("backoff") public int backOff = -1;
	@SerializedName("error_id") public int errorId = -1;
	@SerializedName("error_message") public String errorMessage = "";
	@SerializedName("error_name") public String errorName = "";
	@SerializedName("has_more") public boolean hasMore = false;
	@SerializedName("items")public List<T> itemsList = null; 
	@SerializedName("page") public int pageNumber = -1;
	@SerializedName("page_size") public int pageSize = -1;
	@SerializedName("quota_max") public int quotaMax = -1;
	@SerializedName("quota_remaining") public int quotaRemaining = -1;
	@SerializedName("total") public int total = -1;
	@SerializedName("type") public String type = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasMore ? 1231 : 1237);
		result = prime * result + backOff;
		result = prime * result + errorId;
		result = prime * result
				+ ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result
				+ ((errorName == null) ? 0 : errorName.hashCode());
		result = prime * result
				+ ((itemsList == null) ? 0 : itemsList.hashCode());
		result = prime * result + pageNumber;
		result = prime * result + pageSize;
		result = prime * result + quotaMax;
		result = prime * result + quotaRemaining;
		result = prime * result + total;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommonSEWrapper<T> other = (CommonSEWrapper<T>) obj;
		if (hasMore != other.hasMore)
			return false;
		if (backOff != other.backOff)
			return false;
		if (errorId != other.errorId)
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (errorName == null) {
			if (other.errorName != null)
				return false;
		} else if (!errorName.equals(other.errorName))
			return false;
		if (itemsList == null) {
			if (other.itemsList != null)
				return false;
		} else if (!itemsList.equals(other.itemsList))
			return false;
		if (pageNumber != other.pageNumber)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (quotaMax != other.quotaMax)
			return false;
		if (quotaRemaining != other.quotaRemaining)
			return false;
		if (total != other.total)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonSEWrapper [backOff=");
		builder.append(backOff);
		builder.append(", errorId=");
		builder.append(errorId);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", errorName=");
		builder.append(errorName);
		builder.append(", hasMore=");
		builder.append(hasMore);
		builder.append(", itemsList=");
		builder.append(itemsList);
		builder.append(", pageNumber=");
		builder.append(pageNumber);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", quotaMax=");
		builder.append(quotaMax);
		builder.append(", quotaRemaining=");
		builder.append(quotaRemaining);
		builder.append(", total=");
		builder.append(total);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
}
