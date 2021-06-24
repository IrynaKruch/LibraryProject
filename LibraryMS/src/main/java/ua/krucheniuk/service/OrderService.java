package ua.krucheniuk.service;

import java.util.List;

import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;


public class OrderService {
		
	DaoFactory factory = DaoFactory.getInstance();

	public String orderBook(Order order) {
		return factory.createOrderDao().create(order);
	}
	
    public List<Order>getReaderOrders(Integer readerId){
    	return factory.createOrderDao().findReaderOrders(readerId);
    	}
    
    public Book findBookById(Integer bookId) {
		return factory.createBookDao().findById(bookId);
		}

}
