package ua.krucheniuk.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.krucheniuk.dao.mapper.OrderMapper;
import ua.krucheniuk.dao.model.OrderDao;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;

public class OrderDaoMySQL implements OrderDao {

	private final static Logger LOGGER = Logger.getLogger(OrderDaoMySQL.class);

	private static volatile OrderDaoMySQL orderDaoMySQL;

	private final String INSERT_ORDER = "INSERT INTO orders (readerId,bookId,subscribe) VALUES(?,?,?)";
	private final String SELECT_ALL_ORDERS = "SELECT o.id,o.lendDate,o.returnDate,us.fullName,us.id,b.id,b.name,b.author,b.edition,b.yearOfEd,b.quantity,o.subscribe,o.processed FROM orders o JOIN users us ON o.readerId=us.id JOIN books b ON o.bookId=b.id";
	private final String SELECT_ORDER_byId = "SELECT o.id,o.lendDate,o.returnDate,us.fullName,us.id,b.id,b.name,b.author,b.edition,b.yearOfEd,b.quantity,o.subscribe,o.processed FROM orders o JOIN users us ON o.readerId=us.id JOIN books b ON o.bookId=b.id WHERE o.id=?";
	private final String SELECT_ORDERS_ON_BOOKS = SELECT_ALL_ORDERS + " WHERE o.processed=b'0'";
	private final String SELECT_ORDERS_RETURN_DATES = "SELECT id AS 'o.id', returnDate AS 'o.returnDate',readerId AS 'us.id' FROM orders WHERE processed=b'0' AND returnDate IS NOT NULL";
	private final String GET_BOOK_TO_READER = "UPDATE orders SET lendDate=?,returnDate=? WHERE id=?";
	private final String SELECT_BOOKS_FOR_READER = SELECT_ALL_ORDERS + " WHERE us.id=? AND o.processed=b'0'";
	private final String UPDATE_ORDER = "UPDATE orders SET readerId=?,bookId=?,lendDate=?,returnDate=?,subscribe=?,processed=? WHERE id=?";
	private final String UPDATE_BOOK_QUANTITY = "UPDATE books SET quantity=? WHERE id=?";
	private final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";
	private final String READER_RETURNS_BOOK = "UPDATE orders SET processed=b'1' WHERE id=?";
	private final String IS_BOOK_ORDERED = "SELECT COUNT(id) AS count FROM orders WHERE bookId=? AND processed=b'0'";
	private final String SELECT_ORDERS_BY_SUBSCRIBE = SELECT_ORDERS_ON_BOOKS + " AND o.subscribe = ?";

	private final Long daysInMillis = 1000L * 60L * 60L * 24L;

	public static OrderDaoMySQL getInstance() {
		OrderDaoMySQL localInstance = orderDaoMySQL;
		if (localInstance == null) {
			synchronized (OrderDaoMySQL.class) {
				localInstance = orderDaoMySQL;
				if (localInstance == null) {
					orderDaoMySQL = localInstance = new OrderDaoMySQL();
				}
			}
		}
		return localInstance;
	}

	private OrderDaoMySQL() {
	}

	@Override
	public String create(Order order) {
		Connection connection = null;
		try {
			connection = DBConnection.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
			connection.setSavepoint();
			connection.setAutoCommit(false);
			User user = order.getUser();
			Book book = order.getBook();
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, book.getId());
			preparedStatement.setBoolean(3, order.isSubscribe());
			preparedStatement.executeUpdate();

			book.setQuantity(book.getQuantity() - 1);
			preparedStatement = connection.prepareStatement(UPDATE_BOOK_QUANTITY);
			preparedStatement.setInt(1, book.getQuantity());
			preparedStatement.setInt(2, book.getId());
			preparedStatement.executeUpdate();
			connection.commit();
			return "SUCCESS";
		} catch (SQLException e) {
			try {
				connection.rollback();
				LOGGER.error(e.getMessage());
				return "Oops.. Something went wrong there..!";
			} catch (SQLException e1) {
				LOGGER.error(e.getMessage());
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return "Oops.. Something went wrong there..!";

	}

	@Override
	public Order findById(int id) {
		Order order = null;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_byId)) {

			OrderMapper mapper = new OrderMapper();
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				resultSet.next();
				order = mapper.getOrderFromResultSet(resultSet);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return order;
	}

	@Override
	public List<Order> findAll() {
		List<Order> orderList = new ArrayList<>();
		OrderMapper mapper = new OrderMapper();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				Order order = new Order();
				order = mapper.getOrderFromResultSet(resultSet);
				orderList.add(order);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return orderList;
	}

	@Override
	public String update(Order order) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ORDER);) {
			preparedStatement.setInt(1, order.getUser().getId());
			preparedStatement.setInt(2, order.getBook().getId());
			preparedStatement.setDate(3, (Date) order.getLendDate());
			preparedStatement.setDate(4, (Date) order.getReturnDate());
			preparedStatement.setBoolean(5, order.isSubscribe());
			preparedStatement.setBoolean(6, order.isProcessed());
			int i = preparedStatement.executeUpdate();
			if (i != 0)
				return "SUCCESS";
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return "Oops.. Something went wrong there..!";
	}

	@Override
	public void delete(Order order) {
		try (Connection con = DBConnection.createConnection();
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_ORDER);) {
			preparedStatement.setInt(1, order.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public List<Order> findBookOrders() {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_ON_BOOKS);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			OrderMapper mapper = new OrderMapper();
			while (resultSet.next()) {
				Order order = new Order();
				order = mapper.getOrderFromResultSet(resultSet);
				orders.add(order);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return orders;
	}

	public List<Order> findHandledBookOrders() {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_RETURN_DATES);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			OrderMapper mapper = new OrderMapper();
			while (resultSet.next()) {
				Order order = new Order();
				order = mapper.getOrderReturnDates(resultSet);
				orders.add(order);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return orders;
	}

	public void getBookToReader(Integer orderId, Integer days) {
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_TO_READER)) {
			Timestamp lendDate = new Timestamp(System.currentTimeMillis());
			preparedStatement.setTimestamp(1, lendDate);
			Timestamp returnDate = new Timestamp(System.currentTimeMillis() + (days * daysInMillis));
			preparedStatement.setTimestamp(2, returnDate);
			preparedStatement.setInt(3, orderId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public List<Order> findReaderOrders(Integer readerId) {
		List<Order> orders = new ArrayList<>();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_FOR_READER)) {
			preparedStatement.setInt(1, readerId);
			OrderMapper mapper = new OrderMapper();

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order();
					order = mapper.getOrderFromResultSet(resultSet);
					orders.add(order);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return orders;
	}

	public void readerReturnsBook(Integer orderId) {
		Connection connection = null;
		try {
			connection = DBConnection.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(READER_RETURNS_BOOK);
			connection.setSavepoint();
			connection.setAutoCommit(false);

			preparedStatement.setInt(1, orderId);
			preparedStatement.executeUpdate();
			Book book = findById(orderId).getBook();

			book.setQuantity(book.getQuantity() + 1);
			preparedStatement = connection.prepareStatement(UPDATE_BOOK_QUANTITY);
			preparedStatement.setInt(1, book.getQuantity());
			preparedStatement.setInt(2, book.getId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				LOGGER.error(e.getMessage());
			} catch (SQLException e1) {
				LOGGER.error(e.getMessage());
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				e.printStackTrace();
			}
		}

	}

	public Boolean isBookOrdered(Integer id) {
		Boolean ordered = false;
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(IS_BOOK_ORDERED)) {
			preparedStatement.setInt(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					ordered = resultSet.getInt("count") > 0;
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return ordered;
	}

	@Override
	public List<Order> findbySubscribe(Boolean type) {
		List<Order> orderList = new ArrayList<>();
		OrderMapper mapper = new OrderMapper();
		try (Connection connection = DBConnection.createConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_SUBSCRIBE)) {
			preparedStatement.setBoolean(1, type);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Order order = new Order();
					order = mapper.getOrderFromResultSet(resultSet);
					orderList.add(order);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return orderList;
	}

}
