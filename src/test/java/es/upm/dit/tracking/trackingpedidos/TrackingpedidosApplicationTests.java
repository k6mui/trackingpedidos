package es.upm.dit.tracking.trackingpedidos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private ProductController productController;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void testPut() {
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

	@Test
	void testUpdate() {
		
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
}
