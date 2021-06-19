package ua.krucheniuk.controller.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.service.LibrarianService;

public class GetOrdersCommand implements Command {
    private LibrarianService librarianService = LibrarianService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Order> currentOrders = librarianService.getBookOrders();		
		request.setAttribute("orders", currentOrders);
		
		Map<Integer, String> daysleft = librarianService.countDaysLeft(currentOrders);
		request.setAttribute("daysLeft", daysleft);

        return Path.LIBRARIAN_PAGE;
	}

}
