package cl.lapalmera.api.repository;

import cl.lapalmera.api.model.Ciudad;
import cl.lapalmera.api.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, String> {
}
