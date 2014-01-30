package com.stackexchange.api.restapi;

import com.stackexchange.api.objects.Revision;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface IRevisions {
	
	/***
	 * Returns edit revisions identified by ids in {ids}.
	 * {ids} can contain up to 20 semicolon delimited ids, to find ids programatically look for {@link Revision#revisionGuid} on {@link Revision} objects. 
	 * Note that unlike most other id types in the API, revision_guid is a string.
	 * This method returns a list of revisions.
	 * 
	 * @param listIds
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbAllRevisionsByIds
	 * @see Revision
	 */
	@GET("/revisions/{ids}")
	public void getAllRevisionsByIds(
			@Path("ids") String listIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<Revision> cbAllRevisionsByIds);
}
