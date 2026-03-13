package es.etg.dam.acceso;

public class InstitutoDAOFactory {

    public static InstitutoDAO obtenerDAO(Modo modo) throws Exception {
        if (modo == Modo.SQLITE) {
            return new InstitutoSQLiteDAOImp();
        } else if (modo == Modo.ORACLE) {
            return new InstitutoOracleXeDAOImp();
        
        } else if (modo == Modo.HIBERNATE) {
            return new InstitutoHibernateDAOImp();

        } else {
            return new InstitutoMockDAOImp();
        }
    }
}
