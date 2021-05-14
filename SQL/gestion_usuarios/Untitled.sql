SELECT * from mysql.user;

GRANT all privileges 
on *.* 
to luis@localhost 
identified by '123ASDF!' with grant option;

GRANT ALL PRIVILEGES 
ON sakila.*
TO luis@localhost
identified by '123ASDF!' with grant option;

GRANT ALL PRIVILEGES 
ON sakila.film
TO luis@localhost
identified by '123ASDF!' with grant option;

REVOKE ALL PRIVILEGES
ON sakila.film
FROM luis@localhost;


SHOW GRANTS FOR luis@localhost;
DROP USER luis@localhost;

