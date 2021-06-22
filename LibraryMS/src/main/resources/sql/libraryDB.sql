CREATE DATABASE IF NOT EXISTS library;

USE library;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS books;


CREATE TABLE books(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL, 
	author VARCHAR(50) NOT NULL,
	edition VARCHAR (300), 
	yearOfEd YEAR,
	quantity INT,
	UNIQUE (name, author)
	
);

INSERT INTO books VALUES(DEFAULT, 'Forest Song','Ukrainka Lesia', 'Bookman Associates', '2005',5); 
INSERT INTO books VALUES(DEFAULT, 'KOBZAR','Shevchenko Taras', 'Glagoslav Publications', '2013',2);
INSERT INTO books VALUES(DEFAULT, 'Tygrolovy','Ivan Bahriany', 'Chas maystriv', '2016',3);
INSERT INTO books VALUES(DEFAULT, 'Pride and Prejudice','Austen Jane', 'Penguin Classics', '2012',3);
INSERT INTO books VALUES(DEFAULT, 'The Great Gatsby','Fitzgerald F. Scott', 'Penguin Classics', '2000',2);
INSERT INTO books VALUES(DEFAULT, 'Frankenstein','Shelley Mary', 'Penguin Classics', '2012',4);
INSERT INTO books VALUES(DEFAULT, 'Don Quixote','Miguel de Cervantes', 'Ecco', '2005',6);

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
fullname VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL UNIQUE,
login VARCHAR(30) NOT NULL UNIQUE,
password VARCHAR(30) NOT NULL,
role ENUM ('READER','ADMIN','LIBRARIAN','BLOCKED') DEFAULT 'READER'
);

INSERT INTO users VALUES(DEFAULT, 'Iryna', 'ira@ukr.net','ira', 'Qwerty1', 'ADMIN');
INSERT INTO users VALUES(DEFAULT, 'Anna Nikolaevna', 'ann@gmail.com','Anna', 'Password2', 'LIBRARIAN');
INSERT INTO users VALUES(DEFAULT, 'Svetlana', 'sveta@ukr.net', 'sveta', 'Sveta3', 'READER');


CREATE TABLE orders (
id INT PRIMARY KEY AUTO_INCREMENT,
readerId INT NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
bookId INT NOT NULL REFERENCES books(id) ON DELETE CASCADE ON UPDATE CASCADE,
lendDate DATE DEFAULT NULL,
returnDate DATE DEFAULT NULL,
subscribe bit(1) DEFAULT 0,
processed bit(1) DEFAULT 0
);

