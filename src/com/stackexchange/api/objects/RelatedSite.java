package com.stackexchange.api.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.Relation;


/***
 * This type represents a site that is related in some way to another site. 
 * Examples include chat and meta, and parent sites.
 * Applications should be able to gracefully handle the additon of new related site types.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/related-site
 */
public class RelatedSite implements Parcelable {
	@SerializedName("api_site_parameter") public String apiSiteParameter = "";
	@SerializedName("name") public String name = "";
	@SerializedName("relation") public Relation relation = Relation.Unknown;
	@SerializedName("site_url") public String siteUrl = "";

	private RelatedSite(Parcel in){
		readFromParcel(in);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((apiSiteParameter == null) ? 0 : apiSiteParameter
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((relation == null) ? 0 : relation.hashCode());
		result = prime * result
				+ ((siteUrl == null) ? 0 : siteUrl.hashCode());
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
		RelatedSite other = (RelatedSite) obj;
		if (apiSiteParameter == null) {
			if (other.apiSiteParameter != null)
				return false;
		} else if (!apiSiteParameter.equals(other.apiSiteParameter))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (relation != other.relation)
			return false;
		if (siteUrl == null) {
			if (other.siteUrl != null)
				return false;
		} else if (!siteUrl.equals(other.siteUrl))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelatedSite [apiSiteParameter=");
		builder.append(apiSiteParameter);
		builder.append(", name=");
		builder.append(name);
		builder.append(", relation=");
		builder.append(relation);
		builder.append(", siteUrl=");
		builder.append(siteUrl);
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
		// TODO Auto-generated method stub
		dest.writeString(apiSiteParameter);
		dest.writeString(name);
		dest.writeString(siteUrl);
		dest.writeInt(relation.ordinal());
	}
	private void readFromParcel(Parcel src){
		apiSiteParameter = src.readString();
		name = src.readString();
		siteUrl = src.readString();
		relation = Relation.values()[src.readInt()];
	}
	public static final Parcelable.Creator<RelatedSite> CREATOR = new Parcelable.Creator<RelatedSite>(){
		@Override
		public RelatedSite createFromParcel(Parcel source) {
			return new RelatedSite(source);
		}
		@Override
		public RelatedSite[] newArray(int size){
			return new RelatedSite[size];
		}
	};
}
