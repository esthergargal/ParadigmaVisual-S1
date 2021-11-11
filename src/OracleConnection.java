import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

class OracleConnection {
    /*
        Código para conectarse a la base de datos:
        https://mkyong.com/jdbc/connect-to-oracle-db-via-jdbc-driver-java/
    */
    private Connection conn;

    /**
     * @brief Permite conectarse a la base de datos
     * @param user Usuario
     * @param passwd Contraseña
     * @return conn Objeto que almacena los datos de la conexión
     * @throws SQLException
     */
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

    /**
     * @brief Constructor con parámetros
     * @param user Usuario
     * @param passwd Contraseña
     * @param autocommit
     */
    public OracleConnection(String user, String passwd, boolean autocommit) {
        try {
            conn = conectar(user, passwd);
            conn.setAutoCommit(autocommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Permite cerrar la conexión con la base de datos
     */
    public void cerrarConexion(){
        try {
            commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Conexión a la base de datos cerrada");
        }
    }

    /**
     * @brief Permite realizar consultas a la base de datos
     * @param consulta La consulta a realizar
     * @return
     * @throws SQLException
     */
    public ResultSet consultar(String consulta) throws SQLException {
        Statement stmnt = conn.createStatement();
        ResultSet res = stmnt.executeQuery(consulta);
        return res;
    }

    /**
     * @brief Guarda el estado de la base de datos
     * @param nombre Nombre del punto de guardado
     * @return El punto de guardado
     * @throws SQLException
     */
    public Savepoint crearSavePoint(String nombre) throws SQLException {
        return conn.setSavepoint(nombre);
    }

    /**
     * @brief Elimina los cambios no guardados en la base de datos hasta el punto de guardado
     * @param save_point Punto de guardado
     * @throws SQLException
     */
    public void rollback(Savepoint save_point) throws SQLException {
        conn.rollback(save_point);
    }

    /**
     * @brief Guarda los cambios en la base de datos
     * @throws SQLException
     */
    public void commit() throws  SQLException{
        conn.commit();
    }

    /**
     * @brief Inserta una tupla en una tabla
     * @param nombre_tabla Nombre de la tabla en la que se va a insertar una tupla
     * @param atributos Atributos de la tupla que va a ser insertada
     * @throws SQLException
     * @return @true si la operacio ha sido realizada con éxito
     */
    public boolean insertarTupla(String nombre_tabla, ArrayList<String> atributos) throws SQLException{
        boolean res = false;
        String consulta = "INSERT INTO " + nombre_tabla + " VALUES ( ";

        // Almacenamos todos los valores en el string
        for(int i=0; i < atributos.size()-1; ++i){
            consulta += atributos.get(i) + ", ";
        }

        // Cerrar la insercción
        consulta += atributos.get(atributos.size()-1) + ")";

        // INSERT INTO tabla VALUES (a1, a2, a3)
        try(ResultSet rs = consultar(consulta)){
            res = true;
        } catch(SQLException e){
            cerrarConexion();
            e.printStackTrace();
        }

        return res;
    }

    public boolean existeTabla(String nombre) throws SQLException{
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null,null,nombre,null);
        if (rs.next()){
            return true;
        } else{
            return false;
        }
    }
    
}