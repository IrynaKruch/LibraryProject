package ua.krucheniuk.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.krucheniuk.entity.Book;

public class BookMapper {

	public Book getBookFromResultSet(ResultSet rs) throws SQLException {
		try {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setName(rs.getString("name"));
		book.setAuthor(rs.getString("author"));
		book.setEdition(rs.getString("edition"));
		book.setYearOfEd(rs.getInt("yearOfEd"));
		book.setQuantity(rs.getInt("quantity"));
		return book;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
