package com.example.demo.dto;

import com.example.demo.constant.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private String sku;
    private BigDecimal price;
    private String category;
    private Integer stockQuantity;
    private ProductStatus status;
    private List<ProductAssignmentDto> accountAssignments;
}
