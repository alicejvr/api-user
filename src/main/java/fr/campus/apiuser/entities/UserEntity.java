package fr.campus.apiuser.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

    @Id
    public String id;

    public String name;

    public String password;
}
