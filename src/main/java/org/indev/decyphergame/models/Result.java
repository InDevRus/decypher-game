package org.indev.decyphergame.models;

import org.hibernate.annotations.CreationTimestamp;
import org.indev.decyphergame.models.values.ResultValue;

import javax.persistence.*;
import java.util.Date;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Encryption encryption;

    @Transient
    private int encryptionId;

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
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public int getEncryptionId() {
        return encryptionId;
    }

    public void setEncryptionId(int encryptionId) {
        this.encryptionId = encryptionId;
    }
}
