package ua.krucheniuk.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;

public class OrderMapper {

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
            throw new IllegalStateException(e);
        }
    }
}
