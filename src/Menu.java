import jdk.jshell.spi.ExecutionControlProvider;
import org.jetbrains.annotations.NotNull;

import java.awt.desktop.SystemSleepEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final String opciones =
            "1. Borrar tabla\n" +
            "2. Crear tabla\n" +
            "3. Insertar en tabla\n" +
            "4. Dar de alta un pedido\n" +
            "5. Mostrar contenido de la tabla\n" +
            "6. Salir del programa\n";
    
    private OracleConnection mi_conexion;
    private Scanner capt;


    public Menu(OracleConnection conn){
        mi_conexion = conn;
        capt = new Scanner(System.in);
    }


    public void mostrarMenu(){
        System.out.print(opciones);
    }

    public void seleccionarOpcion(@NotNull int eleccion)
    {
        switch(eleccion){
            case 1 ->infoReset();
            case 2 -> infoInsertar();
            case 3 -> infoDarAlta();
            case 4 -> infoMostrar();
            case 5 -> salir();
        }
    }

    private void infoReset() {

    }
    private void infoInsertar(){

    }
    private void infoDarAlta(){

    }
    private void infoMostrar(){

    }
    public void salir(){
        mi_conexion.cerrarConexion();
        System.out.println("Hasta la próxima!");
    }

    public boolean borrarTablas(){
        boolean correcto = true;
        ArrayList<String> nombre_tablas = new ArrayList<>();
        try(ResultSet rs = mi_conexion.consultar("SELECT TABLE_NAME FROM USER_TABLES")){
            while (rs.next()){
                nombre_tablas.add(rs.getString("TABLE_NAME"));
            }
        } catch (Exception e){
            correcto = false;
            mi_conexion.cerrarConexion();
            e.printStackTrace();
        }

        for (String nombre : nombre_tablas){
            try (ResultSet rs = mi_conexion.consultar("DROP TABLE " + nombre)){
                System.out.println("Borrando tabla " + nombre);
            }catch (Exception e){
                correcto = false;
                System.out.println("Error borrando tabla " + nombre);
                mi_conexion.cerrarConexion();
                e.printStackTrace();
            }
        }

        return correcto;
    }

    public boolean crearTablas(){
        boolean correcto = true;
        ArrayList<String> nombres_tabla = new ArrayList<>();
        ArrayList<String> columnas = new ArrayList<>();
        nombres_tabla.add("PRUEBA1");
        columnas.add("(nombre1 VARCHAR2(30), n1 NUMBER PRIMARY KEY)");
        nombres_tabla.add("PRUEBA2");
        columnas.add("(nombre2 VARCHAR2(30), n2 NUMBER PRIMARY KEY)");
        nombres_tabla.add("PRUEBA3");
        columnas.add("(nombre3 VARCHAR2(30), n3 NUMBER PRIMARY KEY)");

        for (int i=0; i < nombres_tabla.size(); i++) {
            try (ResultSet rs = mi_conexion.consultar("CREATE TABLE " + nombres_tabla.get(i) + columnas.get(i))) {
                System.out.println("Creada tabla " + nombres_tabla.get(i));
            } catch (Exception e) {
                System.out.println("Error creando tabla " + nombres_tabla.get(i));
                mi_conexion.cerrarConexion();
                correcto = false;
                e.printStackTrace();
            }
        }
        return correcto;
    }

    private boolean insertarTabla(String tabla){
        return false;
    }

    private boolean darAlta(String tabla, String tupla){
        return false;
    }

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
