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
    @Column(nullable = false)
    private int accessTokenTimeToLive = 30;
    private int refreshTokenTimeToLive = 86400;
    private int reUseRefreshToken = 0;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ClientDetail> clientDetails = new HashSet<>();

    public Client(String clientId, String secret) {
        this.clientId = clientId;
        this.secret = secret;
    }
}
