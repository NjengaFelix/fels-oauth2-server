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
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<UserDetail> userDetails = new HashSet<>();
}
