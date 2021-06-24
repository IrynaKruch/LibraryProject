package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.service.AuthService;
import ua.krucheniuk.service.SearchService;

public class SearchBookCommand implements Command {

	SearchService searchService;
	AuthService authService;

	public SearchBookCommand(SearchService searchService, AuthService authService) {
		this.searchService = searchService;
		this.authService = authService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String criteria = request.getParameter("searchType");
		if (criteria != null) {
			String text = request.getParameter("searchText");
			request.setAttribute("searchText", text);

			if (criteria.equals("author"))
				request.getSession().setAttribute("catalogue", searchService.getBooksByAuthor(text));
			else if (criteria.equals("yearOfEd") & authService.checkYear(text))
				request.getSession().setAttribute("catalogue", searchService.getBooksByYear(Integer.parseInt(text)));
			else
				request.getSession().setAttribute("catalogue", searchService.getBooksByName(text));

		}

		return Path.HOME_PAGE;
	}
}
