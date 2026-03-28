package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductAssignment;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repo.ProductAssignmentRepository;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductAssignmentRepository productAssignmentRepository;

    public ProductService(ProductRepository productRepository, ProductAssignmentRepository productAssignmentRepository) {
        this.productRepository = productRepository;
        this.productAssignmentRepository = productAssignmentRepository;
    }

    @Transactional
    public ProductDto create(ProductDto dto) {
        Product product = ProductMapper.toEntity(dto);
        // persist product first so it gets an ID for account assignments
        Product saved = productRepository.save(product);
        // ensure account assignments point to saved product and save them if any
        if (saved.getAccountAssignments() != null) {
            for (ProductAssignment ap : saved.getAccountAssignments()) {
                ap.setProduct(saved);
                if (ap.getId() == null) {
                    productAssignmentRepository.save(ap);
                }
            }
        }
        return ProductMapper.toDto(productRepository.findById(saved.getId()).orElse(saved));
    }

    public Optional<ProductDto> getById(Long id) {
        return productRepository.findById(id).map(ProductMapper::toDto);
    }

    public List<ProductDto> list() {
        return productRepository.findAll().stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
