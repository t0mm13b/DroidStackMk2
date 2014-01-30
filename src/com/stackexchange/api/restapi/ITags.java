package com.stackexchange.api.restapi;

import com.stackexchange.api.objects.Enums.Period;
import com.stackexchange.api.objects.Question;
import com.stackexchange.api.objects.Tag;
import com.stackexchange.api.objects.Enums.SortOrder;
import com.stackexchange.api.objects.Enums.SortType;
import com.stackexchange.api.objects.TagScore;
import com.stackexchange.api.objects.TagSynonym;
import com.stackexchange.api.objects.TagWiki;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ITags {
	/***
	 * Returns the tags found on a site.
	 * The inname parameter lets a consumer filter down to tags that contain a certain substring. 
	 * For example, inname=own would return both "download" and "owner" amongst others.
	 * This method returns a list of tags.
	 * The sorts accepted by this method operate on the follow fields of the tag object:
	 *  popular – count
	 *  activity – the creation_date of the last question asked with the tag
	 *  name – name
	 *  popular is the default sort.
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
	 * @param typeOfSort - popular (default), activity or name
	 * @param inName
	 * @param cbAllTags
	 * @see Tag
	 */
	@GET("/tags")
	public void getAllTags(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("inname") String inName,
			Callback<Tag> cbAllTags);
	
	/***
	 * Returns tag objects representing the tags in {tags} found on the site.
	 * This method diverges from the standard naming patterns to avoid to conflicting with existing methods, 
	 *  due to the free form nature of tag names.
	 *  This method returns a list of tags.
	 *  The sorts accepted by this method operate on the follow fields of the tag object:
	 *   popular – count
	 *   activity – the creation_date of the last question asked with the tag
	 *   name – name
	 *   popular is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  
	 * @param listTags
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - popular (default), activity or name
	 * @param tagsList
	 * @param cbSelectedTagInfo
	 * @see Tag
	 */
	@GET("/tags/{tags}/info")
	public void getSelectedTagInfo(
			@Path("tags") String listTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("tags") String tagsList,
			Callback<Tag> cbSelectedTagInfo);
	
	/***
	 * Returns the tags found on a site that only moderators can use.
	 * The inname parameter lets a consumer filter down to tags that contain a certain substring. 
	 * For example, inname=own would return both "download" and "owner" amongst others.
	 * This method returns a list of tags.
	 * The sorts accepted by this method operate on the follow fields of the tag object:
	 *  popular – count
	 *  activity – the creation_date of the last question asked with the tag
	 *  name – name
	 *  popular is the default sort.
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
	 * @param typeOfSort - popular (default), activity or name
	 * @param inName
	 * @param cbModeratorOnlyTags
	 * @see Tag
	 */
	@GET("/tags/moderator-only")
	public void getModeratorOnlyTags(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("inname") String inName,
			Callback<Tag> cbModeratorOnlyTags);
	
	/***
	 * Returns the tags found on a site that fulfill required tag constraints on questions.
	 * The inname parameter lets a consumer filter down to tags that contain a certain substring. 
	 * For example, inname=own would return both "download" and "owner" amongst others.
	 * This method returns a list of tags.
	 * The sorts accepted by this method operate on the follow fields of the tag object:
	 *  popular – count
	 *  activity – the creation_date of the last question asked with the tag
	 *  name – name
	 *  popular is the default sort.
	 *  It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 *  
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - popular (default), activity or name
	 * @param inName
	 * @param cbRequiredTags
	 * @see Tag
	 */
	@GET("/tags/required")
	public void getRequiredTags(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			@Query("inname") String inName,
			Callback<Tag> cbRequiredTags);

	/***
	 * Returns all tag synonyms found a site.
	 * When searching for synonyms of specific tags, it is better to use /tags/{tags}/synonyms over this method.
	 * The sorts accepted by this method operate on the follow fields of the tag_synonym object:
	 *  creation – creation_date
	 *  applied – applied_count
	 *  activity – last_applied_date
	 *  creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of tag_synonyms.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort - popular (default), activity or name
	 * @param cbSynonymsTags
	 * @see TagSynonym
	 */
	@GET("/tags/synonyms")
	public void getSynonymsTags(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<TagSynonym> cbSynonymsTags);

	/***
	 * Returns the frequently asked questions for the given set of tags in {tags}.
	 * For a question to be returned, it must have all the tags in {tags} and be considered "frequently asked". 
	 * The exact algorithm for determining whether a question is considered a FAQ is subject to change at any time.
	 * {tags} can contain up to 5 individual tags per request.
	 * This method returns a list of questions.
	 * 
	 * @param listTags
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbFAQsBySelectedTags
	 * @see Question
	 */
	@GET("/tags/{tags}/faq")
	public void getFAQsBySelectedTags(
			@Path("tags") String listTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<Question> cbFAQsBySelectedTags);
	
	/***
	 * Returns the tags that are most related to those in {tags}.
	 * Including multiple tags in {tags} is equivalent to asking for "tags related to tag #1 and tag #2" not "tags related to tag #1 or tag #2".
	 * count on tag objects returned is the number of question with that tag that also share all those in {tags}.
	 * {tags} can contain up to 4 individual tags per request.
	 * This method returns a list of tags.
	 * 
	 * @param listTags
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbRelatedBySelectedTags
	 * @see Tag
	 */
	@GET("/tags/{tags}/related")
	public void getRelatedBySelectedTags(
			@Path("tags") String listTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<Tag> cbRelatedBySelectedTags);

	/***
	 * Gets all the synonyms that point to the tags identified in {tags}. 
	 * If you're looking to discover all the tag synonyms on a site, use the /tags/synonyms methods instead of call this method on all tags.
	 * {tags} can contain up to 20 individual tags per request.
	 * The sorts accepted by this method operate on the follow fields of the tag_synonym object:
	 *  creation – creation_date
	 *  applied – applied_count
	 *  activity – last_applied_date
	 *  creation is the default sort.
	 * It is possible to create moderately complex queries using sort, min, max, fromdate, and todate.
	 * This method returns a list of tag synonyms.
	 * 
	 * @param listTags
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param fromDate
	 * @param toDate
	 * @param orderOfSort
	 * @param minDate
	 * @param maxDate
	 * @param typeOfSort
	 * @param cbSynonymsBySelectedTags
	 * @see TagSynonym
	 */
	@GET("/tags/{tags}/synonyms")
	public void getSynonymsBySelectedTags(
			@Path("tags") String listTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			@Query("fromdate") String fromDate,
			@Query("todate") String toDate,
			@Query("order") SortOrder orderOfSort,
			@Query("min") String minDate,
			@Query("max") String maxDate,
			@Query("sort") SortType typeOfSort,
			Callback<TagSynonym> cbSynonymsBySelectedTags);
	
	/***
	 * Returns the top 30 answerers active in a single tag, of either all-time or the last 30 days.
	 * This is a view onto the data presented on the tag info page on the sites.
	 * This method returns a list of tag score objects.
	 * 
	 * @param singleTag
	 * @param typeOfPeriod - either all_time or month
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbTopAnswerersBySingleTag
	 * @see TagScore
	 */
	@GET("/tags/{tag}/top-answerers/{period}")
	public void getTopAnswerersBySingleTag(
			@Path("tag") String singleTag,
			@Query("period") Period typeOfPeriod,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<TagScore> cbTopAnswerersBySingleTag);
	
	/***
	 * Returns the top 30 askers active in a single tag, of either all-time or the last 30 days.
	 * This is a view onto the data presented on the tag info page on the sites.
	 * This method returns a list of tag score objects.
	 * 
	 * @param singleTag
	 * @param typeOfPeriod - either all_time or month
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbTopAskersBySingleTag
	 * @see TagScore
	 */
	@GET("/tags/{tag}/top-askers/{period}")
	public void getTopAskersBySingleTag(
			@Path("tag") String singleTag,
			@Query("period") Period typeOfPeriod,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<TagScore> cbTopAskersBySingleTag);
	
	/***
	 * Returns the wikis that go with the given set of tags in {tags}.
	 * Be aware that not all tags have wikis.
	 * {tags} can contain up to 20 individual tags per request.
	 * This method returns a list of tag wikis.
	 * 
	 * @param selectedTags
	 * @param siteId
	 * @param pageNumber
	 * @param pageSize
	 * @param cbWikiBySelectedTags
	 * @see TagWiki
	 */
	@GET("/tags/{tags}/wiki")
	public void getWikiBySelectedTags(
			@Path("tags") String selectedTags,
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<TagWiki> cbWikiBySelectedTags);
}
