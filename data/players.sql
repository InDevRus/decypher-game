delete
from "player"
where true;

insert into "player"
    (id, nick_name, password, created_at, updated_at)
values (1, 'Player1', 'Password1', now(), now()),
       (2, 'Player2', 'Password2', now(), now());
