package com.example.demo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping()
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	@GetMapping("/products")
	List<Product> getProducts() throws SQLException{
		return productService.getProducts();
	}
	
	
	@GetMapping("/products/{id}")
	Product getProduct(@PathVariable("id") Long id) throws SQLException{
		return productService.getProduct(id);
	}

	
	@PostMapping(value = "/products")
	public ResponseEntity<String> createProduct(@RequestParam(required= false) Long id, @RequestParam(required= false) String name, @RequestParam(required= false) Integer price ,@RequestBody(required= false) List<Product> products) throws SQLException {
		if (products != null) {
			return ResponseEntity.ok(productService.createProduct(products));
		}	

		return ResponseEntity.ok(productService.createProduct(createArrayListProduct(id,name,price)));
	}


	@PutMapping("/products/{id}")
	public Map<String, Object> updateProduct(@PathVariable(value="id") Long id, @RequestParam(required= false) String name, @RequestParam(required= false) Integer price,  @RequestBody(required= false) Product product) throws SQLException{
		if (product != null) {
			name = product.getProductName();
			price = product.getProductPrice();
			
		}
		productService.updateProduct(id, name, price);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status","Product udated!");
		
		return map;	
	}
	
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable(value="id") Long id) throws SQLException {
		
		productService.deleteProductById(createArrayListProduct(id,null,null));
		return ResponseEntity.ok("Product deleted successfully");
		
	}
	
	
	@DeleteMapping("/products")
	public ResponseEntity<String> deleteProductsById(@RequestBody(required= false) List<Product> products) throws SQLException {
		
		productService.deleteProductById(products);
		return ResponseEntity.ok("Products deleted successfully");
		
	}
	
	
	public List<Product> createArrayListProduct(Long id, String name, Integer price) {
		List<Product> paramProduct = new ArrayList<>();
		Product newProduct = new Product();
		newProduct.setProductID(id);
		if(name != null) {
			newProduct.setProductName(name);
		}
		if(price != null) {
			newProduct.setProductPrice(price);
		}
		paramProduct.add(newProduct);
		return paramProduct;
	}
	
	
}
