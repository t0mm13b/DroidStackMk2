package com.stackexchange.api.objects;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type represents a question on one of the Stack Exchange sites, such as this famous RegEx question. 
 * This type is heavily inspired by the question page itself, and can optionally return comments and answers accordingly.
 * 
 * @author t0mm13b
 * 
 * @see http://api.stackexchange.com/docs/types/question
 */
public class Question implements Parcelable{
	@SerializedName("accepted_answer_id") public int acceptedAnswer = -1;
	@SerializedName("answer_count") public int answerCount = -1;
	@SerializedName("answers") public List<Answer> listAnswers;
	@SerializedName("body") public String body = "";
	@SerializedName("bounty_amount") public int bountyAmount = -1;
	@SerializedName("bounty_closes_date") public long bountyClosesDate = -1;
	@SerializedName("close_vote_count") public int closeVoteCount = -1;
	@SerializedName("closed_date") public long closedDate = -1;
	@SerializedName("closed_reason") public String closedReason = "";
	@SerializedName("comments") public List<Comment> listComments;
	@SerializedName("community_owned_date") public long communityOwnedDate = -1;
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("delete_vote_count") public int deleteVoteCount = -1;
	@SerializedName("down_vote_count") public int downvoteCount = -1;
	@SerializedName("favorite_count") public int favouriteCount = -1;
	@SerializedName("is_answered") public boolean isAnswered = false;
	@SerializedName("last_activity_date") public long lastActivityDate = -1;
	@SerializedName("last_edit_date") public long lastEditDate = -1;
	@SerializedName("link") public String link = "";
	@SerializedName("locked_date") public long lockedDate = -1;
	@SerializedName("migrated_from") public MigrationInfo migratedFrom;
	@SerializedName("migrated_to") public MigrationInfo migratedTo;
	@SerializedName("notice") public Notice notice;
	@SerializedName("owner") public ShallowUser owner;
	@SerializedName("protected_date") public long protectedDate = -1;
	@SerializedName("question_id") public int questionId;
	@SerializedName("reopen_vote_count") public int reopenVoteCount = -1;
	@SerializedName("score") public int score = -1;
	@SerializedName("tags") public List<String> listTags;
	@SerializedName("title") public String title = "";
	@SerializedName("up_vote_count") public int upvoteCount = -1;
	@SerializedName("view_count") public int viewCount = -1;

	private Question(Parcel in){
		readFromParcel(in);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAnswered ? 1231 : 1237);
		result = prime * result + acceptedAnswer;
		result = prime * result + answerCount;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + bountyAmount;
		result = prime * result
				+ (int) (bountyClosesDate ^ (bountyClosesDate >>> 32));
		result = prime * result + closeVoteCount;
		result = prime * result + (int) (closedDate ^ (closedDate >>> 32));
		result = prime * result
				+ ((closedReason == null) ? 0 : closedReason.hashCode());
		result = prime * result
				+ (int) (communityOwnedDate ^ (communityOwnedDate >>> 32));
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result + deleteVoteCount;
		result = prime * result + downvoteCount;
		result = prime * result + favouriteCount;
		result = prime * result
				+ (int) (lastActivityDate ^ (lastActivityDate >>> 32));
		result = prime * result
				+ (int) (lastEditDate ^ (lastEditDate >>> 32));
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((listAnswers == null) ? 0 : listAnswers.hashCode());
		result = prime * result
				+ ((listComments == null) ? 0 : listComments.hashCode());
		result = prime * result + (int) (lockedDate ^ (lockedDate >>> 32));
		result = prime * result
				+ ((migratedFrom == null) ? 0 : migratedFrom.hashCode());
		result = prime * result
				+ ((migratedTo == null) ? 0 : migratedTo.hashCode());
		result = prime * result + ((notice == null) ? 0 : notice.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result
				+ (int) (protectedDate ^ (protectedDate >>> 32));
		result = prime * result + questionId;
		result = prime * result + reopenVoteCount;
		result = prime * result + score;
		result = prime * result + ((listTags == null) ? 0 : listTags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + upvoteCount;
		result = prime * result + viewCount;
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
		Question other = (Question) obj;
		if (isAnswered != other.isAnswered)
			return false;
		if (acceptedAnswer != other.acceptedAnswer)
			return false;
		if (answerCount != other.answerCount)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (bountyAmount != other.bountyAmount)
			return false;
		if (bountyClosesDate != other.bountyClosesDate)
			return false;
		if (closeVoteCount != other.closeVoteCount)
			return false;
		if (closedDate != other.closedDate)
			return false;
		if (closedReason == null) {
			if (other.closedReason != null)
				return false;
		} else if (!closedReason.equals(other.closedReason))
			return false;
		if (communityOwnedDate != other.communityOwnedDate)
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (deleteVoteCount != other.deleteVoteCount)
			return false;
		if (downvoteCount != other.downvoteCount)
			return false;
		if (favouriteCount != other.favouriteCount)
			return false;
		if (lastActivityDate != other.lastActivityDate)
			return false;
		if (lastEditDate != other.lastEditDate)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (listAnswers == null) {
			if (other.listAnswers != null)
				return false;
		} else if (!listAnswers.equals(other.listAnswers))
			return false;
		if (listComments == null) {
			if (other.listComments != null)
				return false;
		} else if (!listComments.equals(other.listComments))
			return false;
		if (lockedDate != other.lockedDate)
			return false;
		if (migratedFrom == null) {
			if (other.migratedFrom != null)
				return false;
		} else if (!migratedFrom.equals(other.migratedFrom))
			return false;
		if (migratedTo == null) {
			if (other.migratedTo != null)
				return false;
		} else if (!migratedTo.equals(other.migratedTo))
			return false;
		if (notice == null) {
			if (other.notice != null)
				return false;
		} else if (!notice.equals(other.notice))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (protectedDate != other.protectedDate)
			return false;
		if (questionId != other.questionId)
			return false;
		if (reopenVoteCount != other.reopenVoteCount)
			return false;
		if (score != other.score)
			return false;
		if (listTags == null) {
			if (other.listTags != null)
				return false;
		} else if (!listTags.equals(other.listTags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (upvoteCount != other.upvoteCount)
			return false;
		if (viewCount != other.viewCount)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Question [acceptedAnswer=");
		builder.append(acceptedAnswer);
		builder.append(", answerCount=");
		builder.append(answerCount);
		builder.append(", listAnswers=");
		builder.append(listAnswers);
		builder.append(", body=");
		builder.append(body);
		builder.append(", bountyAmount=");
		builder.append(bountyAmount);
		builder.append(", bountyClosesDate=");
		builder.append(bountyClosesDate);
		builder.append(", closeVoteCount=");
		builder.append(closeVoteCount);
		builder.append(", closedDate=");
		builder.append(closedDate);
		builder.append(", closedReason=");
		builder.append(closedReason);
		builder.append(", listComments=");
		builder.append(listComments);
		builder.append(", communityOwnedDate=");
		builder.append(communityOwnedDate);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", deleteVoteCount=");
		builder.append(deleteVoteCount);
		builder.append(", downvoteCount=");
		builder.append(downvoteCount);
		builder.append(", favouriteCount=");
		builder.append(favouriteCount);
		builder.append(", isAnswered=");
		builder.append(isAnswered);
		builder.append(", lastActivityDate=");
		builder.append(lastActivityDate);
		builder.append(", lastEditDate=");
		builder.append(lastEditDate);
		builder.append(", link=");
		builder.append(link);
		builder.append(", lockedDate=");
		builder.append(lockedDate);
		builder.append(", migratedFrom=");
		builder.append(migratedFrom);
		builder.append(", migratedTo=");
		builder.append(migratedTo);
		builder.append(", notice=");
		builder.append(notice);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", protectedDate=");
		builder.append(protectedDate);
		builder.append(", questionId=");
		builder.append(questionId);
		builder.append(", reopenVoteCount=");
		builder.append(reopenVoteCount);
		builder.append(", score=");
		builder.append(score);
		builder.append(", listTags=");
		builder.append(listTags);
		builder.append(", title=");
		builder.append(title);
		builder.append(", upvoteCount=");
		builder.append(upvoteCount);
		builder.append(", viewCount=");
		builder.append(viewCount);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		List<Answer> tmpListAnswers = new ArrayList<Answer>();
		List<String> tmpListStrings = new ArrayList<String>();
		List<Comment> tmpListComments = new ArrayList<Comment>();
		dest.writeInt(acceptedAnswer);
		dest.writeInt(answerCount);
		dest.writeTypedList((listAnswers == null) ? tmpListAnswers : listAnswers);
		dest.writeString(body);
		dest.writeInt(bountyAmount);
		dest.writeLong(bountyClosesDate);
		dest.writeInt(closeVoteCount);
		dest.writeLong(closedDate);
		dest.writeString(closedReason);
		dest.writeTypedList((listComments == null) ? tmpListComments : listComments);
		dest.writeLong(communityOwnedDate);
		dest.writeLong(creationDate);
		dest.writeInt(deleteVoteCount);
		dest.writeInt(downvoteCount);
		dest.writeInt(favouriteCount);
		dest.writeInt((isAnswered == true) ? 1 : 0);
		dest.writeLong(lastActivityDate);
		dest.writeLong(lastEditDate);
		dest.writeString(link);
		dest.writeLong(lockedDate);
		dest.writeParcelable(migratedFrom, flags);
		dest.writeParcelable(migratedTo, flags);
		dest.writeParcelable(notice, flags);
		dest.writeParcelable(owner, flags);
		dest.writeLong(protectedDate);
		dest.writeInt(questionId);
		dest.writeInt(reopenVoteCount);
		dest.writeInt(score);
		dest.writeStringList((listTags == null) ? tmpListStrings : listTags);
		dest.writeString(title);
		dest.writeInt(upvoteCount);
		dest.writeInt(viewCount);
	}
	private void readFromParcel(Parcel src){
		acceptedAnswer = src.readInt();
		answerCount = src.readInt();
		src.readTypedList(listAnswers, Answer.CREATOR);
		body = src.readString();
		bountyAmount = src.readInt();
		bountyClosesDate = src.readLong();
		closeVoteCount = src.readInt();
		closedDate = src.readLong();
		closedReason = src.readString();
		src.readTypedList(listComments, Comment.CREATOR);
		communityOwnedDate = src.readLong();
		creationDate = src.readLong();
		deleteVoteCount = src.readInt();
		downvoteCount = src.readInt();
		favouriteCount = src.readInt();
		isAnswered = (src.readInt() == 1 ? true : false);
		lastActivityDate = src.readLong();
		lastEditDate = src.readLong();
		link = src.readString();
		lockedDate = src.readLong();
		migratedFrom = src.readParcelable(MigrationInfo.class.getClassLoader());
		migratedTo = src.readParcelable(MigrationInfo.class.getClassLoader());
		notice = src.readParcelable(Notice.class.getClassLoader());
		owner = src.readParcelable(ShallowUser.class.getClassLoader());
		protectedDate = src.readLong();
		questionId = src.readInt();
		reopenVoteCount = src.readInt();
		score = src.readInt();
		src.readStringList(listTags);
		title = src.readString();
		upvoteCount = src.readInt();
		viewCount = src.readInt();
	}
	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {

		@Override
		public Question createFromParcel(Parcel source) {
			return new Question(source);
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};
	
}
