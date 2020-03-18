package hu.PetClinic.PetClinic.Service;

import hu.PetClinic.PetClinic.Enity.Order;
import hu.PetClinic.PetClinic.Enity.ServiceNote;

import hu.PetClinic.PetClinic.Repository.OrderRepository;
import hu.PetClinic.PetClinic.Repository.PetRepository;
import hu.PetClinic.PetClinic.Repository.ServiceNoteRepository;
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

@Service
public class ServiceNoteService {

    @Autowired
    private ServiceNoteRepository serviceNoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    public List<ServiceNote> getAllService(){
        return serviceNoteRepository.findAll();
    }


    public ResponseEntity<Void> create(long id, ServiceNote serviceNote) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.findById(id).map(x -> {
                x.getServiceNotes().add(serviceNote);
                serviceNote.setCommonSurgery(serviceNote.getCommonSurgery());
                serviceNote.setOperation(serviceNote.getOperation());
                serviceNote.setPrice(serviceNote.getPrice());
                serviceNote.setVaccination(serviceNote.getVaccination());
                return serviceNoteRepository.save(serviceNote);
            });
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
