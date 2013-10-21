package com.turpgames.ichigu.db;

import java.util.ArrayList;
import java.util.List;

public class SqlQuery {
	public String query;
	private List<SqlParameter> parameters;
	
	public SqlQuery() {
		this("");
	}
	
	public SqlQuery(String query) {
		this.query = query;
		this.parameters = new ArrayList<SqlParameter>();
	}
	
	public SqlQuery(String query, SqlParameter... params) {
		this(query);
		if (params != null) {
			for (SqlParameter param : params) {
				this.parameters.add(param);
			}
		}
	}
	
	public SqlParameter[] getParameters() {
		return parameters.toArray(new SqlParameter[0]);
	}
	
	public void append(SqlQuery sql) {
		query += " " + sql.query;
		for (SqlParameter param : sql.parameters) {
			parameters.add(param);
		}
	}
	
	public void addParameter(Object value, int sqlType) {
		addParameter(new SqlParameter(value, sqlType));
	}
	
	public void addParameter(SqlParameter param) {
		parameters.add(param);
	}
	
	public void addParameters(SqlParameter[] params) {
		if (params != null) {
			for (SqlParameter param : params) {
				addParameter(param);
			}
		}
		
	}
	
	public void removeParameter(int index) {
		parameters.remove(index);
	}
	
	public void clearParameters() {
		parameters.clear();
	}
}
