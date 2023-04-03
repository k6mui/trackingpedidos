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
	class dataLoader implements CommandLineRunner{
		@Autowired
		private ProductRepository productRepository;
		@Override
		public void run(String... args) throws Exception {

			Producto p1 = new Producto();
			p1.setId("1");
			p1.setNombre("Silla");
			p1.setDescripcion("Silla de madera: Gröningen");
			p1.setEstado(Estado.TRANSITO);
			p1.setPedido("01");
			p1.setCliente("javi");
			p1.setEmpresa("Ikea");
			p1.setTransportista("8976GVL");
			productRepository.save(p1);

			Producto p2 = new Producto();
			p2.setId("2");
			p2.setNombre("Maceta");
			p2.setDescripcion("Maceta Ostergaard");
			p2.setEstado(Estado.TRANSITO);
			p2.setPedido("01");
			p2.setCliente("javi");
			p2.setEmpresa("Ikea");
			p2.setTransportista("8976GVL");
			productRepository.save(p2);

			Producto p3 = new Producto();
			p3.setId("3");
			p3.setNombre("Felpudo");
			p3.setDescripcion("Felpudo Oaklast");
			p3.setEstado(Estado.ENTREGADO);
			p3.setPedido("01");
			p3.setCliente("javi");
			p3.setEmpresa("Ikea");
			p3.setTransportista("8976GVL");
			productRepository.save(p3);

			Producto p4 = new Producto();
			p4.setId("4");
			p4.setNombre("Whey Protein");
			p4.setDescripcion("Proteína en polvo");
			p4.setEstado(Estado.ENTREGADO);
			p4.setPedido("02");
			p4.setCliente("jorge");
			p4.setEmpresa("Prozis");
			p4.setTransportista("9543POU");
			productRepository.save(p4);
			
			Producto p5 = new Producto();
			p5.setId("5");
			p5.setNombre("Mantequilla de cacahuete");
			p5.setDescripcion("Crema de maní");
			p5.setEstado(Estado.ENTREGADO);
			p5.setPedido("02");
			p5.setCliente("jorge");
			p5.setEmpresa("Prozis");
			p5.setTransportista("9543POU");
			productRepository.save(p5);

			Producto p6 = new Producto();
			p6.setId("6");
			p6.setNombre("Monitor");
			p6.setDescripcion("Monitor de ordenador 4K");
			p6.setEstado(Estado.INICIADO);
			p6.setPedido("03");
			p6.setCliente("alex");
			p6.setEmpresa("PC Componentes");
			p6.setTransportista("1728TRE");
			productRepository.save(p6);

			Producto p7 = new Producto();
			p7.setId("7");
			p7.setNombre("Tarjeta gráfica");
			p7.setDescripcion("NVIDIA-TG");
			p7.setEstado(Estado.ENTREGADO);
			p7.setPedido("04");
			p7.setCliente("adri");
			p7.setEmpresa("PC Componentes");
			p7.setTransportista("1728TRE");
			productRepository.save(p7);
		}
	}


}
 