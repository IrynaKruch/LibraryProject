package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.service.LibrarianService;

public class SetBookAmountCommand implements Command {

	LibrarianService librarianService;

	public SetBookAmountCommand(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Integer bookId = Integer.valueOf(request.getParameter("bookId"));
		Integer amount = Integer.valueOf(request.getParameter("amount"));
		librarianService.setBookAmount(bookId, amount);
		return "redirect:" + Path.CATALOGUE_ALL_BOOKS_COMMAND;
	}
}
