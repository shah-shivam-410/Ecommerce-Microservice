package com.example.demo.mapper;

import com.example.demo.dto.ProductAssignmentDto;
import com.example.demo.entity.ProductAssignment;

public class ProductAssignmentMapper {

    private ProductAssignmentMapper() {}

    public static ProductAssignment toEntity(ProductAssignmentDto dto) {
        if (dto == null) return null;
        ProductAssignment.ProductAssignmentBuilder builder = ProductAssignment.builder()
                .id(dto.getId())
                .accountId(dto.getAccountId())
                .assignedByOrderId(dto.getAssignedByOrderId())
                .assignedAt(dto.getAssignedAt())
                ;
        // product association handled in ProductMapper when converting ProductDto -> Product
        return builder.build();
    }

    public static ProductAssignmentDto toDto(ProductAssignment entity) {
        if (entity == null) return null;
        return ProductAssignmentDto.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .productId(entity.getProduct() != null ? entity.getProduct().getId() : null)
                .assignedByOrderId(entity.getAssignedByOrderId())
                .assignedAt(entity.getAssignedAt())
                .build();
    }
}
