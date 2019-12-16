package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Role;
import org.indev.decyphergame.models.RoleValue;

public interface RoleDAO {
    Role findByValue(RoleValue roleValue);
}
