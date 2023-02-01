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
@Table(name = "clients",uniqueConstraints = {
    @UniqueConstraint(name = "clients_client_id",columnNames = "client_id")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id",nullable = false)
    private String clientId;
    @Column(nullable = false)
    private String secret;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private Set<ClientDetail> clientDetails = new HashSet<>();

}
