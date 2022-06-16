package ru.job4j.accident.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRep;
    private final AuthorityRepository authorityRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository rep, AuthorityRepository authorityRepo,
                       PasswordEncoder encoder) {
        this.userRep = rep;
        this.authorityRepo = authorityRepo;
        this.encoder = encoder;
    }

    public void save(User user, String role) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityRepo.findByAuthority(role));
        userRep.save(user);
    }
}
