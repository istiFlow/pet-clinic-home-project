package hu.PetClinic.PetClinic.Repository;

import hu.PetClinic.PetClinic.Enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String name);

    User findByEmail(String email);

    @Query("from User u where u.role = ?1")
    List<User> findByRole(String role);



}
