/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import datos.MySQL;
import java.sql.*;
import casa_cultura.Casa_Cultura;
import datos.Eventos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import datos.Taller;

/**
 *
 * @author zahori
 */
public class ControlTaller {
     public static final int AGREGAR=1;
   public static final int ELIMINAR=2;
   public static final int EDITAR=3;
   public static final int CONSULTAR=4;
   ControlEventos ce= new ControlEventos();

 
    public void mostrarDatos(JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] datos =new Object[4];
        tabla.setModel(dtm);
        dtm.addColumn("ID");
         dtm.addColumn("NOMBRE");
           dtm.addColumn("DESCRIPCION"); 
           dtm.addColumn("EVENTOS");
           ResultSet rs= Casa_Cultura.mysql.consultaSQL("select * from taller;");//llamar la coneccion remota que tenemos
           try{
               while (rs.next()){
               datos[0]= rs.getObject("id");
               datos[1]= rs.getObject("nombre");
               datos[2]= rs.getObject("descripcion");
               int id = (Integer)rs.getObject("id_evento");
               datos[3]= ce.getEventos(id).getNombre();
               dtm.addRow(datos);
               }
           }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"No hay datos para mostrar ");
           }
    } 
    
    public boolean altas(Taller t){
        String cadena="insert into taller values (null,'"+t.getNombre()+"','"+t.getDescripcion()+"','"+t.getId_evento().getId()+"');";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    
    public boolean bajas(Taller t){
        String cadena= "delete from taller where id = "+t.getId()+";";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    
    public boolean cambios(Taller t){
        String cadena= "update taller set nombre='"
                +t.getNombre()+"',descripcion='"
                +t.getDescripcion()+"', id_evento='"
                +t.getId_evento().getId()
                +"' where id= '"+t.getId()+"';";
                return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    
    
    public boolean ejecutaTx (Taller t, int Accion){
        boolean b=false;
        switch(Accion){
    case AGREGAR: b=altas(t);
                  break;
     case ELIMINAR: b=bajas(t);
                  break;
     case EDITAR: b=cambios(t);
                  break;
     case CONSULTAR:b=true;
                    break;    
    }
    return b;
}
    //traer al sis de inf.una especialidad  de acuerdo con el id 
    public  Taller getTaller(int id){
      String cadena="select id,nombre,descripcion,id_evento from taller where id="+id+";";  
      Taller t= new Taller();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            
            while (rs.next()){
                t.setId(rs.getInt("id"));
                t.setNombre(rs.getString("nombre"));
                t.setDescripcion(rs.getString("descripcion"));
                t.setId_evento(ce.getEventos(rs.getInt("id_evento")));
                
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
           t=null;
        }
        return t;
    }
    
              public void colocaEventos (JComboBox c){
        String Cadena = "select * from evento;";
        c.removeAllItems();
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(Cadena);
        Eventos e = new Eventos();
        try{
            while (rs.next()){
                e.setId((Integer)rs.getObject("id"));
                e.setNombre((String)rs.getObject("nombre"));
                e.setFecha((java.util.Date)rs.getObject("fecha"));
                e.setDireccion((String)rs.getObject("direccion"));
                //c.addItem(e);
                c.addItem(e.getNombre());
            }
        }catch (SQLException er){
            System.out.println("Error!!");
        }
    }
            
    public void colocaTaller(JComboBox c){
   String Cadena="select id,nombre,descripcion from taller;"; 
   c.removeAllItems();
   ResultSet rs = Casa_Cultura.mysql.consultaSQL(Cadena);
   Taller t = new Taller();
   try{
       while(rs.next()){
       t.setId((Integer)rs.getObject("id"));
       t.setNombre((String) rs.getObject("nombre"));
       t.setDescripcion((String)rs.getObject("descripcion"));
       c.addItem(t.getNombre());
      }
   
   }catch (SQLException er){
   System.out.println("Error!!!!");
   }
     
    }
    
   public  Taller getTaller(String nombre){
      String cadena="select * from taller where nombre='"+nombre+"';";  
      Taller t= new Taller();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            
            while (rs.next()){
                t.setId(rs.getInt("id"));
                t.setNombre(rs.getString("nombre"));
                t.setDescripcion(rs.getString("descripcion"));
                 t.setId_evento(ce.getEventos(rs.getInt("id_evento")));
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
           t=null;
        }
        return t;
    }
   
}
