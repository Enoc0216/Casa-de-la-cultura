
package datos;
import java.sql.*;
/**
 *
 * @author LEMUEL
 */
public class MySQL {
    private String bd="casa_cultural";
    private String usuario="root";
    private String pass="";
    private String url="jdbc:mysql://localhost/"+bd;
    private Connection miConexion=null;
    private ResultSet rs;//trae toda la tabla
    
    public MySQL(){
        try{
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               miConexion = DriverManager.getConnection(url,usuario,pass);
               System.out.println("Conexion a la base de datos "+bd+" MYSQL Exitosa");
        }catch (Exception e){
            e.printStackTrace();

        }
     
    }
    public Connection getConexion(){
        return miConexion;
    }
    //ejecuta una actualizacion:insert, delete y update
    // ejecuta uan actualizacion de consultas
    
    public boolean ejecutaSQL(String sentenciaSQL){
        boolean bnd=false;
        try{
            Statement ejecutaSQL = miConexion.createStatement();
            ejecutaSQL.executeUpdate(sentenciaSQL);
            ejecutaSQL.close();
            bnd=true;
            
        }catch(SQLException e){
            System.out.println("Error al ejecutar la sentencia SQL: "
            +sentenciaSQL+" -- "+e.getMessage());
        }
        return bnd;
    }
    
    public ResultSet consultaSQL(String qry){ //query consulta
        ResultSet cursor;  //una tabla de que puedes manipular
        try{
           Statement consulta = miConexion.createStatement(); 
           cursor = consulta.executeQuery(qry);
           return cursor;
        }catch(SQLException e){
            System.out.println("Error con la consulta: "+ qry);
            e.printStackTrace();
        }
         return null;
    }
    
    public boolean guardaImagen(int id, String tabla, byte[] foto){
        boolean b = false;
        if (foto!=null){
            String cadena = "update " + tabla + " set foto=? where id= " +id+ ";";
            try{
                PreparedStatement qry=miConexion.prepareCall(cadena);
                qry.setBytes(1, foto);
                qry.execute();
                qry.close();
            }catch (SQLException e){
                System.out.println("Error al actualizar la fotografia!!");
                e.printStackTrace();
            }
        }
        return b;
    }

}
