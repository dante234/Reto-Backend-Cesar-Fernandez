package com.sekk.reto.backend.adapter.in;

import com.sekk.reto.backend.adapter.in.dto.LoginDto;
import com.sekk.reto.backend.adapter.in.dto.UsernameDto;
import com.sekk.reto.backend.application.services.UserSecurityService;
import com.sekk.reto.backend.config.JwtUtil;
import com.sekk.reto.backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserSecurityService userSecurityService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    private LoginDto loginDto;
    private User user;

    @BeforeEach
    public void setUp() {
        // Configurar el DTO de login y el usuario
        loginDto = new LoginDto("testUser", "testPassword");
        user = new User(1, "testUser", "testPassword", false,false, List.of(new com.sekk.reto.backend.domain.model.Role(1,"USER", "Basic user")));
    }

    @Test
    public void testLoginSuccess() {
        // Configurar mocks
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(jwtUtil.create(eq("testUser"))).thenReturn("mockedJwtToken");
        when(userSecurityService.findUser(eq("testUser"))).thenReturn(Optional.of(user));

        // Llamar al método de login
        ResponseEntity<UsernameDto> response = authController.login(loginDto);

        // Verificar la respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mockedJwtToken", response.getBody().token());
        assertEquals(user.getUsername(), response.getBody().username());
    }

    @Test
    public void testLoginUserNotFound() {
        // Configurar mocks
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new UsernameNotFoundException("User not found"));

        // Llamar al método de login
        ResponseEntity<UsernameDto> response = authController.login(loginDto);

        // Verificar la respuesta
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testLoginUnauthorized() {
        // Configurar mocks para un login fallido
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(false);

        // Llamar al método de login
        ResponseEntity<UsernameDto> response = authController.login(loginDto);

        // Verificar la respuesta
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testLoginException() {
        // Configurar mocks para una excepción
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new RuntimeException("Unexpected error"));

        // Llamar al método de login
        ResponseEntity<UsernameDto> response = authController.login(loginDto);

        // Verificar la respuesta
        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Unexpected error", response.getBody().username());
    }
}
