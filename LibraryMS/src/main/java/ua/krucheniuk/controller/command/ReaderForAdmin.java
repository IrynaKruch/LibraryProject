package ua.krucheniuk.controller.command;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.service.LibrarianService;
import ua.krucheniuk.service.OrderService;

public class ReaderForAdmin implements Command {
	
	LibrarianService librarianService;
	OrderService orderService;

	public ReaderForAdmin(LibrarianService librarianService, OrderService orderService) {
		this.librarianService = librarianService;
		this.orderService = orderService;
	}



	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int readerId = 0;
		Optional<String> readerIdReq = Optional.ofNullable(request.getParameter("id"));
		if (readerIdReq.isPresent()) {
			readerId = Integer.parseInt(readerIdReq.get());
		} else {
			return Path.READERS_COMMAND;}
		
		request.setAttribute("reader", librarianService.findUserById(readerId));

		List<Order> userOrders = orderService.getReaderOrders(readerId);
		request.setAttribute("userOrders", userOrders);
		
		Map<Integer, String> daysleft = librarianService.setDaysLeftColumn(userOrders);
		request.setAttribute("daysLeft", daysleft);

		return Path.READER_PAGE;
	}

}
