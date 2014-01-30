package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * This type represents some stylings of a site in the Stack Exchange network. 
 * 
 * These stylings are meant to allow developers to subtly vary the presentation of resources in their applications so as to indicate to users the original source site.
 * Applications should be able to gracefully handle these styles changes, though they can safely assume that these style changes are infrequent.
 * Note that colors can be returned either as six or three digit hex triplets.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/styling
 */
public class Styling {
	@SerializedName("link_color") public String linkColor = "";
	@SerializedName("tag_background_color") public String tagBackgroundColor = "";
	@SerializedName("tag_foreground_color") public String tagForegroundColor = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((linkColor == null) ? 0 : linkColor.hashCode());
		result = prime
				* result
				+ ((tagBackgroundColor == null) ? 0 : tagBackgroundColor
						.hashCode());
		result = prime
				* result
				+ ((tagForegroundColor == null) ? 0 : tagForegroundColor
						.hashCode());
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
		Styling other = (Styling) obj;
		if (linkColor == null) {
			if (other.linkColor != null)
				return false;
		} else if (!linkColor.equals(other.linkColor))
			return false;
		if (tagBackgroundColor == null) {
			if (other.tagBackgroundColor != null)
				return false;
		} else if (!tagBackgroundColor.equals(other.tagBackgroundColor))
			return false;
		if (tagForegroundColor == null) {
			if (other.tagForegroundColor != null)
				return false;
		} else if (!tagForegroundColor.equals(other.tagForegroundColor))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Styling [linkColor=");
		builder.append(linkColor);
		builder.append(", tagBackgroundColor=");
		builder.append(tagBackgroundColor);
		builder.append(", tagForegroundColor=");
		builder.append(tagForegroundColor);
		builder.append("]");
		return builder.toString();
	}
	
}
