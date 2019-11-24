package jdbc0;

import com.mysql.jdbc.Driver;

import java.util.*;
import java.sql.* ;
import java.sql.SQLException;

public class JDBC0 {
    public static void main(String[] args) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        
        // Creamos el Statement para poder hacer consultas
        Statement st = cn.createStatement();     
        
        // La consulta es un String con código SQL
        String sql1 = "SELECT * FROM cuentas";
        
        // Ejecuta una consulta que devuelve resultados                
        ResultSet rs = st.executeQuery(sql1);
        
        while (rs.next()) {
            System.out.println ("#" +rs.getString ("codigo") + "#" + rs.getString (2)+ "#" + rs.getString("cliente")+"#"+rs.getString("saldo")+"#");
        }
        
		// Ojo, el siuiente while no muestra nada porque no nos hemos ido al principio del rs con un rs.beforeFirst        
        while (rs.next()) {
            System.out.println ("#" +rs.getString ("codigo") + "#" + rs.getString (2)+ "#" + rs.getString("cliente")+"#"+rs.getString("saldo")+"#");
        }
        
		// El absolute os coloca en el registro 2, pero el next inmediatamete posterior nos coloca en el registro 3
        rs.absolute (2);
        while (rs.next()) {
            System.out.println ("#" +rs.getString ("codigo") + "#" + rs.getString (2)+ "#" + rs.getString("cliente")+"#"+rs.getString("saldo")+"#");
        }
              // Usando Scanner para obtener información del usuario
        Scanner in = new Scanner(System.in);
		
        System.out.print("Introduzca una cadena: ");
        String s = in.nextLine();
        System.out.println("Usted introdujo la cadena: " + s);
 
        System.out.print("Introduzca un entero: ");
        int a = in.nextInt();
        System.out.println("Usted introdujo el entero: " + a);

        System.out.print("Introduzca una float: ");
        float b = in.nextFloat();
        System.out.println("Usted introdujo el float: " + b);
        //Ejemplo para actualizar un registro, tener en cuenta el uso de las comillas simples y dobles
        //para generar una sentencia SQL que contenga dichas comillas simples.
        //Note que la sentencia SQL no contiene ; al final de la linea de sentencia SQL
        int cantidad = 22 ;
        String cliente1 = "Juan" ;
        String sql2 = "UPDATE cuentas SET saldo = " + cantidad + " WHERE cliente = '" + cliente1 + "'" ;
        int resultado ;
        resultado = st.executeUpdate(sql2) ;
      
        // Ejecuta una consulta de tipo insert, update o delete
        String codigo1 = "Juan" ;
        String sqlBusqueda = "SELECT codigo FROM cuentas WHERE cliente=?";
        
        PreparedStatement pstBuscarCodigo;
        //Se invoca el metodo prepareStatement de la conexion, pasando como parametro la sentencia SQL.
        pstBuscarCodigo = cn.prepareStatement(sqlBusqueda);
        //Se invoca el metodo setString para convertir a tipo String, el contenido de la variable codigo1
        //y se genera la sentencia con los nuevos valores
        pstBuscarCodigo.setString(1, codigo1);
        //Se ejecuta la sentencia normalmente
        rs = pstBuscarCodigo.executeQuery();
		
        while (rs.next()) {
            System.out.println ("#" +rs.getString (1) + "#" );
        }

        //Este codigo, despues de la primera ejecucion, tratara de crear una entrada duplicada y 
        //producira un error en la SGBD, se detendra la ejecucion si no se ha tratado la excepcion
        String codigo2 = "1239";
        String nombre2 = "Juan";
        String email2 = "juan@gmail.com";
        int saldo2 = 1234;

        PreparedStatement pstInsertarCuenta;
        String sqlNuevaCuenta = "INSERT INTO cuentas VALUES (?,?,?,?)";
        pstInsertarCuenta = cn.prepareStatement(sqlNuevaCuenta); 
        pstInsertarCuenta.setString(1, codigo2);
        pstInsertarCuenta.setString(2, nombre2);
        pstInsertarCuenta.setString(3, email2);
        pstInsertarCuenta.setDouble(4, saldo2);
        pstInsertarCuenta.executeUpdate();    
        
        rs = st.executeQuery(sql1);
        while (rs.next()) {
            System.out.println ("#" +rs.getString ("codigo") + "#" + rs.getString (2)+ "#" + rs.getString("cliente")+"#"+rs.getString("saldo")+"#");
        }
        
        rs.close(); // Cierra el resulset
        st.close(); // Cierra el statement
        cn.close(); // Cierra la conexión

        System.out.println ("TRAZA14 Si se tratar de acceder a los objeto conexiones, que han sido cerrado, produciria un error");
        System.out.println ("TRAZA14 Si se tratese de ejecutar la siguiente sentencia rs.next() se produciria un error");
        rs.next() ;
        
    } catch (ClassNotFoundException ex2) {
        System.out.println ("Error de Clase: " +ex2 ) ;
    } catch (SQLException ex1) {
    System.out.println ("Excepcion " + ex1);
}
}
}
