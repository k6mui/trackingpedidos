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
@RequestMapping("/productos")
public class ProductController {
  private final ProductRepository productRepository;
  public static final Logger log = LoggerFactory.getLogger(ProductController.class);
  public ProductController(ProductRepository t) {
    this.productRepository = t;
  }

  //Obtener todos los productos
  @GetMapping
  List<Producto> getAllProductos() {
    return (List<Producto>) productRepository.findAll();
  }

  //Obtener un producto por su id
  @GetMapping("/{id}")
  ResponseEntity<Producto> getProductosById(@PathVariable String id) {
    return productRepository.findById(id).map(producto -> ResponseEntity.ok().body(producto))
        .orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));
  }


  //Metodo para actualizar un producto
  @PutMapping("/{id}")
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

  // Obtiene los productos asociados a un cliente (email)
  @GetMapping("/cliente/{emailCliente}") 
    List<Producto> getProductosByEmailCliente(@PathVariable String emailCliente) {
      List<Producto> productosByEmail = new ArrayList<Producto>();

      for (Producto producto : productRepository.findAll()) {
        if (producto.getEmailCliente().equals(emailCliente)) {
          productosByEmail.add(producto);
        }
      }
      return productosByEmail;

      // return productRepository.findAll().stream()
      //    .filter(producto -> producto.getEmailCliente().equals(email))
      //    .collect(Collectors.toList()); 
  }

  // Obtiene los productos asociados a un transportista (matricula)
  @GetMapping("/transportista/{matricula}") 
    List<Producto> getProductosByMatricula(@PathVariable String matricula) {
      List<Producto> productosByMatricula = new ArrayList<Producto>();

      for (Producto producto : productRepository.findAll()) {
        if (producto.getMatricula().equals(matricula)) {
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
        if (producto.getempresa().equals(empresa)) {
          productosByEmpresa.add(producto);
        }
      }
      return productosByEmpresa;
  }

// Obtiene los productos asociados a un pedido (id_pedido)
@GetMapping("/pedidos/{id_pedido}") 
List<Producto> getProductosByPedido(@PathVariable String id_pedido) {
  List<Producto> productosByPedido = new ArrayList<Producto>();

  for (Producto producto : productRepository.findAll()) {
    if (producto.getMatricula().equals(id_pedido)) {
      productosByPedido.add(producto);
    }
  }
  return productosByPedido;
}

  

  

}
