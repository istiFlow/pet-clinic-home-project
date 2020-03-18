package hu.PetClinic.PetClinic.DTO;

import hu.PetClinic.PetClinic.Enity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OwnerRegistrationResponseDTO {

    private String firstName;
    private String lastName;
    private String email; //unique kell legyen mert ez alapjan fogom ellenorizni a usert h szerepel e amar a db -ben vagy sem.
    private String address;
    private LocalDate dob;
    private Role role;
}
