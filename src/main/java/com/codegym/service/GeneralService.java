package com.codegym.service;

import java.util.List;

public interface GeneralService<T> {

    List<T> findAll();

    void addProduct(T t);
}
