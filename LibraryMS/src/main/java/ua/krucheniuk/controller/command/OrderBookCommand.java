package ua.krucheniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.krucheniuk.constants.Messages;
import ua.krucheniuk.constants.Path;
import ua.krucheniuk.dao.impl.BookDaoMySQL;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;
import ua.krucheniuk.service.OrderService;

public class OrderBookCommand implements Command {
	private final static Logger log = Logger.getLogger(OrderBookCommand.class);

	
	OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("sessionUser");
		Integer bookId = Integer.valueOf(request.getParameter("itemId"));
		Book book = BookDaoMySQL.getInstance().findById(bookId);
		Boolean subscribe = Boolean.valueOf(request.getParameter("subscribe"));
		String locale = (String) request.getSession().getAttribute("locale");
		
		Order order = new Order();
		order.setUser(user);
		order.setBook(book);
		order.setSubscribe(subscribe);

		String result = orderService.orderBook(order);
		if (result.equals("SUCCESS")) {
			request.getSession().setAttribute("message", Messages.getInstance(locale).getString(Messages.ORDER_SUSSESS));
			return "redirect:" + Path.CATALOGUE_ALL_BOOKS_COMMAND;
		} else {
			log.info(result);
			request.getSession().setAttribute("errMessage", result);
			return Path.ERROR_PAGE;}
	}

}
