package ua.krucheniuk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.krucheniuk.dao.mapper.UserMapper;
import ua.krucheniuk.dao.model.UserDao;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.exception.NotUniqueUserException;

public class UserDaoMySQL implements UserDao {

	private final static Logger log = Logger.getLogger(UserDaoMySQL.class);

	private static volatile UserDaoMySQL userDaoMySQL;
	
	private final String INSERT_USER = "INSERT INTO users (fullname,email,login,password) VALUES(?,?,?,?)";
	private final String SELECT_USERS_byId = "SELECT * FROM users WHERE id=?";
	private final String SELECT_All_USERS = "SELECT * FROM users";
	private final String UPDATE_USER = "UPDATE users SET fullname=?,email=?,login=?,password=?,role=? WHERE id=?";
	private final String DELETE_USER = "DELETE FROM users WHERE id=?";
	private final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=? and password=?";


	public static UserDaoMySQL getInstance() {
		UserDaoMySQL localInstance = userDaoMySQL;
		if (localInstance == null) {
			synchronized (UserDaoMySQL.class) {
				localInstance = userDaoMySQL;
				if (localInstance == null) {
					userDaoMySQL = localInstance = new UserDaoMySQL();
				}
			}
		}
		return localInstance;
	}

	private UserDaoMySQL() {
	}

	@Override
	public String create(User user) {
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
			preparedStatement.setString(1, user.getFullname());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			int i = preparedStatement.executeUpdate();
			if (i!=0) 
	             return "SUCCESS";
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
        return "Oops.. Something went wrong there..!";  

	}
	
	@Override
	public String createUser(User user) throws NotUniqueUserException {
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
			preparedStatement.setString(1, user.getFullname());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			int i = preparedStatement.executeUpdate();
			if (i!=0) 
	             return "SUCCESS";
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new NotUniqueUserException(e.getMessage(), (user.getLogin()+" & "+user.getEmail()));
		}
        return "Oops.. Something went wrong there..!";  

	}


	@Override
	public User findById(int id) {
		User user = null;

		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_byId)) {

			UserMapper mapper = new UserMapper();
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					user = mapper.getUserFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		UserMapper mapper = new UserMapper();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_All_USERS);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				User user = new User();
				user = mapper.getUserFromResultSet(resultSet);
				userList.add(user);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return userList;
	}

	@Override
	public String update(User user) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USER);) {
			preparedStatement.setString(1, user.getFullname());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setInt(6, user.getId());
			int i = preparedStatement.executeUpdate();
			if (i!=0) 
	             return "SUCCESS";
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
        return "Oops.. Something went wrong there..!";
	}
	
	
	@Override
	public String updateUser(User user) throws NotUniqueUserException {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USER);) {
			preparedStatement.setString(1, user.getFullname());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setInt(6, user.getId());
			int i = preparedStatement.executeUpdate();
			if (i!=0) 
	             return "SUCCESS";
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new NotUniqueUserException(e.getMessage(), (user.getLogin()+" & "+user.getEmail()));
		}
        return "Oops.. Something went wrong there..!";
	}

	@Override
	public void delete(User user) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_USER);) {
			preparedStatement.setInt(1, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
	
	public User login(String login, String password) {
		User user = null;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				UserMapper mapper = new UserMapper();
				if (resultSet.next()) {
					user = mapper.getUserFromResultSet(resultSet); 
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return user;
	}
	
	
}
