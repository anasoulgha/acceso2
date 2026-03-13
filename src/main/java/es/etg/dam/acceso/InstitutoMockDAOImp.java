package es.etg.dam.acceso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstitutoMockDAOImp implements InstitutoDAO {

    
    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("test1", "test1", 1));
        alumnos.add(new Alumno("test2", "test2", 2));
        return alumnos;
    }

   @Override
    public int insertar(Alumno a) throws SQLException {
        return 1; 
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        return 1; 
    }

    @Override
    public int borrar(Alumno a) throws SQLException {
        return 1; 
    }

    @Override
    public void crearTabla() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTabla'");
    }

    @Override
    public int insertarCurso(String nombreCurso, String descripcion, Alumno alumno) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertarCurso'");
    }

    @Override
    public int actualizarCurso(int idCurso, String nuevoNombre, String nuevaDesc) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarCurso'");
    }

    @Override
    public List<String> listarCursosConAlumnos() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCursosConAlumnos'");
    }

    @Override
    public List<Alumno> listarAlumnosPorEdad(int edadMin) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAlumnosPorEdad'");
    }
}
