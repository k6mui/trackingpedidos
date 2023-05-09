package es.upm.dit.tracking.trackingpedidos.controller;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.tracking.trackingpedidos.repository.PosicionRepository;
import es.upm.dit.tracking.trackingpedidos.repository.ProductRepository;
import es.upm.dit.tracking.trackingpedidos.models.Posicion;
import es.upm.dit.tracking.trackingpedidos.models.Producto;

@RestController
@CrossOrigin
public class PosicionController {
  private final PosicionRepository posicionRepository;
  ProductRepository productRepository;
  
  private int contador = 15;
  public PosicionController(PosicionRepository t, ProductRepository p) {
    this.posicionRepository = t;
    this.productRepository = p;
  }

  // Obtener Posicion de una vehiculo 
  @GetMapping("/cliente/{email}/{id_pedido}/{id_producto}")
  List<Posicion> getCoordenadasByMatricula(@PathVariable String id_pedido, @PathVariable String id_producto) {
    String matricula = new String("");

    for (Producto producto : productRepository.findAll()) {
      if ((producto.getId().equals(id_producto)) && (producto.getPedido().equals(id_pedido))) {
        matricula = producto.getTransportista();
        break;
      }
    }

    List<Posicion> coordenadasByMatricula = new ArrayList<Posicion>();

    for (Posicion transporte : posicionRepository.findAll()) {
      if (transporte.getMatricula() != null && transporte.getMatricula().equals(matricula))  {
        coordenadasByMatricula.add(transporte);
      }
    }
    return coordenadasByMatricula;
  }



  // Gestor de la empresa introduce una nueva traza/posición de un vehículo
  @PostMapping("/transportista/{matricula}")
  public ResponseEntity<Posicion> addPosicion(@RequestBody Posicion jsonRecibido, @PathVariable String matricula) {

    double longitud = jsonRecibido.getLongitud();
    double latitud = jsonRecibido.getLatitud();

    // Crear un objeto Producto con los valores recibidos
    Posicion posicionAñadida = new Posicion();
    posicionAñadida.setId(Integer.toString(contador));
    contador++;

    posicionAñadida.setMatricula(matricula);
    posicionAñadida.setLatitud(latitud);
    posicionAñadida.setLongitud(longitud);

    posicionRepository.save(posicionAñadida);

    return ResponseEntity.status(HttpStatus.CREATED).body(posicionAñadida);

  }

}
