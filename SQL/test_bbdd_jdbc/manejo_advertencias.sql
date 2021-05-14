START TRANSACTION;

INSERT INTO clientes (nombre, apellidos) VALUES('Pedro', 'Martin');

ROLLBACK;

COMMIT;