package es.etg.dam.acceso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALUMNO") // Nombre de la tabla en Oracle
public class Alumno {

    @Id
    @Column(name = "NOMBRE") // Clave primaria
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "EDAD")
    private int edad;

    // IMPORTANTE: Hibernate necesita este constructor vacío para crear 
    // instancias cuando recupera datos de la base de datos
    public Alumno() {
    }

    public Alumno(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " " + edad;
    }
}