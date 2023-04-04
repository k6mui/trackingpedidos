package es.upm.dit.tracking.trackingpedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.filter.Filter;
import es.upm.dit.tracking.trackingpedidos.models.Producto;
import es.upm.dit.tracking.trackingpedidos.repository.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.slf4j.*;
import java.net.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ProductController {
  private final ProductRepository productRepository;
  public static final Logger log = LoggerFactory.getLogger(ProductController.class);
  public ProductController(ProductRepository t) {
    this.productRepository = t;
  }
 
   // Obtiene los productos asociados a un cliente (email)
   @GetMapping("/{emailCliente}") 
   List<Producto> getProductosByEmailCliente(@PathVariable String emailCliente) {
     List<Producto> productosByEmail = new ArrayList<Producto>();

     for (Producto producto : productRepository.findAll()) {
       if (producto.getCliente().equals(emailCliente)) {
         productosByEmail.add(producto);
       }
     }
     return productosByEmail;

     // return productRepository.findAll().stream()
     //    .filter(producto -> producto.getEmailCliente().equals(email))
     //    .collect(Collectors.toList());
     
 }

 // Obtiene los productos asociados a un pedido (id_pedido)
@GetMapping("/{emailCliente}/{id_pedido}") 
List<Producto> getProductosByPedido(@PathVariable String id_pedido) {
  List<Producto> productosByPedido = new ArrayList<Producto>();

  for (Producto producto : productRepository.findAll()) {
    if (producto.getPedido().equals(id_pedido)) {
      productosByPedido.add(producto);
    }
  }
  return productosByPedido;
}

  // Obtener un producto por su id
  @GetMapping("/{emailCliente}/{id_pedido}/{id}")
  ResponseEntity<Producto> getProductosById(@PathVariable String id) {
    return productRepository.findById(id).map(producto -> ResponseEntity.ok().body(producto))
        .orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));
  }

  //Obtener todos los productos
  @GetMapping
  List<Producto> getAllProductos() {
    return (List<Producto>) productRepository.findAll();
  }


  //Metodo para actualizar un producto
  @PutMapping("/{id}")
  ResponseEntity<Producto> update(@RequestBody Producto newProducto, @PathVariable String id) {
    return productRepository.findById(id).map(producto -> {

      producto.setNombre(newProducto.getNombre());
      producto.setDescripcion(newProducto.getDescripcion());
      producto.setEstado(newProducto.getEstado());
      producto.setPedido(newProducto.getPedido());
      producto.setCliente(newProducto.getCliente());
      producto.setEmpresa(newProducto.getEmpresa());
      producto.setTransportista(newProducto.getTransportista());
      productRepository.save(producto);

      return ResponseEntity.ok().body(producto);

    }).orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));

  }

  @DeleteMapping("/{id}")
  ResponseEntity<Producto> delete(@PathVariable String id) {
    productRepository.deleteById(id);
    return ResponseEntity.ok().body(null);

  }
 

  // Obtiene los productos asociados a un transportista (matricula)
  @GetMapping("/transportista/{matricula}") 
    List<Producto> getProductosByMatricula(@PathVariable String matricula) {
      List<Producto> productosByMatricula = new ArrayList<Producto>();

      for (Producto producto : productRepository.findAll()) {
        if (producto.getTransportista().equals(matricula)) {
          productosByMatricula.add(producto);
        }
      }
      return productosByMatricula;
  }

  // Obtiene los productos asociados a un transportista (matricula)
  @GetMapping("/empresa/{empresa}") 
    List<Producto> getProductosByEmpresa(@PathVariable String empresa) {
      List<Producto> productosByEmpresa = new ArrayList<Producto>();

      for (Producto producto : productRepository.findAll()) {
        if (producto.getEmpresa().equals(empresa)) {
          productosByEmpresa.add(producto);
        }
      }
      return productosByEmpresa;
  }



  

  

}
