package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.service.LibrarianService;


public class SetBookAmountCommand implements Command {
	
    private LibrarianService librarianService = LibrarianService.getInstance();


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        System.out.print(bookId);
        Integer amount=Integer.valueOf(request.getParameter("amount"));
        System.out.print(bookId);
        librarianService.setBookAmount(bookId,amount);
        return "redirect:" + Path.CATALOGUE_ALL_BOOKS_COMMAND;
    }
}
