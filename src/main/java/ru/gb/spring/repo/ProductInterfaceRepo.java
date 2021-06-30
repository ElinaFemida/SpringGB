package ru.gb.spring.repo;

import ru.gb.spring.model.Product;

public interface ProductInterfaceRepo {
    void createdProduct(Product product);

    void readProduct(Long id);

    void updateProduct(Product product);

    void deletedProduct(Long id);
}