package org.indev.decyphergame.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"word", "description"}))
public class Question {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    @Pattern(regexp = "[А-ЯЁа-яё]")
    @Size(min = 4, max = 10)
    private String word;

    @Column(nullable = false)
    @Pattern(regexp = "[А-ЯЁ][а-яё]+( [а-яё]+)")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "question")
    private Set<Encryption> encryptions;

    public Question() {
    }

    public Question(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }
}
