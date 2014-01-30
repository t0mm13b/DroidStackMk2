package com.stackexchange.api.restapi;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import com.stackexchange.api.objects.Privilege;

/***
 * Related to {@link Privilege} on StackExchange site.
 *  
 * @author t0mm13b
 * @see Privilege
 */
public interface IPrivileges {
	/***
	 * Returns the earnable privileges on a site.
	 * Privileges define abilities a user can earn (via reputation) on any Stack Exchange site.
	 * While fairly stable, over time they do change. 
	 * New ones are introduced with new features, and the reputation requirements change as a site matures.
	 * This method returns a list of privileges.
	 * 
	 * @param siteId - {@link Site#apiSiteParameter}
	 * @param pageNumber
	 * @param pageSize
	 * @param cbAllPrivileges
	 */
	@GET("/privileges")
	public void getAllPrivileges(
			@Query("site") String siteId,
			@Query("page") String pageNumber, 
			@Query("pagesize") String pageSize,
			Callback<List<Privilege>> cbAllPrivileges);
}
