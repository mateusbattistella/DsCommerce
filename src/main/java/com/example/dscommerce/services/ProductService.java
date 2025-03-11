package com.example.dscommerce.services;

import java.util.Optional;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dscommerce.dto.ProductDTO;
import com.example.dscommerce.entities.Product;
import com.example.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	//dependencia que busca os dados no banco
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findByID(Long id) {
		//Optional<Product> result = repository.findById(id);
		//Product product = result.get();
		//ProductDTO dto = new ProductDTO(product);
		//return dto;
		Product product = repository.findById(id).get();
		return new ProductDTO(product);
	}

}
