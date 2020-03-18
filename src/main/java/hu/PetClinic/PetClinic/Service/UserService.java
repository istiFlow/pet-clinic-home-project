package hu.PetClinic.PetClinic.Service;
import hu.PetClinic.PetClinic.DTO.UserLoginDTO;
import hu.PetClinic.PetClinic.DTO.UserRequestDTO;
import hu.PetClinic.PetClinic.DTO.UserResponseDTO;
import hu.PetClinic.PetClinic.Enity.User;
import hu.PetClinic.PetClinic.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;



    

    public UserResponseDTO getUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
                UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setEmail(user.getEmail());
                    userResponseDTO.setFirstName(user.getFirstName());
                    userResponseDTO.setLastName(user.getLastName());
                    userResponseDTO.setId(user.getId());
                    userResponseDTO.setDob(user.getDob());
                    userResponseDTO.setAddress(user.getAddress());
                    userResponseDTO.setRole(user.getRole());
                return userResponseDTO;
        } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }




    public ResponseEntity<Void> createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.getEmail()) == null) {
           /* userRequestDTO.toEntity();*/
            User user = new User();
            if (userRequestDTO.getPassword() != null) {
                String passw = BCrypt.hashpw(userRequestDTO.getPassword(), BCrypt.gensalt());
                user.setPassword(passw);
            }
            user.setRole(userRequestDTO.getRole());
            user.setUsername(userRequestDTO.getUsername());
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setEmail(userRequestDTO.getEmail());
            user.setDob(userRequestDTO.getDob());
            user.setAddress(userRequestDTO.getAddress());
            user.setPets(userRequestDTO.getPets());
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            throw new ValidationException("check you email");
        }
    }


    public ResponseEntity<Void> update(User user, Long id) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            user.setUsername(userRepository.findById(id).get().getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



    public ResponseEntity<List<UserResponseDTO>>findAll(){
        if (userRepository.findAll().size() > 0) {
            List<User> users = userRepository.findAll();
            List<UserResponseDTO> userResponseDTOS = users.stream().map(user -> {
                UserResponseDTO userResponseDTO = new UserResponseDTO();
                userResponseDTO.setRole(user.getRole());
                userResponseDTO.setDob(user.getDob());
                userResponseDTO.setEmail(user.getEmail());
                userResponseDTO.setFirstName(user.getFirstName());
                userResponseDTO.setLastName(user.getLastName());
                userResponseDTO.setId(user.getId());
                userResponseDTO.setUsername(user.getUsername());
                userResponseDTO.setPets(user.getPets());
                userResponseDTO.setAddress(user.getAddress());
                userResponseDTO.setPassword(user.getPassword());
                return userResponseDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userResponseDTOS);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public UserResponseDTO findById(Long id){
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setRole(user.getRole());
            userResponseDTO.setDob(user.getDob());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setFirstName(user.getFirstName());
            userResponseDTO.setLastName(user.getLastName());
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setPets(user.getPets());
            return userResponseDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<User> findClient(String role){
        return  userRepository.findByRole(role);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public UserResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        if (userRepository.findByEmail(userLoginDTO.getEmail()) != null) {
            User u = userRepository.findByEmail(userLoginDTO.getEmail());
            if (BCrypt.checkpw(userLoginDTO.getPassword(), u.getPassword())) {
                UserResponseDTO uDTO = new UserResponseDTO();
                uDTO.setEmail(u.getEmail());
                uDTO.setFirstName(u.getFirstName());
                uDTO.setLastName(u.getLastName());
                uDTO.setId(u.getId());
                uDTO.setAddress(u.getAddress());
                uDTO.setDob(u.getDob());
                uDTO.setRole(u.getRole());
                uDTO.setPassword(u.getPassword());
                return uDTO;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
