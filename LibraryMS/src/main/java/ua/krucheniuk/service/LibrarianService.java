package ua.krucheniuk.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;

public class LibrarianService {

	DaoFactory factory = DaoFactory.getInstance();

	public LibrarianService() {

	}

	public List<Book> findAllBooks() {
		return factory.createBookDao().findAll();
	}

	public List<Order> getBookOrders() {
		return factory.createOrderDao().findBookOrders();
	}

	public List<Order> getHandledBookOrders() {
		return factory.createOrderDao().findHandledBookOrders();
	}

	public void getBookToReader(Integer orderId, Integer days) {
		factory.createOrderDao().getBookToReader(orderId, days);
	}

	public List<User> getInfoAboutAllReaders() {
		return factory.createUserDao().findAll();
	}

	public Map<Integer, String> setDaysLeftColumn(List<Order> userOrders) {
		Map<Integer, String> daysleft = new HashMap<>();
		for (Order order : userOrders) {
			if (order.getReturnDate() == null) {
				daysleft.put(order.getId(), "Not processed yet");
			} else {
				countDaysAndFine(daysleft, order);
			}
		}
		return daysleft;
	}

	private void countDaysAndFine(Map<Integer, String> daysleft, Order order) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long diffInMillies = order.getReturnDate().getTime() - timestamp.getTime();
		long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (days >= 0) {
			daysleft.put(order.getId(), String.valueOf(days));
		} else {
			daysleft.put(order.getId(), "Expired:pay 10$ fine");
		}
	}

	public Map<Integer, Integer> countDebt(List<Order> userOrders) {
		Map<Integer, Integer> debt = new HashMap<>();
		for (Order order : userOrders) {
			Integer userId = order.getUser().getId();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			long diffInMillies = order.getReturnDate().getTime() - timestamp.getTime();
			long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (days < 0 & debt.containsKey(userId)) {
				debt.computeIfPresent(userId, (key, val) -> val + 10);
			} else if (days < 0 & !debt.containsKey(userId)) {
				debt.put(userId, 10);
			}
		}
		return debt;
	}

	public void setBookAmount(Integer bookId, Integer amount) {
		factory.createBookDao().setBookAmount(bookId, amount);
	}

	public void readerReturnBook(Integer id) {
		factory.createOrderDao().readerReturnsBook(id);
	}

	public Boolean isBookOrdered(Integer bookId) {
		return factory.createOrderDao().isBookOrdered(bookId);
	}

	public void deleteBook(Integer bookId) {
		factory.createBookDao().deleteBook(bookId);
	}

	public User findUserById(Integer id) {
		return factory.createUserDao().findById(id);
	}

	public String addNewBook(Book book) {
		return factory.createBookDao().create(book);
	}
}
