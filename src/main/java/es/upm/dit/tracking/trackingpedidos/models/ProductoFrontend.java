package es.upm.dit.tracking.trackingpedidos.models;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductoFrontend {
    @Id
    private String producto;
    private String descripcion;
    private String pedido;
    private String cliente;
   


    // Constructores
    public ProductoFrontend(String producto, String descripcion, String cliente, String pedido) {
        this.producto = producto;
        this.descripcion = descripcion;
        this.pedido = pedido;
        this.cliente = cliente;
    }

    // Métodos accesores y modificadores
   
   
    public String getProducto() {
        return this.producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    public String getPedido() {
        return this.pedido;
    }
    public void setPedido(String id_pedido) {
        this.pedido = id_pedido;
    }
    public String getCliente() {
        return this.cliente;
    }
    public void setCliente(String emailCliente) {
        this.cliente = emailCliente;
    }


  
}
