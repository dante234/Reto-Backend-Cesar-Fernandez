package com.sekk.reto.backend.application.services;

import com.sekk.reto.backend.domain.model.Role;
import com.sekk.reto.backend.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    // Lista de usuarios en memoria
    private static List<User> usersInMemory = new ArrayList<>(Arrays.asList(
            new User(1,"admin", "$2a$12$F1HnPgS2zsfOfmR9b6GzzOHqbKUx6bnJ/8q2w74vOyUZIlHd4FbTC",  false, false,List.of(new Role(1,"ADMIN", "Administrador"))),
            new User(2,"user", "$2a$12$F1HnPgS2zsfOfmR9b6GzzOHqbKUx6bnJ/8q2w74vOyUZIlHd4FbTC", false, false, List.of(new Role(2,"USER", "Usuario")))
    ));

    public void setUsersInMemory(List<User> users) {
        usersInMemory = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = this.findUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));



        String[] roles = userEntity.getRoles().stream().map(Role::getRoleName).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisabled())
                .build();
    }
    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[] {"random_order"};
        }

        return new String[] {};
    }
    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority: this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }

    // Método para encontrar un usuario en la lista en memoria
    public Optional<User> findUser(String username) {
        return usersInMemory.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
