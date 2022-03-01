package cl.lapalmera.api.controller;

import cl.lapalmera.api.model.CustomerModel;
import cl.lapalmera.api.model.UserDto;
import cl.lapalmera.api.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static cl.lapalmera.api.constant.SecurityConstants.*;

@RestController
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<CustomerModel> findAll() {
        System.out.println("UsuarioController findAll");
        return userService.findAll();
    }

    @PostMapping("/users")
    public long save(@RequestBody UserDto userDto) {
        System.out.println("UsuarioController save");
        return userService.saveDto(userDto);
    }
}
