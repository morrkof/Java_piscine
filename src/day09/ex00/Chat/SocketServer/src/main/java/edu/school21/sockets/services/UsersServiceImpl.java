package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("service")
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

//    public UsersServiceImpl() {}

    @Autowired
    @Qualifier("encodePassword")
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    @Qualifier("usersRepository")
    public void setUsersRepository(UsersRepositoryImpl usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean signUp(String username, String password) {
        Optional<User> optionalUser = usersRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return false;
        } else {
            usersRepository.save(new User(null, username, passwordEncoder.encode(password)));
            return true;
        }
    }
}
