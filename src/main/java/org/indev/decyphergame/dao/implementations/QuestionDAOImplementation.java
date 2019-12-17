package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.QuestionDAO;
import org.indev.decyphergame.models.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
class QuestionDAOImplementation implements QuestionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Question> find(int questionId) {
        return Optional.ofNullable(entityManager.find(Question.class, questionId));
    }
}
