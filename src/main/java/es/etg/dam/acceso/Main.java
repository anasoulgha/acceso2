package es.etg.dam.acceso;

import java.util.List;
import java.util.Scanner;

public class Main {

   public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InstitutoDAO dao = null;

       try {
            // SELECCIÓN DE MODO
            System.out.println("==== Seleccione modo de conexión ====");
            System.out.println("1. MOCK");
            System.out.println("2. SQLITE");
            System.out.println("3. ORACLE");
            System.out.println("4. HIBERNATE");
            System.out.print("Elige una opción: ");
            int opcionModo = sc.nextInt();
            sc.nextLine();

            Modo modo;
            switch (opcionModo) {
                case 2:  modo = Modo.SQLITE; break;
                case 3:  modo = Modo.ORACLE; break;
                case 4:  modo = Modo.HIBERNATE; break;
                default: modo = Modo.MOCK;   break;
            }

            dao = InstitutoDAOFactory.obtenerDAO(modo);
            System.out.println("Conectado usando modo: " + modo);

            int opcion = 1; 

            while (opcion != 0) {
                System.out.println("\n===== MENÚ INSTITUTO =====");
                System.out.println("1. Crear tabla curso");
                System.out.println("2. Insertar alumno");
                System.out.println("3. Insertar curso");
                System.out.println("4. Actualizar alumno");
                System.out.println("5. Actualizar curso");
                System.out.println("6. Listar alumnos");
                System.out.println("7. Listar cursos con alumnos");
                System.out.println("8. Consulta por parámetro");
                System.out.println("9. Borrar alumno");
                System.out.println("0. Salir");
                System.out.print("Elige opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1: // CREAR TABLA CURSO
                        dao.crearTabla();
                        System.out.println("Tabla curso creada correctamente.");
                        break;

                    case 2: // INSERTAR ALUMNO
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = sc.nextInt();
                        sc.nextLine();

                        Alumno nuevo = new Alumno(nombre, apellido, edad);
                        int filasInsert = dao.insertar(nuevo);
                        System.out.println("Registros insertados: " + filasInsert);
                        break;

                    case 3: // INSERTAR CURSO
                       System.out.print("Nombre del curso: ");
                        String nombreCurso = sc.nextLine();
                        System.out.print("Descripción: ");
                        String descripcion = sc.nextLine();
                        System.out.print("Alumno(nombre): ");
                        String alumnoNombre = sc.nextLine();

                        Alumno alumnoCurso = new Alumno(alumnoNombre, "", 0);
                        int filasCurso = dao.insertarCurso(nombreCurso, descripcion, alumnoCurso);
                        System.out.println("Cursos insertados: " + filasCurso);
                        break;

                    case 4: // ACTUALIZAR ALUMNO
                        System.out.print("Nombre del alumno a actualizar: ");
                        String nombreUpd = sc.nextLine();
                        System.out.print("Apellido: ");
                        String nuevoApellido = sc.nextLine();
                        System.out.print("Nueva edad: ");
                        int nuevaEdad = sc.nextInt();
                        sc.nextLine();

                        Alumno upd = new Alumno(nombreUpd, nuevoApellido, nuevaEdad);
                        int filasUpd = dao.actualizar(upd);
                        System.out.println("actualizados: " + filasUpd);
                        break;

                    case 5: // ACTUALIZAR CURSO
                        System.out.print("ID del curso a actualizar: ");
                        int idCurso = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo nombre del curso: ");
                        String nuevoCurso = sc.nextLine();
                        System.out.print("Nueva descripción: ");
                        String nuevaDesc = sc.nextLine();

                        int filasUpdCurso = dao.actualizarCurso(idCurso, nuevoCurso, nuevaDesc);
                        System.out.println("Cursos actualizados: " + filasUpdCurso);
                        break;
                              
                    case 6: // LISTAR ALUMNOS
                        List<Alumno> lista = dao.listarAlumnos();
                        System.out.println("--- Alumnos ---");
                        for (Alumno a : lista) {
                            System.out.println(a);
                        }
                        break;

                   case 7: // LISTAR CURSOS CON ALUMNOS
                        List<String> cursos = dao.listarCursosConAlumnos();
                        System.out.println("--- Cursos con alumnos ---");
                        for (String c : cursos) {
                            System.out.println(c);
                        }
                        break;

                    case 8: // CONSULTA POR PARÁMETRO
                        System.out.print("Introduce edad: ");
                        int edadMin = sc.nextInt();
                        sc.nextLine();
                        List<Alumno> filtrados = dao.listarAlumnosPorEdad(edadMin);
                        System.out.println("--- Alumnos filtrados ---");
                        for (Alumno a : filtrados) {
                            System.out.println(a);
                        }
                        break;

                     case 9: // BORRAR ALUMNO
                        System.out.print("Nombre del alumno a borrar: ");
                        String nombreDelAlumno = sc.nextLine();
                        Alumno borrarAlumno = new Alumno(nombreDelAlumno, "", 0);
                        int filasBorradas = dao.borrar(borrarAlumno);
                        System.out.println("Registros borrados: " + filasBorradas);
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();

        } finally {
            sc.close();
        }
    }
}