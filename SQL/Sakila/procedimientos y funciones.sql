DELIMITER $$

CREATE PROCEDURE show_all_countries()
SELECT * FROM country;
$$

DELIMITER ;

CALL show_all_countries();

-- SHOW PROCEDURE STATUS LIKE '%countr%';

-- DROP PROCEDURE show_all_countries $$

DELIMITER $$

CREATE PROCEDURE show_all_countries(IN q VARCHAR(64), OUT number_countries INT)
COMMENT 'Procedimiento que devuelve un resultsetcon todos los paises de la base de datos'
cuerpo_procedimiento: BEGIN
SET q = CONCAT('%',q,'%');

-- select q
SELECT * FROM country WHERE country LIKE q;
-- devolvemos un resultset con todos los paises

SELECT FOUND_ROWS() INTO number_countries;
END cuerpo_procedimiento
$$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION show_all_countries_f(q VARCHAR(64))
RETURNS INT
COMMENT 'Funcion que devuelve un resultsetcon todos los paises de la base de datos'
cuerpo_funcionn: BEGIN
DECLARE number_countries INT;

SET q = CONCAT('%',q,'%');

SELECT COUNT(*) INTO number_countries FROM country WHERE country LIKE q;

RETURN number_countries;
END cuerpo_procedimiento
$$

DELIMITER ;
