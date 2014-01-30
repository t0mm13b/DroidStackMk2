package com.stackexchange.api.restapi;


import java.util.List;

import com.stackexchange.api.objects.Badge;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/***
 * Each of these messages returns a {@link Badge}
 * @author t0mm13b
 * @see Badge
 * @see http://api.stackexchange.com/docs/vectors
 */
public interface IBadges {
	/***
 	 * Get all badges on the site, in alphabetical order.
	 * Returns all the badges in the system.
	 * Badge sorts are a tad complicated. 
	 * For the purposes of sorting (and min/max) tag_based is considered to be greater than named.
	 * This means that you can get a list of all tag based badges by passing min=tag_based, and conversely all the named badges by passing max=named, with sort=type.
	 * For ranks, bronze is greater than silver which is greater than gold. Along with sort=rank, set max=gold for just gold badges, max=silver&min=silver for just silver, and min=bronze for just bronze.
	 * rank is the default sort.
	 * This method returns a list of badges.
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
	 * @param cbAllBadges
	 */
	@GET("/badges")
	public void getAllBadges(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Badge>> cbAllBadges);
	
	/***
	 * Gets the badges identified in id.
	 * Note that badge ids are not constant across sites, and thus should be looked up via the /badges method. 
	 * A badge id on a single site is, however, guaranteed to be stable.
	 * Badge sorts are a tad complicated. For the purposes of sorting (and min/max) tag_based is considered to be greater than named.
	 * This means that you can get a list of all tag based badges by passing min=tag_based, and conversely all the named badges by passing max=named, with sort=type.
	 * For ranks, bronze is greater than silver which is greater than gold. 
	 * Along with sort=rank, set max=gold for just gold badges, max=silver&min=silver for just silver, and min=bronze for just bronze.
	 * rank is the default sort.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for badge_id on badge objects.
	 * This method returns a list of badges.
	 * 
	 * @param badgeIds - {@link Badge#badgeId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbAllBadgesById
	 */
	@GET("/badges/{ids}")
	public void getAllBadgesById(
			@Path("ids") String badgeIds,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Badge>> cbAllBadgesById);
		
	/***
	 * Get all non-tagged-based badges in alphabetical order.
	 * Gets all explicitly named badges in the system.
	 * A named badged stands in opposition to a tag-based badge. These are referred to as general badges on the sites themselves. 
	 * For the rank sort, bronze is greater than silver which is greater than gold. 
	 * Along with sort=rank, set max=gold for just gold badges, max=silver&min=silver for just silver, and min=bronze for just bronze.
	 * rank is the default sort.
	 * This method returns a list of badges.
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
	 * @param cbAllNamedBadges
	 */
	@GET("/badges/name")
	public void getAllNamedBadges(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Badge>> cbAllNamedBadges);

	
	/***
	 * Get badges recently awarded on the site.
	 * Returns recently awarded badges in the system. 
	 * As these badges have been awarded, they will have the badge.user property set.
	 * This method returns a list of badges.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbRecentAwardedBadges
	 */
	@GET("/badges/recipients")
	public void getRecentAwardedBadges(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<List<Badge>> cbRecentAwardedBadges);
	
	/***
	 * Returns recently awarded badges in the system, constrained to a certain set of badges.
	 * As these badges have been awarded, they will have the badge.user property set.
	 * {ids} can contain up to 100 semicolon delimited ids, to find ids programatically look for badge_id on badge objects.
	 * This method returns a list of badges.
	 * 
	 * @param badgeIds - {@link Badge#badgeId}
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param cbRecentRecipientsOfGivenBadges
	 */
	@GET("/badges/{ids}/recipients")
	public void getRecentRecipientsOfGivenBadges(
			@Path("ids") String badgeIds, 
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			Callback<List<Badge>> cbRecentRecipientsOfGivenBadges);

	/***
	 * Returns the badges that are awarded for participation in specific tags.
	 * For the rank sort, bronze is greater than silver which is greater than gold. 
	 * Along with sort=rank, set max=gold for just gold badges, max=silver&min=silver for just silver, and min=bronze for just bronze.
	 * rank is the default sort.
	 * This method returns a list of badges.
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
	 * @param cbBadgesByTag
	 */
	@GET("/badges/tags")
	public void getBadgesByTag(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<List<Badge>> cbBadgesByTag);
}
