package ua.krucheniuk.controller.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.service.LibrarianService;
import ua.krucheniuk.service.SearchService;

public class SearchOrderCommand implements Command {

	SearchService searchService;
	LibrarianService librarianService;

	public SearchOrderCommand(SearchService searchService, LibrarianService librarianService) {
		this.searchService = searchService;
		this.librarianService = librarianService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Order> orders = new ArrayList<>();
		Optional<String> typeOpt = Optional.ofNullable(request.getParameter("type"));
		if (typeOpt.isPresent()) {
			Boolean type = Boolean.parseBoolean(typeOpt.get());
			orders = searchService.getOrdersBySubscribe(type);
		} else {
			orders = librarianService.getBookOrders();
		}

		Map<Integer, String> daysleft = librarianService.setDaysLeftColumn(orders);

		request.setAttribute("orders", orders);
		request.setAttribute("daysLeft", daysleft);

		return Path.LIBRARIAN_PAGE;
	}

}
