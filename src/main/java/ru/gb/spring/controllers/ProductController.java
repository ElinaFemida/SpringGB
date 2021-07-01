package ru.gb.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ProductService productService;
    @GetMapping()
    private String showAll(Model model,
                           @RequestParam(required = false, name = "min_cost") Double minCost,
                           @RequestParam(required = false, name = "max_cost") Double maxCost
    ){
        model.addAttribute("products", productService.findAll(minCost, maxCost));
        return "products";
    }
    @GetMapping("/test")
    @ResponseBody
    public Product getById(@RequestParam Long id){
        return productService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable Long id) {
        productService.deleteBydId(id);
        return "redirect:/products";
    }
}


