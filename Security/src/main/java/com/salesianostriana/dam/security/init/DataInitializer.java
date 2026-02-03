package com.salesianostriana.dam.security.init;

import com.salesianostriana.dam.security.model.User;
import com.salesianostriana.dam.security.model.UserRole;
import com.salesianostriana.dam.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuario normal
        if (!userRepository.findByUsername("user").isPresent()) {
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("password"))
                    .active(true)
                    .roles(Set.of(UserRole.USER))
                    .build();
            userRepository.save(user);
            System.out.println("Usuario creado - username: user, password: password");
        }

        // Crear administrador
        if (!userRepository.findByUsername("admin").isPresent()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .active(true)
                    .roles(Set.of(UserRole.ADMIN, UserRole.USER))
                    .build();
            userRepository.save(admin);
            System.out.println("Administrador creado - username: admin, password: admin");
        }
    }

}
