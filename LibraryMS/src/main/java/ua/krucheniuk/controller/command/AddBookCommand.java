package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.service.LibrarianService;


public class AddBookCommand implements Command {
	
	LibrarianService librarianService = LibrarianService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Book book = new Book();
        book.setName(request.getParameter("bookname"));
        book.setAuthor(request.getParameter("author"));
        book.setEdition(request.getParameter("edition"));
        book.setYearOfEd(Integer.parseInt(request.getParameter("yearOfEd")));
		book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		
		
		String result = librarianService.addNewBook(book);
		if (result.equals("SUCCESS")) {
			request.getSession().setAttribute("message", "The book is added");
		} else {
			request.getSession().setAttribute("errMessage", result);}
        return Path.ADD_BOOK_PAGE;
    }

}
