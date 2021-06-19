package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.service.SearchService;

public class SearchBookCommand implements Command {

	private SearchService searchService = SearchService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String criteria = request.getParameter("searchType");
		if (criteria != null) {
			String text = request.getParameter("searchText");
			request.setAttribute("searchText", text);

			if (criteria.equals("author"))
				request.getSession().setAttribute("catalogue", searchService.getBooksByAuthor(text));
			else if (criteria.equals("bookname"))
				request.getSession().setAttribute("catalogue", searchService.getBooksByName(text));
			else
				request.getSession().setAttribute("catalogue", searchService.getBooksByYear(Integer.parseInt(text)));
		}

		return Path.HOME_PAGE;
	}
}
