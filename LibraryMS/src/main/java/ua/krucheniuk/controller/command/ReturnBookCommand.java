package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.service.LibrarianService;


public class ReturnBookCommand implements Command {
    
	private LibrarianService librarianService = LibrarianService.getInstance();


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer id= Integer.valueOf(request.getParameter("orderId"));
        librarianService.readerReturnBook(id);
		return Path.SHOWORDERS_COMMAND;
	}

}
