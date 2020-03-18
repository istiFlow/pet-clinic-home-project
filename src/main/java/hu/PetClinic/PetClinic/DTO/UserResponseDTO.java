package hu.PetClinic.PetClinic.DTO;


import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Enity.Role;
import hu.PetClinic.PetClinic.Enity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String role;
    private String password;
    private String username;
    private String address;
    private List<Pet> pets;

   /* public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }*/

}
