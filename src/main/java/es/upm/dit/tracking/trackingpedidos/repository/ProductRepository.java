package es.upm.dit.tracking.trackingpedidos.repository;
import org.springframework.data.repository.CrudRepository;

import es.upm.dit.tracking.trackingpedidos.models.Producto;

public interface ProductRepository extends CrudRepository<Producto, String>{

}
