package hu.PetClinic.PetClinic.Service;

import hu.PetClinic.PetClinic.Enity.Order;
import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Repository.OrderRepository;
import hu.PetClinic.PetClinic.Repository.PetRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;

    public ResponseEntity<Void> createOrder(Order order, Long id) {
        if (petRepository.findById(id).isPresent()) {
            petRepository.findById(id).map(pet -> {
                pet.getOrders().add(order);
                order.setPet(pet);
                return orderRepository.save(order);
            });
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        if(orderRepository.findById(id).isPresent()){
            return orderRepository.findById(id).get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Order> getAllOrdersByPet(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            return orderRepository.getAllOrdersByPet(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> update(Long id, Order order) {
        orderRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Order o = orderRepository.findById(id).get();
        o.setAppointment(order.getAppointment());
        o.setIsPayed(order.getIsPayed());

        Pet petRefresh = petRepository.findById(o.getPet().getId()).get();
        petRepository.save(petRefresh);
        orderRepository.save(o);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(long id){
        if(orderRepository.findById(id).isPresent()){
            Long temp = orderRepository
                    .findById(id)
                    .get().getPet()
                    .getId();
            orderRepository.deleteById(id);

            Pet petRefresh = petRepository.findById(temp).get();
            petRepository.save(petRefresh);
            petRepository.save(petRefresh);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
