package ua.krucheniuk.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.krucheniuk.entity.User;

public class UserMapper {

	public User getUserFromResultSet(ResultSet rs) throws SQLException {
		try {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFullname(rs.getString("fullname"));
		user.setEmail(rs.getString("email"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getString("role"));
		return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
