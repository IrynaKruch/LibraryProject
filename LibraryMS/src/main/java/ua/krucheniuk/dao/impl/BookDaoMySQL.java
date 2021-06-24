package ua.krucheniuk.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.krucheniuk.dao.mapper.BookMapper;
import ua.krucheniuk.dao.model.BookDao;
import ua.krucheniuk.entity.Book;

public class BookDaoMySQL implements BookDao {

	private final static Logger log = Logger.getLogger(BookDaoMySQL.class);

	private static volatile BookDaoMySQL bookDaoMySQL;

	private final String INSERT_BOOK = "INSERT INTO books (name,author,edition,yearOfEd,quantity) VALUES(?,?,?,?,?)";
	private final String SELECT_All_BOOKS = "SELECT * FROM books";
	private final String SELECT_BOOK_byId = SELECT_All_BOOKS + " WHERE id=?";
	private final String SELECT_BOOKS_BY_NAME = SELECT_All_BOOKS + " WHERE name like ?";
	private final String SELECT_BOOKS_BY_AUTHOR = SELECT_All_BOOKS + " WHERE author like ?";
	private final String SELECT_BOOKS_BY_YEAR = SELECT_All_BOOKS + " WHERE yearOfEd=?";
	private final String UPDATE_BOOK = "UPDATE books SET name=?,author=?,edition=?,yearOfED=?,quantity=? WHERE id=?";
	private final String UPDATE_BOOK_AMOUNT_BY_ID = "UPDATE books SET quantity=? WHERE id=?";
	private final String DELETE_BOOK = "DELETE FROM books WHERE id=?";

	private int bookCount;

	public static BookDaoMySQL getInstance() {
		BookDaoMySQL localInstance = bookDaoMySQL;
		if (localInstance == null) {
			synchronized (BookDaoMySQL.class) {
				localInstance = bookDaoMySQL;
				if (localInstance == null) {
					bookDaoMySQL = localInstance = new BookDaoMySQL();
				}
			}
		}
		return localInstance;
	}

	private BookDaoMySQL() {

	}

	@Override
	public String create(Book book) {
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getEdition());
			preparedStatement.setInt(4, book.getYearOfEd());
			preparedStatement.setInt(5, book.getQuantity());
			int i = preparedStatement.executeUpdate();
			if (i != 0)
				return "SUCCESS";
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return "Oops.. Something went wrong there..!";

	}

	@Override
	public Book findById(int id) {
		Book book = null;

		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_byId)) {

			BookMapper mapper = new BookMapper();
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next())
					book = mapper.getBookFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return book;
	}

	@Override
	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<>();
		BookMapper mapper = new BookMapper();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_All_BOOKS);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				Book book = new Book();
				book = mapper.getBookFromResultSet(resultSet);
				bookList.add(book);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return bookList;
	}

	@Override
	public String update(Book book) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_BOOK);) {
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getEdition());
			preparedStatement.setInt(4, book.getYearOfEd());
			preparedStatement.setInt(5, book.getQuantity());
			preparedStatement.setInt(6, book.getId());
			int i = preparedStatement.executeUpdate();
			if (i != 0)
				return "SUCCESS";
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return "Oops.. Something went wrong there..!";
	}

	@Override
	public void delete(Book book) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_BOOK);) {
			preparedStatement.setInt(1, book.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public List<Book> findByName(String name) {
		List<Book> books = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_BY_NAME)) {
			preparedStatement.setString(1, name + "%");
			BookMapper mapper = new BookMapper();
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Book book = new Book();
					book = mapper.getBookFromResultSet(resultSet);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return books;
	}

	public List<Book> findByAuthor(String authorName) {
		List<Book> books = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_BY_AUTHOR)) {
			preparedStatement.setString(1, authorName + "%");
			BookMapper mapper = new BookMapper();
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Book book = new Book();
					book = mapper.getBookFromResultSet(resultSet);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return books;
	}

	public List<Book> findByYearOfEd(int year) {
		List<Book> books = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_BY_YEAR)) {
			preparedStatement.setInt(1, year);
			BookMapper mapper = new BookMapper();
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Book book = new Book();
					book = mapper.getBookFromResultSet(resultSet);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return books;
	}

	@Override
	public void setBookAmount(Integer bookId, Integer amount) {
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_AMOUNT_BY_ID)) {
			preparedStatement.setInt(1, amount);
			preparedStatement.setInt(2, bookId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public List<Book> sortBooks(int offset, int recordsOnPage, String sortingType, String sortingColumn) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT SQL_CALC_FOUND_ROWS * FROM books ");
		queryBuilder.append(" ORDER BY ").append(sortingColumn).append(" ").append(sortingType).append(" limit ")
				.append(offset).append(", ").append(recordsOnPage);

		List<Book> books = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				Statement statement = connection.createStatement();) {
			connection.setAutoCommit(false);

			BookMapper mapper = new BookMapper();
			ResultSet resultSet = statement.executeQuery(queryBuilder.toString());
			while (resultSet.next()) {
				Book book = new Book();
				book = mapper.getBookFromResultSet(resultSet);
				books.add(book);
			}
			resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
			if (resultSet.next())
				this.bookCount = resultSet.getInt(1);

			connection.commit();
			resultSet.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return books;
	}

	public int getBookCount() {
		return this.bookCount;
	}

	public void deleteBook(Integer bookId) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_BOOK);) {
			preparedStatement.setInt(1, bookId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
}
