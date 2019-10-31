package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PlayerDAOImplementation implements PlayerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Player findById(int id) {
        // return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Player.class, id);
        return entityManager.find(Player.class, id);
    }

    public Player findByNickName(String nickName) {
        return entityManager
                .createQuery("select p from Player as p where p.nickName = :nickName", Player.class)
                .getSingleResult();
        /*var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var query = session.createQuery("select p from Player as p where p.nickName = :nickName", Player.class);
        query.setParameter("nickName", nickName);
        return query.getSingleResult();*/
    }

    public void save(Player player) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(player);
        transaction.commit();

        /*Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.save(player);
        txl.commit();
        session.close();*/
    }

    public void update(Player player) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(player);
        transaction.commit();

        /*Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.update(player);
        txl.commit();
        session.close();*/
    }

    public void delete(Player player) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(player);
        transaction.commit();

        /*Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.delete(player);
        txl.commit();
        session.close();*/
    }
}
