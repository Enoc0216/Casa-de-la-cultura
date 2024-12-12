 




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import casa_cultura.Casa_Cultura;
import static control.ControlDocentes.AGREGAR;
import static control.ControlDocentes.CONSULTAR;
import static control.ControlDocentes.EDITAR;
import static control.ControlDocentes.ELIMINAR;
import datos.Imparte;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author zahori
 */
public class ControlImparte {
     public static final int AGREGAR=1;
   public static final int ELIMINAR=2;
   public static final int EDITAR=3;
   public static final int CONSULTAR=4;
    ControlTaller ct= new ControlTaller();
    ControlDocentes cd= new ControlDocentes();
    
     public void mostrarDatos(JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] datos =new Object[5];
        tabla.setModel(dtm);
        dtm.addColumn("ID IMPARTE");
         dtm.addColumn("ID TALLER");
        dtm.addColumn("ID DOCENTE");
        dtm.addColumn("FECHA DE INICIO");
           dtm.addColumn("FECHA DE TERMINO");
       
            
           ResultSet rs= Casa_Cultura.mysql.consultaSQL("select * from imparte;");
           try{
               while (rs.next()){
               datos[0]= rs.getObject("id");
               int idT = (Integer)rs.getObject("id_taller");
               datos[1]= ct.getTaller(idT).getNombre();
               int idD = (Integer)rs.getObject("id_docente");
               datos[2]= cd.getDocente (idD).getNombre();
               datos[3]=rs.getObject("fecha_inicio");
               datos[4]=rs.getString("fecha_termino");
                  dtm.addRow(datos);   
               }
             
           }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"No hay datos para mostrar ");
           }
    }
      public boolean altas(Imparte i){
        String cadena="insert into imparte values (null,'"+i.getId_taller().getId()+"','"+i.getId_Docente().getId()+"','"
                + i.getFecha_inicio()+"','"
                + i.getFecha_termino()+"');";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    
    }
     public boolean bajas(Imparte i){
        String cadena= "delete from imparte where id="+i.getId()+";";
        
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    public boolean cambios(Imparte i){
        String cadena= "update imparte set id_taller="
                +i.getId_taller().getId()+",id_Docente="
                +i.getId_Docente().getId()+",fecha_inicio='"
                +i.getFecha_inicio()+"',fecha_termino='"
                +i.getFecha_termino()+"' where id="+i.getId()+";";
                return Casa_Cultura.mysql.ejecutaSQL(cadena);
                
    }
    public boolean ejecutaTx (Imparte i, int Accion){
        boolean b=false;
        switch(Accion){
    case AGREGAR: b=altas(i);
                  break;
     case ELIMINAR: b=bajas(i);
                  break;
     case EDITAR: b=cambios(i);
                  break;
     case CONSULTAR:b=true;
                    break;    
    }
    return b;
}
     public  Imparte getImparte(int id){
      String cadena="select id,id_taller,id_Docente,fecha_inicio,fecha_termino from imparte where id="+id+";";   
      
      Imparte i= new Imparte();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            //rs.next recorre al final del archivo o final de resultdo los registros ...
            //elresult set esta en la memoria d ela compu que se trae de la base de datos
            //1-50
            //solo traer una vez
            while (rs.next()){
                i.setId(rs.getInt("id"));
                 i.setId_taller(ct.getTaller(rs.getInt("id_taller")));
                  i.setId_Docente(cd.getDocente(rs.getInt("id_Docente")));
                i.setFecha_inicio(rs.getDate("fecha_inicio"));
                i.setFecha_termino(rs.getDate("fecha_termino"));
                  
               
                
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           
           i=null;
        }
        return i;
    }
      public int ultimoImparte(){
     int id=0;
     String cadena= "select max(id) as id from imparte;";
     ResultSet rs =Casa_Cultura.mysql.consultaSQL(cadena);
     try{
         rs.next();
     id = rs.getInt("id"); 
     }catch(SQLException e){
    System.out.println("Error al invocar al ultimo a impartir") ;
    e.printStackTrace();
     }
     return id;
     }
      public  Imparte getImparte(String nombre){
      String cadena="select * from imparte where nombre='"+nombre+"';"; 
      Imparte i= new Imparte();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
           
           
            while (rs.next()){
                i.setId(rs.getInt("id"));
                 i.setId_taller(ct.getTaller(rs.getInt("id_taller")));
                  i.setId_Docente(cd.getDocente(rs.getInt("id_Docente")));
                i.setFecha_inicio(rs.getDate("fecha_inicio"));
                i.setFecha_termino(rs.getDate("fecha_termino"));
                  
               
                
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           
           i=null;
        }
        return i;
    }
    
}
