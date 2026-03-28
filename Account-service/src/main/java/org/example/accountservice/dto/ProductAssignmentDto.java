package org.example.accountservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAssignmentDto {
    private Long id;
    private Long accountId;
    private Long productId;
    private Long assignedByOrderId;
    private LocalDateTime assignedAt;
}
