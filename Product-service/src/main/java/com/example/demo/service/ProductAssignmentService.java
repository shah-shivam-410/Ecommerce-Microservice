package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductAssignmentDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductAssignment;
import com.example.demo.mapper.ProductAssignmentMapper;
import com.example.demo.repo.ProductAssignmentRepository;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductAssignmentService {

    private final ProductAssignmentRepository productAssignmentRepository;
    private final ProductRepository productRepository;

    public ProductAssignmentService(ProductAssignmentRepository productAssignmentRepository, ProductRepository productRepository) {
        this.productAssignmentRepository = productAssignmentRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductAssignmentDto assign(ProductAssignmentDto dto) {
        ProductAssignment entity = ProductAssignmentMapper.toEntity(dto);
        // Ensure productId provided and load the Product entity to satisfy the non-null relationship
        if (dto.getProductId() == null) {
            throw new IllegalArgumentException("productId is required");
        }
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + dto.getProductId()));
        entity.setProduct(product);

        if (entity.getAssignedAt() == null) {
            entity.setAssignedAt(LocalDateTime.now());
        }
        ProductAssignment saved = productAssignmentRepository.save(entity);
        return ProductAssignmentMapper.toDto(saved);
    }

    public Optional<ProductAssignmentDto> getById(Long id) {
        return productAssignmentRepository.findById(id).map(ProductAssignmentMapper::toDto);
    }

    public List<ProductAssignmentDto> listAll() {
        return productAssignmentRepository.findAll().stream().map(ProductAssignmentMapper::toDto).collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        if (!productAssignmentRepository.existsById(id)) return false;
        productAssignmentRepository.deleteById(id);
        return true;
    }
}