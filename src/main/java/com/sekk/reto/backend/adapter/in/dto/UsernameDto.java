package com.sekk.reto.backend.adapter.in.dto;

import java.util.List;

public record UsernameDto(
        String token,
        int id,
        String username,
        Boolean isValid,
        List<RoleDto> role

) {
}
