package com.stackexchange.api.objects;

import com.google.gson.annotations.SerializedName;

/***
 * Excerpt: This type describes a user's score and activity in a given Tag.
 * 
 * @author t0mm13b
 * 
 * @see http://api.stackexchange.com/docs/types/top-tag
 * @see Tag
 */
public class TopTag {
	@SerializedName("answer_count") public int answerCount = -1;
	@SerializedName("answer_score") public int answerScore = -1;
	@SerializedName("question_count") public int questionCount = -1;
	@SerializedName("question_score") public int questionScore = -1;
	@SerializedName("tag_name") public String tagName = "";
	@SerializedName("user_id") public int userId = -1;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + answerCount;
		result = prime * result + answerScore;
		result = prime * result + questionCount;
		result = prime * result + questionScore;
		result = prime * result
				+ ((tagName == null) ? 0 : tagName.hashCode());
		result = prime * result + userId;
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
		TopTag other = (TopTag) obj;
		if (answerCount != other.answerCount)
			return false;
		if (answerScore != other.answerScore)
			return false;
		if (questionCount != other.questionCount)
			return false;
		if (questionScore != other.questionScore)
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TopTag [answerCount=");
		builder.append(answerCount);
		builder.append(", answerScore=");
		builder.append(answerScore);
		builder.append(", questionCount=");
		builder.append(questionCount);
		builder.append(", questionScore=");
		builder.append(questionScore);
		builder.append(", tagName=");
		builder.append(tagName);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
}
