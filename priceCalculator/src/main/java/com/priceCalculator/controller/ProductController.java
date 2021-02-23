package com.priceCalculator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priceCalculator.model.Product;
import com.priceCalculator.controller.ProductController;
import com.priceCalculator.exception.ResourceNotFoundException;
import com.priceCalculator.repository.ProductRepository;

 
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

     @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
    	Product product = productRepository.findById(productId)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
    	return ResponseEntity.ok().body(product);
    }
    
    public float getProductUnitPrice(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
	    Product product = productRepository.findById(productId)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return (product.getUnitPrice());
    }
    
    public float getProductCartonPrice(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
	    Product product = productRepository.findById(productId)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        return (product.getCartonPrice());
    }
        
    @GetMapping("calculate/{productId}/{numberofUnit}/{numerOfCarton}")
    public int add(@PathVariable int numberofUnit, @PathVariable long productId,@PathVariable int numerOfCarton  ) 
    	throws ResourceNotFoundException {
	    	float unitPrice= getProductUnitPrice(productId);
	    	float cartonPrice= getProductCartonPrice(productId);
	    	int price =0;
	    
	    	if(numerOfCarton!=0 && numberofUnit ==0 ) {
	    		if(numerOfCarton >= 3) {
	    			price= (int) ((cartonPrice*numerOfCarton)*.9);
	    		}else {
	    			price= (int) (cartonPrice*numerOfCarton);
	    		}
	    	}
	    	
	    	if(numberofUnit !=0 && numerOfCarton==0) {
	    		price= (int) ((unitPrice*numberofUnit)*1.3);
	    	}
	    	
	    	if(numerOfCarton!=0 && numberofUnit !=0 ) {
	    		if(numerOfCarton >= 3) {
	    			price= (int) (((cartonPrice*numerOfCarton)+(unitPrice*numberofUnit))*.9);
	    		}else {
	    			price= (int) ((cartonPrice*numerOfCarton)+(unitPrice*numberofUnit));
	    		}
	    	}	   		
				return price;
    	}
            
}