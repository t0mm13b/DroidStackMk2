package com.stackexchange.api.restapi;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.stackexchange.api.objects.Answer;
import com.stackexchange.api.objects.Badge;
import com.stackexchange.api.objects.Comment;
import com.stackexchange.api.objects.CommonSEWrapper;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;
import com.stackexchange.api.objects.NetworkUser;
import com.stackexchange.api.objects.Notification;
import com.stackexchange.api.objects.Privilege;
import com.stackexchange.api.objects.Question;
import com.stackexchange.api.objects.Reputation;
import com.stackexchange.api.objects.ReputationHistory;
import com.stackexchange.api.objects.SuggestedEdit;
import com.stackexchange.api.objects.Tag;
import com.stackexchange.api.objects.TopTag;
import com.stackexchange.api.objects.User;
import com.stackexchange.api.objects.UserTimeline;
import com.stackexchange.api.objects.WritePermission;

/***
 * All user methods that take an {ids} parameter have a /me equivalent method that takes an access_token instead. 
 * These methods are provided for developer convenience, with the exception of plain /me, which is actually necessary for 
 * discovering which user authenticated to an application.
 * 
 * @author t0mm13b
 *
 */
public interface IUsers {

	/***
	 * Returns all users on a site.
	 * This method returns a list of {@link User}.
	 * The sorts accepted by this method operate on the follow fields of the user object:
	 *  reputation – reputation
	 *  creation – creation_date
	 *  name – display_name
	 *  modified – last_modified_date
	 * reputation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * The inname parameter lets consumers filter the results down to just those users with a certain substring in their display name. 
	 * For example, inname=kevin will return all users with both users named simply "Kevin" or those with Kevin as one of (or part of) 
	 * their names; such as "Kevin Montrose".
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be one of reputation (default), creation, name or modified
	 * @param inName
	 * @param cbAllUsers
	 * @see User
	 */
	@GET("/users")
	public void getAllUsers(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("inname") String inName,
			Callback<User> cbAllUsers);
	
	/***
	 * Gets the users identified in ids in {ids}.
	 * Typically this method will be called to fetch user profiles when you have obtained user ids from some other source, 
	 * such as /questions.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for 
	 * {@link User#userId} on {@link User} or {@link ShallowUser} objects.
	 * The sorts accepted by this method operate on the follow fields of the user object:
	 *  reputation – reputation
	 *  creation – creation_date
	 *  name – display_name
	 *  modified – last_modified_date
	 *  reputation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link User}.
	 *  
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be one of reputation (default), creation, name or modified
	 * @param inName
	 * @param cbUsersById
	 * @see User
	 */
	@GET("/users/{ids}")
	public void getUsersById(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<CommonSEWrapper<User>> cbUsersById);
	
	/***
	 * Returns the answers the users in {ids} have posted.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link ShallowUser#userId}/{@link User#userId} on {@link User} or {@link ShallowUser} objects.
	 * The sorts accepted by this method operate on the follow fields of the answer object:
	 *   activity – last_activity_date
	 *   creation – creation_date
	 *   votes – score
	 *   activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Answer}.
	 *   
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be one of reputation (default), creation, name or modified
	 * @param cbAnswersByUserIds
	 * @see Answer
	 */
	@GET("/users/{ids}/answers")
	public void getAnswersByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Answer> cbAnswersByUserIds);
			
	/***
	 * Get the badges the users in {ids} have earned.
	 * Badge sorts are a tad complicated. 
	 * For the purposes of sorting (and min/max) tag_based is considered to be greater than named.
	 * This means that you can get a list of all tag based badges a user has by passing min=tag_based, and conversely all the 
	 * named badges by passing max=named, with sort=type.
	 * For ranks, bronze is greater than silver which is greater than gold. 
	 * Along with sort=rank, set max=gold for just gold badges, max=silver&min=silver for just silver, and min=bronze for just bronze.
	 * rank is the default sort.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for {@link User#userId} on {@link User}
	 * or {@link ShallowUser} object
	 * This method returns a list of {@link Badge}.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be one of reputation (default), creation, name or modified
	 * @param cbBadgesByUserIds
	 * @see Badge
	 */
	@GET("/users/{ids}/badges")
	public void getBadgesByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Badge> cbBadgesByUserIds);
	
	/***
	 * Get the comments posted by users in {ids}.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for {@link User#userId} on {@link User}
	 * or {@link ShallowUser} object
	 * The sorts accepted by this method operate on the follow fields of the comment object:
	 *   creation – creation_date
	 *   votes – score
	 *   creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * 
	 * This method returns a list of {@link Comment}.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbCommentsByUserIds
	 * @see Comment
	 */
	@GET("/users/{ids}/comments")
	public void getCommentsByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Comment> cbCommentsByUserIds);
	
	/***
	 * Get the comments that the users in {ids} have posted in reply to the single user identified in {toid}.
	 * This method is useful for extracting conversations, especially over time or across multiple posts.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 *  {toid} can contain only 1 id, found in the same manner as those in {ids}.
	 * The sorts accepted by this method operate on the follow fields of the comment object:
	 *  creation – creation_date
	 *  votes – score
	 *  creation is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of comments.
	 *  
	 * @param listIds - {@link Site#apiSiteParameter}
	 * @param singleId
	 * @param siteId
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbCommentsByUserIdsToIds
	 * @see Comment
	 */
	@GET("/users/{ids}/comments/{toid}")
	public void getCommentsByUserIdsToIds(
			@Path("ids") String listIds,
			@Path("toid") String singleId,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Comment> cbCommentsByUserIdsToIds);
	
	/***
	 * Get the questions that users in {ids} have favorited.
	 * This method is effectively a view onto a user's favorites tab.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  added – when the user favorited the question
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
	 *  
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - activity (default), creation, votes or added
	 * @param cbFavoritesByUserIds
	 * @see Question
	 */
	@GET("/users/{ids}/favorites")
	public void getFavoritesByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Question> cbFavoritesByUserIds);
	
	/***
	 * Gets all the comments that the users in {ids} were mentioned in.
	 * Note, to count as a mention the comment must be considered to be "in reply to" a user. 
	 * Most importantly, this means that a comment can only be in reply to a single user.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the comment object:
	 *  creation – creation_date
	 *  votes – score
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Comment}.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be creation (default) or votes
	 * @param cbMentionsInCommentsByUserIds
	 * @see Comment
	 */
	@GET("/users/{ids}/mentioned")
	public void getMentionsInCommentsByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Comment> cbMentionsInCommentsByUserIds);
	
	/***
	 * Returns the privileges a user has.
	 * Applications are encouraged to calculate privileges themselves, without repeated queries to this method. 
	 * A simple check against the results returned by /privileges and {@link User#userType} would be sufficient.
	 * {id} can contain only a single, to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * This method returns a list of {@link Privilege}.
	 * 
	 * @param singleIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbPrivilegesByUserIds
	 * @see Privilege
	 */
	@GET("/users/{ids}/privileges")
	public void getPrivilegesByUserIds(
			@Path("ids") String singleIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<Privilege> cbPrivilegesByUserIds);
	
	/***
	 * Gets the questions asked by the users in {ids}.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of questions.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - activity (default), creation or votes
	 * @param cbQuestionsByUserIds
	 * @see Question
	 */
	@GET("/users/{ids}/questions")
	public void getQuestionsByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Question> cbQuestionsByUserIds);
	
	/***
	 * Gets the questions on which the users in {ids} have active bounties.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 * to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of {@link Question}.
	 *  
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - activity (default), creation or votes
	 * @param cbFeaturedQuestionsByUserIds
	 * @see Question
	 */
	@GET("/users/{ids}/questions/featured")
	public void getFeaturedQuestionsByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Question> cbFeaturedQuestionsByUserIds);
	
	/***
	 * Gets the questions asked by the users in {ids} which have no answers.
	 * Questions returns by this method actually have zero undeleted answers. 
	 * It is completely disjoint /users/{ids}/questions/unanswered and /users/{ids}/questions/unaccepted, 
	 * which only return questions with at least one answer, subject to other contraints.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 * to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - activity (default), creation or votes.
	 * @param cbNoAnswersToQuestionsByUserIds
	 * @see Question
	 */
	@GET("/users/{ids}/questions/no-answers")
	public void getNoAnswersToQuestionsByUserIds(
			@Path("ids") String listIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Question> cbNoAnswersToQuestionsByUserIds);
	
	/***
	 * Gets the questions asked by the users in {ids} which have at least one answer, but no accepted answer.
	 * Questions returned by this method have answers, but the owner has not opted to accept any of them.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 * to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 * activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - activity (default), creation or votes.
	 * @param cbUnacceptedQuestionsByUserIds
	 * @see Question
	 */
	@GET("/users/{ids}/questions/unaccepted")
	public void getUnacceptedQuestionsByUserIds(
			@Path("ids") String listIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Question> cbUnacceptedQuestionsByUserIds);
	
	/***
	 * Gets the questions asked by the users in {ids} which the site consideres unanswered, 
	 *  while still having at least one answer posted.
	 * These rules are subject to change, but currently any question without at least one upvoted or accepted answer is considered unanswered.
	 * To get the set of questions that a user probably considers unanswered, the returned questions should be unioned with those returned by /users/{id}/questions/no-answers. 
	 * These methods are distinct so that truly unanswered (that is, zero posted answers) questions can be easily separated from mearly poorly or inadequately answered ones.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of {@link Question}.
	 *  
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - activity (default), creation or votes.
	 * @param cbUnansweredQuestionsByUserIds
	 * @see Question
	 */
	@GET("/users/{ids}/questions/unanswered")
	public void getUnansweredQuestionsByUserIds(
			@Path("ids") String listIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Question> cbUnansweredQuestionsByUserIds);
	
	/***
	 * Gets a subset of the reputation changes for users in {ids}.
	 * Reputation changes are intentionally scrubbed of some data to make it difficult to correlate votes on particular posts with user reputation changes. 
	 * That being said, this method returns enough data for reasonable display of reputation trends.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * 
	 * This method returns a list of {@link Reputation} objects.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbReputationByUserIds
	 * @see Reputation
 	 *         Alternative: /me/reputation 
	 */
	@GET("/users/{ids}/reputation")
	public void getReputationByUserIds(
			@Path("ids") String listIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<Reputation> cbReputationByUserIds);
	
	/***
	 * Returns users' public reputation history.
	 * This method returns a list of {@link ReputationHistory}.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbReputationHistoryByUserIds
	 * @see ReputationHistory
 	 *         Alternative: /me/reputation-history
	 */
	@GET("/users/{ids}/reputation-history")
	public void getReputationHistoryByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<ReputationHistory> cbReputationHistoryByUserIds);
	
	/***
	 * Returns the suggested edits a users in {ids} have submitted.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the suggested_edit object:
	 *  creation – creation_date
	 *  approval – approval_date Does not return unapproved suggested_edits
	 *  rejection – rejection_date Does not return unrejected suggested_edits
	 *  creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  
	 * This method returns a list of {@link SuggestedEdit}.
	 *  
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - creation (default), approval or rejection
	 * @param cbSuggestedEditsByUserIds
	 * @see SuggestedEdit
	 *         Alternative: /me/suggested-edits
	 */
	@GET("/users/{ids}/suggested-edits")
	public void getSuggestedEditsByUserIds(
			@Path("ids") String listIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<SuggestedEdit> cbSuggestedEditsByUserIds);
	
	/***
	 * Returns the tags the users identified in {ids} have been active in.
	 * This route corresponds roughly to user's stats tab, but does not include tag scores. 
	 * A subset of tag scores are available (on a single user basis) in /users/{id}/top-answer-tags and /users/{id}/top-question-tags.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * The sorts accepted by this method operate on the follow fields of the tag object:
	 *  popular – count
	 *  activity – the creation_date of the last question asked with the tag
	 *  name – name
	 *  popular is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of {@link Tags}.
	 *  
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - popular (default), activity or name
	 * @param cbActiveTagsIdentifiedByUserIds
	 * @see Tag
	 *         Alternative: /me/tags
	 */
	@GET("/users/{ids}/tags")
	public void getActiveTagsIdentifiedByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Tag> cbActiveTagsIdentifiedByUserIds);
	
	/***
	 * Returns the top 30 answers a user has posted in response to questions with the given tags.
	 * {id} can contain a single id, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 *  {tags} is limited to 5 tags, passing more will result in an error.
	 * The sorts accepted by this method operate on the follow fields of the answer object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Answer}.
	 *  
	 * @param singleId
	 * @param listTags
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - sort by activity (default), creation or votes
	 * @param cbTopAnswersInTagsIdentifiedByUserIds
	 */
	@GET("/users/{id}/tags/{tags}/top-answers")
	public void getTopAnswersInTagsIdentifiedByUserIds(
			@Path("id") String singleId,
			@Path("tags") String listTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Answer> cbTopAnswersInTagsIdentifiedByUserIds);
	
	/***
	 * Returns the top 30 questions a user has asked with the given tags.
	 * {id} can contain a single id, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 *  {tags} is limited to 5 tags, passing more will result in an error.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
	 * 
	 * @param singleId
	 * @param listTags
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - sort by activity (default), creation or votes
	 * @param cbTopQuestionsInTagsIdentifiedByUserIds
	 * @see Question
	 */
	@GET("/users/{id}/tags/{tags}/top-questions")
	public void getTopQuestionsInTagsIdentifiedByUserIds(
			@Path("id") String singleId,
			@Path("tags") String listTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<Answer> cbTopQuestionsInTagsIdentifiedByUserIds);
	
	/***
	 * Returns a subset of the actions the users in {ids} have taken on the site.
	 * This method returns users' posts, edits, and earned badges in the order they were accomplished. 
	 * It is possible to filter to just a window of activity using the fromdate and todate parameters.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * This method returns a list of {@link UserTimeline} objects.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbTimelineIdentifiedByUserIds
	 * @see UserTimeline
	 */
	@GET("/users/{ids}/timelines")
	public void getTimelineIdentifiedByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<UserTimeline> cbTimelineIdentifiedByUserIds);
	
	/***
	 * Returns a single user's top tags by answer score.
	 * This a subset of the data returned on a user's tags tab.
	 * {id} can contain a single id, 
	 * to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * This method returns a list of {@link TopTag} objects.
	 * 
	 * @param singleId
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbTopAnswerTagsIdentifiedByUserIds
	 * @see TopTag
	 */
	@GET("/users/{id}/top-answer-tags")
	public void getTopAnswerTagsIdentifiedByUserIds(
			@Path("id") String singleId,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<TopTag> cbTopAnswerTagsIdentifiedByUserIds);
	
	/***
	 * Returns a single user's top tags by question score.
	 * This a subset of the data returned on a user's tags tab.
	 * {id} can contain a single id, 
	 * to find ids programatically look for {@link User#userId} on {@link User} or {@link ShallowUser} object.
	 * This method returns a list of {@link TopTag} objects.
	 * 
	 * @param singleId
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbTopQuestionsTagsIdentifiedByUserIds
	 * @see TopTag
	 */
	@GET("/users/{id}/top-question-tags")
	public void getTopQuestionsTagsIdentifiedByUserIds(
			@Path("id") String singleId,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<TopTag> cbTopQuestionsTagsIdentifiedByUserIds);
	
	/***
	 * Returns the write permissions a user has via the api.
	 * The Stack Exchange API gives users the ability to create, edit, and delete certain types. 
	 * This method returns whether the passed user is capable of performing those actions at all, as well as how many times a day they can.
	 * This method does not consider the user's current quota (ie. if they've already exhausted it for today) nor any additional restrictions on write access, such as editing deleted comments.
	 * This method returns a list of {@link WritePermission}.
	 * 
	 * @param singleId
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbWritePermissionsIdentifiedByUserId
	 * @see WritePermission
	 */
	@GET("/users/{id}/write-permissions")
	public void getWritePermissionsIdentifiedByUserId(
			@Path("id") String singleId,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<WritePermission> cbWritePermissionsIdentifiedByUserId);
	
	/***
	 * Gets those users on a site who can exercise moderation powers.
	 * Note, employees of Stack Exchange Inc. will be returned if they have been granted moderation powers on a site even if they have never been appointed or elected explicitly. 
	 * This method checks abilities, not the manner in which they were obtained.
	 * The sorts accepted by this method operate on the follow fields of the user object:
	 *  reputation – reputation
	 *  creation – creation_date
	 *  name – display_name
	 *  modified – last_modified_date
	 *  reputation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link User}.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - sort by reputation(default), creation, name or modified
	 * @param cbModerators
	 * @see User
	 */
	@GET("/users/moderators")
	public void getModerators(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<User> cbModerators);
	
	/***
	 * Returns those users on a site who both have moderator powers, and were actually elected.
	 * This method excludes Stack Exchange Inc. employees, unless they were actually elected moderators on a site (which can only have happened prior to their employment).
	 * The sorts accepted by this method operate on the follow fields of the user object:
	 *  reputation – reputation
	 *  creation – creation_date
	 *  name – display_name
	 *  modified – last_modified_date
	 *  reputation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link User}.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - sort by reputation(default), creation, name or modified
	 * @param cbModerators
	 */
	@GET("/users/moderators/elected")
	public void getElectedModerators(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<User> cbModerators);
	
	/***
	 * Returns all of a user's associated accounts, given their account_ids in {ids}.
	 * {ids} can contain up to 100 semicolon delimited ids, 
	 * to find ids programatically look for {@link NetworkUser#accountId} on user objects.
	 * This method returns a list of {@link NetworkUser}.
	 * 
	 * @param listIds
	 * @param pageNumber
	 * @param pageSize
	 * @param cbAssociatedAccountsByAccountId
	 * @see NetworkUser
	 */
	@GET("/users/{ids}/associated")
	public void getAssociatedAccountsByAccountId(
			@Path("ids") String listIds,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<NetworkUser> cbAssociatedAccountsByAccountId);
	
	/***
	 * Returns a record of merges that have occurred involving the passed account ids.
	 * This method allows you to take now invalid account ids and find what account they've become, or take currently valid account ids and find which ids were equivalent in the past.
	 * This is most useful when confirming that an account_id is in fact "new" to an application.
	 * Account merges can happen for a wide range of reasons, applications should not make assumptions that merges have particular causes.
	 * Note that accounts are managed at a network level, users on a site may be merged due to an account level merge but there is no guarantee that a merge has an effect on any particular site.
	 * This method returns a list of {@link AccountMerge}.
	 * 
	 * @param listIds
	 * @param pageNumber
	 * @param pageSize
	 * @param cbMergedAccountsByUserIds
	 * @see AccountMerge
	 */
	@GET("/users/{ids}/merged")
	public void getMergedAccountsByUserIds(
			@Path("ids") String listIds,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<NetworkUser> cbMergedAccountsByUserIds);
	
	//--------------------------------------- OAUTH TOKENS REQUIRED HERE -------------------------------------------------------------
	
	/***
	 * Returns a user's notifications.
	 * This method requires an access_token, with a scope containing "read_inbox".
	 * This method returns a list of {@link Notification}.
	 * 
	 * @param listIds
	 * @param siteId
	 * @param pageNumber
	 * @param pageSize
	 * @param accessToken
	 * @param cbNotificationsByUserIds
	 * @see Notification
	 */
	@GET("/users/{ids}/notifications")
	public void getNotificationsByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("access_token") String accessToken,
			Callback<Notification> cbNotificationsByUserIds);
	
	/***
	 * Returns a user's unread notifications.
	 * This method requires an access_token, with a scope containing "read_inbox".
	 * This method returns a list of {@link Notification}.
	 * 
	 * @param listIds
	 * @param siteId
	 * @param pageNumber
	 * @param pageSize
	 * @param accessToken
	 * @param cbUnreadNotificationsByUserIds
	 * @see Notification
	 */
	@GET("/users/{ids}/notifications/unread")
	public void getUnreadNotificationsByUserIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("access_token") String accessToken,
			Callback<Notification> cbUnreadNotificationsByUserIds);
	
	@GET("/me")
	public void getUserByMyself(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("access_token") String accessToken,
			Callback<User> cbUserByMyself);
}
