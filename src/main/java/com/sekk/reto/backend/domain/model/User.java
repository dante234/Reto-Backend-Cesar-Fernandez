package com.sekk.reto.backend.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String username;
    private String password;
    private Boolean locked;
    private Boolean disabled;
    private List<Role> roles;

}
