package org.example.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_id")
    @SequenceGenerator(
    		name = "seq_address_id",
    		sequenceName = "seq_address_id",
    		initialValue = 1,
    		allocationSize = 50
    	)
    private Long id;

    private String street;

    private String city;

    private String state;

    private String country;

    @Column(name = "postal_code")
    private String postalCode;

}

