package ua.krucheniuk.entity;

public class Book {
	
private int id;
private String name;
private String author;
private String edition;
private int yearOfEd;
private int quantity;



public Book() {
	
}
public Book(String name, String author, String edition, int yearOfEd, int quantity) {
	this.name = name;
	this.author = author;
	this.edition = edition;
	this.yearOfEd = yearOfEd;
	this.quantity = quantity;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEdition() {
	return edition;
}
public void setEdition(String edition) {
	this.edition = edition;
}
public int getYearOfEd() {
	return yearOfEd;
}
public void setYearOfEd(int yearOfEd) {
	this.yearOfEd = yearOfEd;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Override
public String toString() {
	return "Book [id=" + id + ", name=" + name + ", author=" + author +", edition=" + edition + ", yearOfEd=" + yearOfEd + ", quantity="
			+ quantity + "]";
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
}
