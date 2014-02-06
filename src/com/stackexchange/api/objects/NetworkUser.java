package com.stackexchange.api.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.UserType;


/***
 * This type represents a user, however it is greatly reduced when compared to the full {@link User} 
 * type to reduce the amount of work that needs to be done to fetch it from multiple sites in the network.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/network-user
 */
public class NetworkUser implements Parcelable{
	@SerializedName("account_id") public int accountId = -1;
	@SerializedName("answer_count") public int answerCount = -1;
	@SerializedName("badge_counts") public BadgeCounts badges;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("last_access_date") public long lastAccessDate = -1;
	@SerializedName("question_count") public int questionCount = -1;
	@SerializedName("reputation") public int reputation = -1;
	@SerializedName("site_name") public String siteName;
	@SerializedName("site_url") public String siteUrl;
	@SerializedName("user_id") public int userId = -1;
	@SerializedName("user_type") public transient UserType userType = UserType.Unknown; // Excluded from default filter... 

	private NetworkUser(Parcel in){
		readFromParcel(in);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + answerCount;
		result = prime * result + ((badges == null) ? 0 : badges.hashCode());
		result = prime * result + (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result
				+ (int) (lastAccessDate ^ (lastAccessDate >>> 32));
		result = prime * result + questionCount;
		result = prime * result + reputation;
		result = prime * result
				+ ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result + ((siteUrl == null) ? 0 : siteUrl.hashCode());
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
		NetworkUser other = (NetworkUser) obj;
		if (accountId != other.accountId)
			return false;
		if (answerCount != other.answerCount)
			return false;
		if (badges == null) {
			if (other.badges != null)
				return false;
		} else if (!badges.equals(other.badges))
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (lastAccessDate != other.lastAccessDate)
			return false;
		if (questionCount != other.questionCount)
			return false;
		if (reputation != other.reputation)
			return false;
		if (siteName == null) {
			if (other.siteName != null)
				return false;
		} else if (!siteName.equals(other.siteName))
			return false;
		if (siteUrl == null) {
			if (other.siteUrl != null)
				return false;
		} else if (!siteUrl.equals(other.siteUrl))
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
		builder.append("NetworkUser [accountId=");
		builder.append(accountId);
		builder.append(", answerCount=");
		builder.append(answerCount);
		builder.append(", badges=");
		builder.append(badges);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", lastAccessDate=");
		builder.append(lastAccessDate);
		builder.append(", questionCount=");
		builder.append(questionCount);
		builder.append(", reputation=");
		builder.append(reputation);
		builder.append(", siteName=");
		builder.append(siteName);
		builder.append(", siteUrl=");
		builder.append(siteUrl);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userType=");
		builder.append(userType);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(accountId);
		dest.writeInt(answerCount);
		dest.writeParcelable(badges, flags);
		dest.writeLong(creationDate);
		dest.writeLong(lastAccessDate);
		dest.writeInt(questionCount);
		dest.writeInt(reputation);
		dest.writeString(siteName);
		dest.writeString(siteUrl);
		dest.writeInt(userId);
		dest.writeInt(userType.ordinal());
	}
	
	private void readFromParcel(Parcel src){
		accountId = src.readInt();
		answerCount = src.readInt();
		badges = src.readParcelable(BadgeCounts.class.getClassLoader());
		creationDate = src.readLong();
		lastAccessDate = src.readLong();
		questionCount = src.readInt();
		reputation = src.readInt();
		siteName = src.readString();
		siteUrl = src.readString();
		userId = src.readInt();
		userType = UserType.values()[src.readInt()];
	}
	public static final Parcelable.Creator<NetworkUser> CREATOR = new Parcelable.Creator<NetworkUser>() {

		@Override
		public NetworkUser createFromParcel(Parcel source) {
			return new NetworkUser(source);
		}

		@Override
		public NetworkUser[] newArray(int size) {
			return new NetworkUser[size];
		}
	};
}
