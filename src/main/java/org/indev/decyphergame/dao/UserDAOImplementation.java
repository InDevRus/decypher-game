package org.indev.decyphergame.dao;

import org.indev.decyphergame.dao.api.UserDAO;
import org.indev.decyphergame.models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class UserDAOImplementation implements UserDAO {
    // В туториале написано, что по хорошему надо создать интерфейс UserDAO,
    // а это должнл быть его имплементацией UserDAOImpl

    public Player findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Player.class, id);
    }

    public Player findByNickName(String nickName) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var query = session.createQuery("select p from Player as p where p.nickName = :nickName", Player.class);
        query.setParameter("nickName", nickName);
        return query.getSingleResult();
    }

    public void save(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.save(player);
        txl.commit();
        session.close();
    }

    public void update(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.update(player);
        txl.commit();
        session.close();
    }

    public void delete(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.delete(player);
        txl.commit();
        session.close();
    }
}
