package com.stackexchange.api.restapi;

import java.util.List;

import com.stackexchange.api.objects.Event;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/***
 * Events on StackExchange.
 * 
 * @author t0mm13b
 * @see Event
 */
public interface IEvents {
	/***
	 * Returns a stream of events that have occurred on the site.
	 * 
	 * The API considers the following "events":
	 *  posting a question
	 *  posting an answer
	 *  posting a comment
	 *  editing a post
	 *  creating a user
	 * Events are only accessible for 15 minutes after they occurred, and by default only events in the last 5 minutes are returned. 
	 * You can specify the age of the oldest event returned by setting the since parameter.
	 * It is advised that developers batch events by ids and make as few subsequent requests to other methods as possible.
	 * This method returns a list of events.
	 * 
	 * NOTICE: This is accessible only via SSL!!!
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param accessToken
	 * @param since
	 * @param cbAllEvents
	 * @see Event
	 */
	@GET("/events")
	public void getAllEvents(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("since") String since,
			@Query("access_token") String accessToken,
			Callback<List<Event>> cbAllEvents);
}
