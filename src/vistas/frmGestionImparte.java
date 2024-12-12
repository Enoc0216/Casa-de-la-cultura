/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.ControlDocentes;
import control.ControlImparte;
import control.ControlTaller;
import datos.Docentes;
import datos.Imparte;
import datos.Taller;
import java.util.Date;
import javax.swing.JOptionPane;
import controlReportes.rptINDimparte;

/**
 *
 * @author zahori
 */
public class frmGestionImparte extends javax.swing.JDialog {
   ControlImparte ci = new ControlImparte();
    ControlTaller ct = new ControlTaller();
    ControlDocentes cd = new ControlDocentes();
    private int Accion;
     Imparte i= new Imparte();
    Taller t= new Taller();
    Docentes d= new Docentes();
    
    private String Titulo;
   
    frmGestionImparte(java.awt.Frame parent, boolean modal,
            int id,int Accion) {
        super(parent, modal);
        initComponents();
          this.Accion=Accion;
        ct.colocaTaller(cbTaller);
         cd.colocaDocente(cbDocente);
              
        if(Accion!=ControlTaller.AGREGAR)
            mostrar(id);
        
        switch(Accion){
        
            case ControlImparte.AGREGAR:Titulo="Agregar Imparte";
            habilita(true);
            break;
            
            case ControlImparte.ELIMINAR:Titulo="Eliminar Imparte";
            habilita(false);
            break;
            
            case ControlImparte.EDITAR:Titulo="Editar Imparte";
            habilita(true);
            break;
            
            case ControlImparte.CONSULTAR:Titulo="Consultar Imparte";
            habilita(false);
            break;
        }
        lblTitulo.setText(Titulo);
        setTitle("..::Impartir::..");
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    private void mostrar(int id) {
    
    i = ci.getImparte(id);
    
     cbTaller.setSelectedItem(i.getId_taller().getNombre());
    cbDocente.setSelectedItem(i.getId_Docente().getNombre());
    dcInicio.setDate(i.getFecha_inicio());
    dcTermino.setDate(i.getFecha_termino());
    
    }
    
    private void habilita (boolean bnd){
   
    cbTaller.setEnabled(bnd);
    cbDocente.setEnabled(bnd);
    dcInicio.setEnabled(bnd);
    dcTermino.setEnabled(bnd);
    
    
    }
    
    private boolean valida(){
        boolean b= false;
        if(dcInicio.getDate()==null){
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de inicio!!");
        dcInicio.requestFocus();
        }else if(dcTermino.getDate()==null){
            JOptionPane.showMessageDialog(this, "Debe escribir el descripcion!");
        dcTermino.requestFocus();
        }else b=true;
        return b;
    }
    //colocs en el onjeto el objeto el contenido de los controles txt
    
    private void asigna(){
         Date dateI = dcInicio.getDate();
        Date dateF = dcTermino.getDate();
        long di = dateI.getTime();
        long df = dateF.getTime();
        java.sql.Date fecha_inicio = new java.sql.Date(di);
         java.sql.Date fecha_termino = new java.sql.Date(df);
        t=ct.getTaller(cbTaller.getSelectedItem().toString());
         i.setId_taller(t);
         d=cd.getDocente(cbDocente.getSelectedItem().toString());
         i.setId_Docente(d);
          i.setFecha_inicio(fecha_inicio);
         i.setFecha_termino(fecha_termino);
//         i.setFecha_inicio(dcInicio.getDate());
//         i.setFecha_termino(dcTermino.getDate());
         
         
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbTaller = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbDocente = new javax.swing.JComboBox<>();
        lblInicio = new javax.swing.JLabel();
        dcInicio = new com.toedter.calendar.JDateChooser();
        lblTermino = new javax.swing.JLabel();
        dcTermino = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jPanel1.setBackground(new java.awt.Color(204, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTitulo.setBackground(new java.awt.Color(255, 255, 153));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("DATOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Taller");

        cbTaller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTaller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTallerActionPerformed(evt);
            }
        });

        jLabel1.setText("Docente");

        cbDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDocenteActionPerformed(evt);
            }
        });

        lblInicio.setText("Fecha Inicio");

        lblTermino.setText("Fecha Termino");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbTaller, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTermino)
                        .addGap(18, 18, 18)
                        .addComponent(dcTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTaller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbDocente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblInicio)
                    .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTermino))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Aceptar.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salir.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar)
                    .addComponent(btnAceptar)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
//     if (valida()){
//      asigna();
//      if (Accion == ControlImparte.CONSULTAR)
//          new rptINDimparte(i.getId());
//      this.dispose();
//      
//      return;
//      }
//      String cadena ="No se ejecuto la transaccion!!";
//    // System.out.println(a.getPassword().toString());
//      if(ci.ejecutaTx(i, Accion)){
//          cadena="Se ejecuto la transaccion!";
//      }
//      JOptionPane.showMessageDialog(this,cadena);
//      int id=0;
//      if(Accion==ControlImparte.AGREGAR){
//          id= ci.ultimoImparte();
//      }else{
//          id=i.getId();
//      }
//      if((Accion!=ControlImparte.ELIMINAR ) ||
//          (Accion!=ControlImparte.CONSULTAR ))  {
//     
//      }
//      this.dispose();
       if (valida()){
            asigna();
            if (Accion == ControlImparte.CONSULTAR){
                new rptINDimparte(i.getId());
                this.dispose();
                return;
            }
            String cadena="No se ejecuto la transaccion!!!";
            //System.out.println("");
            if (ci.ejecutaTx(i, Accion))
                cadena= "Se ejecuto la Transaccion!!";
                JOptionPane.showMessageDialog(this, cadena);
                int id = 0;
            if (Accion==ControlImparte.AGREGAR)
                id = ci.ultimoImparte();
            else
                id=i.getId();
            if ((Accion != ControlImparte.ELIMINAR) || (Accion != ControlImparte.CONSULTAR))
//                Casa_Cultura.mysql.guardaImagen(id, "alumno", a.getFoto());
            this.dispose();
        }
                                     
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void cbDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDocenteActionPerformed

    private void cbTallerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTallerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTallerActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbDocente;
    private javax.swing.JComboBox<String> cbTaller;
    private com.toedter.calendar.JDateChooser dcInicio;
    private com.toedter.calendar.JDateChooser dcTermino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblTermino;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
