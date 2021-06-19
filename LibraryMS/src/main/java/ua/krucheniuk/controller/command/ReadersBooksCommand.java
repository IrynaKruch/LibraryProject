package ua.krucheniuk.controller.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.LibrarianService;
import ua.krucheniuk.service.OrderService;

public class ReadersBooksCommand implements Command {

	private OrderService orderService = OrderService.getInstance();
	private LibrarianService librarianService = LibrarianService.getInstance();


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User currentUser = (User) request.getSession().getAttribute(USER);
		List<Order> userOrders;
        if (currentUser.getRole()=="ADMIN" || currentUser.getRole()=="LIBRARIAN") {
            request.getSession().setAttribute("reader", currentUser);
        }
		userOrders = orderService.getReaderOrders(currentUser.getId());
		request.setAttribute("userOrders", userOrders);
		Map<Integer, String> daysleft = librarianService.countDaysLeft(userOrders);
		
			request.setAttribute("daysLeft", daysleft);

		return Path.READER_PAGE;
	}

	
	
	
}
