package ua.krucheniuk.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.exception.WrongInputException;
import ua.krucheniuk.service.AuthService;
import ua.krucheniuk.service.LibrarianService;

public class AddBookCommand implements Command {
	
	private final static Logger log = Logger.getLogger(AddBookCommand.class);

	LibrarianService librarianService = LibrarianService.getInstance();
	AuthService authService = AuthService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Book book = new Book();
		String locale = (String) request.getSession().getAttribute("locale");

		try {
			Optional<String> bookname = Optional.ofNullable(request.getParameter("bookname"));
			book.setName(bookname.orElseThrow(() -> new WrongInputException(bookname.get())));
			
			Optional<String> author = Optional.ofNullable(request.getParameter("author"));
			book.setAuthor(author.orElseThrow(() -> new WrongInputException(author.get())));
			
			Optional<String> edition = Optional.ofNullable(request.getParameter("edition"));
			book.setEdition(edition.orElseThrow(() -> new WrongInputException(edition.get())));
			
			Optional<String> yearOfEd = Optional.ofNullable(request.getParameter("yearOfEd"));
			if (yearOfEd.isPresent() & authService.checkYear(yearOfEd.get())) {
				book.setYearOfEd(Integer.parseInt(yearOfEd.get()));} 
			else { throw new WrongInputException(yearOfEd.get());}
			
			Optional<String> quantity = Optional.ofNullable(request.getParameter("quantity"));
			if (quantity.isPresent() & authService.checkQuantity(quantity.get()))
				book.setQuantity(Integer.parseInt(quantity.get()));
			else throw new WrongInputException(quantity.get());
			
		} catch (WrongInputException ex) {
			log.info(ex.getMessage());
			request.getSession().setAttribute("errMessage", "Wrong input: " + ex.getInputData());
			return "redirect:" + Path.ADD_BOOK_PAGE;
		}

		String result = librarianService.addNewBook(book);

		if (result.equals("SUCCESS")) {
			request.getSession().setAttribute("message",
					Messages.getInstance(locale).getString(Messages.ADDBOOK_SUCCESS));
		} else {
			request.getSession().setAttribute("errMessage",
					Messages.getInstance(locale).getString(Messages.ADDBOOK_FAILER));
		}
		return "redirect:" + Path.ADD_BOOK_PAGE;
	}

}
