// Ojo sl nombre del paquete. Cambiar ejercicio2 por el nombre de tu paquete
package ejercicio2;

import com.mysql.jdbc.Driver;
import java.util.*;
import java.sql.* ;
import java.sql.SQLException;



// Ojo al nombre de la clase. Cambiar EJERCICIO2 por el nombre de tu clase
public class EJERCICIO2 {
    public static void main(String[] args) {
        try {
            // Apartado a). Conexión
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://10.2.1.162:3306/test", "ALUMNO1", "Palabra123$");

            // Creamos el Statement para poder hacer consultas
            Statement st = cn.createStatement();

            // Consulta de la tabla
            String sql1 = "SELECT * FROM coches";              
            ResultSet rs = st.executeQuery(sql1);
            System.out.println ("Apartado a");
            while (rs.next()) {
                   System.out.println ("#" +rs.getString (1) + "#" + rs.getString (2)+ "#" + rs.getString(3)+"#"+rs.getString(4)+"#");
            }
        
            // Apartado b)
            System.out.println ("Apartado b");
            String consulta = "SELECT kilometros, conductor, matricula FROM coches";
            PreparedStatement sentencia= cn.prepareStatement(consulta);//
            ResultSet rs2 = sentencia.executeQuery();//
			//ResultSet rs2 = st.executeQuery(consulta);
            
            while (rs2.next()) {
                   System.out.println ("#" +rs2.getString (1) + "#" + rs2.getString (2)+ "#" + rs2.getString(3));
            }
            
            //Apartado c)
            System.out.println ("Apartado c");
            rs2.beforeFirst(); //Instrucción para volver al principio del ResultSet
            int mayor=0;
            int indice=0;
            int indiceMayor=1;
            String kilometros;
            int kilometrosInt=0;
            while (rs2.next()) {
                   indice++;
                   kilometros=rs2.getString (1);
                   kilometrosInt=Integer.parseInt(kilometros);
                   if (kilometrosInt>mayor){
                       mayor=kilometrosInt;
                       indiceMayor=indice;
                   }
            }
            rs2.absolute (indiceMayor);
            System.out.println ("El coche con matrícula "+rs2.getString (3)+ " es el que tiene más kilómetros: #" + rs2.getString (1));
            

            
            // Apartado d
            System.out.println ("Apartado d");
            rs2.beforeFirst(); //Reseteo para no tener que volver a hacer la consulta
            int contador2=0;
            while (rs2.next()) {
                contador2++;
                if (contador2%2!=0){
                    System.out.println ("#" +rs2.getString (1) + "#" + rs2.getString (2)+ "#" + rs2.getString(3));
                }
            }
            
            // Apartado e
            System.out.println ("Apartado e");
            rs2.last(); // Coloco el puntero en el ultimo registro
            int cantidad = rs2.getRow(); 
            System.out.println("El ResultSet tiene " + cantidad + " registros");
            
            // Apartado f
            System.out.println ("Apartado f");
            rs2.last(); // Coloco el puntero en el ultimo registro
            int cantidad2 = rs2.getRow();
            while (cantidad2>0) {
                rs2.absolute (cantidad2);
                System.out.println ("#" +rs2.getString (1) + "#" + rs2.getString (2)+ "#" + rs2.getString(3));
                cantidad2--;
            }
            
        } catch (ClassNotFoundException ex2) {
            System.out.println ("Error de Clase: " +ex2 ) ;
        } catch (SQLException ex1) {
            System.out.println ("Excepcion " + ex1);
        }
    }
}

