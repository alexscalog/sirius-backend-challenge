package com.emailservice.CrudEmailService.Controllers;


import com.emailservice.CrudEmailService.DTO.Request.LoginDto;
import com.emailservice.CrudEmailService.DTO.Response.ResponseDto;
import com.emailservice.CrudEmailService.DTO.Response.StatsDto;
import com.emailservice.CrudEmailService.Models.User;
import com.emailservice.CrudEmailService.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/stats")
    public Collection<StatsDto> getStats() {
        return userService.getUserStats();
    }

    @GetMapping("/find/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User u) {
        return userService.saveUser(u);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        if (userService.deleteById(id))
            return "Se ha eliminado correctamente.";
        else
            return "No se ha podido eliminar el usuario.";

    }

}


