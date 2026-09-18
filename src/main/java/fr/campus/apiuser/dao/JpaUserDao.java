package fr.campus.apiuser.dao;

import fr.campus.apiuser.entities.UserEntity;
import fr.campus.apiuser.repository.UserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserDao implements UserDao {

    private final UserEntityRepository userRepository;

    public JpaUserDao(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
