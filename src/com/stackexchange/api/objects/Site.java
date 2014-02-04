package com.stackexchange.api.objects;


import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.SiteState;
import com.stackexchange.api.objects.Enums.SiteType;

/***
 * Excerpt: This type represents a site in the Stack Exchange network.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/site
 */
public class Site implements Parcelable{
	@SerializedName("aliases") public List<String> aliases;
	@SerializedName("api_site_parameter") public String apiSiteParameter = "";
	@SerializedName("audience") public String audience = "";
	@SerializedName("closed_beta_date") public long closedBetaDate = -1;
	@SerializedName("fav_icon_url") public String favIconUrl = "";
	@SerializedName("high_resolution_icon_url") public String highResolutionIconUrl = "";
	@SerializedName("icon_url") public String iconUrl = "";
	@SerializedName("launch_date") public long launchDate = -1;
	@SerializedName("logo_url") public String logoUrl = "";
	@SerializedName("markdown_extensions") public List<String> markDownExtensions;
	@SerializedName("name") public String name = "";
	@SerializedName("open_beta_date") public long openBetaDate = -1;
	@SerializedName("related_sites") public List<RelatedSite> relatedSites;
	@SerializedName("site_state") public SiteState siteState = SiteState.Unknown;
	@SerializedName("site_type") public SiteType siteType = SiteType.Unknown;
	@SerializedName("site_url") public String siteUrl = "";
	@SerializedName("styling") public Styling styling;
	@SerializedName("twitter_account") public String twitterAccount = "";
	
	private Site(){
		aliases = new ArrayList<String>();
		markDownExtensions = new ArrayList<String>();
		relatedSites = new ArrayList<RelatedSite>();
	}
	
	private Site(Parcel in){
		readFromParcel(in);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aliases == null) ? 0 : aliases.hashCode());
		result = prime
				* result
				+ ((apiSiteParameter == null) ? 0 : apiSiteParameter
						.hashCode());
		result = prime * result
				+ ((audience == null) ? 0 : audience.hashCode());
		result = prime * result
				+ (int) (closedBetaDate ^ (closedBetaDate >>> 32));
		result = prime * result
				+ ((favIconUrl == null) ? 0 : favIconUrl.hashCode());
		result = prime
				* result
				+ ((highResolutionIconUrl == null) ? 0
						: highResolutionIconUrl.hashCode());
		result = prime * result
				+ ((iconUrl == null) ? 0 : iconUrl.hashCode());
		result = prime * result + (int) (launchDate ^ (launchDate >>> 32));
		result = prime * result
				+ ((logoUrl == null) ? 0 : logoUrl.hashCode());
		result = prime
				* result
				+ ((markDownExtensions == null) ? 0 : markDownExtensions
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ (int) (openBetaDate ^ (openBetaDate >>> 32));
		result = prime * result
				+ ((relatedSites == null) ? 0 : relatedSites.hashCode());
		result = prime * result
				+ ((siteState == null) ? 0 : siteState.hashCode());
		result = prime * result
				+ ((siteType == null) ? 0 : siteType.hashCode());
		result = prime * result
				+ ((siteUrl == null) ? 0 : siteUrl.hashCode());
		result = prime * result
				+ ((styling == null) ? 0 : styling.hashCode());
		result = prime * result
				+ ((twitterAccount == null) ? 0 : twitterAccount.hashCode());
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
		Site other = (Site) obj;
		if (aliases == null) {
			if (other.aliases != null)
				return false;
		} else if (!aliases.equals(other.aliases))
			return false;
		if (apiSiteParameter == null) {
			if (other.apiSiteParameter != null)
				return false;
		} else if (!apiSiteParameter.equals(other.apiSiteParameter))
			return false;
		if (audience == null) {
			if (other.audience != null)
				return false;
		} else if (!audience.equals(other.audience))
			return false;
		if (closedBetaDate != other.closedBetaDate)
			return false;
		if (favIconUrl == null) {
			if (other.favIconUrl != null)
				return false;
		} else if (!favIconUrl.equals(other.favIconUrl))
			return false;
		if (highResolutionIconUrl == null) {
			if (other.highResolutionIconUrl != null)
				return false;
		} else if (!highResolutionIconUrl.equals(other.highResolutionIconUrl))
			return false;
		if (iconUrl == null) {
			if (other.iconUrl != null)
				return false;
		} else if (!iconUrl.equals(other.iconUrl))
			return false;
		if (launchDate != other.launchDate)
			return false;
		if (logoUrl == null) {
			if (other.logoUrl != null)
				return false;
		} else if (!logoUrl.equals(other.logoUrl))
			return false;
		if (markDownExtensions == null) {
			if (other.markDownExtensions != null)
				return false;
		} else if (!markDownExtensions.equals(other.markDownExtensions))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (openBetaDate != other.openBetaDate)
			return false;
		if (relatedSites == null) {
			if (other.relatedSites != null)
				return false;
		} else if (!relatedSites.equals(other.relatedSites))
			return false;
		if (siteState != other.siteState)
			return false;
		if (siteType != other.siteType)
			return false;
		if (siteUrl == null) {
			if (other.siteUrl != null)
				return false;
		} else if (!siteUrl.equals(other.siteUrl))
			return false;
		if (styling == null) {
			if (other.styling != null)
				return false;
		} else if (!styling.equals(other.styling))
			return false;
		if (twitterAccount == null) {
			if (other.twitterAccount != null)
				return false;
		} else if (!twitterAccount.equals(other.twitterAccount))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Site [aliases=");
		builder.append(aliases);
		builder.append(", apiSiteParameter=");
		builder.append(apiSiteParameter);
		builder.append(", audience=");
		builder.append(audience);
		builder.append(", closedBetaDate=");
		builder.append(closedBetaDate);
		builder.append(", favIconUrl=");
		builder.append(favIconUrl);
		builder.append(", highResolutionIconUrl=");
		builder.append(highResolutionIconUrl);
		builder.append(", iconUrl=");
		builder.append(iconUrl);
		builder.append(", launchDate=");
		builder.append(launchDate);
		builder.append(", logoUrl=");
		builder.append(logoUrl);
		builder.append(", markDownExtensions=");
		builder.append(markDownExtensions);
		builder.append(", name=");
		builder.append(name);
		builder.append(", openBetaDate=");
		builder.append(openBetaDate);
		builder.append(", relatedSites=");
		builder.append(relatedSites);
		builder.append(", siteState=");
		builder.append(siteState);
		builder.append(", siteType=");
		builder.append(siteType);
		builder.append(", siteUrl=");
		builder.append(siteUrl);
		builder.append(", styling=");
		builder.append(styling);
		builder.append(", twitterAccount=");
		builder.append(twitterAccount);
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
		dest.writeStringList(aliases);
		dest.writeString(apiSiteParameter);
		dest.writeString(audience);
		dest.writeLong(closedBetaDate);
		dest.writeString(favIconUrl);
		dest.writeString(highResolutionIconUrl);
		dest.writeString(iconUrl);
		dest.writeLong(launchDate);
		dest.writeString(logoUrl);
		dest.writeStringList(markDownExtensions);
		dest.writeString(name);
		dest.writeLong(openBetaDate);
		dest.writeTypedList(relatedSites);
		dest.writeInt(siteState.ordinal());
		dest.writeInt(siteType.ordinal());
		dest.writeString(siteUrl);
		dest.writeParcelable(styling, flags);
		dest.writeString(twitterAccount);
	}
	private void readFromParcel(Parcel in){
		in.readStringList(aliases);
		apiSiteParameter = in.readString();
		audience = in.readString();
		closedBetaDate = in.readLong();
		favIconUrl = in.readString();
		highResolutionIconUrl = in.readString();
		iconUrl = in.readString();
		launchDate = in.readLong();
		logoUrl = in.readString();
		in.readStringList(markDownExtensions);
		name = in.readString();
		openBetaDate = in.readLong();
		in.readTypedList(relatedSites, RelatedSite.CREATOR);
		siteState = SiteState.values()[in.readInt()];
		siteType = SiteType.values()[in.readInt()];
		siteUrl = in.readString();
		styling = (Styling)in.readParcelable(Styling.class.getClassLoader());
		twitterAccount = in.readString();
	}
	public static final Parcelable.Creator<Site> CREATOR = new Parcelable.Creator<Site>(){
		@Override
		public Site createFromParcel(Parcel source) {
			return new Site(source);
		}
		@Override
		public Site[] newArray(int size){
			return new Site[size];
		}
	};
}
