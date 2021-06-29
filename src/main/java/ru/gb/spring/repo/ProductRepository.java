package ru.gb.spring.repo;

import org.springframework.stereotype.Component;
import ru.gb.spring.model.Product;


import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Predicate;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "lesson_2.Product 1", 100),
                new Product(2L, "lesson_2.Product 2", 200),
                new Product(3l, "lesson_2.Product 3", 300),
                new Product(3L, "lesson_2.Product 4", 400),
                new Product(3l, "lesson_2.Product 5", 500)
        ));
    }
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product saveOrUpdate(Product p) {
        if (p.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(p.getId())) {
                    products.set(i, p);
                    return p;
                }
            }
        }

        Long newId = products.stream().mapToLong(Product::getId).max().orElseGet(() -> 0L) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }


    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }


    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

    }

    public void deleteById(Long id) {
        products.removeIf(s -> s.getId().equals(id));
    }
}
