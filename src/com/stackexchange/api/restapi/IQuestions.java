package com.stackexchange.api.restapi;

import java.util.List;

import com.stackexchange.api.objects.Answer;
import com.stackexchange.api.objects.Comment;
import com.stackexchange.api.objects.CommonSEWrapper;
import com.stackexchange.api.objects.Question;
import com.stackexchange.api.objects.QuestionTimeline;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/***
 * Related to {@link Question} 's on StackExchange site
 * 
 * @author t0mm13b
 * @see Question
 */
public interface IQuestions {
	/***
	 * Gets all the questions on the site.
	 * This method allows you make fairly flexible queries across the entire corpus of questions on a site. 
	 * For example: 
	 * getting all questions asked in the the week of Jan 1st 2011 with scores of 10 or more is a single query with 
	 *  parameters sort=votes&min=10&fromdate=1293840000&todate=1294444800.
	 *  To constrain questions returned to those with a set of tags, use the tagged parameter with a semi-colon delimited list of tags. 
	 *  This is an and contraint, passing tagged=c;java will return only those questions with both tags. 
	 * As such, passing more than 5 tags will always return zero results.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date 
	 *  votes – score 
	 *  hot – by the formula ordering the hot tab (Does not accept min or max)
	 *  week – by the formula ordering the week tab (Does not accept min or max)
	 *  month – by the formula ordering the month tab (Does not accept min or max)
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity(default),creation, votes, hot, week, month.
	 * @param tagged - restricted to 5 tags
	 * @param cbAllQuestions
	 * @see Question
	 */
	@GET("/questions")
	public void getAllQuestions(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("tagged") String tagged,
			Callback<List<Question>> cbAllQuestions);
	
	/***
	 * Returns the questions identified in {ids}.
	 * This is most useful for fetching fresh data when maintaining a cache of question ids, or polling for changes.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link Question#qquestionId} on {@link Question} objects.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of questions.
	 *  
	 * @param questionIds - {@link Question#questionId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbAllQuestionsByIds
	 */
	@GET("/questions/{ids}")
	public void getAllQuestionsByIds(
			@Path("ids") String questionIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Question>> cbAllQuestionsByIds);
	
	/***
	 * Gets the answers to a set of questions identified in id.
	 * This method is most useful if you have a set of interesting questions, and you wish to obtain all of their answers at once 
	 *  or if you are polling for new or updates answers (in conjunction with sort=activity).
	 *  {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for {@link Question#questionId} on {@link Question} objects.
	 *  The sorts accepted by this method operate on the follow fields of the answer object:
	 *   activity – last_activity_date
	 *   creation – creation_date
	 *   votes – score
	 *   activity is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of answers.
	 *  
	 * @param questionIds - {@link Question#questionId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbAllAnswersToQuestionsByIds
	 * @see Answer
	 */
	@GET("/questions/{ids}/answers")
	public void getAllAnswersToQuestionsByIds(
			@Path("ids") String questionIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Answer>> cbAllAnswersToQuestionsByIds);
	
	/***
	 * Gets the comments on a question.
	 * If you know that you have an question id and need the comments, use this method. 
	 * If you know you have a answer id, use /answers/{ids}/comments. 
	 * If you are unsure, use /posts/{ids}/comments.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link Question#questionId} on question objects.
	 * The sorts accepted by this method operate on the follow fields of the comment object:
	 *  creation – creation_date
	 *  votes – score
	 *  creation is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of {@link Comment}.
	 *  
	 * @param questionIds - {@link Question#questionId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - either creation(default) or votes
	 * @param cbCommentsOnQuestionsByIds
	 * @see Comment
	 */
	@GET("/questions/{ids}/comments")
	public void getCommentsOnQuestionsByIds(
			@Path("ids") String questionIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Comment>> cbCommentsOnQuestionsByIds);
	
	/***
	 * Gets questions which link to those questions identified in {ids}.
	 * This method only considers questions that are linked within a site, and will never return questions from another Stack Exchange site.
	 * A question is considered "linked" when it explicitly includes a hyperlink to another question, there are no other heuristics.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 * to find ids programatically look for {@link Question#questionId} on {@link Question} objects.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  rank – a priority sort by site applies, subject to change at any time
	 *  Does not accept min or max
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of questions.
	 *  
	 * @param questionIds - {@link Question#questionId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity, creation, votes or rank.
	 * @param cbLinkedQuestionsByIds
	 * @see Question
	 */
	@GET("/questions/{ids}/linked")
	public void getLinkedQuestionsByIds(
			@Path("ids") String questionIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Question>> cbLinkedQuestionsByIds);
	
	/***
	 * Returns questions that the site considers related to those identified in {ids}.
	 * The algorithm for determining if questions are related is not documented, and subject to change at any time. 
	 * Futhermore, these values are very heavily cached, and may not update immediately after a question has been editted. 
	 * It is also not guaranteed that a question will be considered related to any number (even non-zero) of questions, 
	 *  and a consumer should be able to handle a variable number of returned questions.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for {@link Question#questionId} on {@link Question} objects.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  rank – a priority sort by site applies, subject to change at any time
	 *  Does not accept min or max
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of questions.
	 *  
	 * @param questionIds - {@link Question#questionId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity (default), creation, votes or rank
	 * @param cbRelatedQuestionsByIds
	 * @see Question
	 */
	@GET("/questions/{ids}/related")
	public void getRelatedQuestionsByIds(
			@Path("ids") String questionIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Question>> cbRelatedQuestionsByIds);
	
	/***
	 * Returns a subset of the events that have happened to the questions identified in id.
	 * This provides data similar to that found on a question's timeline page.
	 * Voting data is scrubbed to deter inferencing of voter identity.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for {@link Question#questionId} on {@link Question} objects.
	 * This method returns a list of question timeline events.
	 * 
	 * @param questionIds - {@link Question#questionId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbTimelineQuestionsByIds
	 * @see QuestionTimeline
	 */
	@GET("/questions/{ids}/timeline")
	public void getTimelineQuestionsByIds(
			@Path("ids") String questionIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<List<QuestionTimeline>> cbTimelineQuestionsByIds);
	
	/***
	 * Returns all the questions with active bounties in the system.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of questions.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity (default), creation or votes
	 * @param cbFeaturedQuestions
	 * @see Question
	 */
	@GET("/questions/featured")
	public void getFeaturedQuestions(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<CommonSEWrapper<Question>> cbFeaturedQuestions);
	
	/***
	 * Returns questions the site considers to be unanswered.
	 * Note that just because a question has an answer, that does not mean it is considered answered. 
	 * While the rules are subject to change, at this time a question must have at least one upvoted answer to be considered answered.
	 * To constrain questions returned to those with a set of tags, use the tagged parameter with a semi-colon delimited list of tags. 
	 * This is an and contraint, passing tagged=c;java will return only those questions with both tags. 
	 * As such, passing more than 5 tags will always return zero results.
	 * 
	 * Compare with /questions/no-answers.
	 * 
	 * This method corresponds roughly with the unanswered tab.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of questions.
	 *  
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity (default), creation or votes
	 * @param tagged
	 * @param cbUnansweredQuestions
	 * @see Question
	 */
	@GET("/questions/unanswered")
	public void getUnansweredQuestions(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("tagged") String tagged,
			Callback<List<Question>> cbUnansweredQuestions);
	
	/***
	 * Returns questions which have received no answers.
	 * Compare with /questions/unanswered which mearly returns questions that the sites consider insufficiently well answered.
	 * This method corresponds roughly with the this site tab.
	 * To constrain questions returned to those with a set of tags, use the tagged parameter with a semi-colon delimited list of tags. 
	 * This is an and contraint, passing tagged=c;java will return only those questions with both tags. 
	 * As such, passing more than 5 tags will always return zero results.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date 
	 *  votes – score
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of questions.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity (default), creation or votes
	 * @param tagged
	 * @param cbQuestionsWithNoAnswers
	 * @see Question
	 */
	@GET("/questions/no-answers")
	public void getQuestionsWithNoAnswers(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("tagged") String tagged,
			Callback<List<Question>> cbQuestionsWithNoAnswers);
}
