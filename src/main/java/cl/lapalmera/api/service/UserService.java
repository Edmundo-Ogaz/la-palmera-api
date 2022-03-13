package cl.lapalmera.api.service;

import cl.lapalmera.api.dto.UserDto;
import cl.lapalmera.api.model.UserModel;
import cl.lapalmera.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public long saveDto(UserDto userDto) {
        System.out.println("UserService saveDto");
        userDto.setPassword(passwordEncoder
                .encode(userDto.getPassword()));
        UserModel user = new UserModel(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword());
        return userRepository.save(user).getId();
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
}
