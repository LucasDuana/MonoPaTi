package org.example.usermicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String password;

    private String rol;



    @ManyToMany
    @JoinTable(
            name = "account_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts;


    public User() {
        this.accounts = new ArrayList<>();
    }


}
