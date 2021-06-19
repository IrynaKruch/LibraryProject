package ua.krucheniuk.dao.model;

import java.util.List;

import ua.krucheniuk.entity.Book;

public interface BookDao extends GenericDao<Book> {
	
	List<Book> findByName(String name);
	
	List<Book> findByAuthor(String authorName);
	
	List<Book> findByYearOfEd(int year);
	
	void setBookAmount(Integer bookId, Integer amount);
	List<Book> sortBooks(int offset, int recordsOnPage, String sorting, String sortingType);
	
	int getBookCount();
	
	void deleteBook(Integer bookId);
}
