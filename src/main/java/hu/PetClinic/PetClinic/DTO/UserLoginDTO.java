package hu.PetClinic.PetClinic.DTO;


import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Enity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//login
public class UserLoginDTO {
       private String email;
     private String password;

}
