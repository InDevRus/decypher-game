delete
from "player"
where true;

insert into "player"
    (id, nick_name, created_at, updated_at)
values (1, 'Player1', now(), now()),
       (2, 'Player2', now(), now());
