package org.indev.decyphergame.models;

import org.hibernate.annotations.CreationTimestamp;
import org.indev.decyphergame.models.values.CypherType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Table
public class Encryption {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Question question;

    @Column(updatable = false, nullable = false)
    private String cypher;

    @Column(updatable = false, nullable = false)
    private CypherType cypherType;

    @PositiveOrZero
    @Max(10)
    @Column
    private Integer hintPosition;

    @OneToOne(mappedBy = "encryption")
    private Result result;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getCypher() {
        return cypher;
    }

    public void setCypher(String cypher) {
        this.cypher = cypher;
    }

    public CypherType getCypherType() {
        return cypherType;
    }

    public void setCypherType(CypherType cypherType) {
        this.cypherType = cypherType;
    }

    public Integer getHintPosition() {
        return hintPosition;
    }

    public void setHintPosition(int hintPosition) {
        this.hintPosition = hintPosition;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
