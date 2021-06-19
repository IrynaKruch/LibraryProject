package ua.krucheniuk.controller.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.service.SearchService;

public class SortingCommand implements Command{
	
	SearchService searchService = SearchService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int recordsOnPage = 3;
		
		Optional <String> pageInt = Optional.ofNullable(request.getParameter("page"));
        int currentPage = Integer.parseInt(pageInt.orElse("1"));
                        
        String sortingType = request.getParameter("sortingType");
        
        String activeSortingType = (String) request.getSession().getAttribute("activeSortingType");
        if (sortingType == null & activeSortingType != null) {
        	sortingType = activeSortingType;
        }
        else if (sortingType.equals("ASC")) {
        	sortingType = "DESC";
        	activeSortingType = "DESC"; 
        } else {
        	sortingType = "ASC";
        	activeSortingType = "ASC";
        }

        
        Optional <String> sortingColumnReq = Optional.ofNullable(request.getParameter("sortingColumn"));
        
        String sortingColumn = sortingColumnReq.orElse((String) request.getSession().getAttribute("activeSortingColumn"));
        
        List<Book> books = searchService.sortBooks((currentPage-1) * recordsOnPage, recordsOnPage, activeSortingType, sortingColumn);
        
        int bookCount = searchService.bookCount();
        int numberOfPages = (int) Math.ceil(bookCount * 1.0 / recordsOnPage);
        
        request.setAttribute("catalogue", books);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("bookCount", bookCount);
        
        request.setAttribute("sortingType", sortingType);
        request.getSession().setAttribute("activeSortingType", activeSortingType);
        request.getSession().setAttribute("activeSortingColumn", sortingColumn);        
        
        return Path.HOME_PAGE;
    
	}

}
