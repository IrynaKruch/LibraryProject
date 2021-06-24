package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.service.LibrarianService;

public class GetBookToReaderCommand implements Command {

	LibrarianService librarianService;

	public GetBookToReaderCommand(LibrarianService librarianService) {
		this.librarianService = librarianService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer days = Integer.valueOf(request.getParameter("days"));
		librarianService.getBookToReader(id, days);
		request.setAttribute("orders", librarianService.getBookOrders());
		return Path.SHOWORDERS_COMMAND;
	}

}
