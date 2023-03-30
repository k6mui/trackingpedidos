package es.upm.dit.tracking.trackingpedidos.models;
import java.util.Arrays.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {
    @Id
    private int id;
    private String emailCliente;
    private String empresa;
    private int id_pedido;
    private String matricula;
    private Estado estado;
    private enum Estado{
        INICIADO, TRANSITO, ENTREGADO;
        private Estado() {
        }
    }

    // Constructores
    public Producto() {} 
    public Producto(int id, String emailCliente, String empresa, int id_pedido, String matricula, Estado estado) {
        this.id = id;
        this.emailCliente = emailCliente;
        this.empresa = empresa;
        this.id_pedido = id_pedido;
        this.matricula = matricula;
        this.estado = Estado.INICIADO;
    }

    // Métodos accesores y modificadores
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmailCliente() {
        return emailCliente;
    }
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    public String getempresa() {
        return empresa;
    }
    public void setempresa(String empresa) {
        this.empresa = empresa;
    }
    public int getid_pedido() {
        return id_pedido;
    }
    public void setid_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public Estado getEstado() {
        return this.estado;
    }
    public void setEstado(Estado estado) { //Estado.Entregado
        this.estado = estado;
    } 


    // Hashcode y equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        if (id != other.id)
            return false;
        return true;
    }
    
}
