package cl.lapalmera.api.controller;

import cl.lapalmera.api.model.Comuna;
import cl.lapalmera.api.service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping("/comunas")
    public List<Comuna> findAll() {
        System.out.println("ComunaController findAll");
        return comunaService.findAll();
    }

    @GetMapping("/comunas/search")
    public List<Comuna> search(@RequestParam(value = "comuna", defaultValue = "") String comuna,
                                @RequestParam(value = "ciudad", defaultValue = "") String ciudad) {
        System.out.println("ComunaController search" + "comuna" + comuna + "ciudad" + ciudad);
        comuna = comuna.length() != 0 ? comuna : null;
        ciudad = ciudad.length() != 0 ? ciudad : null;
        return comunaService.findByCodigoOrCodigoCiudad(comuna, ciudad);
    }
}
