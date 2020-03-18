package hu.PetClinic.PetClinic.Enity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNote {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String vaccination;

    @Column
    private String operation;

    @Column
    private String commonSurgery;

    @Column
    private Integer price;


    @ManyToMany()
    @JsonIgnore
    private Set<Order> orders;


    @Override
    public String toString() {
        return "ServiceNote{" +
                "id=" + id +
                ", vaccination='" + vaccination + '\'' +
                ", operation='" + operation + '\'' +
                ", commonSurgery='" + commonSurgery + '\'' +
                ", price=" + price +
                '}';
    }
}
