package org.eservices.toto.controler;

import java.sql.Connection;
import java.sql.SQLException;

import org.eservices.toto.dao.UserJDBCDao;
import org.eservices.toto.model.User;
import org.sqlite.SQLiteDataSource;

public class Main {
	public static void main(String args[]) throws SQLException{
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:data.db");
		Connection con = ds.getConnection();
		
		UserJDBCDao dao = new UserJDBCDao();
		dao.setConnection(con);
		
		User user = dao.find(args[0]);
		System.out.println(user.getPrenom());
	}
}
