/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.Alumnos;
import datos.Eventos;
import datos.MySQL;
import java.sql.*;
import casa_cultura.Casa_Cultura;
import datos.Cursa;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import datos.Taller;
/**
 *
 * @author LEMUEL
 */
public class ControlCursa {
   public static final int AGREGAR=1;
   public static final int ELIMINAR=2;
   public static final int EDITAR=3;
   public static final int CONSULTAR=4;
 
   ControlAlumnos ca= new ControlAlumnos();
   ControlTaller ct= new ControlTaller();
   
       public void mostrarDatos(JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] datos =new Object[5];
        tabla.setModel(dtm);
         dtm.addColumn("CURSA");
        dtm.addColumn("ALUMNOS");
         dtm.addColumn("TALLER"); 
           dtm.addColumn("FECHA DE INICIO");
            dtm.addColumn("FECHA DE TERMINO");
           ResultSet rs= Casa_Cultura.mysql.consultaSQL("select * from cursa;");//llamar la conexion remota que tenemos
           try{
               while (rs.next()){
               datos[0]= rs.getObject("id");
               int idA = (Integer)rs.getObject("id_alumno");
               datos[1]= ca.getAlumnos(idA).getNombre();
               int idT = (Integer)rs.getObject("id_taller");
               datos[2]= ct.getTaller(idT).getNombre();
               datos[3]= rs.getObject("fecha_inicio");
               datos[4]= rs.getObject("fecha_termino");
               dtm.addRow(datos);
               }
           }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"No hay datos para mostrar ");
           }
    } 
       
    public boolean altas(Cursa c){
        String cadena="insert into cursa values (null,'"+c.getId_alumno().getId()+"','"+c.getId_taller().getId()+"','"+c.getFecha_inicio()+"','"+c.getFecha_termino()+"');";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    
    public boolean bajas(Cursa c){
        String cadena= "delete from cursa where id='"+c.getId()+"';";
        System.out.println("hgjhgj");
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    
    public boolean cambios(Cursa c){
        String cadena= "update cursa set id_alumno='"
                +c.getId_alumno().getId()+"',id_taller='"
                +c.getId_taller().getId()+"', fecha_inicio='"
                +c.getFecha_inicio()+"', fecha_termino='"
                +c.getFecha_termino()+"' where id="+c.getId()+";";
                return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    
    public boolean ejecutaTx (Cursa c, int Accion){
        boolean b = false;
        switch(Accion){
        case AGREGAR:  b=altas(c);
                  break;
        case ELIMINAR: b=bajas(c);
                  break;
        case EDITAR: b=cambios(c);
                  break;
        case CONSULTAR: b=false;
                  break;    
        }
        return b;
    }
    //traer al sis de inf.una especialidad  de acuerdo con el id 
    public  Cursa getCursa(int id){
      String cadena="select id,id_alumno,id_taller,fecha_inicio,fecha_termino from cursa where id="+id+";";  
      Cursa c= new Cursa();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            
            while (rs.next()){
                c.setId(rs.getInt("id"));
                c.setId_alumno(ca.getAlumnos(rs.getInt("id_alumno")));
                c.setId_taller(ct.getTaller(rs.getInt("id_taller")));
                c.setFecha_inicio(rs.getDate("fecha_inicio"));
                c.setFecha_termino(rs.getDate("fecha_termino"));
               // t.setEvento(ce.getEventos(rs.getInt("Eventos_idEventos")));
                
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
             System.out.println(ex);
           //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
          c=null;
        }
        return c;
    }
    
  

   public  Cursa getCursa(String nombre){
      String cadena="select * from taller where nombre='"+nombre+"';";  
      Cursa c= new Cursa();
     ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
     
        try {
            
            while (rs.next()){
                c.setId(rs.getInt("id"));
                c.setId_alumno(ca.getAlumnos(rs.getInt("id_alumno")));
                c.setId_taller(ct.getTaller(rs.getInt("id_taller")));
                c.setFecha_inicio(rs.getDate("fecha_inicio"));
                c.setFecha_termino(rs.getDate("fecha_termino"));
                // t.setEvento(ce.getEventos(rs.getInt("Eventos_idEventos")));
            }  } catch (SQLException ex) {
             System.out.println("hay un error o no existe datos : " + cadena);
           //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
           c=null;
        }
        return c;
    }
   
       public int UltimoCursa(){
        int id = 0;
        String cadena = "select max(id) as id from cursa;";
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
        try{
          rs.next();
          id = rs.getInt("id");
        }catch(SQLException e){
            System.out.println("Error al invocar el ultimo cursa!!!");
            e.printStackTrace();
        }
        return id;
    }
}
