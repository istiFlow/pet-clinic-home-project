package hu.PetClinic.PetClinic.Service;


import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Repository.OrderRepository;
import hu.PetClinic.PetClinic.Repository.PetRepository;
import hu.PetClinic.PetClinic.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


    public Pet saveAnimal(long id, Pet pet){
        Pet animal = new Pet();
        animal.setUser(userRepository.findById(id).get());
        animal.setName(pet.getName());
        animal.setType(pet.getType());
        animal.setOrders(pet.getOrders());
        return animal;
    }
    public ResponseEntity<Void> createPet(long id, Pet pet){
        petRepository.save(saveAnimal(id,pet));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public Pet setData(Pet pet){
        Pet dog = new Pet();
        dog.setName(pet.getName());
        dog.setType(pet.getType());
        return dog;
    }

    public Pet findPetById(Long id){
        if(petRepository.findById(id).isPresent()) {
            Pet pet = petRepository.findById(id).get();
            return setData(pet);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Pet> findAll(){
        if (petRepository.findAll().size() != 0 ) {
            List<Pet> pets = petRepository.findAll();
            List<Pet> listOfPets = pets.stream().map(dog -> {
                return setData(dog);
            }).collect(Collectors.toList());
            return listOfPets;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<Void> update(Pet pet, Long id){
        if(petRepository.findById(id).isPresent()) {
            Pet animal = petRepository.findById(id).get();
                animal.setName(pet.getName());
                animal.setType(pet.getType());
                animal.setOrders(petRepository.findById(id).get().getOrders());
                animal.setUser(petRepository.findById(id).get().getUser());
            petRepository.save(animal);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    public ResponseEntity<Void> delete(Long id){
        if(petRepository.findById(id).isPresent()) {
            petRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
