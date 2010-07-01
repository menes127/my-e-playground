package com.menes.webapp.ch005;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

/**
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * @author Robin and menes127
 * 
 */
public class MyFilterSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private IRescRoleDao rescRoleDao = null;
	
	public MyFilterSecurityMetadataSource(IRescRoleDao rescRoleDao) {
		this.rescRoleDao = rescRoleDao;
		loadResourceDefine();
	}
	
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = null;
		for(RescRole rescRole : (List<RescRole>) rescRoleDao.findAll()){
			String url = rescRole.getResc();
			String role = rescRole.getRole();
			if(!resourceMap.containsKey(url)){
				atts = new ArrayList<ConfigAttribute>();
			}
			ConfigAttribute ca = new SecurityConfig(role);
			atts.add(ca);
			resourceMap.put(url, atts);
		}
	}

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// guess object is a URL.
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	public void setRescRoleDao(IRescRoleDao rescRoleDao) {
		this.rescRoleDao = rescRoleDao;
	}
}