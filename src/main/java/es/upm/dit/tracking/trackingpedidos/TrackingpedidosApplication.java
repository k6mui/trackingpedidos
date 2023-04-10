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
			p1.setEstado(Estado.INICIADO);
			p1.setPedido("1");
			p1.setCliente("javi");
			p1.setEmpresa("Ikea");
			p1.setTransportista("8976GVL");
			p1.setRes_envio(0);
			p1.setRes_esc("");
			p1.setRes_prod(0);
			productRepository.save(p1);

			Producto p2 = new Producto();
			p2.setId("2");
			p2.setNombre("Maceta");
			p2.setDescripcion("Maceta Ostergaard");
			p2.setEstado(Estado.INICIADO);
			p2.setPedido("1");
			p2.setCliente("javi");
			p2.setEmpresa("Ikea");
			p2.setTransportista("8976GVL");
			p2.setRes_envio(0);
			p2.setRes_esc("");
			p2.setRes_prod(0);
			productRepository.save(p2);

			Producto p3 = new Producto();
			p3.setId("3");
			p3.setNombre("Felpudo");
			p3.setDescripcion("Felpudo Oaklast");
			p3.setEstado(Estado.INICIADO);
			p3.setPedido("1");
			p3.setCliente("javi");
			p3.setEmpresa("Ikea");
			p3.setTransportista("8976GVL");
			p3.setRes_envio(0);
			p3.setRes_esc("");
			p3.setRes_prod(0);
			productRepository.save(p3);

			Producto p4 = new Producto();
			p4.setId("4");
			p4.setNombre("Whey Protein");
			p4.setDescripcion("Proteína en polvo");
			p4.setEstado(Estado.INICIADO);
			p4.setPedido("2");
			p4.setCliente("jorge");
			p4.setEmpresa("Prozis");
			p4.setTransportista("9543POU");
			p4.setRes_envio(0);
			p4.setRes_esc("");
			p4.setRes_prod(0);
			productRepository.save(p4);
			
			Producto p5 = new Producto();
			p5.setId("5");
			p5.setNombre("Mantequilla de cacahuete");
			p5.setDescripcion("Crema de maní");
			p5.setEstado(Estado.INICIADO);
			p5.setPedido("2");
			p5.setCliente("jorge");
			p5.setEmpresa("Prozis");
			p5.setTransportista("9543POU");
			p5.setRes_envio(0);
			p5.setRes_esc("");
			p5.setRes_prod(0);
			productRepository.save(p5);

			Producto p6 = new Producto();
			p6.setId("6");
			p6.setNombre("Monitor");
			p6.setDescripcion("Monitor de ordenador 4K");
			p6.setEstado(Estado.INICIADO);
			p6.setPedido("3");
			p6.setCliente("alex");
			p6.setEmpresa("PC Componentes");
			p6.setTransportista("1728TRE");
			p6.setRes_envio(0);
			p6.setRes_esc("");
			p6.setRes_prod(0);
			productRepository.save(p6);

			Producto p7 = new Producto();
			p7.setId("7");
			p7.setNombre("Tarjeta gráfica");
			p7.setDescripcion("NVIDIA-TG");
			p7.setEstado(Estado.INICIADO);
			p7.setPedido("4");
			p7.setCliente("adri");
			p7.setEmpresa("PC Componentes");
			p7.setTransportista("1728TRE");
			p7.setRes_envio(0);
			p7.setRes_esc("");
			p7.setRes_prod(0);
			productRepository.save(p7);


			Producto p8 = new Producto();
			p8.setId("8");
			p8.setNombre("Macbook Air");
			p8.setDescripcion("Ordenador Apple Chip M2");
			p8.setEstado(Estado.INICIADO);
			p8.setPedido("4");
			p8.setCliente("adri");
			p8.setEmpresa("PC Componentes");
			p8.setTransportista("1728TRE");
			p8.setRes_envio(0);
			p8.setRes_esc("");
			p8.setRes_prod(0);
			productRepository.save(p8);


			Producto p9 = new Producto();
			p9.setId("9");
			p9.setNombre("Blackwidow v3");
			p9.setDescripcion("Teclado mecanico gaming");
			p9.setEstado(Estado.INICIADO);
			p9.setPedido("5");
			p9.setCliente("adri");
			p9.setEmpresa("PC Componentes");
			p9.setTransportista("1890ALB");
			p9.setRes_envio(0);
			p9.setRes_esc("");
			p9.setRes_prod(0);
			productRepository.save(p9);


			Producto p10 = new Producto();
			p10.setId("10");
			p10.setNombre("Sueñan los androides con ovejas eléctricas");
			p10.setDescripcion("Libro Sci-Fi");
			p10.setEstado(Estado.INICIADO);
			p10.setPedido("6");
			p10.setCliente("alex");
			p10.setEmpresa("Libreria Ocho y medio");
			p10.setTransportista("3002ABC");
			p10.setRes_envio(0);
			p10.setRes_esc("");
			p10.setRes_prod(0);
			productRepository.save(p10);


			Producto p11 = new Producto();
			p11.setId("11");
			p11.setNombre("El mago del Kremlin");
			p11.setDescripcion("Novela contemporánea");
			p11.setEstado(Estado.INICIADO);
			p11.setPedido("6");
			p11.setCliente("alex");
			p11.setEmpresa("Libreria Ocho y medio");
			p11.setTransportista("3002ABC");
			p11.setRes_envio(0);
			p11.setRes_esc("");
			p11.setRes_prod(0);
			productRepository.save(p11);


			Producto p12 = new Producto();
			p12.setId("12");
			p12.setNombre("Todos en mi familia han matado a alguien");
			p12.setDescripcion("Novela negra");
			p12.setEstado(Estado.INICIADO);
			p12.setPedido("6");
			p12.setCliente("alex");
			p12.setEmpresa("Libreria Ocho y medio");
			p12.setTransportista("3002ABC");
			p12.setRes_envio(0);
			p12.setRes_esc("");
			p12.setRes_prod(0);
			productRepository.save(p12);


			Producto p13 = new Producto();
			p13.setId("13");
			p13.setNombre("Kit Mancuernas");
			p13.setDescripcion("Mancuernas y barras 93 KG");
			p13.setEstado(Estado.INICIADO);
			p13.setPedido("7");
			p13.setCliente("jorge");
			p13.setEmpresa("Deportes Manolo");
			p13.setTransportista("7664POL");
			p13.setRes_envio(0);
			p13.setRes_esc("");
			p13.setRes_prod(0);
			productRepository.save(p13);

			Producto p14 = new Producto();
			p14.setId("14");
			p14.setNombre("Chaleco musculación");
			p14.setDescripcion("Cross training lastrado 5KG");
			p14.setEstado(Estado.INICIADO);
			p14.setPedido("7");
			p14.setCliente("jorge");
			p14.setEmpresa("Deportes Manolo");
			p14.setTransportista("7664POL");
			p14.setRes_envio(0);
			p14.setRes_esc("");
			p14.setRes_prod(0);
			productRepository.save(p14);

			Producto p15 = new Producto();
			p15.setId("15");
			p15.setNombre("Audio Technica");
			p15.setDescripcion("ATH-M50XBT2");
			p15.setEstado(Estado.INICIADO);
			p15.setPedido("8");
			p15.setCliente("javi");
			p15.setEmpresa("Thomann-Madrid");
			p15.setTransportista("7775GMN");
			p15.setRes_envio(0);
			p15.setRes_esc("");
			p15.setRes_prod(0);
			productRepository.save(p15);

			Producto p16 = new Producto();
			p16.setId("16");
			p16.setNombre("Mackie CR3'X BT");
			p16.setDescripcion("Monitores estudio Mackie");
			p16.setEstado(Estado.INICIADO);
			p16.setPedido("8");
			p16.setCliente("javi");
			p16.setEmpresa("Thomann-Madrid");
			p16.setTransportista("7775GMN");
			p16.setRes_envio(0);
			p16.setRes_esc("");
			p16.setRes_prod(0);
			productRepository.save(p16);

			Producto p17 = new Producto();
			p17.setId("17");
			p17.setNombre("FL STUDIO");
			p17.setDescripcion("Secuenciador Audio-MIDI DAW");
			p17.setEstado(Estado.INICIADO);
			p17.setPedido("8");
			p17.setCliente("adri");
			p17.setEmpresa("Thomann-Madrid");
			p17.setTransportista("7775GMN");
			p17.setRes_envio(0);
			p17.setRes_esc("");
			p17.setRes_prod(0);
			productRepository.save(p17);

		}
	}


}