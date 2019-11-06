package org.indev.decyphergame.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String nickName;

    @OneToMany(mappedBy = "player")
    private Set<Result> results;

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
}
