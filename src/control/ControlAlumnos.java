
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.Alumnos;
import casa_cultura.Casa_Cultura;
import datos.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JComboBox;

/**
 *
 * @author LEMUEL
 */
public class ControlAlumnos {
     
    public static final int AGREGAR=1;
    public static final int ELIMINAR=2;
    public static final int EDITAR=3;
    public static final int CONSULTAR=4;
    //private ControlEspecialidades ce = new ControlEspecialidades();
   
      
    public void mostrarDatos(JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] datos = new Object[6];
        tabla.setModel(dtm);
        dtm.addColumn("ID");
        dtm.addColumn("NOMBRE");
        dtm.addColumn("APELLIDO PATERNO");//LO QUE SE IMPRIME EN PANTALLA
        dtm.addColumn("APELLIDO MATERNO");
        dtm.addColumn("TELEFONO");
        dtm.addColumn("DIRECCION");
        ResultSet rs = Casa_Cultura.mysql.consultaSQL("select * from alumno;");
        
        try{
            while (rs.next()){
                datos[0] = rs.getObject("id");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("apellidoP");
                datos[3] = rs.getString("apellidoM");
                datos[4] = rs.getString("telefono");
                datos[5] = rs.getString("direccion");
;
                dtm.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog (null,"NO HAY DATOS PARA MOSTRAR");
        }
    }
           private boolean altas (Alumnos a){
        String cadena="insert into alumno values (null,'"
                +a.getNombre()+"','"
                +a.getApellidoP()+"','"
                +a.getApellidoM()+"','"
                +a.getTelefono()+"','"
                +a.getDireccion()+"','"+a.getFoto()+"');";
             
       return Casa_Cultura.mysql.ejecutaSQL(cadena);
       
// return mysql.ejecutaSQL(cadena);

    }
    private boolean bajas(Alumnos a){
        String cadena ="delete from alumno where id="+a.getId()+";";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
    private boolean cambios(Alumnos a){
        String cadena="update alumno set nombre='"
                +a.getNombre()+"', apellidoP='"
                +a.getApellidoP()+"', apellidoM='"
                +a.getApellidoM()+"', telefono='"
                +a.getTelefono()+"', direccion='"
                +a.getDireccion()+"', foto='"
                +a.getFoto()+"' "+ "where id ="
                +a.getId()+";";
  
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
             public boolean ejecutaTx (Alumnos a, int Accion){
        boolean b=false;
        switch (Accion){
            case AGREGAR: b=altas(a);
                          break;
            case ELIMINAR: b=bajas(a);
                          break;
            case EDITAR: b=cambios(a);
                          break;
            case CONSULTAR:b=true;
                          break;
        }
        return b;
    }
       public Alumnos getAlumnos(int id){
        String cadena="select id,nombre,apellidoP"
                + ",apellidoM,telefono,direccion,"
                + "foto from alumno where id="+id+";";
        Alumnos a = new Alumnos();
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
        try {
            while (rs.next()){
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidoP(rs.getString("apellidoP"));
                a.setApellidoM(rs.getString("apellidoM"));
                a.setTelefono(rs.getString("telefono"));
                a.setDireccion(rs.getString("direccion"));
                a.setFoto((byte[])rs.getObject("foto"));
            } 
        } catch (SQLException ex) {
          
            System.out.println("Hay un error o no existen datos; "+cadena);
            a=null;
        }
        return a;
    }
        public void colocaAlumnos(JComboBox c){
        String Cadena = "select * from alumno;";
        c.removeAllItems();
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(Cadena);
        Alumnos e = new Alumnos();
        try{
            while (rs.next()){
                
                e.setId((Integer)rs.getObject("id"));
                e.setNombre((String)rs.getObject("nombre"));
                e.setApellidoP((String)rs.getObject("apellidoP"));
                e.setApellidoM((String)rs.getObject("apellidoM"));
                //c.addItem(e);
                c.addItem(e.getNombre());
            }
        }catch (SQLException er){
            System.out.println("Error!!");

        }
    }
      
                   
    public int UltimoAlumno(){
        int id = 0;
        String cadena = "select max(id) as id from alumno;";
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
        try{
          rs.next();
          id = rs.getInt("id");
        }catch(SQLException e){
            System.out.println("Error al invocar el ultimo alumno!!!");
            e.printStackTrace();
        }
        return id;
    }
       public Alumnos getAlumno(String nombre){
        String cadena="select * from alumno where nombre='"+nombre+"';";
        Alumnos a = new Alumnos();
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
        try {
            while (rs.next()){ //Es cuando se trae muchos registros
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidoP(rs.getString("apellidoP"));
                a.setApellidoM(rs.getString("apellidoM"));
                a.setTelefono(rs.getString("telefono"));
                a.setDireccion(rs.getString("direccion"));
                a.setFoto((byte[])rs.getObject("foto"));
            } //poner el cursor en el primer registro, pasa al segundo registro, recorre uno por uno 
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
            System.out.println("Hay un error o no existen datos; ");
             System.out.println(ex);
            a=null;
        }
        return a;
    }
//       
}


