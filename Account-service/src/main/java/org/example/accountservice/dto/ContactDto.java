package org.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long accountId;
}

