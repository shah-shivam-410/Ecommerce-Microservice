package org.example.accountservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
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

