package com.stackexchange.api.restapi;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;
import com.stackexchange.api.objects.SuggestedEdit;

public interface ISuggestedEdits {

	/***
	 * Returns all the suggested edits in the systems.
	 * This method returns a list of suggested-edits.
	 * The sorts accepted by this method operate on the follow fields of the suggested_edit object:
	 *  creation – creation_date
	 *  approval – approval_date - Does not return unapproved suggested_edits
	 *  rejection – rejection_date - Does not return unrejected suggested_edits
	 *  creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - can be one of creation (default), approval, or rejection
	 * @param cbAllSuggestedEdits
	 * @see SuggestedEdit
	 */
	@GET("/suggested-edits")
	public void getAllSuggestedEdits(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<SuggestedEdit> cbAllSuggestedEdits);
	
	/***
	 * Returns suggested edits identified in ids.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for {@link SuggestedEdit#suggestedEditId} on {@link SuggestedEdit} objects.
	 * The sorts accepted by this method operate on the follow fields of the suggested_edit object:
	 *  creation – creation_date
	 *  approval – approval_date - Does not return unapproved suggested_edits
	 *  rejection – rejection_date - Does not return unrejected suggested_edits
	 *  creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of suggested-edits.
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
	 * @param typeOfSort - can be one of creation (default), approval, or rejection
	 * @param cbSuggestedEditsByIds
	 * @see SuggestedEdit
	 */
	@GET("/suggested-edits/{ids}")
	public void getSuggestedEditsById(
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
			Callback<SuggestedEdit> cbSuggestedEditsByIds);
			
}
