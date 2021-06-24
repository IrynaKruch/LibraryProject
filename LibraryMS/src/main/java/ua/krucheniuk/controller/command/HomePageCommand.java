package ua.krucheniuk.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.service.LibrarianService;

public class HomePageCommand implements Command {

	LibrarianService librarianService;

	public HomePageCommand(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Book> catalogue = librarianService.findAllBooks();
		request.setAttribute("catalogue", catalogue);
		return Path.HOME_PAGE;
	}

}
