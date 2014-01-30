package com.stackexchange.api.objects;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.UserType;


/***
 * User object is the "main".
 * 
 * Excerpt: This type describes a user on a Stack Exchange site. 
 * There are a number of different user types returned by the Stack Exchange API, depending on the method. 
 * Others include {@link ShallowUser} and {@link NetworkUser}.
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/user
 */
public class User {
	@SerializedName("about_me") public String aboutMe = "";
	@SerializedName("accept_rate") public int acceptRate = -1;
	@SerializedName("account_id") public int accountId = -1;
	@SerializedName("age") public int age = -1;
	@SerializedName("answer_count") public int answerCount = -1;
	@SerializedName("badge_counts") public int[] badgeCount = new int[3];
	@SerializedName("creation_date") public long creationDate = -1;
	@SerializedName("display_name") public String displayName = "";
	@SerializedName("down_vote_count") public int downvoteCount = -1;
	@SerializedName("is_employee") public boolean isEmployee = false;
	@SerializedName("last_access_date") public long lastAccessDate = -1;
	@SerializedName("last_modified_date") public long lastModifiedDate = -1;
	@SerializedName("link") public String link = "";
	@SerializedName("location") public String location = "";
	@SerializedName("profile_image") public String profileImage = "";
	@SerializedName("question_count") public int questionCount = -1;
	@SerializedName("reputation") public int reputation = -1;
	@SerializedName("reputation_change_day") public int repChangeDay = -1;
	@SerializedName("reputation_change_month") public int repChangeMonth = -1;
	@SerializedName("reputation_change_quarter") public int repChangeQuarter = -1;
	@SerializedName("reputation_change_week") public int repChangeWeek = -1;
	@SerializedName("reputation_change_year") public int repChangeYear = -1;
	@SerializedName("timed_penalty_date") public long timedPenaltyDate = -1;
	@SerializedName("up_vote_count") public int upvoteCount = -1;
	@SerializedName("user_id") public int userId = -1;
	@SerializedName("user_type") public UserType userType = UserType.Unknown;
	@SerializedName("view_count") public int viewCount = -1;
	@SerializedName("website_url") public String websiteUrl = "";

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + acceptRate;
		result = prime * result + accountId;
		result = prime * result + age;
		result = prime * result + answerCount;
		result = prime * result + Arrays.hashCode(badgeCount);
		result = prime * result
				+ (int) (creationDate ^ (creationDate >>> 32));
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + downvoteCount;
		result = prime * result + (isEmployee ? 1231 : 1237);
		result = prime * result
				+ (int) (lastAccessDate ^ (lastAccessDate >>> 32));
		result = prime * result
				+ (int) (lastModifiedDate ^ (lastModifiedDate >>> 32));
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((profileImage == null) ? 0 : profileImage.hashCode());
		result = prime * result + questionCount;
		result = prime * result + repChangeDay;
		result = prime * result + repChangeMonth;
		result = prime * result + repChangeQuarter;
		result = prime * result + repChangeWeek;
		result = prime * result + repChangeYear;
		result = prime * result + reputation;
		result = prime * result
				+ (int) (timedPenaltyDate ^ (timedPenaltyDate >>> 32));
		result = prime * result + upvoteCount;
		result = prime * result + userId;
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + viewCount;
		result = prime * result
				+ ((websiteUrl == null) ? 0 : websiteUrl.hashCode());
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
		User other = (User) obj;
		if (aboutMe == null) {
			if (other.aboutMe != null)
				return false;
		} else if (!aboutMe.equals(other.aboutMe))
			return false;
		if (acceptRate != other.acceptRate)
			return false;
		if (accountId != other.accountId)
			return false;
		if (age != other.age)
			return false;
		if (answerCount != other.answerCount)
			return false;
		if (!Arrays.equals(badgeCount, other.badgeCount))
			return false;
		if (creationDate != other.creationDate)
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (downvoteCount != other.downvoteCount)
			return false;
		if (isEmployee != other.isEmployee)
			return false;
		if (lastAccessDate != other.lastAccessDate)
			return false;
		if (lastModifiedDate != other.lastModifiedDate)
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		if (questionCount != other.questionCount)
			return false;
		if (repChangeDay != other.repChangeDay)
			return false;
		if (repChangeMonth != other.repChangeMonth)
			return false;
		if (repChangeQuarter != other.repChangeQuarter)
			return false;
		if (repChangeWeek != other.repChangeWeek)
			return false;
		if (repChangeYear != other.repChangeYear)
			return false;
		if (reputation != other.reputation)
			return false;
		if (timedPenaltyDate != other.timedPenaltyDate)
			return false;
		if (upvoteCount != other.upvoteCount)
			return false;
		if (userId != other.userId)
			return false;
		if (userType != other.userType)
			return false;
		if (viewCount != other.viewCount)
			return false;
		if (websiteUrl == null) {
			if (other.websiteUrl != null)
				return false;
		} else if (!websiteUrl.equals(other.websiteUrl))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [aboutMe=");
		builder.append(aboutMe);
		builder.append(", acceptRate=");
		builder.append(acceptRate);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", age=");
		builder.append(age);
		builder.append(", answerCount=");
		builder.append(answerCount);
		builder.append(", badgeCount=");
		builder.append(Arrays.toString(badgeCount));
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", downvoteCount=");
		builder.append(downvoteCount);
		builder.append(", isEmployee=");
		builder.append(isEmployee);
		builder.append(", lastAccessDate=");
		builder.append(lastAccessDate);
		builder.append(", lastModifiedDate=");
		builder.append(lastModifiedDate);
		builder.append(", link=");
		builder.append(link);
		builder.append(", location=");
		builder.append(location);
		builder.append(", profileImage=");
		builder.append(profileImage);
		builder.append(", questionCount=");
		builder.append(questionCount);
		builder.append(", reputation=");
		builder.append(reputation);
		builder.append(", repChangeDay=");
		builder.append(repChangeDay);
		builder.append(", repChangeMonth=");
		builder.append(repChangeMonth);
		builder.append(", repChangeQuarter=");
		builder.append(repChangeQuarter);
		builder.append(", repChangeWeek=");
		builder.append(repChangeWeek);
		builder.append(", repChangeYear=");
		builder.append(repChangeYear);
		builder.append(", timedPenaltyDate=");
		builder.append(timedPenaltyDate);
		builder.append(", upvoteCount=");
		builder.append(upvoteCount);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", viewCount=");
		builder.append(viewCount);
		builder.append(", websiteUrl=");
		builder.append(websiteUrl);
		builder.append("]");
		return builder.toString();
	}
	
}
