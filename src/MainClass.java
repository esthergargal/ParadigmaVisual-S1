import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
public class MainClass{


    public static void main(String [] args) throws SQLException {
        Menu m = new Menu(new OracleConnection("x6752695", "x6752695", false));
        System.out.println("Antes de hacer nada");
        m.mostrarTabla();
        m.borrarTablas();
        System.out.println("Después de borrar");
        m.mostrarTabla();
        m.crearTablas();
        System.out.println("Después de crear");
        m.mostrarTabla();
        m.salir();
//
//
//        m.mostrarMenu();
//        OracleConnection connection = new OracleConnection("x6752695", "x6752695", false);
//        ResultSet rs = connection.consultar("SELECT TABLE_NAME FROM USER_TABLES");
//        ArrayList<String> ids = new ArrayList<>();
//        ArrayList<Integer> precios = new ArrayList<>();
//
//        while (rs.next()){
//            String id = rs.getString("TABLE_NAME");
//            int c = rs.getInt("PRECIO");
//            ids.add(id);
//            precios.add(c);
//        }
//
//        System.out.println("ID\tPRECIO");
//        for (int i=0; i < ids.size(); i++){
//            System.out.println(ids.get(i) + "\t" + precios.get(i));
//        }

//        connection.cerrarConexion();
    }
}
