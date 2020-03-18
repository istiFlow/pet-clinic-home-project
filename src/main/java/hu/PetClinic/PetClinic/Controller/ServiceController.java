package hu.PetClinic.PetClinic.Controller;

import hu.PetClinic.PetClinic.Enity.Pet;
import hu.PetClinic.PetClinic.Enity.ServiceNote;
import hu.PetClinic.PetClinic.Service.ServiceNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceNoteService serviceNoteService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody ServiceNote serviceNote){
        return serviceNoteService.create(id,serviceNote);
    }

    @GetMapping("/services")
    public List<ServiceNote> getAll(){
        return serviceNoteService.getAllService();
    }

}
