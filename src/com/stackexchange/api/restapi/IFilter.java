package com.stackexchange.api.restapi;

import com.stackexchange.api.objects.CommonSEWrapper;
import com.stackexchange.api.objects.Filter;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface IFilter {
	/***
	 * Creates a new filter given a list of includes, excludes, a base filter, and whether or not this filter should be "unsafe".
	 * Filter "safety" is defined as follows. 
	 * Any string returned as a result of an API call with a safe filter will be inline-able into HTML without script-injection concerns. 
	 * That is to say, no additional sanitizing (encoding, HTML tag stripping, etc.) will be necessary on returned strings. 
	 * Applications that wish to handle sanitizing themselves should create an unsafe filter. 
	 * All filters are safe by default, under the assumption that double-encoding bugs are more desirable than script injections.
	 * If no base filter is specified, the default filter is assumed. 
	 * When building a filter from scratch, the none built-in filter is useful.
	 * When the size of the parameters being sent to this method grows to large, problems can occur. 
	 * This method will accept POST requests to mitigate this.
	 * It is not expected that many applications will call this method at runtime, filters should be pre-calculated and "baked in" in the common cases. 
	 * Furthermore, there are a number of built-in filters which cover common use cases. This method returns a single filter.
	 * 
	 * @param strInclude Semicolon delimited string
	 * @param strExclude
	 * @param blnUnsafe
	 * @param cbAddFilter
	 * @see Filter
	 */
	@PUT("/filters/create")
	public void addFilter(@Query("include") String strInclude, @Query("exclude") String strExclude, @Query("base") String baseFilterName, @Query("unsafe") boolean blnUnsafe, Callback<Filter> cbAddFilter);
	
	@GET("/filters/{filters}")
	public void getFilter(@Path("filters") String filterName, Callback<CommonSEWrapper<Filter>> cbGetFilters);
}
