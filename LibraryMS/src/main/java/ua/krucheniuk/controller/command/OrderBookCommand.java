package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.krucheniuk.constants.Path;
import ua.krucheniuk.dao.impl.BookDaoMySQL;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.OrderService;

public class OrderBookCommand implements Command {
	
	OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("sessionUser");
		Integer bookId = Integer.valueOf(request.getParameter("itemId"));
		Book book = BookDaoMySQL.getInstance().findById(bookId);
		Boolean subscribe = Boolean.valueOf(request.getParameter("subscribe"));

		Order order = new Order();
		order.setUser(user);
		order.setBook(book);
		order.setSubscribe(subscribe);
		
		
		

		String result = orderService.orderBook(order);
		if (result.equals("SUCCESS")) {
			request.getSession().setAttribute("message", "The book is succesfully ordered");
			return "redirect:/catalogue";
		} else {
			request.getSession().setAttribute("errMessage", result);
			return Path.ERROR_PAGE;}
	}

}
