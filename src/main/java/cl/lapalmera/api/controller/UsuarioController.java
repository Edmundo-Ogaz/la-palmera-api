package cl.lapalmera.api.controller;

import cl.lapalmera.api.dto.UserDto;
import cl.lapalmera.api.model.UserModel;
import cl.lapalmera.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserModel> findAll() {
        System.out.println("UsuarioController findAll");
        return userService.findAll();
    }

    @PostMapping("/users")
    public long save(@RequestBody UserDto userDto) {
        System.out.println("UsuarioController save");
        return userService.saveDto(userDto);
    }
}
