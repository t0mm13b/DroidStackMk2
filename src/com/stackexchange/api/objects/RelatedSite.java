package com.stackexchange.api.objects;

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
public class RelatedSite {
	@SerializedName("api_site_parameter") public String apiSiteParameter = "";
	@SerializedName("name") public String name = "";
	@SerializedName("relation") public Relation relation = Relation.Unknown;
	@SerializedName("site_url") public String siteUrl = "";

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
	
}
