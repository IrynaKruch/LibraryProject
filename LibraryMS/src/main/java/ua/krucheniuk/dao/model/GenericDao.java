package ua.krucheniuk.dao.model;

import java.util.List;

public interface GenericDao<T> {
    String create (T entity);
    T findById(int id);
    List<T> findAll();
    String update(T entity);
    void delete(T entity);
}