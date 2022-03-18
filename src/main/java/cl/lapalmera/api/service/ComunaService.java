package cl.lapalmera.api.service;

import cl.lapalmera.api.dto.ComunaDto;
import cl.lapalmera.api.model.Comuna;
import cl.lapalmera.api.repository.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ComunaService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }

    public List<Comuna> findByCodigoOrCodigoCiudad(String codigo, String codigoCiudad) {
        System.out.println("ComunaService findByCodigoOrCodigoCiudad" + "comuna" + codigo + "ciudad" + codigoCiudad);
        Comuna comuna = Comuna.builder().codigo(codigo).codigociudad(codigoCiudad).build();
        return comunaRepository.findAll(Example.of(comuna));
    }

    public Comuna save(ComunaDto comunaDto) {
        System.out.println("ComunaService save" +
                "comuna" + comunaDto.getCode() +
                "nombre" + comunaDto.getName() +
                "ciudad" + comunaDto.getCityCode());
        Comuna comuna = Comuna.builder().codigo(comunaDto.getCode()).nombre(comunaDto.getName()).codigociudad(comunaDto.getCityCode()).build();
        return comunaRepository.save(comuna);
    }

    public Comuna test(ComunaDto comunaDto) {
        return Comuna.builder().codigo(comunaDto.getCode()).nombre(comunaDto.getName()).codigociudad(comunaDto.getCityCode()).build();
    }

    public Comuna update(ComunaDto c) {
        Comuna comuna = comunaRepository.findByCodigo(c.getCode());
        comuna.setNombre(c.getName());
        comuna.setCodigociudad(c.getCityCode());
        return comunaRepository.save(comuna);
    }
}
