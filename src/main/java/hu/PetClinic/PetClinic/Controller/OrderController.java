package hu.PetClinic.PetClinic.Controller;

import hu.PetClinic.PetClinic.Enity.Order;
import hu.PetClinic.PetClinic.Service.OrderService;
import hu.PetClinic.PetClinic.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> create (@PathVariable Long id,@RequestBody Order order){
        return orderService.createOrder(order,id);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order OrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/pets/{id}")
    public List<Order> allOrdersByPet(@PathVariable Long id){
        return orderService.getAllOrdersByPet(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> order (@PathVariable Long id, @RequestBody Order order){
        return orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return orderService.delete(id);
    }

}
