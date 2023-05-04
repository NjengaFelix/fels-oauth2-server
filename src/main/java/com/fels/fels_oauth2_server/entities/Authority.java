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
@Table(name = "authorities",uniqueConstraints = {
    @UniqueConstraint(name = "authorities_name",columnNames = "name")
})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "authority",cascade = CascadeType.ALL)
    private Set<UserDetail> userDetails = new HashSet<>();

    public Authority(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
