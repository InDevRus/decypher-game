package org.indev.decyphergame.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, updatable = false)
    @Pattern(regexp = "\\w{4,20}",
            message = "Никнейм должен быть строкой от 4 до 20 символов, состоящих из латинских букв и цифр.")
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirmation;

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
    private Set<Role> roles = new HashSet<>();

    public Player() {
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    @SuppressWarnings("unused")
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
