package com.fels.fels_oauth2_server.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_details")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "authority_id",referencedColumnName = "id",nullable = false)
    private Authority authority;

    public UserDetail(User user, Authority authority) {
        this.user = user;
        this.authority = authority;
    }
}
