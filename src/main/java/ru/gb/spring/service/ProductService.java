package ru.gb.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.spring.exeptions.ResourceNotFoundException;
import ru.gb.spring.model.Product;
import ru.gb.spring.repo.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
   // List<Product> productsList = productRepository.getProducts();

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This product does not exist - " + id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(Double minCost, Double maxCost) {
        List<Product> out = findAll();
        if (minCost != null) {
            out = out.stream().filter(p -> p.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(p -> p.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

    public Product saveOrUpdate(Product p){
        return productRepository.saveOrUpdate(p);
    }

    public void addProducts(long id, String title, int cost) {
        productRepository.saveOrUpdate(new Product(id, title, cost));
    }

    public List<Product> showProducts() {
        System.out.println(productRepository.getProducts());
        return productRepository.getProducts();
    }

    public void deleteBydId(Long id) {
        productRepository.deleteById(id);
    }
}
