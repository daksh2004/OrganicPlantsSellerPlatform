package com.Plants.Nature.Heaven.service;

import com.Plants.Nature.Heaven.entity.Product;
import com.Plants.Nature.Heaven.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> getProduct(){
        return repository.findAll();
    }

    public Product getProductById(int ProductID){
        return repository.findById(ProductID).orElse(null);
    }

    public Product getProductByName(String name){
        return repository.findByName(name);
    }

    public String deleteProduct(int ProductID){
        repository.deleteById(ProductID);
        return "Product removed !! "+ProductID;
    }

    // Update product
    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getProductID()).orElse(null);

        if (existingProduct == null) {
            throw new RuntimeException("User with ID " + product.getProductID() + " not found.");
        }

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock_quantity(product.getStock_quantity());

        return repository.save(existingProduct);
    }
}
