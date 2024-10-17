package com.sekk.reto.backend.adapter.in;

import com.sekk.reto.backend.adapter.in.dto.LoginDto;
import com.sekk.reto.backend.adapter.in.dto.RoleDto;
import com.sekk.reto.backend.adapter.in.dto.UsernameDto;
import com.sekk.reto.backend.application.services.UserSecurityService;
import com.sekk.reto.backend.config.JwtUtil;
import com.sekk.reto.backend.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Controller for Authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private UserSecurityService userSecurityService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserSecurityService userSecurityService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userSecurityService = userSecurityService;
    }

    @Operation(
            summary = "Login User",
            description = "Autorizacion y retorna JWT",
            tags = {"Authentication"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Autorizacion con usuario y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful",
                            content = @Content(
                                    mediaType = HttpHeaders.AUTHORIZATION
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<UsernameDto> login(@RequestBody LoginDto loginDto) {

        System.out.println("Login: " + loginDto.getUsername() + " - " + loginDto.getPassword());

        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(login);

            if (!authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            String jwt = this.jwtUtil.create(loginDto.getUsername());
            Optional<User> user = userSecurityService.findUser(loginDto.getUsername());
            UsernameDto usernameDto = new UsernameDto(jwt,
                    user.get().getUserId(),
                    user.get().getUsername(),
                    user.get().getDisabled(),
                    user.get().getRoles().stream().map(role -> {
                     return new RoleDto(role.getRoleName(), role.getDescription());
                    }).toList()
            );


            // Devolver el JWT en la respuesta
            return ResponseEntity.ok().body(usernameDto);

        } catch (UsernameNotFoundException e) {
            // Manejar la excepción cuando el usuario no es encontrado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UsernameDto(null,0, e.getMessage(), null, null));
        }

    }

}
