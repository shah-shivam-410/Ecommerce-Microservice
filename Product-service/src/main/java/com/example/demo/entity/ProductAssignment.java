package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
	name = "product_assignment", 
	uniqueConstraints = @UniqueConstraint(columnNames = { 
			"account_id", "product_id",
			"assigned_by_order_id" 
	})
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAssignment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_assignment_id")
	@SequenceGenerator(name = "seq_product_assignment_id", sequenceName = "seq_product_assignment_id", initialValue = 1, allocationSize = 50)
	Long id;

	@Column(nullable = false)
	private Long accountId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	// Tractability — which order triggered this assignment
    @Column(nullable = false)
    private Long assignedByOrderId;

	@Column(nullable = false)
    private LocalDateTime assignedAt;
	
}
