package cl.lapalmera.api.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author edmundoogaz
 */
@Builder
@Entity
@Getter
public class Comuna {

    @Id
    @Column(name="CODIGOCOMUNA", nullable=false, unique=true)
    private String codigo;
    @Column(name="NOMBRECOMUNA", nullable=false, unique=true)
    private String nombre;
    @Column(name="CODIGOCIUDAD", nullable=false, unique=true)
    private String codigociudad;

    public Comuna() {}

    public Comuna(String codigo, String nombre, String codigociudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigociudad = codigociudad;
    }

    @Override
    public String toString() {
        return "Comuna{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", codigociudad='" + codigociudad + '\'' +
                '}';
    }
}
