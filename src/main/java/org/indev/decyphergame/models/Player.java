package org.indev.decyphergame.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, updatable = false)
    @Pattern(regexp = "[A-Za-z]\\w+")
    private String nickName;

    @Column(nullable = false)
    @Pattern(regexp = "[A-Za-z]\\w+")
    private String password;

    @OneToMany(mappedBy = "player")
    private Set<Result> results;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    @ManyToMany
    private Set<Role> roles;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
