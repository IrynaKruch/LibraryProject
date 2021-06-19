package ua.krucheniuk.entity;

import java.sql.Date;

public class Order {
	
private int id;
private User user;
private Book book;
private Date lendDate;
private Date returnDate;
private boolean subscribe;
private boolean processed;
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
public Date getReturnDate() {
	return returnDate;
}
public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
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
