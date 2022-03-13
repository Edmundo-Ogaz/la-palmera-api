package cl.lapalmera.api.repository;

import cl.lapalmera.api.model.Comuna;
import cl.lapalmera.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, String> {
    Comuna findByCodigo(String codigo);
}
