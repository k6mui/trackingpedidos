package es.upm.dit.tracking.trackingpedidos.models;
import java.util.Arrays.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private Estado estado;
    public enum Estado{
        INICIADO, TRANSITO, ENTREGADO;
        private Estado() {
        }
    }
    private String pedido;
    private String cliente;
    private String empresa;
    private String transportista;
    private int res_envio;
    private String res_esc;
    private int res_prod;


    // Constructores
    public Producto() {} 
    public Producto(String id, String nombre, String descripcion, Estado estado, String emailCliente, String empresa, String id_pedido, String matricula, int res_envio, String res_esc, int res_prod) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = Estado.INICIADO;
        this.pedido = id_pedido;
        this.cliente = emailCliente;
        this.empresa = empresa;
        this.transportista = matricula;
        this.res_envio = res_envio;
        this.res_esc = res_esc;
        this.res_prod = res_prod;
    }

    // Métodos accesores y modificadores
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Estado getEstado() {
        return this.estado;
    }
    public void setEstado(Estado estado) { //Estado.Entregado
        this.estado = estado;
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
    public String getEmpresa() {
        return this.empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getTransportista() {
        return this.transportista;
    }
    public void setTransportista(String matricula) {
        this.transportista = matricula;
    }
    public int getRes_envio() {
        return this.res_envio;
    }
    public void setRes_envio(int res_envio) {
        this.res_envio = res_envio;
    }
    public String getRes_esc() {
        return this.res_esc;
    }
    public void setRes_esc(String res_esc) {
        this.res_esc = res_esc;
    }
    public int getRes_prod() {
        return this.res_prod;
    }
    public void setRes_prod(int res_prod) {
        this.res_prod = res_prod;
    }

    // Hashcode y equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }    
}
