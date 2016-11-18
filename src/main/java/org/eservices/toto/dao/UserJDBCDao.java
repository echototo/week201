package org.eservices.toto.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eservices.toto.model.User;

public class UserJDBCDao implements UserDao {

	Connection conn;

	public void setConnection(Connection connection) {
		this.conn = connection;
	}

	public User find(String email) {
		User user = null;
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT * FROM user WHERE email=?");
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			user = new User();
			user.setMail(rs.getString("email"));
			user.setNom(rs.getString("lastname"));
			user.setPrenom(rs.getString("firstname"));
			user.setMotDePasse(rs.getString("pwd"));
			rs.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void delete(String email) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("DELETE FROM user WHERE email=?");
			stmt.setString(1, email);
			stmt.executeUpdate();
		} catch(SQLException e) {
			throw new Error("Unable to delete User " + email, e);
		}
	}

	public void create(User user) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("INSERT INTO user values (?,?,?,?)");
			stmt.setString(1, user.getMail());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getMotDePasse());
			stmt.executeUpdate();
		} catch(SQLException e) {
			throw new Error("Unable to insert User " + user, e);
		}
	}

	public void update(User user) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("UPDATE user SET lastname=?,firstname=?,pwd=? WHERE email=?");
			stmt.setString(1, user.getNom());
			stmt.setString(2, user.getPrenom());
			stmt.setString(3, user.getMotDePasse());
			stmt.setString(4, user.getMail());
			stmt.executeUpdate();
		} catch(SQLException e) {
			throw new Error("Unable to insert User " + user, e);
		}
	}


	public boolean checkPassword(String email, String password) {
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = conn.prepareStatement("SELECT * FROM user WHERE email=? AND pwd=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			boolean exists = rs.next();
			rs.close();
			return exists;
		} catch(SQLException e) {
			throw new Error("Unable to identified User " + email, e);
		}
	}
}