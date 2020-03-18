package hu.PetClinic.PetClinic.Repository;

import hu.PetClinic.PetClinic.Enity.Order;
import hu.PetClinic.PetClinic.Enity.ServiceNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
//@Transactional
public interface ServiceNoteRepository extends JpaRepository<ServiceNote,Long> {


   /* @Query("from ServiceNote o where o.orders.id = ?1");
    List<ServiceNote> getAllServicesByOrder(Long id);*/
}
