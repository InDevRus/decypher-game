package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Question;

import java.util.Optional;

public interface QuestionDAO {
    Optional<Question> find(int questionId);
}
