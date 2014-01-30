package com.stackexchange.api.restapi;

import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;
import com.stackexchange.api.objects.Question;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ISearch {
	
	/***
	 * Searches a site for any questions which fit the given criteria.
	 * This method is intentionally quite limited. 
	 * For more general searching, you should use a proper internet search engine restricted to the domain of the site in question.
	 * At least one of tagged or intitle must be set on this method. 
	 * nottagged is only used if tagged is also set, for performance reasons.
	 * tagged and nottagged are semi-colon delimited list of tags. 
	 * At least 1 tag in tagged will be on each returned question if it is passed, 
	 *  making it the OR equivalent of the AND version of tagged on /questions.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  relevance – matches the relevance tab on the site itself
	 *  Does not accept min or max
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
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
	 * @param taggedList
	 * @param notTaggedList
	 * @param inTitleList
	 * @param cbSearch
	 * @see Question
	 */
	@GET("/search")
	public void getSearch(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("tagged") String taggedList,
			@Query("nottagged") String notTaggedList,
			@Query("intitle") String inTitleList,
			Callback<Question> cbSearch);

	/***
	 * Searches a site for any questions which fit the given criteria.
	 * Search criteria are expressed using the following parameters:
	 *  q - a free form text parameter, will match all question properties based on an undocumented algorithm.
	 *  accepted - true to return only questions with accepted answers, false to return only those without. Omit to elide constraint.
	 *  answers - the minimum number of answers returned questions must have.
	 *  body - text which must appear in returned questions' bodies.
	 *  closed - true to return only closed questions, false to return only open ones. Omit to elide constraint.
	 *  migrated - true to return only questions migrated away from a site, false to return only those not. Omit to elide constraint.
	 *  notice - true to return only questions with post notices, false to return only those without. Omit to elide constraint.
	 *  nottagged - a semicolon delimited list of tags, none of which will be present on returned questions.
	 *  tagged - a semicolon delimited list of tags, of which at least one will be present on all returned questions.
	 *  title - text which must appear in returned questions' titles.
	 *  user - the id of the user who must own the questions returned.
	 *  url - a url which must be contained in a post, may include a wildcard.
	 *  views - the minimum number of views returned questions must have.
	 *  wiki - true to return only community wiki questions, false to return only non-community wiki ones. Omit to elide constraint.
	 *  At least one additional paramter must be set if nottagged is set, for performance reasons.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  relevance – matches the relevance tab on the site itself
	 *  Does not accept min or max
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of {@link Question}.
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
	 * @param qList
	 * @param isAccepted
	 * @param answersList
	 * @param bodyList
	 * @param isClosed
	 * @param isMigrated
	 * @param isNotice
	 * @param notTaggedList
	 * @param taggedList
	 * @param titleList
	 * @param userList
	 * @param urlList
	 * @param viewsList
	 * @param isWiki
	 * @param cbAdvancedSearch
	 */
	@GET("/search/advanced")
	public void getAdvancedSearch(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("q") String qList,
			@Query("accepted") boolean isAccepted,
			@Query("answers") String answersList,
			@Query("body") String bodyList,
			@Query("closed") boolean isClosed,
			@Query("migrated") boolean isMigrated,
			@Query("notice") boolean isNotice,
			@Query("nottagged") String notTaggedList,
			@Query("tagged") String taggedList,
			@Query("title") String titleList,
			@Query("user") String userList,
			@Query("url") String urlList,
			@Query("views") String viewsList,
			@Query("wiki") boolean isWiki,
			Callback<Question> cbAdvancedSearch);

	/***
	 * Returns questions which are similar to a hypothetical one based on a title and tag combination.
	 * This method is roughly equivalent to a site's related questions suggestion on the ask page.
	 * This method is useful for correlating data outside of a Stack Exchange site with similar content within one.
	 * Note that title must always be passed as a parameter. tagged and nottagged are optional, semi-colon delimited lists of tags.
	 * If tagged is passed it is treated as a preference, there is no guarantee that questions returned will have any of those tags. 
	 * nottagged is treated as a requirement, no questions will be returned with those tags.
	 * The sorts accepted by this method operate on the follow fields of the question object:
	 *  activity – last_activity_date
	 *  creation – creation_date
	 *  votes – score
	 *  relevance – order by "how similar" the questions are, most likely candidate first with a descending order
	 *  Does not accept min or max
	 *  activity is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of questions.
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
	 * @param taggedList
	 * @param notTaggedList
	 * @param titleList
	 * @param cbSimilar
	 */
	@GET("/similar")
	public void getSimilar(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("tagged") String taggedList,
			@Query("nottagged") String notTaggedList,
			@Query("title") String titleList,
			Callback<Question> cbSimilar);	
}
