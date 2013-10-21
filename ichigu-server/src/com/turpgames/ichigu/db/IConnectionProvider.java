package com.turpgames.ichigu.db;

public interface IConnectionProvider {
	String getConnectionProvider();
	String getConnectionString();
	String getUsername();
	String getPassword();
}
