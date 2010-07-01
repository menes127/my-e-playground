package com.menes.webapp.ch005;
/**
 * 未使用
 */
import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.RequestKey;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class JdbcFilterSecurityMetadataSource extends JdbcDaoSupport
		implements FilterInvocationSecurityMetadataSource {
	private String resourceQuery;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		Collection<ConfigAttribute> atts = null;
		ConfigAttribute ca = null;
		
		Map<String, String> resourceMap = this.findResources();
		/*
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
		*/
		System.out.println("|||||||||||resourceMap=" + resourceMap);
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Object getObject() {
		return new DefaultFilterInvocationSecurityMetadataSource(this
				.getUrlMatcher(), this.buildRequestMap());
	}

	protected Map<String, String> findResources() {
		ResourceMapping resourceMapping = new ResourceMapping(getDataSource(),
				resourceQuery);

		Map<String, String> resourceMap = new LinkedHashMap<String, String>();

		for (Resource resource : (List<Resource>) resourceMapping.execute()) {
			String url = resource.getUrl();
			String role = resource.getRole();

			if (resourceMap.containsKey(url)) {
				String value = resourceMap.get(url);
				resourceMap.put(url, value + "," + role);
			} else {
				resourceMap.put(url, role);
			}
		}

		return resourceMap;
	}

	protected LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap() {
		LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap = null;
		requestMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = null;
		ConfigAttribute ca = null;

		Map<String, String> resourceMap = this.findResources();

		for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
			RequestKey key = new RequestKey(entry.getKey(), null);
			String role = entry.getValue();
			atts = new ArrayList<ConfigAttribute>();
			String[] roles = role.split(",");
			for (int i = 0; i < roles.length; i++) {
				ca = new SecurityConfig(roles[i]);
				atts.add(ca);
			}
			requestMap.put(key, atts);
		}
		System.out.println("================requestMap" + requestMap);
		return requestMap;
	}

	protected UrlMatcher getUrlMatcher() {
		return new AntUrlPathMatcher();
	}

	public void setResourceQuery(String resourceQuery) {
		this.resourceQuery = resourceQuery;
	}

	private class Resource {
		private String url;
		private String role;

		public Resource(String url, String role) {
			this.url = url;
			this.role = role;
		}

		public String getUrl() {
			return url;
		}

		public String getRole() {
			return role;
		}
	}

	private class ResourceMapping extends MappingSqlQuery<Resource> {
		protected ResourceMapping(DataSource dataSource, String resourceQuery) {
			super(dataSource, resourceQuery);
			compile();
		}

		protected Resource mapRow(ResultSet rs, int rownum) throws SQLException {
			String url = rs.getString(1);
			String role = rs.getString(2);
			Resource resource = new Resource(url, role);
			return resource;
		}
	}
}
