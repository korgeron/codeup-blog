package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Product;
import com.codeup.springblog.repos.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class ProductController {

    private ProductRepository pr;

    public ProductController(ProductRepository pr) {
        this.pr = pr;
    }

    @GetMapping("/products")
    public String allProducts(Model model) {
        List<Product> productList = pr.findAll();
        Collections.reverse(productList);
        model.addAttribute("products", productList);
        return "products/index";
    }

    @PostMapping("/products")
    public String productMethod(Long delete, Long edit, HttpSession session) {
        if (edit != null) {
            session.setAttribute("id", edit);
            return "products/edit";
        }

        if (delete != null) {
            Product p = pr.getById(delete);
            pr.delete(p);
        }
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String singleProduct(@PathVariable Long id, Model model) {
        Product p = pr.getById(id);
        model.addAttribute("product", p);
        return "products/show";
    }

    @GetMapping("/products/create")
    public String createProductPage () {
        return "/products/create";
    }

    @PostMapping("/products/create")
    public String createProduct (String name, String price) {
        if (name != null && price != null) {
            pr.save(new Product(name, String.format("$%s", price)));
        }
        return "redirect:/products";
    }

}
