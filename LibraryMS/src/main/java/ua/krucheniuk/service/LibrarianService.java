package ua.krucheniuk.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ua.krucheniuk.dao.impl.OrderDaoMySQL;
import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.entity.Book;
import ua.krucheniuk.entity.Order;
import ua.krucheniuk.entity.User;


public class LibrarianService {

	private static volatile LibrarianService librarianService;
	DaoFactory factory = DaoFactory.getInstance();

    private LibrarianService(){

    }

    public static LibrarianService getInstance() {
        LibrarianService localInstance = librarianService;
        if (localInstance == null) {
            synchronized (LibrarianService.class) {
                localInstance = librarianService;
                if (localInstance == null) {
                    librarianService = localInstance = new LibrarianService();
                }
            }
        }
        return localInstance;
    }

    
    public List<Book> findAllBooks(){
    	return factory.createBookDao().findAll();
    }
    
    public List<Order> getBookOrders(){
        return factory.createOrderDao().findBookOrders();
    }
    
    public void getBookToReader(Integer orderId, Integer days){
        factory.createOrderDao().getBookToReader(orderId,days);
    }
    
    public List<User>getInfoAboutAllReaders(){
        return factory.createUserDao().findAll();
    }

    public Map<Integer, String> countDaysLeft(List<Order> userOrders) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Map<Integer, String> daysleft = new HashMap<>();
		for (Order order : userOrders) {
			if (order.getReturnDate() != null) {
				long diffInMillies = order.getReturnDate().getTime() - timestamp.getTime();
				long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				if (days >= 0) {
					daysleft.put(order.getId(), String.valueOf(days));
				} else {
					daysleft.put(order.getId(), "Expired:pay 10$ fine");
				}
			} else {
				daysleft.put(order.getId(), "Not processed yet");
			}
		} return daysleft;
	}
    
    public Map<Integer, Integer> countDebt(List<Order> userOrders) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Map<Integer, Integer> debt = new HashMap<>();
		for (Order order : userOrders) {
			Integer userId = order.getUser().getId();
			if (order.getReturnDate() != null) {
				long diffInMillies = order.getReturnDate().getTime() - timestamp.getTime();
				long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			 if (days < 0 & debt.containsKey(userId)) {
					debt.computeIfPresent(userId,(key, val) -> val+10);
				} else if (days < 0 & !debt.containsKey(userId))
					{debt.put(userId, 10);}
			}
		} return debt;
	}

    
    
    public void setBookAmount(Integer bookId, Integer amount) {
    	factory.createBookDao().setBookAmount(bookId,amount);
    }
    
    public void readerReturnBook(Integer id) {
    	factory.createOrderDao().readerReturnsBook(id);    	
    }
    
    public Boolean isBookOrdered(Integer bookId){
        return factory.createOrderDao().isBookOrdered(bookId);
    }
    
    public void deleteBook(Integer bookId){
        factory.createBookDao().deleteBook(bookId);
        }
    
    public User findUserById (Integer id) {
    	return factory.createUserDao().findById(id);
    }
    
    public String addNewBook (Book book) {
    	return factory.createBookDao().create(book);
    }
}
