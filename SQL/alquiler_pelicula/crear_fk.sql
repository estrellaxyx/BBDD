ALTER TABLE `alquiler_pelicula`.`actuar` 
ADD CONSTRAINT `fk_actor`
  FOREIGN KEY (`nombre_actor`,`apellidos_actor`)
  REFERENCES `alquiler_pelicula`.`actor` (`nombre`,`apellidos`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_pelicula`
  FOREIGN KEY (`titulo_pelicula`,`anio_pelicula`)
  REFERENCES `alquiler_pelicula`.`pelicula` (`titulo`,`anio_estreno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;