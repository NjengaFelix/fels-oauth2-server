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
@Table(name = "scopes",uniqueConstraints = {
        @UniqueConstraint(name = "scopes_name",columnNames = "name")
})
public class Scope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "scope",cascade = CascadeType.ALL)
    private Set<ClientDetail> clientDetails = new HashSet<>();

}
