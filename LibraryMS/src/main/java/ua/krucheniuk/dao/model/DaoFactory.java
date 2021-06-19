package ua.krucheniuk.dao.model;

import ua.krucheniuk.dao.impl.DaoFactoryMYSQL;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract BookDao createBookDao();
    public abstract OrderDao createOrderDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new DaoFactoryMYSQL();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
