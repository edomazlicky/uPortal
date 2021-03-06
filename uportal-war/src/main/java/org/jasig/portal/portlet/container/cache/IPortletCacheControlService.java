/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/**
 * 
 */
package org.jasig.portal.portlet.container.cache;

import javax.portlet.CacheControl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.portal.portlet.om.IPortletWindowId;

/**
 * Interface defining mechanism for retrieving {@link CacheControl}s.
 * 
 * @author Nicholas Blair
 * @version $Id$
 */
public interface IPortletCacheControlService {

	/**
	 * 
	 * @return the maximum size of cached portlet output, in bytes
	 */
	int getCacheSizeThreshold();
	/**
	 * 
	 * @param portletWindowId
	 * @return the {@link CacheControl} for the specified window id
	 */
	CacheControl getPortletRenderCacheControl(IPortletWindowId portletWindowId, HttpServletRequest httpRequest);
	
	/**
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @param httpResponse
	 * @return the {@link CacheControl} for the specifie window id
	 */
	CacheControl getPortletResourceCacheControl(IPortletWindowId portletWindowId, HttpServletRequest httpRequest, HttpServletResponse httpResponse);
	/**
	 * Get the {@link CachedPortletData} for the portlet window id and render request, if there is any.
	 * This method internally will determine if the data is stored in a public or private scoped cache.
	 * If their is no portlet data for the windowId and request, this method returns null.
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @return the {@link CachedPortletData} for this render request, or null if the portlet data was not cached
	 */
	CachedPortletData getCachedPortletRenderOutput(IPortletWindowId portletWindowId, HttpServletRequest httpRequest);
	
	/**
	 *  Get the {@link CachedPortletData} for the portlet window id and resource request, if there is any.
	 * This method internally will determine if the data is stored in a public or private scoped cache.
	 * If their is no portlet data for the windowId and request, this method returns null.
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @return  the {@link CachedPortletData} for this resource request, or null if the portlet data was not cached
	 */
	CachedPortletData getCachedPortletResourceOutput(IPortletWindowId portletWindowId, HttpServletRequest httpRequest);
	
	/**
	 * This method checks the {@link CacheControl} to determine if the output should be captured
	 * for caching.
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @return true if the {@link CacheControl} indicates the output should be cached
	 */
	boolean shouldOutputBeCached(CacheControl cacheControl);
	
	/**
	 * Store the output of a render request in the cache for the portlet and request. This method internally will determine
	 * if the content needs to be stored in a public or private scoped cache.
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @param content
	 * @param cacheControl
	 */
	void cachePortletRenderOutput(IPortletWindowId portletWindowId, HttpServletRequest httpRequest, String content, CacheControl cacheControl);
	
	/**
	 * Store the output of a resource request in the cache for the portlet and request. This method internally will determine
	 * if the content needs to be stored in a public or private scoped cache.
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @param content
	 * @param contentType
	 * @param cacheControl
	 */
	void cachePortletResourceOutput(IPortletWindowId portletWindowId, HttpServletRequest httpRequest, CachedPortletData cachedPortletData, CacheControl cacheControl);
	
	/**
	 * Purge any {@link CachedPortletData} for the portlet.
	 * Generally triggered on any Action or Event Request.
	 * 
	 * @param portletWindowId
	 * @param httpRequest
	 * @param cacheControl
	 * @return true if the element was found in the cache and removed, false if otherwise
	 */
	boolean purgeCachedPortletData(IPortletWindowId portletWindowId,
			HttpServletRequest httpRequest, CacheControl cacheControl);
}
