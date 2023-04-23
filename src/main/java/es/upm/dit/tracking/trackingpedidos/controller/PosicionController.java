package es.upm.dit.tracking.trackingpedidos.controller;

import java.util.*;

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
import es.upm.dit.tracking.trackingpedidos.models.Posicion;

@RestController
@CrossOrigin
public class PosicionController {
  private final PosicionRepository posicionRepository;
  private int contador = 4;
  public PosicionController(PosicionRepository t) {
    this.posicionRepository = t;
  }


  // Obtener coordenadas lat y long de todos los transportes (para ver la posicion de todos los transportes de una empresa)
  @GetMapping("/datos")
  List<Posicion> getAllCoordenadas() {
    return (List<Posicion>) posicionRepository.findAll();
  }


  // Obtener coordenadas lat y long de una matricula
  @GetMapping("/datos/{matricula}")
  List<Posicion> getCoordenadasByMatricula(@PathVariable String matricula) {
    List<Posicion> coordenadasByMatricula = new ArrayList<Posicion>();

    for (Posicion transporte : posicionRepository.findAll()) {
      if (transporte.getMatricula().equals(matricula)) {
        coordenadasByMatricula.add(transporte);
      }
    }
    return coordenadasByMatricula;
  }

  // Actualizar una traza de la tabla TrazaTransporte
  @PutMapping("/datos/{matricula}")
  ResponseEntity<Posicion> updatePosicion(@RequestBody Posicion newPosicion, @PathVariable String matricula) {
    return posicionRepository.findById(newPosicion.getId()).map(posicion -> {

      posicion.setMatricula(newPosicion.getMatricula());
      posicion.setLatitud(newPosicion.getLatitud());
      posicion.setLongitud(newPosicion.getLongitud());

      posicionRepository.save(posicion);

      return ResponseEntity.ok().body(posicion);

    }).orElse(new ResponseEntity<Posicion>(HttpStatus.NOT_FOUND));

  }

  // Gestor de empresa introduce un pedido nuevo a la base de datos
  @PostMapping("/posicion/{matricula}/new/")
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

    return ResponseEntity.status(HttpStatus.CREATED).body(posicionAñadida);

  }

}
