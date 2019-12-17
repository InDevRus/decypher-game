package org.indev.decyphergame.dao.implementations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.RoleDAO;
import org.indev.decyphergame.models.QRole;
import org.indev.decyphergame.models.Role;
import org.indev.decyphergame.models.values.RoleValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class RoleDAOImplementation implements RoleDAO {
    private JPAQueryFactory queryFactory;

    @Autowired
    public void setQueryFactory(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Role findByValue(RoleValue roleValue) {
        var role = QRole.role;

        var foundRole = queryFactory.selectFrom(role)
                .where(role.roleValue.eq(roleValue))
                .fetchOne();

        return Objects.requireNonNull(foundRole);
    }
}
