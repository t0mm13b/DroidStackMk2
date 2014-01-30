package com.stackexchange.api.restapi;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import com.stackexchange.api.objects.Info;

/***
 * Returns a collection of statistics about the site.
 * 
 * Data to facilitate per-site customization, discover related sites, and aggregate statistics is all returned by this method.
 * 
 * This data is cached very aggressively, by design. Query sparingly, ideally no more than once an hour.
 * 
 * This method returns an {@link Info} object.
 * 
 * @author t0mm13b
 * @see Info
 */
public interface IInfo {
	/***
	 * Get information about the entire site.
	 * 
	 * @param cbAllEvents
	 * @see Info
	 */
	@GET("/info")
	public void getInfo(
			@Query("site")String siteName, 
			Callback<List<Info>> cbInfo);
}
