package ua.krucheniuk.entity;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import ua.krucheniuk.controller.command.AddBookCommand;

public class Order {
	
private final static Logger log = Logger.getLogger(AddBookCommand.class);

	
private int id;
private User user;
private Book book;
private Date lendDate;
private Date returnDate;
private boolean subscribe;
private boolean processed;

public Order() {
	
}

public Order(int id, User user, Book book, Date lendDate, Date returnDate) {
	super();
	this.id = id;
	this.user = user;
	this.book = book;
	this.lendDate = lendDate;
	this.returnDate = returnDate;
}

public Order(int id, User user, Book book, String lendDate, String returnDate) {
	super();
	this.id = id;
	this.user = user;
	this.book = book;
	this.lendDate = setLendDate(lendDate);
	this.returnDate = setReturnDate(returnDate);
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
public Date getLendDate() {
	return lendDate;
}
public void setLendDate(Date lendDate) {
	this.lendDate = lendDate;
}

public Date setLendDate(String lendDate) {
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Date date = null;
	try {
		date = (Date) simpleDateFormat.parse(lendDate);
	} catch (ParseException e) {
		log.info(e.getMessage());
	}
	return date;
}

public Date getReturnDate() {
	return returnDate;
}
public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
}

public Date setReturnDate(String returnDate) {
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	Date date = null;
	try {
		date = (Date) simpleDateFormat.parse(returnDate);
	} catch (ParseException e) {
		log.info(e.getMessage());
	}
	return date;
}

public boolean isSubscribe() {
	return subscribe;
}
public void setSubscribe(boolean subscribe) {
	this.subscribe = subscribe;
}
public boolean isProcessed() {
	return processed;
}
public void setProcessed(boolean processed) {
	this.processed = processed;
}
@Override
public String toString() {
	return "Order [id=" + id + ", user=" + user + ", book=" + book + ", lendDate=" + lendDate + ", returnDate="
			+ returnDate + ", subscribe=" + subscribe + ", processed=" + processed + "]";
}





}
