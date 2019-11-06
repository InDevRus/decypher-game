package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class PlayerDAOImplementation implements PlayerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Player findById(int id) {
        return entityManager.find(Player.class, id);
    }

    public Optional<Player> findByNickName(String nickName) {
        return entityManager
                .createQuery("select p from Player as p where p.nickName = :nickName", Player.class)
                .setParameter("nickName", nickName)
                .getResultStream()
                .findAny();
    }

    public void save(Player player) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(player);
        transaction.commit();
    }

    public void update(Player player) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(player);
        transaction.commit();
    }

    public void delete(Player player) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(player);
        transaction.commit();
    }
}
