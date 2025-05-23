package com.Plants.Nature.Heaven.repository;

import com.Plants.Nature.Heaven.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Integer> {

    Product findByName(String name);
}
