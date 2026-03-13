package es.etg.dam.acceso;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InstitutoHibernateDAOImp implements InstitutoDAO {

    private final SessionFactory sessionFactory;

    public InstitutoHibernateDAOImp() {

        sessionFactory = new Configuration().configure("es/etg/dam/acceso/hibernate.cfg.xml").buildSessionFactory();
    }

    @Override
    public List<Alumno> listarAlumnos() {
        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("from Alumno", Alumno.class).list();
        }
    }

    @Override
    public int insertar(Alumno a) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(a);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    @Override
    public int actualizar(Alumno a) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(a);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    @Override
    public int borrar(Alumno a) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(a);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    @Override
    public List<Alumno> listarAlumnosPorEdad(int edadMin) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Alumno where edad >= :edad", Alumno.class)
                    .setParameter("edad", edadMin)
                    .list();
        }
    }

    @Override
    public void crearTabla() {
    }

    @Override
    public int insertarCurso(String nombreCurso, String descripcion, Alumno alumno) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Curso nuevoCurso = new Curso();
            nuevoCurso.setNombre(nombreCurso);
            nuevoCurso.setDescripcion(descripcion);
            nuevoCurso.setAlumnoNombre(alumno.getNombre());

            session.persist(nuevoCurso);

            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int actualizarCurso(int idCurso, String nuevoNombre, String nuevaDesc) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Curso curso = session.get(Curso.class, idCurso);

            if (curso != null) {

                curso.setNombre(nuevoNombre);
                curso.setDescripcion(nuevaDesc);

                tx.commit();
                return 1;
            } else {
                System.out.println("No se encontró el curso con ID: " + idCurso);
                return 0;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> listarCursosConAlumnos() {
        try (Session session = sessionFactory.openSession()) {

            List<Curso> listaCursos = session.createQuery("from Curso", Curso.class).list();

            List<String> resultado = new ArrayList<>();
            for (Curso c : listaCursos) {
                resultado.add("ID: " + c.getId()
                        + " | Curso: " + c.getNombre()
                        + " | Desc: " + c.getDescripcion()
                        + " | Alumno: " + c.getAlumnoNombre());
            }
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
