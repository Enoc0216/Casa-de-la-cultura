/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import casa_cultura.Casa_Cultura;
import datos.Docentes;
import datos.Taller;
import javax.swing.JComboBox;
 
public class ControlDocentes {
    public static final int AGREGAR=1;
   public static final int ELIMINAR=2;
   public static final int EDITAR=3;
   public static final int CONSULTAR=4;
     //ControlImparte ci= new ControlImparte();
     
     public void mostrarDatos(JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] datos =new Object[3];
        tabla.setModel(dtm);
        dtm.addColumn("ID");
         dtm.addColumn("NOMBRE");
         
         dtm.addColumn("TELEFONO");
       
            
           ResultSet rs= Casa_Cultura.mysql.consultaSQL("select * from Docente;");
           try{
               while (rs.next()){
               datos[0]= rs.getObject("id");
               datos[1]= rs.getString("nombre")+" "+
                rs.getString("apellidoP")+" "+
               rs.getString("apellidoM");
               datos[2]=rs.getString("telefono");
                  dtm.addRow(datos);   
               }
             
           }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"No hay datos para mostrar ");
           }
    }
     public boolean altas(Docentes d){
        String cadena="insert into docente values (null,'" +d.getNombre()+"','"
                +d.getApellidoP()+"','"
                + d.getApellidoM()+"','"
                + d.getTelefono()+"',aes_encrypt('"+d.getContraseña()+"','ola'),'"+d.getFoto()+"');";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);  
    }
     
     public boolean bajas(Docentes d){
        String cadena= "delete from docente where id="+d.getId()+";";
        
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
     
    public boolean cambios(Docentes d){
        String cadena= "update docente set nombre='"
                +d.getNombre()
                +"',apellidoP='"+d.getApellidoP()+"',apellidoM='"+d.getApellidoM()+"',telefono='"+d.getTelefono()+"', contraseña=aes_encrypt('"+d.getContraseña()+"','ola'),foto='"
                +d.getFoto()+"' "+" where id="+d.getId()+";";
                return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    public boolean ejecutaTx (Docentes d, int Accion){
        boolean b=false;
        switch(Accion){
    case AGREGAR: b=altas(d);
                  break;
     case ELIMINAR: b=bajas(d);
                  break;
     case EDITAR: b=cambios(d);
                  break;
     case CONSULTAR:b=true;
                    break;    
    }
    return b;
}
     public  Docentes getDocente(int id){
      String cadena="select id,nombre,apellidoP,apellidoM,"
              + "telefono,aes_decrypt('','ola')as pass,foto from docente where id="+id+";";    
      
      Docentes d= new Docentes();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            //rs.next recorre al final del archivo o final de resultdo los registros ...
            //elresult set esta en la memoria d ela compu que se trae de la base de datos
            //1-50
            //solo traer una vez
            while (rs.next()){
                d.setId(rs.getInt("id"));
                 d.setNombre(rs.getString("nombre"));
                  d.setApellidoP(rs.getString("apellidoP"));
                d.setApellidoM(rs.getString("apellidoM"));
                d.setTelefono(rs.getString("telefono"));
                  d.setContraseña(Arrays.toString((byte[])rs.getObject("pass")));
                  
               d.setFoto((byte[])rs.getObject("foto"));
               
                
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
           d=null;
        }
        return d;
    }
      public int ultimoDocente(){
     int id=0;
     String cadena= "select max(id) as id from docente;";
     ResultSet rs =Casa_Cultura.mysql.consultaSQL(cadena);
     try{
         rs.next();
     id = rs.getInt("id");
     }catch(SQLException e){
    System.out.println("Error al invocar al ultimo docente") ;
    e.printStackTrace();
     }
     return id;
     }
       public  Docentes getDocente(String nombre){
      String cadena="select * from docente where nombre='"+nombre+"';";    
      
      Docentes d= new Docentes();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            //rs.next recorre al final del archivo o final de resultdo los registros ...
            //elresult set esta en la memoria d ela compu que se trae de la base de datos
            //1-50
            //solo traer una vez
            while (rs.next()){
                d.setId(rs.getInt("id"));
                 d.setNombre(rs.getString("nombre"));
                  d.setApellidoP(rs.getString("apellidoP"));
                d.setApellidoM(rs.getString("apellidoM"));
                d.setTelefono(rs.getString("telefono"));
                  d.setContraseña(Arrays.toString((byte[])rs.getObject("contraseña")));
                  
               d.setFoto((byte[])rs.getObject("foto"));
               
                
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
           d=null;
        }
        return d;
    }
          public void colocaDocente(JComboBox c){
   String Cadena="select id,nombre,apellidoP,apellidoM from docente";
   c.removeAllItems();
   ResultSet rs = Casa_Cultura.mysql.consultaSQL(Cadena);
   Docentes d = new Docentes();
   try{
       while(rs.next()){
       d.setId((Integer)rs.getObject("id"));
       d.setNombre((String) rs.getObject("nombre"));
      
       d.setApellidoP((String)rs.getObject("apellidoP"));
        d.setApellidoM((String)rs.getObject("apellidoM"));
       c.addItem(d.getNombre());
      }
   
   }catch (SQLException er){
   System.out.println("Error!!!!");
   }
     
    }
     
}




