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
@Table(name = "password_algorithms",uniqueConstraints = {
        @UniqueConstraint(name = "password_algorithms_name",columnNames = "name")
})
public class PasswordAlgorithm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "passwordAlgorithm",cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
}
