package pl.coderslab.egro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.egro.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
