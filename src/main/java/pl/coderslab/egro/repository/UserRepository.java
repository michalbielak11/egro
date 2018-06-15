package pl.coderslab.egro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.egro.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserById(long id);
}
