package com.family168.springsecuritybook.ch207;

import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.method.MapBasedMethodDefinitionSource;
import org.springframework.security.intercept.method.MethodDefinitionSource;
import org.springframework.security.intercept.web.DefaultFilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.RequestKey;
import org.springframework.security.util.UrlMatcher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


public class ResourceDetailsBuilder {
    private DataSource dataSource;

    public ResourceDetailsBuilder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public FilterInvocationDefinitionSource createUrlSource(String query,
        UrlMatcher urlMatcher) {
        return new DefaultFilterInvocationDefinitionSource(urlMatcher,
            this.buildRequestMap(query));
    }

    public MethodDefinitionSource createMethodSource(String query) {
        return new MapBasedMethodDefinitionSource(this.buildMethodMap(query));
    }

    protected LinkedHashMap<RequestKey, ConfigAttributeDefinition> buildRequestMap(
        String query) {
        LinkedHashMap<RequestKey, ConfigAttributeDefinition> requestMap = null;
        requestMap = new LinkedHashMap<RequestKey, ConfigAttributeDefinition>();

        ConfigAttributeEditor editor = new ConfigAttributeEditor();

        Map<String, String> resourceMap = this.findResources(query);

        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            RequestKey key = new RequestKey(entry.getKey(), null);
            editor.setAsText(entry.getValue());
            requestMap.put(key, (ConfigAttributeDefinition) editor.getValue());
        }

        return requestMap;
    }

    protected Map<String, ConfigAttributeDefinition> buildMethodMap(
        String query) {
        Map<String, ConfigAttributeDefinition> methodMap = null;
        methodMap = new LinkedHashMap<String, ConfigAttributeDefinition>();

        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        Map<String, String> resourceMap = this.findResources(query);

        for (Map.Entry<String, String> entry : resourceMap.entrySet()) {
            editor.setAsText(entry.getValue());
            methodMap.put(entry.getKey(),
                (ConfigAttributeDefinition) editor.getValue());
        }

        return methodMap;
    }

    protected Map<String, String> findResources(String query) {
        ResourceDetailsMapping resourceDetailsMapping = new ResourceDetailsMapping(dataSource,
                query);

        Map<String, String> resourceMap = new LinkedHashMap<String, String>();
        List<ResourceDetails> resources = resourceDetailsMapping.execute();

        for (ResourceDetails resourceDetails : resources) {
            String name = resourceDetails.getName();
            String role = resourceDetails.getRole();

            if (resourceMap.containsKey(name)) {
                String value = resourceMap.get(name);
                resourceMap.put(name, value + "," + role);
            } else {
                resourceMap.put(name, role);
            }
        }

        return resourceMap;
    }
}
