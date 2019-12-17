package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Role;
import org.indev.decyphergame.models.values.RoleValue;

public interface RoleDAO {
    Role findByValue(RoleValue roleValue);
}
