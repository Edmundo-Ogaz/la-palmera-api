package cl.lapalmera.api.repository;

import cl.lapalmera.api.model.Comuna;
import cl.lapalmera.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, String> {
    Comuna findByCodigo(String codigo);

    @Transactional
    @Modifying
    @Query(value="update Comuna c set c.nombrecomuna = :name where c.codigocomuna = :code", nativeQuery = true)
    void update(@Param("name")String name, @Param("code")String code);
}
