package ua.krucheniuk.dao.model;

import java.util.List;

import ua.krucheniuk.entity.Order;

public interface OrderDao extends GenericDao<Order> {
	 List<Order> findBookOrders();
	 void getBookToReader(Integer orderId, Integer days);
	 
	 List<Order> findReaderOrders(Integer readerId);
	 
	 void readerReturnsBook(Integer id);
	 
	 Boolean isBookOrdered(Integer bookId);
	 
	 List<Order> findbySubscribe(Boolean type);
	 
	 List<Order> findHandledBookOrders();
}
