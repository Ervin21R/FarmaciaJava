package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static final String URL="jdbc:mysql://localhost:3306/farmacia?autoReconnect=true&useSSL=false";
    public static final String USUARIO="root";
    public static final String PASSWORD="0442334theband";
    
    public Connection getConnection(){
        Connection conexion=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=(Connection)DriverManager.getConnection(URL, USUARIO, PASSWORD); 
            //JOptionPane.showMessageDialog(null,"Conexcion exitosa!!");
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error "+ex);
        }
       return conexion; 
    }
}