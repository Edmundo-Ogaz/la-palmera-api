package cl.lapalmera.api.controller;

import cl.lapalmera.api.dto.ComunaDto;
import cl.lapalmera.api.dto.UserDto;
import cl.lapalmera.api.model.Comuna;
import cl.lapalmera.api.service.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/comunas")
    public Comuna save(@RequestBody ComunaDto comunaDto) {
        System.out.println("ComunaController save");
        return comunaService.save(comunaDto);
    }

    @PutMapping("/comunas")
    public Comuna update(@RequestBody ComunaDto comunaDto) {
        System.out.println("ComunaController update");
        return comunaService.update(comunaDto);
    }
}
