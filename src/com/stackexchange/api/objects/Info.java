package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type describes a site in the Stack Exchange network.
 * 
 * @author t0mm13b
 *
 * @see http://api.stackexchange.com/docs/types/info
 */
public class Info {
	@SerializedName("answers_per_minute") public float ansPerMin = -1.0F;
	@SerializedName("api_revision") public String apiRevision = "";
	@SerializedName("badges_per_minute") public float badgesPerMin = -1.0F;
	@SerializedName("new_active_users") public int newActiveUsersPerMin = -1;
	@SerializedName("questions_per_minute") public float questionsPerMin = -1.0F;
	@SerializedName("site") public Site selectedSite;
	@SerializedName("total_accepted") public int totalAccepted = -1;
	@SerializedName("total_answers") public int totalAnswers = -1;
	@SerializedName("total_badges") public int totalBadges = -1;
	@SerializedName("total_comments") public int totalComments = -1;
	@SerializedName("total_questions") public int totalQuestions = -1;
	@SerializedName("total_unanswered") public int totalUnAnswered = -1;
	@SerializedName("total_users") public int totalUsers = -1;
	@SerializedName("total_votes") public int totalVotes = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(ansPerMin);
		result = prime * result
				+ ((apiRevision == null) ? 0 : apiRevision.hashCode());
		result = prime * result + Float.floatToIntBits(badgesPerMin);
		result = prime * result + newActiveUsersPerMin;
		result = prime * result + Float.floatToIntBits(questionsPerMin);
		result = prime * result + ((selectedSite == null) ? 0 : selectedSite.hashCode());
		result = prime * result + totalAccepted;
		result = prime * result + totalAnswers;
		result = prime * result + totalBadges;
		result = prime * result + totalComments;
		result = prime * result + totalQuestions;
		result = prime * result + totalUnAnswered;
		result = prime * result + totalUsers;
		result = prime * result + totalVotes;
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
		Info other = (Info) obj;
		if (Float.floatToIntBits(ansPerMin) != Float
				.floatToIntBits(other.ansPerMin))
			return false;
		if (apiRevision == null) {
			if (other.apiRevision != null)
				return false;
		} else if (!apiRevision.equals(other.apiRevision))
			return false;
		if (Float.floatToIntBits(badgesPerMin) != Float
				.floatToIntBits(other.badgesPerMin))
			return false;
		if (newActiveUsersPerMin != other.newActiveUsersPerMin)
			return false;
		if (Float.floatToIntBits(questionsPerMin) != Float
				.floatToIntBits(other.questionsPerMin))
			return false;
		if (selectedSite == null) {
			if (other.selectedSite != null)
				return false;
		} else if (!selectedSite.equals(other.selectedSite))
			return false;
		if (totalAccepted != other.totalAccepted)
			return false;
		if (totalAnswers != other.totalAnswers)
			return false;
		if (totalBadges != other.totalBadges)
			return false;
		if (totalComments != other.totalComments)
			return false;
		if (totalQuestions != other.totalQuestions)
			return false;
		if (totalUnAnswered != other.totalUnAnswered)
			return false;
		if (totalUsers != other.totalUsers)
			return false;
		if (totalVotes != other.totalVotes)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Info [ansPerMin=");
		builder.append(ansPerMin);
		builder.append(", apiRevision=");
		builder.append(apiRevision);
		builder.append(", badgesPerMin=");
		builder.append(badgesPerMin);
		builder.append(", newActiveUsersPerMin=");
		builder.append(newActiveUsersPerMin);
		builder.append(", questionsPerMin=");
		builder.append(questionsPerMin);
		builder.append(", selectedSite=");
		builder.append(selectedSite);
		builder.append(", totalAccepted=");
		builder.append(totalAccepted);
		builder.append(", totalAnswers=");
		builder.append(totalAnswers);
		builder.append(", totalBadges=");
		builder.append(totalBadges);
		builder.append(", totalComments=");
		builder.append(totalComments);
		builder.append(", totalQuestions=");
		builder.append(totalQuestions);
		builder.append(", totalUnAnswered=");
		builder.append(totalUnAnswered);
		builder.append(", totalUsers=");
		builder.append(totalUsers);
		builder.append(", totalVotes=");
		builder.append(totalVotes);
		builder.append("]");
		return builder.toString();
	}
	
}
