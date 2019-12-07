package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.RoleDAO;
import org.indev.decyphergame.models.Role;
import org.indev.decyphergame.models.RoleValue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImplementation implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByValue(RoleValue roleValue) {
        return entityManager.createQuery("select role from Role role " +
                "where role.roleValue = :roleValue", Role.class)
                .setParameter("roleValue", roleValue)
                .getSingleResult();
    }
}
