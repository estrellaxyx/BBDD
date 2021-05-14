SELECT co.first_name, co.last_name,ci.city, cr.country
FROM customer AS co 
JOIN address AS a ON co.address_id = a.address_id
JOIN city AS ci ON a.city_id = ci.city_id
JOIN country AS cr ON ci.country_id = cr.country_id
WHERE ci.city LIKE '%mi%' AND co.first_name like '%e%'
ORDER BY cr.country desc,co.first_name asc;

-- actores de la pelicula ALABAMA DEVIL
SELECT a.*
FROM actor AS a
JOIN film_actor AS fa ON a.actor_id = fa.actor_id
JOIN film AS f ON fa.film_id = f.film_id
WHERE f.title = 'ALABAMA DEVIL';

-- ciudades donde no hayan clientes
SELECT *
FROM city AS ci
LEFT JOIN address AS a ON ci.city_id = a.city_id
LEFT JOIN customer AS cu ON cu.address_id = a.address_id
WHERE cu.first_name IS null
LIMIT 100;#limite de las filas del resultado


#2020.03.09

-- actores y numero de peliculas en las que han actuado, 
-- ordenar por numero de peliculas de mayor a menor 
SELECT fpa.first_name, fpa.last_name, fpa.number_of_films
FROM films_per_actor_t AS fpa
GROUP BY fpa.actor_id
ORDER BY  fpa.number_of_films desc;
-- 方法二， 不用film_per_actor_t: 
SELECT MAX(a.first_name) AS name, MAX(a.last_name) as surname, COUNT(*) AS numero_peliculas
FROM actor a 
JOIN film_actor fa ON a.actor_id = fa.actor_id
GROUP BY a.actor_id
HAVING COUNT(*)>=30
ORDER BY COUNT(*) desc;

-- numero de actores en la tabla actor
SELECT COUNT(*) AS numero_actores
FROM actor a
ORDER BY COUNT(*) desc;

-- nombre de actores repetidos
SELECT a.first_name, GROUP_CONCAT(last_name SEPARATOR '-') AS apellidos
FROM actor a
GROUP BY a.first_name
HAVING COUNT(*)>1
ORDER BY COUNT(*) desc;

-- el actor de actores que mas veces ha actuado
# Podemos hacer con LIMIT 1 que solamente sale una fila que es el maximo
# Pero 
#1º: sacamos el maximo de number_of_films
SELECT MAX(numero_peliculas) as max
FROM (
	SELECT a.first_name, COUNT(*) AS numero_peliculas
	FROM actor a 
	JOIN film_actor fa ON a.actor_id = fa.actor_id
	GROUP BY a.actor_id
) t;
#hay que darle un nombre

-- las 10 peliculas mas alquiladas
SELECT f.title, COUNT(*) as numero_alquiladas
FROM rental r 
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
GROUP BY f.film_id
ORDER BY COUNT(*) desc
LIMIT 10;

-- las 10 peliculas que mas tiempo has estado alquilada
SELECT f.title, SUM(to_seconds(r.return_date)- to_SECONDS(r.rental_date))
FROM rental r 
JOIN inventory i ON r.inventory_id = i.inventory_id
JOIN film f ON i.film_id = f.film_id
GROUP BY f.film_id, f.title
ORDER BY SUM(to_seconds(r.return_date)- to_SECONDS(r.rental_date));