package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * This type describes a network level merger of two accounts. Note that ids in old_account_id are no longer valid.
 * 
 * @author t0mm13b
 * @see http://api.stackexchange.com/docs/types/account-merge
 */
public class AccountMerge {
	@SerializedName("merge_date") public long mergeDate = -1;
	@SerializedName("new_account_id") public int newAccountId = -1;
	@SerializedName("old_account_id") public int oldAccountId = -1;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (mergeDate ^ (mergeDate >>> 32));
		result = prime * result + newAccountId;
		result = prime * result + oldAccountId;
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
		AccountMerge other = (AccountMerge) obj;
		if (mergeDate != other.mergeDate)
			return false;
		if (newAccountId != other.newAccountId)
			return false;
		if (oldAccountId != other.oldAccountId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountMerge [mergeDate=");
		builder.append(mergeDate);
		builder.append(", newAccountId=");
		builder.append(newAccountId);
		builder.append(", oldAccountId=");
		builder.append(oldAccountId);
		builder.append("]");
		return builder.toString();
	}
	
}
