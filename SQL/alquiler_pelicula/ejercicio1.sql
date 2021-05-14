#Listar los directores que sus apellidos son ALLEN
SELECT * FROM director
WHERE apellidos = 'ALLEN';

#Listar actores y directores de la pelicula COAST RAINBOW
#se ejecuta primero FROM y por ultimo SELECT
#JOIN se junta tablas por sus claves primarias
SELECT a.nombre_actor,a.apellidos_actor,'actor' AS tipo
FROM actuar AS a JOIN pelicula AS p
ON a.titulo_pelicula = p.titulo AND a.anio_pelicula = p.anio_estreno
WHERE p.titulo= 'COAST RAINBOW'
UNION
SELECT p.nombre_director, p.apellidos_director,'director' AS tipo
FROM actuar AS a JOIN pelicula AS p
ON a.titulo_pelicula = p.titulo AND a.anio_pelicula = p.anio_estreno
WHERE p.titulo= 'COAST RAINBOW';

#Listar actores que han actuado en peliculas de la productora Metro%
SELECT a.nombre_actor,a.apellidos_actor
FROM actuar AS a JOIN pelicula AS p
ON a.titulo_pelicula = p.titulo AND a.anio_pelicula = p.anio_estreno
WHERE p.productora LIKE 'Metro%';
