package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
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

    @Override
    @Transactional
    public void save(Player player) {
        entityManager.persist(player);
    }
}
