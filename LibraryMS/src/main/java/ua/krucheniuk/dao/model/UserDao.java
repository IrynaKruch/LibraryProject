package ua.krucheniuk.dao.model;

import java.util.List;

import ua.krucheniuk.entity.User;
import ua.krucheniuk.exception.NotUniqueUserException;

public interface UserDao extends GenericDao<User> {
	
	User login(String login, String password);
	
	List<User> findAll();
	
	String createUser(User user) throws NotUniqueUserException;

	String updateUser(User user)throws NotUniqueUserException;
}
