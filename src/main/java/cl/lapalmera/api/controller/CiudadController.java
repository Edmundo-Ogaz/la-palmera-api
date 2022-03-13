package cl.lapalmera.api.controller;

import cl.lapalmera.api.model.Ciudad;
import cl.lapalmera.api.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/ciudades")
    public List<Ciudad> findAll() {
        System.out.println("CiudadController findAll");
        return ciudadService.findAll();
    }
}
