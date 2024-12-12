/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

//import datos.Especialidades;
import datos.Eventos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import datos.MySQL;
import casa_cultura.Casa_Cultura;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
/**

/**
 *
 * @author LEMUEL
 */
public class ControlEventos {
    public static final int AGREGAR=1;
    public static final int ELIMINAR=2;
    public static final int EDITAR=3;
    public static final int CONSULTAR=4;
   
        public void mostrarDatos(JTable tabla){
        DefaultTableModel dtm = new DefaultTableModel();
        Object[] datos = new Object[4];
        tabla.setModel(dtm);
        dtm.addColumn("ID");
        dtm.addColumn("NOMBRE");
        dtm.addColumn("FECHA");//LO QUE SE IMPRIME EN PANTALLA
          dtm.addColumn("DIRECCION");
        ResultSet rs = Casa_Cultura.mysql.consultaSQL("select * from evento;");
        
        try{
            while (rs.next()){
                datos[0] = rs.getObject("id");
                datos[1] = rs.getObject("nombre");
                datos[2] = rs.getObject("fecha");
                datos[3] = rs.getObject("direccion");
                dtm.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog (null,"NO HAY DATOS PARA MOSTRAR");
        }
    }
            private boolean altas (Eventos e){
        String cadena="insert into evento values (null,'"+e.getNombre()+"','"
                +e.getFecha()+"','"
                +e.getDireccion()+"');";
       return Casa_Cultura.mysql.ejecutaSQL(cadena);
// return mysql.ejecutaSQL(cadena);
    }
                private boolean bajas(Eventos e){
        String cadena ="delete from evento where id="+e.getId()+";";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
                    private boolean cambios(Eventos e){
        String cadena="update evento set nombre='"
                +e.getNombre()+"', fecha='"
                +e.getFecha()+"', direccion='"
                +e.getDireccion()+"' where id='"+e.getId()+"';";
        return Casa_Cultura.mysql.ejecutaSQL(cadena);
    }
          public boolean ejecutaTx (Eventos e, int Accion){
        boolean b=false;
        switch (Accion){
            case AGREGAR: b=altas(e);
                          break;
            case ELIMINAR: b=bajas(e);
                          break;
            case EDITAR: b=cambios(e);
                          break;
            case CONSULTAR:b=true;
                          break;
        }
        return b;
    }
    
        public Eventos getEventos(int id ){
        String cadena="select * from evento where id="+id+";";
        Eventos e = new Eventos();
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
        try {
            while (rs.next()){ //Es cuando se trae muchos registros
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setFecha(rs.getDate("fecha"));
                e.setDireccion(rs.getString("direccion"));
              //  e.setCreditos(rs.getInt("creditos"));
            } //poner el cursor en el primer registro, pasa al segundo registro, recorre uno por uno 
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "No hay datos para mostrar");
            System.out.println("Hay un error o no existen datos; "+cadena);
            e=null;
        }
        return e;
    }
//            public void colocaEventos (JComboBox c){
//        String Cadena = "select * from evento;";
//        c.removeAllItems();
//        ResultSet rs = Casa_Cultura.mysql.consultaSQL(Cadena);
//        Eventos e = new Eventos();
//        try{
//            while (rs.next()){
//                e.setId((Integer)rs.getObject("id"));
//                e.setNombre((String)rs.getObject("nombre"));
//                e.setFecha((Date)rs.getObject("fecha"));
//                e.setDireccion((String)rs.getObject("direccion"));
//                //c.addItem(e);
//                c.addItem(e.getNombre());
//            }
//        }catch (SQLException er){
//            System.out.println("Error!!");
//        }
//    }
//            
        public Eventos getEventos(String nombre){
	String cadena="select * from evento where nombre='"+nombre+"';";
        ResultSet rs = Casa_Cultura.mysql.consultaSQL(cadena);
        Eventos e = new Eventos();
        try{
            while (rs.next()){   //resultset trae toda la informacion de la bd, se puede no poner el while porque solo trae un registro. 
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setFecha(rs.getDate("fecha"));
                e.setDireccion(rs.getString("direccion"));
            
            }
        }catch (SQLException ex){
            System.out.println("Hay un error o no existen datos : " + cadena);
            e=null;
        }
        return e;
    }
}
