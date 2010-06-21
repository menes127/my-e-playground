package com.family168.springsecuritybook.ch207;

import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class ResourceDetailsMapping extends MappingSqlQuery {
    protected ResourceDetailsMapping(DataSource dataSource, String resourceQuery) {
        super(dataSource, resourceQuery);
        compile();
    }

    protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
        String name = rs.getString(1);
        String role = rs.getString(2);
        ResourceDetails resourceDetails = new ResourceDetails(name, role);

        return resourceDetails;
    }
}
