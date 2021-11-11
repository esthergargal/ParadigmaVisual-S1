 import jdk.jshell.spi.ExecutionControlProvider;
import org.jetbrains.annotations.NotNull;

import java.awt.desktop.SystemSleepEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 import java.util.Locale;
 import java.util.Scanner;

public class Menu {
    private final String opciones =
            "1. Reset de tablas" +
            "2. Dar de alta un pedido\n" +
            "3. Mostrar contenido de la tabla\n" +
            "4. Salir del programa\n";
    
    private OracleConnection mi_conexion;
    private Scanner capt;

    /**
     * @brief Inicializador
     * @param conn Conexión con la base de datos
     */
    public Menu(OracleConnection conn){
        mi_conexion = conn;
        capt = new Scanner(System.in);
    }

    /**
     * @brief Muestra el menú de la aplicación
     */
    public void mostrarMenu(){
        System.out.print(opciones);
    }

    /**
     * @brief Permite seleccionar una opción del menú
     */
    public void seleccionarOp(){
        int eleccion = capt.nextInt();
        boolean estado = true;
        switch(eleccion){
            case 1 -> infoReset();
            case 2 -> infoInsertar();
            case 3 -> infoDarAlta();
            case 4 -> infoMostrar();
            case 5 -> salir();
            default -> estado = false;
        }
    }

    /**
     * @brief Resetea la información de las tablas almacenadas
     */
     private void infoReset() {
        System.out.println("¿Desea resetear las tablas? (s/n)");
        String reset = capt.nextLine(); // Esto diferencia entre "S" y "s"?
        reset.toUpperCase();
        if(reset == "s" || reset == "S"){
            Tienda.reset();
        }
    }

    /**
     * @brief Inserta la información
     */

    private void infoInsertar(){

    }

    /**
     * @brief Inserta la información
     */
    private void infoDarAlta(){
        //Capturar datos básicos: código del pedido, nombre y cantidad de cada material, cif ?,
        // nombre del patrocinador, nº pista, fecha, trabajador ?
        infoInsertar();

        System.out.println("1. Añadir detalle de producto \n" +
                          "2. Eliminartodos los detalles de producto \n" +
                          "3. Cancelar pedido \n" +                     "4. Finalizar pedido");
        int eleccion = capt.nextInt();
        switch(eleccion){
            case 1:
                //Capturar datos articulo y cantidad
                //Si hay stok --> realizar inserción y actualizar
                infoMostrar();
                break;
            case 2:
                infoMostrar();
                break;
            case 3:
                infoMostrar();
                break;
            case 4:
                break;
        }
    }

    /**
     * @brief Muestra la información (qué info? --> contenido de las tablas de la BD
     */
    private void infoMostrar(){
//        ArrayList<String> nombre_tablas = new ArrayList<>();
//        try(ResultSet rs = mi_conexion.consultar("SELECT TABLE_NAME FROM USER_TABLES")){
//            while (rs.next()){
//                nombre_tablas.add(rs.getString("TABLE_NAME"));
//            }
//        }
//        for (String nombre : nombre_tablas){
//            try (ResultSet rs = mi_conexion.consultar("Select * from " + nombre)){
//                System.out.println("Mostrando tabla " + nombre);
//                mostrarTabla();
//            }catch (Exception e){
//                System.out.println("Error al mostrar la tabla " + nombre);
//                mi_conexion.cerrarConexion();
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * @brief Cierra la conexión con la base de datos
     */
    public void salir(){
        mi_conexion.cerrarConexion();
        System.out.println("Hasta la próxima!");
    }

    /**
     * @brief Borra las tablas seleccionadas
     * @return @true si la operación se ha realizado con éxito @false en caso contrario
     */


    /**
     * @brief Function that creates database's tables
     * @return @true si la operación ha sido realizada con éxito @false en caso contrario


    /**
     * @brief
     * @param tabla La tabla a insertar
     * @return
     */
    private boolean insertarTabla(String tabla){
        boolean res = false;

        return res;
    }

    /**
     * @brief Inserta una tupla en una tabla
     * @param tabla La tabla en la que se inserta
     * @param tupla La tupla a insertar
     * @return
     */
    private boolean darAlta(String tabla, String tupla){
        return false;
    }

    /**
     * @brief Muestra la tabla seleccionada
     * @return @true si la operación se ha completado con éxito @false en caso contrario
     */
    public boolean mostrarTabla() {
        boolean correcto = true;
        try (ResultSet rs = mi_conexion.consultar("SELECT TABLE_NAME FROM USER_TABLES")) {
            ArrayList<String> nombres_tablas = new ArrayList<>();
            while (rs.next()) {
                String nombre = rs.getString("TABLE_NAME");
                System.out.println(nombre);
            }
        } catch (Exception e){
            correcto = false;
            mi_conexion.cerrarConexion();
            e.printStackTrace();
        }
        return correcto;
    }
}
