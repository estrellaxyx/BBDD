INSERT INTO actor(nombre,apellidos,nacionalidad)
VALUES('John','Smith','EEUU'),
('Maria','Zuil','Espania');

DELETE 
FROM actor
WHERE nombre = 'John' AND apellidos = 'Smith' AND nacionalidad = 'EEUU';

UPDATE actor
SET nacionalidad = 'Espania', nombre = 'John'
WHERE nombre = 'John' AND apellidos = 'Smith' AND nacionalidad = 'EEUU';
