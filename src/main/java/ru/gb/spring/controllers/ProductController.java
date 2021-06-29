package ru.gb.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.model.Product;
import ru.gb.spring.service.ProductService;
import java.util.List;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;
    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.showProducts();
        model.addAttribute("productList", products);
        return "product";
    }
}


