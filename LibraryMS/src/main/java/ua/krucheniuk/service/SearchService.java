package ua.krucheniuk.service;

import java.util.List;

import ua.krucheniuk.dao.impl.BookDaoMySQL;
import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.Book;

public class SearchService {

	private static volatile SearchService searchService;

	DaoFactory factory = DaoFactory.getInstance();

	private SearchService() {

	}

	public static SearchService getInstance() {
		SearchService localInstance = searchService;
		if (localInstance == null) {
			synchronized (SearchService.class) {
				localInstance = searchService;
				if (localInstance == null) {
					searchService = localInstance = new SearchService();
				}
			}
		}
		return localInstance;
	}

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
}
