package com.codegym.repository;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements GeneralRepository<Product>{

    ArrayList<Product> productList = new ArrayList<>();

    {
        productList.add(new Product(1,"Samsung"));
        productList.add(new Product(2,"Iphone"));
        productList.add(new Product(3,"Nokia"));
        productList.add(new Product(4,"OPPO"));
        productList.add(new Product(5,"BlackBerry"));
        productList.add(new Product(6,"Sony"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }
}
