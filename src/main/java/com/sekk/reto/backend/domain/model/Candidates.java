package com.sekk.reto.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidates {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Double salaryExpected;
    private String position;
    private String status;
}
