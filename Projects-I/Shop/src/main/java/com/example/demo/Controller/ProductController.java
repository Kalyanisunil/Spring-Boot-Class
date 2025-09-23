package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

//import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Models.Product;
import com.example.demo.Repository.ProductRepository;

import org.springframework.data.repository.query.Param;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/create")
    public String createAction(Model model) {
        model.addAttribute("message", "Enter The Product Details");
        return "Products";
    }

    @PostMapping("/create")
    public String createActionProcess(Product productData, Model model) {
        productRepository.save(productData);
        model.addAttribute("message", "The Product " + productData.getName() + " has been created successfully");
        return "Products";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model, @Param("keyword") String keyword) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productRepository.findAllByKeyword(keyword);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        return "list";
    }
    
    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        Optional<Product> optionalProductDetails = productRepository.findById(id);
        if (optionalProductDetails.isPresent()) {
            model.addAttribute("productDetails", optionalProductDetails.get());
            return "update";
        }
        return "redirect:/all"; // Handle not found case
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Product productData) {
        Optional<Product> optionalProductDetails = productRepository.findById(id);
        if (optionalProductDetails.isPresent()) {
            Product productDetails = optionalProductDetails.get();
            productDetails.setName(productData.getName());
            productDetails.setDescription(productData.getDescription());
            productDetails.setExpirydate(productData.getExpirydate());
             productDetails.setPrice(productData.getPrice());
            productRepository.save(productDetails);
        }
        return "redirect:/all";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        Optional<Product> optionalProductDetails = productRepository.findById(id);
        if (optionalProductDetails.isPresent()) {
            model.addAttribute("productDetails", optionalProductDetails.get());
            return "delete";
        }
        return "redirect:/all"; // Handle not found case
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
        return "redirect:/all";
    }
}