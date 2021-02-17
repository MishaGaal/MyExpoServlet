package com.example.myexpo.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Optional<T> create(T entity);

    Optional<T> findById(int id);

    Optional<List<T>> findAll();

    Optional<T> update(T entity);

    Optional<T> delete(T entity);
}
