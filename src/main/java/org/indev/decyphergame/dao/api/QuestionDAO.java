package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Question;

public interface QuestionDAO {
    Question findById(int questionId);
}
