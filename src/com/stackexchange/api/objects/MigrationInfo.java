package com.stackexchange.api.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/***
 * This type represents a question's migration to or from a different site in the Stack Exchange network.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/migration-info
 */
public class MigrationInfo implements Parcelable{
	@SerializedName("on_date") public long onDate = -1;
	@SerializedName("other_site") public Site otherSite;
	@SerializedName("question_id") public int questionId = -1;

	private MigrationInfo(Parcel in){
		readFromParcel(in);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (onDate ^ (onDate >>> 32));
		result = prime * result
				+ ((otherSite == null) ? 0 : otherSite.hashCode());
		result = prime * result + questionId;
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
		MigrationInfo other = (MigrationInfo) obj;
		if (onDate != other.onDate)
			return false;
		if (otherSite == null) {
			if (other.otherSite != null)
				return false;
		} else if (!otherSite.equals(other.otherSite))
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MigrationInfo [onDate=");
		builder.append(onDate);
		builder.append(", otherSite=");
		builder.append(otherSite);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(onDate);
		dest.writeParcelable(otherSite, flags);
		dest.writeLong(questionId);
	}
	private void readFromParcel(Parcel src){
		onDate = src.readLong();
		otherSite = src.readParcelable(Site.class.getClassLoader());
		questionId = src.readInt();
	}
	public static final Parcelable.Creator<MigrationInfo> CREATOR = new Parcelable.Creator<MigrationInfo>() {

		@Override
		public MigrationInfo createFromParcel(Parcel source) {
			return new MigrationInfo(source);
		}

		@Override
		public MigrationInfo[] newArray(int size) {
			return new MigrationInfo[size];
		}
	};
}
