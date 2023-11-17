package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.model.Product;


public interface ProductService {
	
	List<Product> getProducts() throws SQLException;
	Product getProduct(Long id) throws SQLException;	
	void updateProduct(Long productID,String productName,Integer productPrice) throws SQLException;
	String createProduct(List<Product> products) throws SQLException;
	void deleteProductById(List<Product> products) throws SQLException;

}
