package es.upm.dit.tracking.trackingpedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.tracking.trackingpedidos.models.Producto;
import es.upm.dit.tracking.trackingpedidos.repository.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.slf4j.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProductController {
  private final ProductRepository productRepository;
  public static final Logger log = LoggerFactory.getLogger(ProductController.class);
  public ProductController(ProductRepository t) {
    this.productRepository = t;
  }
 
   // Obtiene los productos asociados a un cliente (email)
   @GetMapping("/cliente/{emailCliente}") 
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

  //Metodo para actualizar un producto
  @PutMapping("/cliente/{emailCliente}/historial/{id}")
  ResponseEntity<Producto> update(@RequestBody Producto newProducto, @PathVariable String id) {
    return productRepository.findById(id).map(producto -> {

      producto.setNombre(newProducto.getNombre());
      producto.setDescripcion(newProducto.getDescripcion());
      producto.setEstado(newProducto.getEstado());
      producto.setPedido(newProducto.getPedido());
      producto.setCliente(newProducto.getCliente());
      producto.setEmpresa(newProducto.getEmpresa());
      producto.setTransportista(newProducto.getTransportista());
      producto.setRes_envio(newProducto.getRes_envio());
      producto.setRes_esc(newProducto.getRes_esc());
      producto.setRes_prod(newProducto.getRes_prod());
      productRepository.save(producto);

      return ResponseEntity.ok().body(producto);

    }).orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));

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

  @PutMapping("/transportista/{matricula}")
  ResponseEntity<Producto> updateEstado(@RequestBody Producto newProducto, @PathVariable String matricula) {
    return productRepository.findById(newProducto.getId()).map(producto -> {

      producto.setNombre(newProducto.getNombre());
      producto.setDescripcion(newProducto.getDescripcion());
      producto.setEstado(newProducto.getEstado());
      producto.setPedido(newProducto.getPedido());
      producto.setCliente(newProducto.getCliente());
      producto.setEmpresa(newProducto.getEmpresa());
      producto.setTransportista(newProducto.getTransportista());
      producto.setRes_envio(newProducto.getRes_envio());
      producto.setRes_esc(newProducto.getRes_esc());
      producto.setRes_prod(newProducto.getRes_prod());
      productRepository.save(producto);

      return ResponseEntity.ok().body(producto);

    }).orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));

  }

  // Obtiene los productos asociados a una empresa 
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



   // Obtiene los productos asociados a un pedido (id_pedido)
// @GetMapping("/{emailCliente}/{id_pedido}") 
// List<Producto> getProductosByPedido(@PathVariable String id_pedido) {
//   List<Producto> productosByPedido = new ArrayList<Producto>();

//   for (Producto producto : productRepository.findAll()) {
//     if (producto.getPedido().equals(id_pedido)) {
//       productosByPedido.add(producto);
//     }
//   }
//   return productosByPedido;
// }

//   // Obtener un producto por su id
//   @GetMapping("/{emailCliente}/{id_pedido}/{id}")
//   ResponseEntity<Producto> getProductosById(@PathVariable String id) {
//     return productRepository.findById(id).map(producto -> ResponseEntity.ok().body(producto))
//         .orElse(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND));
//   }

  //Obtener todos los productos
  // @GetMapping
  // List<Producto> getAllProductos() {
  //   return (List<Producto>) productRepository.findAll();
  // }



  

  

}
