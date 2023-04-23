package es.upm.dit.tracking.trackingpedidos.repository;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.tracking.trackingpedidos.models.Posicion;

public interface PosicionRepository extends CrudRepository<Posicion, String>{
    
}
