package com.stackexchange.api.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.PostType;


/***
 * Excerpt: This type represents a suggested edit on a Stack Exchange site.
 * 
 * @author t0mm13b
 * 
 * @see http://api.stackexchange.com/docs/types/suggested-edit
 */
public class SuggestedEdit {
	@SerializedName("approval_date") public long approvalDate = -1;
	@SerializedName("body") public String body = "";
	@SerializedName("comment") public String comment = "";
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("post_type") public PostType postType = PostType.Unknown;
	@SerializedName("proposing_user") public ShallowUser proposingUser;
	@SerializedName("rejection_date") public long rejectionDate = -1;
	@SerializedName("suggested_edit_id") public int suggestedEditId = -1;
	@SerializedName("tags") public List<String> listTags;
	@SerializedName("title") public String title = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (approvalDate ^ (approvalDate >>> 32));
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result
				+ ((listTags == null) ? 0 : listTags.hashCode());
		result = prime * result + postId;
		result = prime * result
				+ ((postType == null) ? 0 : postType.hashCode());
		result = prime * result
				+ ((proposingUser == null) ? 0 : proposingUser.hashCode());
		result = prime * result
				+ (int) (rejectionDate ^ (rejectionDate >>> 32));
		result = prime * result + suggestedEditId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		SuggestedEdit other = (SuggestedEdit) obj;
		if (approvalDate != other.approvalDate)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (listTags == null) {
			if (other.listTags != null)
				return false;
		} else if (!listTags.equals(other.listTags))
			return false;
		if (postId != other.postId)
			return false;
		if (postType != other.postType)
			return false;
		if (proposingUser == null) {
			if (other.proposingUser != null)
				return false;
		} else if (!proposingUser.equals(other.proposingUser))
			return false;
		if (rejectionDate != other.rejectionDate)
			return false;
		if (suggestedEditId != other.suggestedEditId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SuggestedEdit [approvalDate=");
		builder.append(approvalDate);
		builder.append(", body=");
		builder.append(body);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", postType=");
		builder.append(postType);
		builder.append(", mProposingUser=");
		builder.append(proposingUser);
		builder.append(", rejectionDate=");
		builder.append(rejectionDate);
		builder.append(", suggestedEditId=");
		builder.append(suggestedEditId);
		builder.append(", listTags=");
		builder.append(listTags);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}
	
}
