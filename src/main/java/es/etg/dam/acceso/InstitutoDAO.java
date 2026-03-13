package es.etg.dam.acceso;

import java.sql.SQLException;
import java.util.List;

public interface InstitutoDAO {

    public List<Alumno> listarAlumnos() throws SQLException;
    public int insertar(Alumno a) throws SQLException;
    public int actualizar(Alumno a) throws SQLException;
    public int borrar(Alumno a) throws SQLException;

 
    public void crearTabla() throws SQLException;


    public int insertarCurso(String nombreCurso, String descripcion, Alumno alumno) throws SQLException;
    public int actualizarCurso(int idCurso, String nuevoNombre, String nuevaDesc) throws SQLException;
    public List<String> listarCursosConAlumnos() throws SQLException;

   
    public List<Alumno> listarAlumnosPorEdad(int edadMin) throws SQLException;
}
