package es.upm.dit.tracking.trackingpedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.filter.Filter;
import es.upm.dit.tracking.trackingpedidos.models.Producto;
import es.upm.dit.tracking.trackingpedidos.repository.ProductRepository;

import org.slf4j.*;
import java.net.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController

public class ProductController {

  private final ProductRepository productRepository;

  public static final Logger log = LoggerFactory.getLogger(ProductController.class);

  public ProductController(ProductRepository t) {
    this.productRepository = t;
  }

  //Obtener todos los productos
  @GetMapping("/productos")
  List<Producto> readAll() {
    return (List<Producto>) productRepository.findAll();
  }

  // @PostMapping("/productos")
  // ResponseEntity<Product> create(@RequestBody Product newProduct) throws URISyntaxException {

  //   Product result = productRepository.save(newProduct);

  //   return ResponseEntity.created(new URI("/productos/" + result.getEmail())).body(result);

  // }


  //Obtener un producto por su id
  @GetMapping("/productos/{id}")
  ResponseEntity<Producto> read(@PathVariable String id) {
    return productRepository.findById(id).map(producto -> ResponseEntity.ok().body(producto))
        .orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));
  }

  //Metodo para actualizar un producto
  @PutMapping("/productos/{id}")
  ResponseEntity<Producto> update(@RequestBody Producto newProducto, @PathVariable String id) {
    return productRepository.findById(id).map(producto -> {

      producto.setEmailCliente(newProducto.getEmailCliente());
      producto.setempresa(newProducto.getempresa());
      producto.setid_pedido(newProducto.getid_pedido());
      producto.setMatricula(newProducto.getMatricula());
      producto.setEstado(newProducto.getEstado());
      productRepository.save(producto);

      return ResponseEntity.ok().body(producto);

    }).orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));

  }

  @DeleteMapping("productos/{id}")
  ResponseEntity<Producto> delete(@PathVariable String id) {

    productRepository.deleteById(id);

    return ResponseEntity.ok().body(null);

  }

  //Metodo encargado de obtener el repositorio de los productos asociados a un cliente
  @GetMapping("/productos/{email}") 
    List<Producto> findByEmail(@PathVariable String email) {
      List<Producto> productosByEmail = new ArrayList<Producto>();

      for (Producto producto : productRepository.findAll()) {
        if (producto.getEmailCliente().equals(email)) {
          productosByEmail.add(producto);
        }
      }
      return productosByEmail;

      // return productRepository.findAll().stream()
      //.filter(producto -> producto.getEmailCliente().equals(email))
      //.collect(Collectors.toList());
      
  }

  

}
