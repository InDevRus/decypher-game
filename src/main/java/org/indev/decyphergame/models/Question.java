package org.indev.decyphergame.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word", unique = true)
    private String word;

    @Column
    private String hint;

    @OneToMany(mappedBy = "question")
    private Set<Result> results;

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
}
