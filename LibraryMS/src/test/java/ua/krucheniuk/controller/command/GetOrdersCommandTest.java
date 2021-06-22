package ua.krucheniuk.controller.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import ua.krucheniuk.dao.impl.OrderDaoMySQL;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;

public class GetOrdersCommandTest {
	
	static OrderDaoMySQL mockOrderDao = Mockito.mock(OrderDaoMySQL.class);

	HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
	
	static {
		MockedStatic <OrderDaoMySQL> orderDaoStatic = Mockito.mockStatic(OrderDaoMySQL.class);
		orderDaoStatic.when(OrderDaoMySQL::getInstance).thenReturn(mockOrderDao);
	}


	@Test
	public final void testExecute() {
		GetOrdersCommand getOrdersCommand = new GetOrdersCommand();
		Book book = new Book("Name","Author","Edit",2001,3);
		User user = new User("Name","email","login","password");
		Order order = new Order(1, user, book, "2021-06-21", "2021-06-25");
		List <Order> orders = new ArrayList<>();
		orders.add(order);
		when(mockOrderDao.findBookOrders()).thenReturn(orders);

		String result = getOrdersCommand.execute(request, null);
		
		Assert.assertEquals("/views/orders.jsp", result);
		verify(request).setAttribute("orders", orders);
		verify(request).setAttribute("daysLeft", orders);

	}

}
