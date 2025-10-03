package com.example.myapp.Controller;

import com.example.myapp.Models.Product;
import com.example.myapp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3002")
public class ProductAPIController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping("api/addproduct")
    Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }
}