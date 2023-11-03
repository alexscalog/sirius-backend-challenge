package com.emailservice.CrudEmailService.Services.User;

import com.emailservice.CrudEmailService.DTO.Response.StatsDto;
import com.emailservice.CrudEmailService.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    Collection<StatsDto> getUserStats();
    Optional<User> getUserById(int id);
    User saveUser(User u);
    boolean deleteById(int id);
    Optional<User> findByUsername(String username);
}
