delete
from "player_roles"
where true;

delete
from "player"
where true;

delete
from "role"
where true;

insert into "role"
    (id, role_value)
values (1, 'USER'),
       (2, 'MODERATOR');
