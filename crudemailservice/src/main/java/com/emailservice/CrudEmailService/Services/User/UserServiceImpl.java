package com.emailservice.CrudEmailService.Services.User;

import com.emailservice.CrudEmailService.DTO.Response.StatsDto;
import com.emailservice.CrudEmailService.Models.Email;
import com.emailservice.CrudEmailService.Models.User;
import com.emailservice.CrudEmailService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<StatsDto> getUserStats() {
        var users = userRepository.findAll()
                .stream()
                .filter(UserServiceImpl::hasSentEmailsToday);

        return users.map(u-> new StatsDto(u.getUsername(), countTodaySentEmails(u))).toList();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public boolean deleteById(int id) {
        var user = userRepository.findById(id);
        if (user.isEmpty())
            return false;
        userRepository.delete(user.get());

        return true;
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username.toLowerCase());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findByUsername(username.toLowerCase());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        var user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }

    private static boolean hasSentEmailsToday(User user){
        return user.getSentEmails().stream()
                .anyMatch(UserServiceImpl::hasBeenSentToday);
    }

    private static boolean hasBeenSentToday(Email email){
        return email.getSendingDate().after(getTodayMidnight());
    }

    private static long countTodaySentEmails(User user){
        return user.getSentEmails().stream()
                .filter(UserServiceImpl::hasBeenSentToday)
                .count();
    }
    private static Date getTodayMidnight() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
