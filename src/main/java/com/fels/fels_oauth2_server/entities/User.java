package com.fels.fels_oauth2_server.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users",uniqueConstraints = {
        @UniqueConstraint(name = "users_username",columnNames = "username")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String country;
    private String photo;
    @Column(nullable = false)
    private String username;
    @ManyToOne
    @JoinColumn(name = "password_algorithm_id",referencedColumnName = "id",nullable = false)
    private PasswordAlgorithm passwordAlgorithm;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<UserDetail> userDetails = new HashSet<>();

    public User(String firstName, String lastName, String dob, String address, String country, String photo, String username, PasswordAlgorithm passwordAlgorithm, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.country = country;
        this.photo = photo;
        this.username = username;
        this.passwordAlgorithm = passwordAlgorithm;
        this.password = password;
    }
}
