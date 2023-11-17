package com.example.demo.service;

import java.util.ArrayList;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;


@Service
public class ProductServiceImp implements ProductService {
	private static final String dbUrl = "jdbc:mysql://localhost:3306/products";
	private static final String user = "product_user";		
	private static final String pass = "product_user";


	@Override
	public List<Product> getProducts() throws SQLException {
		String myQuery = "select * from product ";
		List<Product> products = new ArrayList<Product>();
		products = mySqlQuery(dbUrl, user, pass, myQuery);
		return  products;
	}

	
	@Override
	public Product getProduct(Long id) throws SQLException {
		Product product = getProductIDSql( id);
		return  product;
	}


	@Override
	public String createProduct(List<Product> products) throws SQLException {
		String returnMessage = insertProductSql(products);
		return returnMessage;
	}


	@Override
	public void updateProduct(Long productID, String productName, Integer productPrice) throws SQLException {
		updateProductSql(productID, productName, productPrice);
		
	}


	@Override
	public void deleteProductById(List<Product> products) throws SQLException {
		
		deleteProductSql(products);
	}
	
	
	private static List<Product> mySqlQuery(String dbUrl, String user, String pass, String myQuery ) throws SQLException {
		List<Product> products = new ArrayList<>();
		
		try (Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
			     Statement myStmt = myConn.createStatement();
			     ResultSet myRs = myStmt.executeQuery(myQuery)) {
			 
            while (myRs.next()) {
                Long id = myRs.getLong("id");
                String productName = myRs.getString("product_name");
                Integer productPrice = myRs.getInt("product_price");

                Product product = new Product(id, productName, productPrice);
                products.add(product);
            	}
           
		} catch (Exception exc) {
			    exc.printStackTrace();
		}
		return products;	
	}
	
	
	public Product getProductIDSql(Long id) throws SQLException {
	    
	    String query = "SELECT * FROM product WHERE id=?";
	    
	    try (Connection connection = DriverManager.getConnection(dbUrl, user, pass);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setLong(1, id);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                Long productId = resultSet.getLong("id");
	                String productName = resultSet.getString("product_name");
	                Integer productPrice = resultSet.getInt("product_price");
	                return new Product(productId, productName, productPrice);
	            }
	        }
	        
	    } catch (SQLException exc) {
	        exc.printStackTrace();
	    }
	    return new Product();
	}
	
	
	private static String insertProductSql(List<Product> products) throws SQLException {
		
		String query = "insert into product(id,product_name,product_price) values(?,?,?)";
		String returnMessage ="done!";
		try (Connection connection = DriverManager.getConnection(dbUrl, user, pass);
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			for(Product product : products){
			    
				preparedStatement.setLong(1, product.getProductID());
				preparedStatement.setString(2,product.getProductName());
				preparedStatement.setInt(3, product.getProductPrice());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		        
		        
		} catch (SQLIntegrityConstraintViolationException e) {
			    System.out.println("The product is already exist with this id");
			    returnMessage = "You can't insert the product with id which already exist!";
			
		} catch (SQLException exc) {
		        exc.printStackTrace();
		}
		
		return returnMessage;    
	}


	
	private static void updateProductSql(Long id, String name_product, Integer product_price) throws SQLException {
	    String query = "UPDATE product SET ";
    
	    if (name_product != null) {
	        query += "product_name = ? ";
	    }
	    if (product_price != null) {
	        query += ", product_price = ? ";
	    }
	    query += "WHERE id = ?";

	    try (Connection connection = DriverManager.getConnection(dbUrl, user, pass);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        int parameterIndex = 1;
	        if (name_product != null) {
	            preparedStatement.setString(parameterIndex++, name_product);
	        }
	        if (product_price != null) {
	            preparedStatement.setInt(parameterIndex++, product_price);
	        }
	        preparedStatement.setLong(parameterIndex, id);

	        preparedStatement.executeUpdate();

	    } catch (SQLException exc) {
	        exc.printStackTrace();
	    }
	}


	public void deleteProductSql(List<Product> products) throws SQLException {
	    
	    String query = "DELETE FROM product WHERE id=?";
	    
	    try (Connection connection = DriverManager.getConnection(dbUrl, user, pass);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	    	
	    	for(Product product : products){
	    		preparedStatement.setLong(1, product.getProductID());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
	        
	    } catch (SQLException exc) {
	        exc.printStackTrace();
	    }
	   
	}











}
