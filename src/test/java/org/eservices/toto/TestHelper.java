package org.eservices.toto;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.sqlite.SQLiteDataSource;

public class TestHelper {
	
	private static Connection connection;
	
	public static org.eservices.toto.dao.UserJDBCDao createUserDao() throws SQLException {
		org.eservices.toto.dao.UserJDBCDao dao = new org.eservices.toto.dao.UserJDBCDao();
		dao.setConnection( getConnection() );
		return dao;
	}

	public static Connection getConnection() throws SQLException {
		if ( connection != null ) return connection;
		DataSource ds = null;
		SQLiteDataSource sqLiteSource = new SQLiteDataSource();
		sqLiteSource.setUrl("jdbc:sqlite:data.db");
		sqLiteSource.setLockingMode("NORMAL");
		ds = sqLiteSource;
		
		return ( connection = ds.getConnection() );
	};
	
	public static int updateDb(String sql) throws SQLException {
		return getConnection()
			.createStatement()
			.executeUpdate(sql);
	}
	
}