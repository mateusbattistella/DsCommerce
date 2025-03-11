package com.example.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dscommerce.entities.Product;

//repository - componente de acesso a dados
public interface ProductRepository extends JpaRepository<Product, Long> {

}
