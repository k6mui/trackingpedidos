package es.upm.dit.tracking.trackingpedidos.models;
import java.util.Arrays.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {
    @Id
    private String id;
    private String emailCliente;
    private String empresa;
    private String id_pedido;
    private String matricula;
    private Estado estado;
    private enum Estado{
        INICIADO, TRANSITO, ENTREGADO;
        private Estado() {
        }
    }

    // Constructores
    public Producto() {} 
    public Producto(String id, String emailCliente, String empresa, String id_pedido, String matricula, Estado estado) {
        this.id = id;
        this.emailCliente = emailCliente;
        this.empresa = empresa;
        this.id_pedido = id_pedido;
        this.matricula = matricula;
        this.estado = Estado.INICIADO;
    }

    // Métodos accesores y modificadores
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmailCliente() {
        return this.emailCliente;
    }
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    public String getempresa() {
        return this.empresa;
    }
    public void setempresa(String empresa) {
        this.empresa = empresa;
    }
    public String getid_pedido() {
        return this.id_pedido;
    }
    public void setid_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }
    public String getMatricula() {
        return this.matricula;
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
        if (emailCliente == null) {
            if (other.emailCliente != null)
                return false;
        } else if (!emailCliente.equals(other.emailCliente))
            return false;
        if (empresa == null) {
            if (other.empresa != null)
                return false;
        } else if (!empresa.equals(other.empresa))
            return false;
        if (id_pedido != other.id_pedido)
            return false;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        if (estado != other.estado)
            return false;
        return true;
    } 


    // Hashcode y equals
  

    
}
