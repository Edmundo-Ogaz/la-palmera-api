package cl.lapalmera.api.service;

import cl.lapalmera.api.model.CustomerModel;
import cl.lapalmera.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl loadUserByUsername");
        final CustomerModel customer = customerRepository.findByUsername(username);
        System.out.println("UserDetailsServiceImpl loadUserByUsername " + customer);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("UserDetailsServiceImpl loadUserByUsername " + customer.toString());
        UserDetails user = User.withUsername(customer.getUsername()).password(customer.getPassword()).authorities("USER").build();
        return user;
    }
}
