package org.eservices.toto.dao;
import org.eservices.toto.model.User;;

public interface UserDao {

	public User find(String email);
	public void delete(String email);
	public void create(User user);
	public void update(User user);

	public boolean checkPassword(String email, String password);
	
}