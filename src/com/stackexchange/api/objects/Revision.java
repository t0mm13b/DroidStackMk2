package com.stackexchange.api.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.PostType;
import com.stackexchange.api.objects.Enums.RevisionType;


/***
 * Excerpt: This type represents that state of a Post at some point in its history.
 * 
 * Note that under some circumstances multiple edits can result in only a single revision.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/revision
 * @see Post
 */
public class Revision {
	@SerializedName("body") public String body = "";
	@SerializedName("comment") public String comment = "";
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("is_rollback") public boolean isRollback = false;
	@SerializedName("last_body") public String lastBody = "";
	@SerializedName("last_tags") public List<String> lastTags;
	@SerializedName("last_title") public String lastTitle = "";
	@SerializedName("post_id") public int postId = -1;
	@SerializedName("post_type") public PostType postType = PostType.Unknown;
	@SerializedName("revision_guid") public String revisionGuid = "";
	@SerializedName("revision_number") public int revisionNumber = -1;
	@SerializedName("revision_type") public RevisionType revisionType = RevisionType.Unknown;
	@SerializedName("set_community_wiki") public boolean isCommunityWiki = false;
	@SerializedName("tags") public List<String> listTags;
	@SerializedName("title") public String title = "";
	@SerializedName("user") public ShallowUser user;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isRollback ? 1231 : 1237);
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result
				+ ((comment == null) ? 0 : comment.hashCode());
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result
				+ ((lastBody == null) ? 0 : lastBody.hashCode());
		result = prime * result
				+ ((lastTags == null) ? 0 : lastTags.hashCode());
		result = prime * result
				+ ((lastTitle == null) ? 0 : lastTitle.hashCode());
		result = prime * result
				+ ((listTags == null) ? 0 : listTags.hashCode());
		result = prime * result + postId;
		result = prime * result
				+ ((postType == null) ? 0 : postType.hashCode());
		result = prime * result
				+ ((revisionGuid == null) ? 0 : revisionGuid.hashCode());
		result = prime * result + revisionNumber;
		result = prime * result
				+ ((revisionType == null) ? 0 : revisionType.hashCode());
		result = prime * result + (isCommunityWiki ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Revision other = (Revision) obj;
		if (isRollback != other.isRollback)
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
		if (lastBody == null) {
			if (other.lastBody != null)
				return false;
		} else if (!lastBody.equals(other.lastBody))
			return false;
		if (lastTags == null) {
			if (other.lastTags != null)
				return false;
		} else if (!lastTags.equals(other.lastTags))
			return false;
		if (lastTitle == null) {
			if (other.lastTitle != null)
				return false;
		} else if (!lastTitle.equals(other.lastTitle))
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
		if (revisionGuid == null) {
			if (other.revisionGuid != null)
				return false;
		} else if (!revisionGuid.equals(other.revisionGuid))
			return false;
		if (revisionNumber != other.revisionNumber)
			return false;
		if (revisionType != other.revisionType)
			return false;
		if (isCommunityWiki != other.isCommunityWiki)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		builder.append("Revision [body=");
		builder.append(body);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", isRollback=");
		builder.append(isRollback);
		builder.append(", lastBody=");
		builder.append(lastBody);
		builder.append(", lastTags=");
		builder.append(lastTags);
		builder.append(", lastTitle=");
		builder.append(lastTitle);
		builder.append(", postId=");
		builder.append(postId);
		builder.append(", postType=");
		builder.append(postType);
		builder.append(", revisionGuid=");
		builder.append(revisionGuid);
		builder.append(", revisionNumber=");
		builder.append(revisionNumber);
		builder.append(", revisionType=");
		builder.append(revisionType);
		builder.append(", isCommunityWiki=");
		builder.append(isCommunityWiki);
		builder.append(", listTags=");
		builder.append(listTags);
		builder.append(", title=");
		builder.append(title);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
}
