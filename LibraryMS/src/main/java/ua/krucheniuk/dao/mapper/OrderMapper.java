package ua.krucheniuk.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;

public class OrderMapper {

	private final static Logger log = Logger.getLogger(OrderMapper.class);

	public Order getOrderFromResultSet(ResultSet rs) throws SQLException {
		try {
			Order order = new Order();
			order.setId(rs.getInt("o.id"));
			User user = new User();
			user.setId(rs.getInt("us.id"));
			user.setFullname(rs.getString("us.fullName"));
			order.setUser(user);
			Book book = new Book();
			book.setId(rs.getInt("b.id"));
			book.setName(rs.getString("b.name"));
			book.setAuthor(rs.getString("b.author"));
			book.setEdition(rs.getString("b.edition"));
			book.setYearOfEd(rs.getInt("b.yearOfEd"));
			book.setQuantity(rs.getInt("b.quantity"));
			order.setBook(book);
			order.setLendDate(rs.getDate("o.lendDate"));
			order.setReturnDate(rs.getDate("o.returnDate"));
			order.setSubscribe(rs.getBoolean("o.subscribe"));
			order.setProcessed(rs.getBoolean("o.processed"));
			return order;
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	public Order getOrderReturnDates(ResultSet rs) throws SQLException {
		try {
			Order order = new Order();
			order.setId(rs.getInt("o.id"));
			User user = new User();
			user.setId(rs.getInt("us.id"));
			order.setUser(user);
			order.setReturnDate(rs.getDate("o.returnDate"));
			return order;
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new IllegalStateException(e);
		}
	}

}
