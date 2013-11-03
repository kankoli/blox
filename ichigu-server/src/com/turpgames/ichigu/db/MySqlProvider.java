package com.turpgames.ichigu.db;

public class MySqlProvider implements IConnectionProvider {

	@Override
	public String getConnectionProvider() {
		return "com.mysql.jdbc.Driver";
	}

	@Override
	public String getConnectionString() {
		return "jdbc:mysql://localhost/ichigu";
	}

	@Override
	public String getUsername() {
		return "root";
	}

	@Override
	public String getPassword() {
		return "123456";
	}
}