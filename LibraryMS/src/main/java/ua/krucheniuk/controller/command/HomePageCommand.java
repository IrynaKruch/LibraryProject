package ua.krucheniuk.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.service.LibrarianService;

public class HomePageCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Book> catalogue = LibrarianService.getInstance().findAllBooks();
		request.setAttribute("catalogue", catalogue);
		return Path.HOME_PAGE;
	}


}
