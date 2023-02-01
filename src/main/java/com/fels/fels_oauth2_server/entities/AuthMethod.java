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
@Table(name = "authorization_methods",uniqueConstraints = {
    @UniqueConstraint(name = "authorization_methods_name",columnNames = "name")
})
public class AuthMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "authMethod",cascade = CascadeType.ALL)
    private Set<ClientDetail> clientDetails = new HashSet<>();
}
