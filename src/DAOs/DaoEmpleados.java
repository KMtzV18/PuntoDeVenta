/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevmt
 */
public class DaoEmpleados {
    public static void CargarTablaEmpleados(JTable tabla) throws ClassNotFoundException, SQLException, Exception{
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        //String codigo = (String)modelo.getValueAt(renglon, 0);
        Agregar_Modificar am = new Agregar_Modificar();
        modelo.setRowCount(0); 
        String[] columnas = {"id_empleado", "nombre_completo", "usuario","password","Activo"};

        
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        // Cargar el driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // URL de conexión con la base de datos
        String url = "jdbc:mysql://localhost:3306/PuntoDeVenta?serverTimezone=America/Mexico_City&zeroDateTimeBehavior=CONVERT_TO_NULL";
        String dbUser = "root";  // Usuario de la base de datos
        String dbPassword = "root";  // Contraseña de la base de datos

        // Establecer la conexión
        conn = DriverManager.getConnection(url, dbUser, dbPassword);

        // Consulta SQL para verificar el usuario y contraseña (hash almacenado)
        String sql = "select * from empleados";
        pstmt = conn.prepareStatement(sql);
        
        
        // Ejecutar la consulta
        rs = pstmt.executeQuery();
        
         while (rs.next()) {
            Object[] fila = new Object[columnas.length];  
            
            for (int j = 0; j < columnas.length; j++) {
                fila[j] = rs.getObject(columnas[j]); 
            }
            
            modelo.addRow(fila);  
        }
            
         } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
    } finally {
        // Cerrar la conexión y liberar recursos
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    }
    
    
    
    
    public static void EliminarEmpleado(String usuario){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        // Cargar el driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // URL de conexión con la base de datos
        String url = "jdbc:mysql://localhost:3306/PuntoDeVenta?serverTimezone=America/Mexico_City&zeroDateTimeBehavior=CONVERT_TO_NULL";
        String dbUser = "root";  // Usuario de la base de datos
        String dbPassword = "root";  // Contraseña de la base de datos

        // Establecer la conexión
        conn = DriverManager.getConnection(url, dbUser, dbPassword);

        // Consulta SQL para verificar el usuario y contraseña (hash almacenado)
        String sql = "update empleados set activo = 0 WHERE usuario = ?;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, usuario);
        
        // Ejecutar la consulta
        pstmt.executeUpdate();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
    } finally {
        // Cerrar la conexión y liberar recursos
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    }
    
    public static void Editar(String usuario)throws ClassNotFoundException, SQLException, Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        registro r = new registro();
        
        try {
        // Cargar el driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // URL de conexión con la base de datos
        String url = "jdbc:mysql://localhost:3306/PuntoDeVenta?serverTimezone=America/Mexico_City&zeroDateTimeBehavior=CONVERT_TO_NULL";
        String dbUser = "root";  // Usuario de la base de datos
        String dbPassword = "root";  // Contraseña de la base de datos

        // Establecer la conexión
        conn = DriverManager.getConnection(url, dbUser, dbPassword);

        // Consulta SQL para verificar el usuario y contraseña (hash almacenado)
        String sql = "select nombre_completo,usuario, password, activo from empleados where usuario = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, usuario);
        
        // Ejecutar la consulta
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
            // Obtenemos el hash almacenado en la base de datos
            //id = rs.getInt("id_empleado");
            r.llenar(rs.getString("nombre_completo"), rs.getString("usuario"), rs.getString("password"),rs.getBoolean("activo"));
            r.setVisible(true);
            
         }
    
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
    } finally {
        // Cerrar la conexión y liberar recursos
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    }
    
    
    
}
