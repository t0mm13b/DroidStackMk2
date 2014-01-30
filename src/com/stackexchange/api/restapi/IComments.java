package com.stackexchange.api.restapi;


import java.util.List;

import com.stackexchange.api.objects.Badge;
import com.stackexchange.api.objects.Comment;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/***
 * Each of these messages returns a {@link Comment}
 * 
 * @author t0mm13b
 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for answer_id on Answer objects.
 * @see Comment
 */
public interface IComments {

	/***
	 * Gets all the comments on the site. 
	 * If you're filtering for interesting comments (by score, creation date, etc.) make use of the sort paramter with appropriate min and max values.
	 * If you're looking to query conversations between users, instead use the /users/{ids}/mentioned and /users/{ids}/comments/{toid} methods.
	 * The sorts accepted by this method operate on the follow fields of the comment object: 
	 *  creation – creation_date
	 *  votes – score
	 *  creation is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  This method returns a list of comments.
	 *  
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbAllComments
	 */
	@GET("/comments")
	public void getAllComments(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Comment>> cbAllComments);
	
	@GET("/comments/{ids}")
	public void getCommentsByIds(
			@Path("ids") String commentIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Comment>> cbCommentsByIds);
	
	//=========> LEAVE THE REST ALONE FOR THE MOMENT!
	
	/***
	 * Delete a comment identified by its id. [auth required]
	 * 
	 * @param commentId
	 * @param cbDeleteCommentById
	 * @see Comment
	 */
	@DELETE("/comment/{id}/delete")
	public void deleteCommentById(@Path("id") String commentId, Callback<List<Comment>> cbDeleteCommentById);
	
	/***
	 * Edit a comment identified by its id. [auth required] (comment_id)
	 * 
	 * @param commentId
	 * @param newComment
	 * @param cbEditCommentById
	 * @see Comment
	 */
	@POST("/comment/{id}/edit")
	public void editCommentById(@Path("id") String commentId, String newComment, Callback<Comment> cbEditCommentById);
	
}
