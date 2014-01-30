package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type represents the community edited wiki for a given Tag.
 * Note that not all tags have a wiki.
 * 
 * @author t0mm13b
 * @see http://api.stackexchange.com/docs/types/tag-wiki
 * @see Tag
 */
public class TagWiki {
	@SerializedName("body") public String body = "";
	@SerializedName("last_edit_date") public long bodyLastEditDate = -1;
	@SerializedName("excerpt") public String excerpt = "";
	@SerializedName("excerpt_last_edit_date") public long excerptLastEditDate = -1;
	@SerializedName("last_body_editor") public ShallowUser lastBodyEditor;
	@SerializedName("last_excerpt_editor") public ShallowUser lastExcerptEditor;
	@SerializedName("tag_name") public String tagName = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ (int) (bodyLastEditDate ^ (bodyLastEditDate >>> 32));
		result = prime * result
				+ ((excerpt == null) ? 0 : excerpt.hashCode());
		result = prime * result
				+ (int) (excerptLastEditDate ^ (excerptLastEditDate >>> 32));
		result = prime * result
				+ ((lastBodyEditor == null) ? 0 : lastBodyEditor.hashCode());
		result = prime
				* result
				+ ((lastExcerptEditor == null) ? 0 : lastExcerptEditor
						.hashCode());
		result = prime * result
				+ ((tagName == null) ? 0 : tagName.hashCode());
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
		TagWiki other = (TagWiki) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (bodyLastEditDate != other.bodyLastEditDate)
			return false;
		if (excerpt == null) {
			if (other.excerpt != null)
				return false;
		} else if (!excerpt.equals(other.excerpt))
			return false;
		if (excerptLastEditDate != other.excerptLastEditDate)
			return false;
		if (lastBodyEditor == null) {
			if (other.lastBodyEditor != null)
				return false;
		} else if (!lastBodyEditor.equals(other.lastBodyEditor))
			return false;
		if (lastExcerptEditor == null) {
			if (other.lastExcerptEditor != null)
				return false;
		} else if (!lastExcerptEditor.equals(other.lastExcerptEditor))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TagWiki [body=");
		builder.append(body);
		builder.append(", bodyLastEditDate=");
		builder.append(bodyLastEditDate);
		builder.append(", excerpt=");
		builder.append(excerpt);
		builder.append(", excerptLastEditDate=");
		builder.append(excerptLastEditDate);
		builder.append(", lastBodyEditor=");
		builder.append(lastBodyEditor);
		builder.append(", lastExcerptEditor=");
		builder.append(lastExcerptEditor);
		builder.append(", tagName=");
		builder.append(tagName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
