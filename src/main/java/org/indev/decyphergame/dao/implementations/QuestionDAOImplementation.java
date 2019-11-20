package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.QuestionDAO;
import org.indev.decyphergame.models.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QuestionDAOImplementation implements QuestionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Question findById(int questionId) {
        return entityManager.find(Question.class, questionId);
    }
}
