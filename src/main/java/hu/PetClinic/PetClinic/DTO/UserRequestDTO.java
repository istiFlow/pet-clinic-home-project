package hu.PetClinic.PetClinic.DTO;


import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Enity.Role;
import hu.PetClinic.PetClinic.Enity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//login
public class UserRequestDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private LocalDate dob;
    private String role;
    private List<Pet> pets;
    public User toEntity() {
        return User.builder().id(id).username(username).password(password).build();
    }
}
