package com.family168.springsecuritybook.ch207;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.security.intercept.method.DelegatingMethodDefinitionSource;
import org.springframework.security.intercept.method.MethodDefinitionSource;
import org.springframework.security.intercept.method.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterSecurityInterceptor;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class ResourceDetailsMonitor implements InitializingBean {
    private String queryUrl = "select re.res_string,r.name" +
        "                      from role r" +
        "                      join resc_role rr" +
        "                        on r.id=rr.role_id" +
        "                      join resc re" +
        "                        on re.id=rr.resc_id" +
        "                     where re.res_type='URL'" +
        "                  order by re.priority";
    private String queryMethod = "select re.res_string,r.name" +
        "                      from role r" +
        "                      join resc_role rr" +
        "                        on r.id=rr.role_id" +
        "                      join resc re" +
        "                        on re.id=rr.resc_id" +
        "                     where re.res_type='METHOD'" +
        "                  order by re.priority";
    private DataSource dataSource;
    private FilterSecurityInterceptor filterSecurityInterceptor;
    private DelegatingMethodDefinitionSource delegatingMethodDefinitionSource;
    private ResourceDetailsBuilder resourceDetailsBuilder;

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public void setQueryMethod(String queryMethod) {
        this.queryMethod = queryMethod;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setFilterSecurityInterceptor(
        FilterSecurityInterceptor filterSecurityInterceptor) {
        this.filterSecurityInterceptor = filterSecurityInterceptor;
    }

    public void setDelegatingMethodDefinitionSource(
        DelegatingMethodDefinitionSource delegatingMethodDefinitionSource) {
        this.delegatingMethodDefinitionSource = delegatingMethodDefinitionSource;
    }

    protected UrlMatcher getUrlMatcher() {
        return new AntUrlPathMatcher();
    }

    public void afterPropertiesSet() {
        resourceDetailsBuilder = new ResourceDetailsBuilder(dataSource);
        refresh();
    }

    public void refresh() {
        if (filterSecurityInterceptor != null) {
            FilterInvocationDefinitionSource source = resourceDetailsBuilder.createUrlSource(queryUrl,
                    getUrlMatcher());
            filterSecurityInterceptor.setObjectDefinitionSource(source);
        }

        if (delegatingMethodDefinitionSource != null) {
            MethodDefinitionSource source = resourceDetailsBuilder.createMethodSource(queryMethod);
            List<MethodDefinitionSource> sources = new ArrayList<MethodDefinitionSource>();
            sources.add(source);
            delegatingMethodDefinitionSource.setMethodDefinitionSources(sources);
        }
    }
}
