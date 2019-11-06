package org.indev.decyphergame.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "player")
    private Set<Result> results;

    @Column(unique = true, nullable = false)
    private String nickName;

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
