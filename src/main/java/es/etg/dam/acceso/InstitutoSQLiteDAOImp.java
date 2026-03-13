package es.etg.dam.acceso;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstitutoSQLiteDAOImp implements InstitutoDAO {

    private static final String DATABASE_NAME = "mybasedatos.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private Connection conn;

    public InstitutoSQLiteDAOImp() throws Exception {
        URL resource = InstitutoSQLiteDAOImp.class.getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        conn = DriverManager.getConnection(url);

    }

    @Override
    public void crearTabla() throws SQLException {

        final String sqlCurso = "CREATE TABLE IF NOT EXISTS curso ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "descripcion TEXT,"
                + "alumno_nombre TEXT NOT NULL,"
                + "FOREIGN KEY (alumno_nombre) REFERENCES alumno(nombre))";

        PreparedStatement ps1 = conn.prepareStatement(sqlCurso);
        ps1.executeUpdate();
        ps1.close();
    }

    @Override
    public int insertarCurso(String nombreCurso, String descripcion, Alumno alumno) throws SQLException {
        final String sql = "INSERT INTO curso (nombre, descripcion, alumno_nombre) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreCurso);
            ps.setString(2, descripcion);
            ps.setString(3, alumno.getNombre());
            return ps.executeUpdate();
        }

    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        final String query = "SELECT nombre, apellido, edad FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");

            Alumno a = new Alumno(nombre, apellido, edad);
            alumnos.add(a);
        }

        rs.close();
        ps.close();

        return alumnos;

    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        final String sql = "INSERT INTO alumno (nombre, apellido, edad) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getApellido());
        ps.setInt(3, a.getEdad());

        int filas = ps.executeUpdate();

        ps.close();

        return filas;

    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        int numRegistrosActualizados = 0;
        final String sql = "UPDATE alumno SET edad = ? where nombre = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a.getEdad());
        ps.setString(2, a.getNombre());
        numRegistrosActualizados = ps.executeUpdate();
        ps.close();
        return numRegistrosActualizados;
    }

    @Override
    public int borrar(Alumno a) throws SQLException {
        int numRegistrosActualizados = 0;
        final String sql = "DELETE FROM alumno where nombre = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getNombre());
        numRegistrosActualizados = ps.executeUpdate();
        ps.close();
        return numRegistrosActualizados;
    }

    public int actualizarCurso(int idCurso, String nuevoNombre, String nuevaDesc) throws SQLException {
        final String sql = "UPDATE curso SET nombre = ?, descripcion = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nuevoNombre);
        ps.setString(2, nuevaDesc);
        ps.setInt(3, idCurso);

        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    public List<String> listarCursosConAlumnos() throws SQLException {
        final String sql = "SELECT c.id, c.nombre, c.descripcion, a.nombre AS alumno "
                + "FROM curso c "
                + "JOIN alumno a ON c.alumno_nombre = a.nombre";

        List<String> cursos = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombreCurso = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String alumno = rs.getString("alumno");

            cursos.add("ID: " + id + " | Curso: " + nombreCurso + " | Desc: " + descripcion + " | Alumno: " + alumno);
        }

        rs.close();
        ps.close();
        return cursos;
    }

// LISTAR ALUMNOS POR EDAD
    public List<Alumno> listarAlumnosPorEdad(int edadMin) throws SQLException {
        final String sql = "SELECT nombre, apellido, edad FROM alumno WHERE edad >= ?";
        List<Alumno> alumnos = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, edadMin);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");

            alumnos.add(new Alumno(nombre, apellido, edad));
        }

        rs.close();
        ps.close();
        return alumnos;
    }
}
