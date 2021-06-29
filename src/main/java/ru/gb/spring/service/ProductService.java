package ru.gb.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.spring.exeptions.ResourceNotFoundException;
import ru.gb.spring.model.Product;
import ru.gb.spring.repo.ProductRepository;


import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    List<Product> productsList = productRepository.getProducts();

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This product does not exist - " + id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void addProducts(long id, String title, int cost) {
        productRepository.saveOrUpdate(new Product(id, title, cost));
    }


    public List<Product> showProducts() {
        System.out.println(productRepository.getProducts());
        return productRepository.getProducts();
    }

    public int calculateAverageCost() {
        int avg = 0;
        for (Product product : productsList) {
            avg += product.getCost();
        }
        avg /= productsList.size();
        return avg;
    }

    public int calculateTotalNumber(){
        return productsList.size();
    }

    public void deleteBydId(Long id) {
        productRepository.deleteById(id);
    }
}
