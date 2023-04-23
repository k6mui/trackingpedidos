package es.upm.dit.tracking.trackingpedidos.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Posicion {
    @Id
    private String id;
    private String matricula;
    private double latitud;
    private double longitud;
    
    // Constructores
    public Posicion() {}
    public Posicion(String id, String matricula, double latitud, double longitud) {
        this.id = id;
        this.matricula = matricula;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Métodos accesores y modificadores
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
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
        Posicion other = (Posicion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    


}
