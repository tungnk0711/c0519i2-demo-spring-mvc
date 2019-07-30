package com.codegym.repository;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements GeneralRepository<Product> {

    ArrayList<Product> productList = new ArrayList<>();


    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public Product findById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }
}
