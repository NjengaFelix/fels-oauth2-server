package com.fels.fels_oauth2_server.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client_details")
public class ClientDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id",referencedColumnName = "id",nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "auth_method_id",referencedColumnName = "id")
    private AuthMethod authMethod;
    @ManyToOne
    @JoinColumn(name = "scope_id",referencedColumnName = "id")
    private Scope scope;
    @ManyToOne
    @JoinColumn(name = "uri_id",referencedColumnName = "id")
    private Uri uri;
    @ManyToOne
    @JoinColumn(name = "grant_type_id",referencedColumnName = "id")
    private GrantType grantType;
}
