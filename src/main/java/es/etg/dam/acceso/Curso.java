package es.etg.dam.acceso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CURSO")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ALUMNO_NOMBRE")
    private String alumnoNombre; 

    
    public Curso() {
    }

    public Curso(int id, String nombre, String descripcion, String alumnoNombre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alumnoNombre = alumnoNombre;
    }

    public Curso(String nombre, String descripcion, String alumnoNombre) {
        this(0, nombre, descripcion, alumnoNombre);
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getAlumnoNombre() { return alumnoNombre; }
    public void setAlumnoNombre(String alumnoNombre) { this.alumnoNombre = alumnoNombre; }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', descripcion='" + descripcion +
               "', alumno='" + alumnoNombre + "'}";
    }
}