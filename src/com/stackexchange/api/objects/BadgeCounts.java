package com.stackexchange.api.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BadgeCounts implements Parcelable{
	@SerializedName("bronze") public int bronze = -1;
	@SerializedName("silver") public int silver = -1;
	@SerializedName("gold") public int gold = -1;
	
	private BadgeCounts(Parcel in){
		readFromParcel(in);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bronze;
		result = prime * result + gold;
		result = prime * result + silver;
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
		BadgeCounts other = (BadgeCounts) obj;
		if (bronze != other.bronze)
			return false;
		if (gold != other.gold)
			return false;
		if (silver != other.silver)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BadgeCounts [bronze=");
		builder.append(bronze);
		builder.append(", silver=");
		builder.append(silver);
		builder.append(", gold=");
		builder.append(gold);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(bronze);
		dest.writeInt(silver);
		dest.writeInt(gold);
	}
	private void readFromParcel(Parcel src){
		bronze = src.readInt();
		silver = src.readInt();
		gold = src.readInt();
	}
	public static final Parcelable.Creator<BadgeCounts> CREATOR = new Parcelable.Creator<BadgeCounts>() {

		@Override
		public BadgeCounts createFromParcel(Parcel source) {
			return new BadgeCounts(source);
		}

		@Override
		public BadgeCounts[] newArray(int size) {
			return new BadgeCounts[size];
		}
	};
}
