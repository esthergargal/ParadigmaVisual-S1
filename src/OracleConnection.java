import java.sql.*;
import java.util.concurrent.ExecutionException;

class OracleConnection {
    /*
    Código para conectarse a la base de datos.
    https://mkyong.com/jdbc/connect-to-oracle-db-via-jdbc-driver-java/
 */
    private Connection conn;
    public Connection conectar(String user, String passwd) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@//oracle0.ugr.es:1521/practbd.oracle0.ugr.es", user, passwd
        );
        if (conn != null) {
            System.out.println("Conectado a la BD");
        } else {
            System.out.println("Error al conectar a la BD");
        }
        return conn;
    }

    public OracleConnection(String user, String passwd, boolean autocommit) {
        try {
            conn = conectar(user, passwd);
            conn.setAutoCommit(autocommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Conexión a la base de datos cerrada");
        }
    }

    public ResultSet consultar(String consulta) throws SQLException {
        Statement stmnt = conn.createStatement();
        ResultSet res = stmnt.executeQuery(consulta);
        return res;
    }
}