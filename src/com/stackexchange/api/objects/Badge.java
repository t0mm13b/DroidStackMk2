package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.BadgeRank;
import com.stackexchange.api.objects.Enums.BadgeType;


/***
 * Excerpt: This type represents a badge on a Stack Exchange site. 
 * Some badges, like Autobiographer, are held in common across all Stack Exchange sites. Others, like most tag badges, vary on a site by site basis.
 * Remember that ids are never guaranteed to be the same between sites, even if a badge exists on both sites.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/badge
 */
public class Badge {
	@SerializedName("award_count") public int awardCount = -1;
	@SerializedName("badge_id") public int badgeId = -1;
	@SerializedName("badge_type") public BadgeType badgeType = BadgeType.Unknown;
	@SerializedName("description") public String description = "";
	@SerializedName("link") public String link = "";
	@SerializedName("name") public String name = "";
	@SerializedName("rank") public BadgeRank rank = BadgeRank.Unknown;
	@SerializedName("user") public ShallowUser user;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + awardCount;
		result = prime * result + badgeId;
		result = prime * result
				+ ((badgeType == null) ? 0 : badgeType.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Badge other = (Badge) obj;
		if (awardCount != other.awardCount)
			return false;
		if (badgeId != other.badgeId)
			return false;
		if (badgeType != other.badgeType)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rank != other.rank)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Badge [awardCount=");
		builder.append(awardCount);
		builder.append(", badgeId=");
		builder.append(badgeId);
		builder.append(", badgeType=");
		builder.append(badgeType);
		builder.append(", description=");
		builder.append(description);
		builder.append(", link=");
		builder.append(link);
		builder.append(", name=");
		builder.append(name);
		builder.append(", rank=");
		builder.append(rank);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
}
