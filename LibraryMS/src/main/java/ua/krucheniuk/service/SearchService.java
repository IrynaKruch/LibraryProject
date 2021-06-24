package ua.krucheniuk.service;

import java.util.List;

import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;

public class SearchService {

	DaoFactory factory = DaoFactory.getInstance();

	public List<Book> getBooksByName(String name) {
		return factory.createBookDao().findByName(name);
	}

	public List<Book> getBooksByAuthor(String authorName) {
		return factory.createBookDao().findByAuthor(authorName);
	}

	public List<Book> getBooksByYear(int year) {
		return factory.createBookDao().findByYearOfEd(year);
	}

	public List<Book> sortBooks(int offset, int recordsOnPage, String sortingType, String sortingColumn) {
		return factory.createBookDao().sortBooks(offset, recordsOnPage, sortingType, sortingColumn);
	}

	public int bookCount() {
		return factory.createBookDao().getBookCount();
	}
	
	public List<Order> getOrdersBySubscribe(Boolean type){
		return factory.createOrderDao().findbySubscribe(type);
	}
}
