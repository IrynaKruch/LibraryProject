package ua.krucheniuk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ua.krucheniuk.dao.impl.DBConnection;
import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;


public class OrderService {
	
private static volatile OrderService orderService;
	
	DaoFactory factory = DaoFactory.getInstance();

	private OrderService() {

	}

	public static OrderService getInstance() {
		OrderService localInstance = orderService;
		if (localInstance == null) {
			synchronized (OrderService.class) {
				localInstance = orderService;
				if (localInstance == null) {
					orderService = localInstance = new OrderService();
				}
			}
		}
		return localInstance;
	}
	
	public String orderBook(Order order) {
		return factory.createOrderDao().create(order);
		
	}
	
    public List<Order>getReaderOrders(Integer readerId){
    	return factory.createOrderDao().findReaderOrders(readerId);}

}
