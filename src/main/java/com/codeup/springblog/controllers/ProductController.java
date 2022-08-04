package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Product;
import com.codeup.springblog.repos.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private ProductRepository pr;

    public ProductController(ProductRepository pr){
        this.pr = pr;
    }

    @GetMapping("/products")
    public String allProducts(Model model){
        List<Product> productList = pr.findAll();
        model.addAttribute("products", productList);
        return "products/index";
    }

}
