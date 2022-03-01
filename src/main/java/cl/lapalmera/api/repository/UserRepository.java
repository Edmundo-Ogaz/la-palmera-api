package cl.lapalmera.api.repository;

import cl.lapalmera.api.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomerModel, String> {
    CustomerModel findByUsername(String username);
}
