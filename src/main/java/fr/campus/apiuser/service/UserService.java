package fr.campus.apiuser.service;

import fr.campus.apiuser.dao.UserDao;
import fr.campus.apiuser.entities.UserEntity;
import fr.campus.apiuser.repository.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserEntityRepository userRepository, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserEntity user) {
// Hashage du mot de passe avant de l'enregistrer
        user.password = passwordEncoder.encode(user.password);
        return userDao.save(user);
    }

    public Optional<UserEntity> getUser(String id) {
        return userDao.findById(id);
    }

    public Optional<UserEntity> getUserByName(String name) {
        return userDao.findByName(name);
    }

    public void deleteUser(String id) {
        userDao.delete(id);
    }

    public boolean userExists(String id) {
        return userDao.existsById(id);
    }
}