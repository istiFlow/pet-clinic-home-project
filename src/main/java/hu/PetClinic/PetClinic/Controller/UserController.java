package hu.PetClinic.PetClinic.Controller;


import hu.PetClinic.PetClinic.DTO.UserLoginDTO;
import hu.PetClinic.PetClinic.DTO.UserRequestDTO;
import hu.PetClinic.PetClinic.DTO.UserResponseDTO;
import hu.PetClinic.PetClinic.Enity.User;
import hu.PetClinic.PetClinic.Repository.UserRepository;
import hu.PetClinic.PetClinic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

@Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO userById(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public UserResponseDTO userLogin(@RequestBody UserLoginDTO userLoginDTO){
    return userService.loginUser(userLoginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser (@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user){
        return userService.update(user,id);
    }

    @GetMapping("/get")
    public List<User> getClient(@RequestParam(value = "client") String client) {
        return userService.findClient(client);
    }

    @GetMapping("/doctor")
    public List<User> getDoctor(@RequestParam(value = "doctor") String doctor) {
        return userService.findClient(doctor);
    }

    @GetMapping("/a")
    public List<User> getAssistant(@RequestParam(value = "assistant") String assistant) {
        return userService.findClient(assistant);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
