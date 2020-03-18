package hu.PetClinic.PetClinic.Controller;


import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable Long id){
        return petService.findPetById(id);
    }

    @GetMapping("/pets")
    public List<Pet> getAllPet(){
        return petService.findAll();
    }

    @PostMapping("/users/pets/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody Pet pet){
        return petService.createPet(id,pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Pet pet){
        return petService.update(pet,id);
    }

    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable Long id){
        petService.delete(id);
    }
}
