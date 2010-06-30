package com.menes.webapp.ch005;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.MappingSqlQuery;

public class RescRoleDaoImpl extends JdbcDaoSupport implements IRescRoleDao {
	private RescRolesAllQuery rescRolesAllQuery;
	private String resourceQuery;
	
	public RescRoleDaoImpl(){}

	@Override
	public List<RescRole> findAll() {
		return rescRolesAllQuery.execute();
	}

	protected void initDao() throws Exception {
		rescRolesAllQuery = new RescRolesAllQuery(getDataSource());
	}
	
	protected class RescRolesAllQuery extends MappingSqlQuery<RescRole> {
		protected RescRolesAllQuery(DataSource ds) {
			super(ds, resourceQuery);
			compile();
		}
		
		protected RescRole mapRow(ResultSet rs, int rownum) throws SQLException {
			RescRole RescRole = new RescRole();
			RescRole.setResc(rs.getString("res_string"));
			RescRole.setRole(rs.getString("name"));
			return RescRole;
		}
	}
	
	public void setResourceQuery(String resourceQuery) {
		this.resourceQuery = resourceQuery;
	}
}
