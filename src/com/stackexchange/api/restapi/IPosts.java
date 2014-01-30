package com.stackexchange.api.restapi;

import java.util.List;

import com.stackexchange.api.objects.Comment;
import com.stackexchange.api.objects.Post;
import com.stackexchange.api.objects.Revision;
import com.stackexchange.api.objects.SuggestedEdit;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/***
 * Gets {@link Post} on stackexchange site.
 * 
 * @author t0mm13b
 * @see Post
 */
public interface IPosts {

	/***
	 * Fetches all posts (questions and answers) on the site.
	 * In many ways this method is the union of /questions and /answers, returning both sets of data albeit only the common fields.
	 * Most applications should use the question or answer specific methods, but /posts is available for those rare cases where any activity is of intereset. 
	 * Examples of such queries would be: "all posts on Jan. 1st 2011" or "top 10 posts by score of all time".
	 * The sorts accepted by this method operate on the follow fields of the post object:
	 * activity – last_activity_date
	 * creation – creation_date
	 * votes – score
	 * activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of posts.
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
	 * @param cbAllPosts
	 * @see Post
	 */
	@GET("/posts")
	public void getAllPosts(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Post>> cbAllPosts);
	
	/***
	 * Fetches a set of posts by ids. 
	 * This method is meant for grabbing an object when unsure whether an id identifies a question or an answer. 
	 * This is most common with user entered data.
	 * {ids} can contain up to 100 semicolon delimited ids.
	 *  to find ids programatically look for {@link Post#postId}, {@link Answer#answerId}, or {@link Question#questionId} 
	 *    on post, answer, and question objects respectively.
	 *  The sorts accepted by this method operate on the follow fields of the post object:
	 *   activity – last_activity_date
	 *   creation – creation_date
	 *   votes – score
	 *   activity is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of posts.
	 *  
	 * @param postIds - {@link Post#postId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be activity (default), creation or votes
	 * @param cbAllPostsByIds
	 * @see Post
	 */
	@GET("/posts/{ids}")
	public void getAllPostsByIds(
			@Path("ids") String postIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Post>> cbAllPostsByIds);
	
	/***
	 * Gets the comments on the posts identified in ids, regardless of the type of the posts.
	 * This method is meant for cases when you are unsure of the type of the post id provided. 
	 * Generally, this would be due to obtaining the post id directly from a user.
	 * {ids} can contain up to 100 semicolon delimited ids.
	 *  to find ids programatically look for {@link Post#postId}, {@link Answer#answerId}, or {@link Question#questionId} 
	 *    on post, answer, and question objects respectively.
	 * The sorts accepted by this method operate on the follow fields of the comment object:
	 *  creation – creation_date
	 *  votes – score
	 *  creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of comments.
	 * 
	 * @param postIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbCommentsOnPostsByIds
	 * @see Comment
	 */
	@GET("/posts/{ids}/comments")
	public void getCommentsOnPostsByIds(
			@Path("ids") String postIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Comment>> cbCommentsOnPostsByIds);
	
	/***
	 * Create a new comment on the post identified by id. [auth required]
	 * 
	 * @param postId
	 * @param cbAddCommentToPostById
	 */
	@PUT("/posts/{id}/comments/add")
	public void addCommentToPostById(@Field("id") String postId, Callback<Post> cbAddCommentToPostById);
	
	/***
	 * Returns edit revisions for the posts identified in ids.
	 * {ids} can contain up to 100 semicolon delimited ids.
	 *  to find ids programatically look for {@link Post#postId}, {@link Answer#answerId}, or {@link Question#questionId} 
	 *    on post, answer, and question objects respectively.
	 * This method returns a list of revisions.
	 * 
	 * @param postIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbRevisionsOnPostsByIds
	 * @see Revision
	 */
	@GET("/posts/{ids}/revisions")
	public void getRevisionsOnPostsByIds(
			@Path("ids") String postIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<List<Revision>> cbRevisionsOnPostsByIds);
	
	/***
	 * Returns suggsted edits on the posts identified in ids.
	 *  creation – creation_date
	 *  approval – approval_date
	 *  rejection – rejection_date
	 *  creation is the default sort.
	 *  {ids} can contain up to 100 semicolon delimited ids, 
	 *  to find ids programatically look for {@link Post#postId}, {@link Answer#answerId}, or {@link Question#questionId} 
	 *    on post, answer, and question objects respectively.
	 * This method returns a list of suggested-edits.
	 *  
	 * @param postIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be one of creation, approval or rejection
	 * @param cbSuggestedEditsByIds
	 * @see SuggestedEdit
	 */
	@GET("/posts/{ids}/suggested-edits")
	public void getSuggestedEditsByIds(
			@Path("ids") String postIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<SuggestedEdit>> cbSuggestedEditsByIds);
}
