package dao;

import models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class UserDAO {
    // В туториале написано, что по хорошему надо создать интерфейс UserDAO,
    // а это должнл быть его имплементацией UserDAOImpl

    public Player findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Player.class, id);
    }

    public void Save(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.save(player);
        txl.commit();
        session.close();
    }

    public void Update(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.update(player);
        txl.commit();
        session.close();
    }

    public void Delete(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction txl = session.beginTransaction();
        session.delete(player);
        txl.commit();
        session.close();
    }
}
