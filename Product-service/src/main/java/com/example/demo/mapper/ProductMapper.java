package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductAssignmentDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductAssignment;

import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {}

    public static Product toEntity(ProductDto dto) {
        if (dto == null) return null;
        Product.ProductBuilder builder = Product.builder()
                .id(dto.getProductId())
                .name(dto.getName())
                .sku(dto.getSku())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .stockQuantity(dto.getStockQuantity())
                .status(dto.getStatus());
        if (dto.getAccountAssignments() != null) {
            builder.accountAssignments(dto.getAccountAssignments().stream()
                    .map(apDto -> {
                        ProductAssignment ap = ProductAssignmentMapper.toEntity(apDto);
                        ap.setProduct(builder.build()); // will set product reference
                        return ap;
                    })
                    .collect(Collectors.toList()));
        }
        return builder.build();
    }

    public static ProductDto toDto(Product entity) {
        if (entity == null) return null;
        ProductDto.ProductDtoBuilder builder = ProductDto.builder()
                .productId(entity.getId())
                .name(entity.getName())
                .sku(entity.getSku())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .stockQuantity(entity.getStockQuantity())
                .status(entity.getStatus());
        if (entity.getAccountAssignments() != null) {
            builder.accountAssignments(entity.getAccountAssignments().stream()
                    .map(ProductAssignmentMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return builder.build();
    }
}
