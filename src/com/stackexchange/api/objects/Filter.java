package com.stackexchange.api.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.stackexchange.api.objects.Enums.FilterType;

/***
 * Excerpt: This type describes a filter on the Stack Exchange API.
 * When passing a filter to methods in the API, it should be referred to by name alone.
 * 
 * @author t0mm13b
 * @see https://api.stackexchange.com/docs/types/filter
 */
public class Filter {
	@SerializedName("filter") public String filter;
	@SerializedName("filter_type") public FilterType filterType = FilterType.Unknown;
	@SerializedName("included_fields") public List<String> listFilters;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filter == null) ? 0 : filter.hashCode());
		result = prime * result
				+ ((listFilters == null) ? 0 : listFilters.hashCode());
		result = prime * result
				+ ((filterType == null) ? 0 : filterType.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		if (filter == null) {
			if (other.filter != null)
				return false;
		} else if (!filter.equals(other.filter))
			return false;
		if (listFilters == null) {
			if (other.listFilters != null)
				return false;
		} else if (!listFilters.equals(other.listFilters))
			return false;
		if (filterType != other.filterType)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Filter [filter=");
		builder.append(filter);
		builder.append(", filterType=");
		builder.append(filterType);
		builder.append(", listFilters=");
		builder.append(listFilters);
		builder.append("]");
		return builder.toString();
	}
	
}
