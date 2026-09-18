package fr.campus.apiuser.dao;

import fr.campus.apiuser.entities.UserEntity;
import java.util.Optional;

public interface UserDao {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(String id);

    void delete(String id);

    boolean existsById(String id);
}