package es.upm.dit.tracking.trackingpedidos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.upm.dit.tracking.trackingpedidos.controller.ProductController;
import es.upm.dit.tracking.trackingpedidos.models.Producto;
import es.upm.dit.tracking.trackingpedidos.models.ProductoFrontend;
import es.upm.dit.tracking.trackingpedidos.models.Producto.Estado;
import es.upm.dit.tracking.trackingpedidos.repository.ProductRepository;

@SpringBootTest
class TrackingpedidosApplicationTests {

	@Autowired
    public ProductController productController;

	@Autowired
	private ProductRepository productRepository;

	// 4. Caso de prueba "Modificar el estado de los productos"
	@Test
	void testUpdateEstado() {
		Producto newProduct = new Producto();
		newProduct.setId("1L"); // mismo ID
		newProduct.setNombre("Silla");
		newProduct.setDescripcion("Silla de madera: Gröningen");
		newProduct.setEstado(Estado.INICIADO);
		newProduct.setPedido("1");
		newProduct.setCliente("javi");
		newProduct.setEmpresa("Ikea");
		newProduct.setTransportista("8976GVL");
		newProduct.setRes_envio(0);
		newProduct.setRes_esc("");
		newProduct.setRes_prod(0);
		productRepository.save(newProduct);
		
		
		// LLamamos a la función 'updateEstado' con 'newProduct' y una matrícula
        ResponseEntity<Producto> response = productController.updateEstado(newProduct, "5881GMW");

        assertEquals(HttpStatus.OK, response.getStatusCode()); // Verificamos que la respuesta tenga HTTP OK
		// Verificamos que el cuerpo de la respuesta contenga el producto actualizado con los nuevos valores
        assertEquals(newProduct.getNombre(), response.getBody().getNombre());
        assertEquals(newProduct.getDescripcion(), response.getBody().getDescripcion());
        assertEquals(newProduct.getEstado(), response.getBody().getEstado());
		assertEquals(newProduct.getEmpresa(), response.getBody().getEmpresa());
		assertEquals(newProduct.getCliente(), response.getBody().getCliente());
		assertEquals(newProduct.getPedido(), response.getBody().getPedido());
		assertEquals(newProduct.getTransportista(), response.getBody().getTransportista());


        
		Producto noExist = new Producto();
		noExist.setId("XXXX");

        ResponseEntity<Producto> notFoundResponse = productController.updateEstado(noExist, "5881GMW");

		// Verificamos una respuesta NOT_FOUND, es decir el producto no se encuentra en el repositorio
        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
		
	}


	// 4. Caso de prueba "Modificar el estado de los productos"
	@Test
	void testUpdateEstado2() {
		Producto p1 = new Producto("1", "Silla", "Silla de madera: Gröningen", Estado.INICIADO, "javi", "Ikea", "1", "8976GVL", 0, "", 0);
		Producto p2 = new Producto("2", "Maceta", "Maceta Ostergaard", Estado.INICIADO, "javi", "Ikea", "1", "8976GVL", 0, "", 0);
		Producto p3 = new Producto("3", "Felpudo", "Felpudo Oaklast", Estado.INICIADO, "javi", "Ikea", "1", "8976GVL", 0, "", 0);
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);

		// Variante 1:
		Producto newP1 = p1;
		newP1.setEstado(Estado.TRANSITO);
		productRepository.save(newP1);

		Producto newP2 = p2;
		newP2.setEstado(Estado.TRANSITO);
		productRepository.save(newP2);

		Producto newP3 = p3;
		newP3.setEstado(Estado.TRANSITO);
		productRepository.save(newP3);


		// LLamamos a la función 'updateEstado' con 'newP1' y la matricula del vehiculo
        ResponseEntity<Producto> response1 = productController.updateEstado(newP1, p1.getTransportista());
        ResponseEntity<Producto> response2 = productController.updateEstado(newP2, p2.getTransportista());
        ResponseEntity<Producto> response3 = productController.updateEstado(newP3, p3.getTransportista());

		// Verificamos que el cuerpo de la respuesta contenga el producto actualizado con los nuevos valores    
		assertEquals(Estado.TRANSITO, p1.getEstado());
		assertEquals(Estado.TRANSITO, p2.getEstado());
		assertEquals(Estado.TRANSITO, p3.getEstado());

		// Variante 2:
		newP1.setEstado(Estado.ENTREGADO);
		productRepository.save(newP1);

		newP2.setEstado(Estado.ENTREGADO);
		productRepository.save(newP2);

		ResponseEntity<Producto> response4 = productController.updateEstado(newP1, p1.getTransportista());
        ResponseEntity<Producto> response5 = productController.updateEstado(newP2, p2.getTransportista());
        
		assertEquals(Estado.ENTREGADO, p1.getEstado());
		assertEquals(Estado.ENTREGADO, p2.getEstado());

		// Verificamos que las respuestas tengan HTTP OK
		assertEquals(HttpStatus.OK, response1.getStatusCode()); 
		assertEquals(HttpStatus.OK, response2.getStatusCode()); 
		assertEquals(HttpStatus.OK, response3.getStatusCode()); 
		assertEquals(HttpStatus.OK, response4.getStatusCode()); 
		assertEquals(HttpStatus.OK, response5.getStatusCode()); 

		Producto noExist = new Producto();
		noExist.setId("XXXX");
        ResponseEntity<Producto> notFoundResponse = productController.updateEstado(noExist, "5881GMW");
		// Verificamos una respuesta NOT_FOUND, es decir el producto no se encuentra en el repositorio
        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
	}



	// 5. Caso de prueba "Introducir un pedido nuevo"
	@Test
	void testAddPedido() {
        ProductoFrontend jsonRecibido = new ProductoFrontend();
        jsonRecibido.setPedido("pedido1");
        jsonRecibido.setProducto("producto1");
        jsonRecibido.setDescripcion("descripcion1");
        jsonRecibido.setCliente("cliente1");
        jsonRecibido.setTransportista("transportista1");

        String empresa = "empresa1";

        ResponseEntity<Producto> response = productController.addPedido(jsonRecibido, empresa);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Producto productoAñadido = response.getBody();
        assertNotNull(productoAñadido);
        assertEquals(Estado.INICIADO, productoAñadido.getEstado());
        assertEquals(jsonRecibido.getPedido(), productoAñadido.getPedido());
        assertEquals(jsonRecibido.getProducto(), productoAñadido.getNombre());
        assertEquals(jsonRecibido.getDescripcion(), productoAñadido.getDescripcion());
        assertEquals(jsonRecibido.getCliente(), productoAñadido.getCliente());
        assertEquals(jsonRecibido.getTransportista(), productoAñadido.getTransportista());
        assertEquals(empresa, productoAñadido.getEmpresa());
    }





	// 3. Caso de prueba "Añadir una reseña a un producto"
	@Test
	void testUpdateReseñas() {
		
		Producto productoNuevo = new Producto();
		productoNuevo.setId("2");
		productoNuevo.setNombre("Maceta");
		productoNuevo.setDescripcion("Maceta Ostergaard");
		productoNuevo.setEstado(Estado.INICIADO);
		productoNuevo.setPedido("1");
		productoNuevo.setCliente("javi");
		productoNuevo.setEmpresa("Ikea");
		productoNuevo.setTransportista("8976GVL");
		productoNuevo.setRes_envio(0);
		productoNuevo.setRes_esc("");
		productoNuevo.setRes_prod(0);
		productRepository.save(productoNuevo);
	
		// Llamar al método 'update()' y verificar la actualización
		ResponseEntity<Producto> response = productController.update(productoNuevo, "2");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(productoNuevo.getNombre(), response.getBody().getNombre());
        assertEquals(productoNuevo.getDescripcion(), response.getBody().getDescripcion());
        assertEquals(productoNuevo.getEstado(), response.getBody().getEstado());
		assertEquals(productoNuevo.getEmpresa(), response.getBody().getEmpresa());
		assertEquals(productoNuevo.getCliente(), response.getBody().getCliente());
		assertEquals(productoNuevo.getPedido(), response.getBody().getPedido());
		assertEquals(productoNuevo.getTransportista(), response.getBody().getTransportista());
		
	
		// Probar el caso en el que el producto no se encuentra en el repositorio
		ResponseEntity<Producto> notFoundResponse = productController.update(productoNuevo, "XXXX");
		assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
	  }



	// 3. Caso de prueba "Añadir una reseña a un producto"  
	@Test
	void testUpdateReseñas2() {
		Producto p1 = new Producto("1", "Silla", "Silla de madera: Gröningen", Estado.ENTREGADO, "javi", "Ikea", "1", "8976GVL", 0, "", 0);
		Producto p2 = new Producto("2", "Maceta", "Maceta Ostergaard", Estado.ENTREGADO, "javi", "Ikea", "1", "8976GVL", 0, "", 0);
		Producto p3 = new Producto("3", "Felpudo", "Felpudo Oaklast", Estado.TRANSITO, "javi", "Ikea", "1", "8976GVL", 0, "", 0);
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
	
		Producto newP1 = p1;
		newP1.setRes_envio(5);
		newP1.setRes_esc("Llegó a tiempo");
		newP1.setRes_prod(4);
		productRepository.save(newP1);

		Producto newP2 = p2;
		newP2.setRes_envio(5);
		newP2.setRes_esc("Maceta rota");
		newP2.setRes_prod(2);
		productRepository.save(newP2);


		// Llamar al método 'update()' y verificar la actualización
		ResponseEntity<Producto> response1 = productController.update(newP1, "1");
		ResponseEntity<Producto> response2 = productController.update(newP2, "2");

		assertEquals(HttpStatus.OK, response1.getStatusCode());
		assertEquals(HttpStatus.OK, response2.getStatusCode());

		assertEquals(newP1.getRes_envio(), p1.getRes_envio());
		assertEquals(newP1.getRes_esc(), p1.getRes_esc());
		assertEquals(newP1.getRes_prod(), p1.getRes_prod());

		assertEquals(newP2.getRes_envio(), p2.getRes_envio());
		assertEquals(newP2.getRes_esc(), p2.getRes_esc());
		assertEquals(newP2.getRes_prod(), p2.getRes_prod());		
	
		// Probar el caso en el que el producto no se encuentra en el repositorio
		ResponseEntity<Producto> notFoundResponse = productController.update(newP1, "XXXX");
		assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
	  }
}
