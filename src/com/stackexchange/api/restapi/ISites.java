package com.stackexchange.api.restapi;

import com.stackexchange.api.objects.CommonSEWrapper;
import com.stackexchange.api.objects.Site;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ISites {

	
	/***
	 * Returns all sites in the network.
	 * This method allows for discovery of new sites, and changes to existing ones. 
	 * Be aware that unlike normal API methods, this method should be fetched very infrequently, 
	 *  it is very unusual for these values to change more than once on any given day. 
	 * It is suggested that you cache its return for at least one day, unless your app encounters evidence that it has changed (such as from the /info method).
	 * The pagesize parameter for this method is unbounded, in acknowledgement that for many applications repeatedly fetching from /sites would complicate start-up tasks needlessly.
	 * This method returns a list of sites.
	 * 
	 * @param filterName can be left blank for now, it defaults to 'default' filter.
	 * @param page A numeric value indicating page
	 * @param pageSize A numeric value indicating number of sites to be returned.
	 * @param cbAllSites Callback method
	 * @see Site
	 */
	@GET("/sites")
	public void getAllSites(
			@Query("filter") String filterName, 
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize, 
			Callback<CommonSEWrapper<Site>> cbAllSites);
	
	@GET("/sites")
	public CommonSEWrapper<Site> getAllSitesSync(
			@Query("filter") String filterName, 
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize);
}