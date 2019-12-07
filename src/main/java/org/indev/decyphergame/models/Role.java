package org.indev.decyphergame.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(mappedBy = "roles")
    private Set<Player> players;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleValue roleValue;
}
