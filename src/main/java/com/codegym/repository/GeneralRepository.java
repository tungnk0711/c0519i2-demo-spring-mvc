package com.codegym.repository;

import java.util.List;

public interface GeneralRepository<T> {

    List<T> findAll();

    void addProduct(T t);

    T findById(Long id);
}
