package fr.campus.apiuser.controller;

import fr.campus.apiuser.entities.UserEntity;
import fr.campus.apiuser.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity user) {
        System.out.println("Créer un utilisateur");
        System.out.println("id = " + user.id);
        System.out.println("name = " + user.name);

        return userService.createUser(user);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and authentication.name == #id)")
    @GetMapping("/users/{id}")
    public Optional<UserEntity> getUser(@PathVariable String id) {
        System.out.println("Récupérer un utilisateur par son identifiant");

        return userService.getUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        System.out.println("Supprimer un utilisateur par son identifiant");

        userService.deleteUser(id);
    }

    @GetMapping("/users/{id}/valid")
    public boolean userExists(@PathVariable String id) {
        System.out.println("Vérification que " + id + " existe");

        return userService.userExists(id);
    }


}
