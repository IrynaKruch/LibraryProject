package ua.krucheniuk.dao.model;

import java.util.List;

import ua.krucheniuk.entity.User;

public interface UserDao extends GenericDao<User> {
	
	User login(String login, String password);
	
	List<User> findAll();
}
