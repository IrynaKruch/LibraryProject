package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.service.LibrarianService;
import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;



public class DeleteBookCommand implements Command{
    private LibrarianService librarianService = LibrarianService.getInstance();


	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId=Integer.valueOf(request.getParameter("bookId"));
        String locale=(String) request.getSession().getAttribute("locale");
        System.out.print(librarianService.isBookOrdered(bookId));
        if(librarianService.isBookOrdered(bookId)){
            request.setAttribute("message",Messages.getInstance(locale).getString(Messages.CANT_DELETE_BOOK));
        }else {
            librarianService.deleteBook(bookId);
            request.getSession().setAttribute("catalogue", librarianService.findAllBooks());
            request.setAttribute("message", Messages.getInstance(locale).getString(Messages.BOOK_DELETED));
        }
        return Path.HOME_PAGE;
    }

}
