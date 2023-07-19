package packControlador1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import packModelo1.Jugador1;
public class Controlador1 {
	
	public int obtenerUltimoID() {
        int ultimoID = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
            Connection conexion = DriverManager.getConnection(url);

            if (conexion != null) {
                System.out.println("Hay conexión");
            } else {
                System.out.println("No hay conexión");
            }

            PreparedStatement pt = conexion.prepareStatement("SELECT MAX(ID) AS UltimoID FROM JUGADORES");
            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                ultimoID = rs.getInt("UltimoID");
            } 

            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "Ha ocurrido un error al obtener el último ID");
        }
        return ultimoID;
	}
    public void guardarEnBaseDatos( Jugador1 jugador ) {                  //  GUARDAR EN BASE DE DATOS :
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
            Connection conexion = DriverManager.getConnection(url);

            if (conexion != null) {
                System.out.println("Hay conexión");
            } else {
                System.out.println("No hay conexión");
            }
         // Obtener el último ID de la base de datos
            int ultimoID = obtenerUltimoID();

            // Generar el nuevo ID automáticamente
            int nuevoID = ultimoID + 1;

            PreparedStatement pt = conexion.prepareStatement("INSERT INTO JUGADORES VALUES (?, ?, ?, ?)");
            pt.setInt(1, jugador.getId());
            pt.setString(2, jugador.getNombre());
            pt.setInt(3, jugador.getDorsal());
            pt.setDouble(4, jugador.getAltura());

            pt.executeUpdate();

            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "Ha ocurrido un error al guardar el registro nuevo ");
        }
    }                                                                         //  EXISTE EN BASE DE DATOS :

    public boolean existeEnBaseDeDatos( String nombre, int dorsal, double altura) {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
            Connection conexion = DriverManager.getConnection(url);

            if (conexion != null) {
                System.out.println("Hay conexión");
            } else {
                System.out.println("No hay conexión");
            }

            
            PreparedStatement pt = conexion.prepareStatement("SELECT * FROM JUGADORES WHERE ID = ? OR Nombre = ? AND Dorsal = ? AND Altura = ?");
          
            pt.setString(2, nombre);
            pt.setInt(3, dorsal);
            pt.setDouble(4, altura);

          
            ResultSet rs = pt.executeQuery();

            if (conexion != null) {
                conexion.close();
            }

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, " BASE DE DATOS : Ha ocurrido un error");
        }
        return false;
    }
    
    
    
    

    public String listarBaseDeDatos() {                                   //   LISTAR BASE DE DATOS :
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
            Connection conexion = DriverManager.getConnection(url);

            if (conexion != null) {
                System.out.println("Hay conexión");
            } else {
                System.out.println("No hay conexión");
            }

            PreparedStatement pt = conexion.prepareStatement("SELECT * FROM JUGADORES");
            ResultSet rs = pt.executeQuery();

            String mensaje = "";

            while (rs.next()) {
            	
                mensaje += "  ID:  " + rs.getInt(1) + "    - Nombre:  " + rs.getString(2) + "     - Dorsal:  " + rs.getInt(3) + "    - Altura:  " + rs.getDouble(4);
                mensaje += "\n";
            }

            if (conexion != null) {
                conexion.close();
            }

            return mensaje;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, "LISTAR_BASE_DATOS : Ha ocurrido un error");
        }

        return null;
    }                                                                       

    public void eliminarDeBaseDatos(int id) {                              // ELIMINAR DE BASE DE DATOS :
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
            Connection conexion = DriverManager.getConnection(url);

            if (conexion != null) {
                System.out.println("Hay conexión");
            } else {
                System.out.println("No hay conexión");
            }

            PreparedStatement pt = conexion.prepareStatement("DELETE FROM JUGADORES WHERE ID = ?");
            pt.setInt(1, id);
            
            int filasAfectadas = pt.executeUpdate();

            if (filasAfectadas > 0) {
                //ptionPane.showMessageDialog(null, "Jugador eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "El jugador con ID " + id + " no existe en la base de datos");
            }

            //.executeUpdate();

            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, " ELIMINAR_BASE_DATOS : Ha ocurrido un error");
        }
    }

    public void modificarEnBaseDatos(int id, String nombre, int dorsal, double altura) {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
            Connection conexion = DriverManager.getConnection(url);

            if (conexion != null) {
                System.out.println("Hay conexión");
            } else {
                System.out.println("No hay conexión");
            } 

            PreparedStatement pt = conexion.prepareStatement("UPDATE JUGADORES SET NOMBRE = ?, DORSAL = ?, ALTURA = ? WHERE ID = ?");
            pt.setString(1, nombre);
            pt.setInt(2, dorsal);
            pt.setDouble(3, altura);
            pt.setInt(4, id);

            pt.executeUpdate();

            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, " MODIFICAR_BASE_DATOS : Ha ocurrido un error");
        }
    }
     // Función para obtener el ID del jugador existente en la base de datos

        public int obtenerIDJugador(String nombre, int dorsal, double altura) {
            int idEnBaseDeDatos = -1; // Valor por defecto en caso de que no se encuentre el jugador
            
            try {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C:\\bbdd\\examen.txt";
                Connection conexion = DriverManager.getConnection(url);

                PreparedStatement pt = conexion.prepareStatement("SELECT ID FROM JUGADORES WHERE NOMBRE = ? AND DORSAL = ? AND ALTURA = ?");
                pt.setString(1, nombre);
                pt.setInt(2, dorsal);
                pt.setDouble(3, altura);

                ResultSet rs = pt.executeQuery();

                if (rs.next()) {
                    idEnBaseDeDatos = rs.getInt("ID");
                }

                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showInternalMessageDialog(null, "OBTENER_ID_JUGADOR: Ha ocurrido un error");
            }

            return idEnBaseDeDatos;
        

    }
}
