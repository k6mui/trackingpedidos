package es.upm.dit.tracking.trackingpedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import es.upm.dit.tracking.trackingpedidos.repository.ProductRepository;
import es.upm.dit.tracking.trackingpedidos.models.Producto;
import es.upm.dit.tracking.trackingpedidos.models.Producto.Estado;

@SpringBootApplication
public class TrackingpedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingpedidosApplication.class, args);
	}

	@Component
	class DemoCommandLineRunner implements CommandLineRunner{
		@Autowired
		private ProductRepository productRepository;
		@Override
		public void run(String... args) throws Exception {
			Producto p = new Producto();
			p.setId("1");
			p.setNombre("Silla");
			p.setDescripcion("Silla de madera");
			p.setEstado(Estado.INICIADO);
			p.setPedido("01");
			p.setCliente("javi");
			p.setEmpresa("Ikea");
			p.setTransportista("8976GVL");
			productRepository.save(p);
		}
	}


}
 