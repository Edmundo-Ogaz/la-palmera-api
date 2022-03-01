package cl.lapalmera.api.service;

import cl.lapalmera.api.model.CustomerModel;
import cl.lapalmera.api.model.UserDto;
import cl.lapalmera.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public List<CustomerModel> list() {
        return userRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public long saveDto(UserDto userDto) {
        System.out.println("UserService saveDto");
        userDto.setPassword(passwordEncoder
                .encode(userDto.getPassword()));
        CustomerModel user = new CustomerModel(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword());
        return userRepository.save(user).getId();
    }

    public List<CustomerModel> findAll() {
        return userRepository.findAll();
    }
}
