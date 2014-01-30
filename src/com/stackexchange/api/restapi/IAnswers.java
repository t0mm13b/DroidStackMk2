package com.stackexchange.api.restapi;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

import com.stackexchange.api.objects.Answer;
import com.stackexchange.api.objects.Comment;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;

/***
 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for answerId on Answer objects.
 * @author t0mm13b
 * @see Answer
 * @see http://api.stackexchange.com/docs/vectors
 */
public interface IAnswers {
	/***
	 * Gets the list of Answers indicated by site
	 * 
	 * @param siteId - - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbGetAllAnswers
	 */
	public void getAllAnswers(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Answer>> cbGetAllAnswers);
	
	/***
	 * Gets the set of answers identified by ids.
	 * This is meant for batch fetching of questions. 
	 * A useful trick to poll for updates is to sort by activity, with a minimum date of the last time you polled.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for answer_id on answer objects.
	 * 
	 * @param listIds - {@link Answer#answerId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - Default is activity, can be activity, creation or votes!
	 * @param cbAnswersById
	 */
	@GET("/answers/{ids}")
	public void getAnswersById(
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
			Callback<List<Answer>> cbAnswersById);
	
	/***
	 * Gets the comments on a set of answers.
	 * If you know that you have an answer id and need the comments, use this method. 
	 * If you know you have a question id, use /questions/{id}/comments. 
	 * If you are unsure, use /posts/{id}/comments.
	 * 
	 * @param listIds - {@link Answer#answerId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - Either Creation or Votes only!
	 * @param cbCommentsOnAnswersById
	 */
	@GET("/answers/{ids}/comments")
	public void getCommentsOnAnswersById(
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
			Callback<List<Comment>> cbCommentsOnAnswersById);
}
