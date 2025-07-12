package com.example.dscommerce.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dscommerce.dto.ProductDTO;
import com.example.dscommerce.entities.Product;
import com.example.dscommerce.repositories.ProductRepository;
import com.example.dscommerce.services.exceptions.ResourceNotFoundException;

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
		Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProductDTO(product);
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {		
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {		
		Product entity = new Product();
		copyDtoToEntity(dto, entity);		
		entity = repository.save(entity);
		
		//retorna o objeto salvo e atualizado
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {	
		
		//não vai no banco, prepara o objeto monitorado pela JPA
		Product entity = repository.getReferenceById(id);				
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		//retorna o objeto salvo e atualizado
		return new ProductDTO(entity);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
	
	@Transactional
	public void delete(Long id) {		
		repository.deleteById(id);
		
	}

}
