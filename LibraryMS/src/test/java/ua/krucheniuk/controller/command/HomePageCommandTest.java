package ua.krucheniuk.controller.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import ua.krucheniuk.dao.impl.BookDaoMySQL;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.service.LibrarianService;


public class HomePageCommandTest {

	static BookDaoMySQL mockBookDao = Mockito.mock(BookDaoMySQL.class);

	HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	
	static {
		MockedStatic <BookDaoMySQL> bookDaoStatic = Mockito.mockStatic(BookDaoMySQL.class);
		bookDaoStatic.when(BookDaoMySQL::getInstance).thenReturn(mockBookDao);
	}
	
	@Test
	public final void testExecute() {
		HomePageCommand homePageCommand = new HomePageCommand(new LibrarianService());
		List <Book> books = new ArrayList<>();
		books.add(new Book("Name","Author","Edit",2001,3));
		when(mockBookDao.findAll()).thenReturn(books);

		String result = homePageCommand.execute(request, null);
		
		Assert.assertEquals("/homepage.jsp", result);
		verify(request).setAttribute("catalogue", books);
		
	}

}
