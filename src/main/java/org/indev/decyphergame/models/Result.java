package org.indev.decyphergame.models;

import org.hibernate.annotations.CreationTimestamp;
import org.indev.decyphergame.models.values.ResultValue;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Result {
    @Id
    @GeneratedValue
    private int id;

    @Column(updatable = false)
    private String answer;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ResultValue resultValue;

    @Column(updatable = false, nullable = false)
    private Integer pointsAmount;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn
    private Encryption encryption;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ResultValue getResultValue() {
        return resultValue;
    }

    public void setResultValue(ResultValue resultValue) {
        this.resultValue = resultValue;

        var hintRequested = Objects.nonNull(Objects.requireNonNull(encryption).getHintPosition());
        switch (resultValue) {
            case SUCCESS: {
                pointsAmount = hintRequested ? 1 : 3;
                break;
            }
            case WRONG_ANSWER: {
                pointsAmount = hintRequested ? -3 : -2;
                break;
            }
            case GIVE_UP: {
                pointsAmount = hintRequested ? -1 : 0;
                break;
            }
        }
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public Integer getPointsAmount() {
        return pointsAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
