import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;


public class Tienda {
    private OracleConnection mi_bd;
    private ArrayList<ArrayList<String>> tienda_por_defecto;
    private ArrayList<String> nombres_tablas;

    public Tienda(){
        nombres_tablas = new ArrayList<>();
        tienda_por_defecto = new ArrayList<>();
        nombres_tablas.add("STOCK");
        nombres_tablas.add("PEDIDO");
        nombres_tablas.add("DETALLE-PEDIDO");

        for (Integer i=0; i < 10; i++){
            int cantidad = (int)Math.random() * 1000;
            tienda_por_defecto.add(new ArrayList<>());
            tienda_por_defecto.get(i).add(i.toString()  + String.valueOf(cantidad));

            DatabaseMetaData md = mi_bd.getMetadata();
            try(ResultSet rs = md.getTables(null, null, "Stock", null)){

            } catch (Exception e){
                mi_bd.cerrarConexion();
                e.printStackTrace();
            }

            if conn.ex

    }

    /**
     * @brief Crea las tablas STOCK, PEDIDO y DETALLE_PEDIDO
     * @return @true
     */
    public boolean crearTablas(){
        String consulta = "CREATE TABLE STOCK (Cproducto NUMBER PRIMARY KEY, Cantidad NUMBER NOT NULL);\n" +
                "CREATE TABLE PEDIDO (Cpedido NUMBER PRIMARY KEY, Ccliente NUMBER NOT NULL, Fecha_pedido DATE NOT NULL);\n" +
                "CREATE TABLE DETALLE_PEDIDO (Cpedido,Cproducto,Cantidad NUMBER NOT NULL, " +
                                            "PRIMARY KEY(Cpedido, Cproducto), \n" +
                                            "FOREIGN KEY(Cpedido) REFERENCES PEDIDO(Cpedido),\n" +
                                            "FOREIGN KEY(Cproducto) REFERENCES STOCK(Cproducto));";
        boolean res;
        try(ResultSet rs = mi_bd.consultar(consulta)){
            res = true;
        } catch(Exception e){
            mi_bd.cerrarConexion();
            res = false;
            e.printStackTrace();
        }
        return res;
    }

    public boolean borrarTablas(){
        boolean correcto = true;

        for (String nombre : nombres_tablas){
            try (ResultSet rs = mi_bd.consultar("DROP TABLE " + nombre)){
            }catch (Exception e){
                correcto = false;
                mi_bd.cerrarConexion();
                e.printStackTrace();
            }
        }

        return correcto;
    }
}

