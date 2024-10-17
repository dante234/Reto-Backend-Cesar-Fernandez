package com.sekk.reto.backend.application.services;

import com.sekk.reto.backend.domain.model.Role;
import com.sekk.reto.backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserSecurityServiceTest {
    @InjectMocks
    private UserSecurityService userSecurityService;

    @BeforeEach
    public void setUp() {
        // Configurar usuarios en memoria para cada prueba
        userSecurityService.setUsersInMemory( List.of(
                new User(1, "admin", "$2a$12$F1HnPgS2zsfOfmR9b6GzzOHqbKUx6bnJ/8q2w74vOyUZIlHd4FbTC", false, false, List.of(new Role(1, "ADMIN", "Administrador"))),
                new User(2, "user", "$2a$12$F1HnPgS2zsfOfmR9b6GzzOHqbKUx6bnJ/8q2w74vOyUZIlHd4FbTC", false, false, List.of(new Role(2, "USER", "Usuario")))
        ));
    }

    @Test
    public void testLoadUserByUsernameSuccess() {
        // Prueba el caso donde el usuario es encontrado y tiene éxito
        UserDetails userDetails = userSecurityService.loadUserByUsername("admin");

        assertNotNull(userDetails);
        assertEquals("admin", userDetails.getUsername());
        assertEquals("$2a$12$F1HnPgS2zsfOfmR9b6GzzOHqbKUx6bnJ/8q2w74vOyUZIlHd4FbTC", userDetails.getPassword());

        // Prueba el caso donde el usuario es encontrado y no tiene contraseña
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        // Prueba el caso donde el usuario no es encontrado y lanza una excepción
        assertThrows(UsernameNotFoundException.class, () -> {
            userSecurityService.loadUserByUsername("nonexistent");
        });
    }

    @Test
    public void testFindUser() {
        // Probar la función findUser para un usuario existente
        Optional<User> foundUser = userSecurityService.findUser("admin");

        assertTrue(foundUser.isPresent());
        assertEquals("admin", foundUser.get().getUsername());
    }



    @Test
    public void testGrantedAuthoritiesForUser() {
        // Probar las autoridades asignadas para un usuario con un rol
        UserDetails userDetails = userSecurityService.loadUserByUsername("user");

        assertNotNull(userDetails);
        assertEquals(1, userDetails.getAuthorities().size());
    }



}
