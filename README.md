@startuml
package "es.etg.dam.acceso" {

    class Alumno <<Entity>> {
        - nombre : String {id}
        - apellido : String
        - edad : int
        + Alumno()
        + Alumno(String, String, int)
        + getNombre() : String
        + setNombre(String) : void
        + getApellido() : String
        + setApellido(String) : void
        + getEdad() : int
        + setEdad(int) : void
        + toString() : String
    }

    class Curso <<Entity>> {
        - id : int {id}
        - nombre : String
        - descripcion : String
        - alumnoNombre : String
        + Curso()
        + Curso(int, String, String, String)
        + Curso(String, String, String)
        + getId() : int
        + setId(int) : void
        + getNombre() : String
        + setNombre(String) : void
        + getDescripcion() : String
        + setDescripcion(String) : void
        + getAlumnoNombre() : String
        + setAlumnoNombre(String) : void
        + toString() : String
    }

    interface InstitutoDAO {
        + listarAlumnos() : List<Alumno>
        + insertar(Alumno) : int
        + actualizar(Alumno) : int
        + borrar(Alumno) : int
        + crearTabla() : void
        + insertarCurso(String, String, Alumno) : int
        + actualizarCurso(int, String, String) : int
        + listarCursosConAlumnos() : List<String>
        + listarAlumnosPorEdad(int) : List<Alumno>
    }

    enum Modo {
        MOCK
        SQLITE
        ORACLE
        HIBERNATE
    }

    class InstitutoDAOFactory {
        + obtenerDAO(Modo) : InstitutoDAO
    }

    class InstitutoMockDAOImp {
        + listarAlumnos() : List<Alumno>
        + insertar(Alumno) : int
        + actualizar(Alumno) : int
        + borrar(Alumno) : int
        + crearTabla() : void
        + insertarCurso(String, String, Alumno) : int
        + actualizarCurso(int, String, String) : int
        + listarCursosConAlumnos() : List<String>
        + listarAlumnosPorEdad(int) : List<Alumno>
    }

    class InstitutoOracleXeDAOImp {
        - conn : Connection
        - URL : String
        - DATABASE_USER : String
        - DATABASE_PASS : String
        + InstitutoOracleXeDAOImp()
        + listarAlumnos() : List<Alumno>
        + insertar(Alumno) : int
        + actualizar(Alumno) : int
        + borrar(Alumno) : int
        + crearTabla() : void
        + insertarCurso(String, String, Alumno) : int
        + actualizarCurso(int, String, String) : int
        + listarCursosConAlumnos() : List<String>
        + listarAlumnosPorEdad(int) : List<Alumno>
    }

    class InstitutoSQLiteDAOImp {
        - DATABASE_NAME : String
        - JDBC_URL : String
        - conn : Connection
        + InstitutoSQLiteDAOImp()
        + listarAlumnos() : List<Alumno>
        + insertar(Alumno) : int
        + actualizar(Alumno) : int
        + borrar(Alumno) : int
        + crearTabla() : void
        + insertarCurso(String, String, Alumno) : int
        + actualizarCurso(int, String, String) : int
        + listarCursosConAlumnos() : List<String>
        + listarAlumnosPorEdad(int) : List<Alumno>
    }

    class InstitutoHibernateDAOImp {
        - sessionFactory : SessionFactory
        + InstitutoHibernateDAOImp()
        + listarAlumnos() : List<Alumno>
        + insertar(Alumno) : int
        + actualizar(Alumno) : int
        + borrar(Alumno) : int
        + crearTabla() : void
        + insertarCurso(String, String, Alumno) : int
        + actualizarCurso(int, String, String) : int
        + listarCursosConAlumnos() : List<String>
        + listarAlumnosPorEdad(int) : List<Alumno>
    }

    class Main {
        + main(String[]) : void
    }

    ' Relaciones
    InstitutoDAO <|.. InstitutoMockDAOImp
    InstitutoDAO <|.. InstitutoOracleXeDAOImp
    InstitutoDAO <|.. InstitutoSQLiteDAOImp
    InstitutoDAO <|.. InstitutoHibernateDAOImp

    InstitutoDAOFactory ..> InstitutoDAO : "crea"
    InstitutoDAOFactory ..> Modo : "usa"

    Main --> InstitutoDAOFactory
    Main --> InstitutoDAO
    Main ..> Alumno
    Main ..> Curso
    Main ..> Modo
}
@enduml