package hu.PetClinic.PetClinic.config;


import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Enity.Role;
import hu.PetClinic.PetClinic.Enity.User;
import hu.PetClinic.PetClinic.Repository.PetRepository;
import hu.PetClinic.PetClinic.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Transactional
@AllArgsConstructor
public class InitDataLoader {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        userRepository.save(User.builder()
                .username("john")
                .password(passwordEncoder.encode("admin"))
               /* .role("ASSISTANT")*/
                .firstName("John")
                .lastName("Bull")
                .address("Kinizsi utca 99, szeged 1029")
                .email("admin")
                .build());
    }

}
