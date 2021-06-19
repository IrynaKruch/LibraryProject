package ua.krucheniuk.dao.impl;

import ua.krucheniuk.dao.model.BookDao;
import ua.krucheniuk.dao.model.DaoFactory;
import ua.krucheniuk.dao.model.OrderDao;
import ua.krucheniuk.dao.model.UserDao;

public class DaoFactoryMYSQL extends DaoFactory {

	@Override
	public UserDao createUserDao() {
		return UserDaoMySQL.getInstance();
	}

	@Override
	public BookDao createBookDao() {
		return BookDaoMySQL.getInstance();
	}

	@Override
	public OrderDao createOrderDao() {
		return OrderDaoMySQL.getInstance();
	}

}
