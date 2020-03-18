package hu.PetClinic.PetClinic.Repository;

import hu.PetClinic.PetClinic.Enity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {


    @Query("from Pet p where p.user.id = ?1")
    List<Pet> getAllPetByUser(Long id);

    Pet findByName(String name);
}
