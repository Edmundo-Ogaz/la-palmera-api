package cl.lapalmera.api.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author edmundoogaz
 */
@Entity
@Getter
public class Ciudad {

    @Id
    @Column(name="CODIGOCIUDAD", nullable=false, unique=true)
    private String codigo;
    @Column(name="NOMBRECIUDAD", nullable=false, unique=false)
    private String nombre;
    @Column(name="CODIGOREGION", nullable=false, unique=false)
    private String codigoRegion;

    public Ciudad() {}

    public Ciudad(String codigo, String nombre, String codigoRegion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigoRegion = codigoRegion;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigoRegion='" + codigoRegion + '\'' +
                '}';
    }
}
