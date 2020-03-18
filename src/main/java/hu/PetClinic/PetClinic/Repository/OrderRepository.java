package hu.PetClinic.PetClinic.Repository;

import hu.PetClinic.PetClinic.Enity.Order;
import hu.PetClinic.PetClinic.Enity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
//@Transactional lehet kell a manytomanyhez
public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query("from Order o where o.pet.id = ?1")
    List<Order> getAllOrdersByPet(Long id);

}
