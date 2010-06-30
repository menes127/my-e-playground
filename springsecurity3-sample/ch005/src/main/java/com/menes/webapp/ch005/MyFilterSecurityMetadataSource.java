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
 * @author Robin
 * 
 */
public class MyFilterSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private IRescRoleDao rescRoleDao = null;
	
	public MyFilterSecurityMetadataSource(IRescRoleDao rescRoleDao) {
		this.rescRoleDao = rescRoleDao;
		loadResourceDefine();
	}
	
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = null;
		ConfigAttribute ca = null;
		
		List<RescRole> rescRoleList = rescRoleDao.findAll();
		String old_role = null;
		
		for(RescRole rescRole : rescRoleList){
			String new_role = rescRole.getRole();
			
			if(old_role == null || !new_role.equals(old_role)){
				atts = new ArrayList<ConfigAttribute>();
				ca = new SecurityConfig(new_role);
				atts.add(ca);
				resourceMap.put(rescRole.getResc(), atts);
			}else{
				resourceMap.put(rescRole.getResc(), atts);
			}
			
			old_role = new_role;
			System.out.println("|||||||||||new_role,getResc=" + new_role + "," + rescRole.getResc());
		}
		
		
		for(RescRole rescRole : rescRoleList){
			System.out.println("======================="+rescRole.getResc() + "," + rescRole.getRole());
		}
		/*
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
		atts.add(ca);
		resourceMap.put("/", atts);
		resourceMap.put("/index.jsp", atts);
		
		Collection<ConfigAttribute> atts2 = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca2 = new SecurityConfig("ROLE_ADMIN");
		atts2.add(ca2);
		resourceMap.put("/index.jsp", atts2);
		resourceMap.put("/admin.jsp", atts2);
		*/
		
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