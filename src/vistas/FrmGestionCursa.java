/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import casa_cultura.Casa_Cultura;
import control.ControlAlumnos;
import controlReportes.reporteInd;
import control.ControlCursa;
import control.ControlTaller;
import datos.Alumnos;
import datos.Cursa;
import datos.Taller;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author LEMUEL
 */
public class FrmGestionCursa extends javax.swing.JDialog {
    
ControlCursa cs = new ControlCursa();
ControlTaller ct = new ControlTaller();
ControlAlumnos ca = new ControlAlumnos();

    private int Accion;
    Taller t =new Taller();
    Alumnos a =new Alumnos();
    Cursa c = new Cursa();
    
    
    private String Titulo;
    /**
     * Creates new form FrmGestionCursa
     */
    public FrmGestionCursa(java.awt.Frame parent, boolean modal,
        int id,int Accion) {
        super(parent, modal);
        initComponents();
        this.Accion=Accion;
        ct.colocaTaller(cbTaller);
        ca.colocaAlumnos(cbAlumno);
        
        if(Accion!=ControlCursa.AGREGAR)
            mostrar(id);
        switch(Accion){
            case ControlCursa.AGREGAR:Titulo="Agregar Cursa";
            habilita(true);
            break;
            
            case ControlCursa.ELIMINAR:Titulo="Eliminar Cursa";
            habilita(false);
            break;
            
            case ControlCursa.EDITAR:Titulo="Editar Cursa";
            habilita(true);
            break;
            
            case ControlCursa.CONSULTAR:Titulo="Consultar Cursa";
            habilita(false);
            break;
        }
        lblTitulo.setText(Titulo);
        setTitle("..::CURSA::..");
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    private void mostrar(int id) {
    c = cs.getCursa(id);
    cbAlumno.setSelectedItem(c.getId_alumno().getNombre());
    cbTaller.setSelectedItem(c.getId_taller().getNombre());
    txtFechaInicio.setDate(c.getFecha_inicio());
    txtFechaTermino.setDate(c.getFecha_termino());
    }
    
    private void habilita (boolean bnd){
    cbAlumno.setEnabled(bnd);
    cbTaller.setEnabled(bnd);
    txtFechaInicio.setEnabled(bnd);
    txtFechaTermino.setEnabled(bnd);
    }
//    
    private boolean valida(){
        boolean b= false;
        if(txtFechaInicio.getDate()==null){
            JOptionPane.showMessageDialog(this, "Debe escribir la fecha de inicio!!");
        txtFechaInicio.requestFocus();
        }else if(txtFechaTermino.getDate()==null){
            JOptionPane.showMessageDialog(this, "Debe escribir la fecha de termino!");
        txtFechaTermino.requestFocus();
        }else{
            b=true;
        }
        return b;
    }
    
//    //colocs en el onjeto el objeto el contenido de los controles txt
    private void asigna(){
        Date dateI = txtFechaInicio.getDate();
        Date dateF = txtFechaTermino.getDate();
        long d = dateI.getTime();
        long e = dateF.getTime();
        java.sql.Date fecha_inicio = new java.sql.Date(d);
        java.sql.Date fecha_termino = new java.sql.Date(e);
        a = ca.getAlumno(cbAlumno.getSelectedItem().toString());
        c.setId_alumno(a);
        t=ct.getTaller(cbTaller.getSelectedItem().toString());
        c.setId_taller(t);
        c.setFecha_inicio(fecha_inicio);
        c.setFecha_termino(fecha_termino);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbAlumno = new javax.swing.JComboBox<>();
        cbTaller = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        txtFechaTermino = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jPanel1.setBackground(new java.awt.Color(204, 255, 51));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTitulo.setText("TITULO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "DATOS DE CURSA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("NOMBRE DEL ALUMNO:");

        jLabel3.setText("NOMBRE DEL TALLER:");

        jLabel4.setText("FECHA DE INICIO:");

        jLabel5.setText("FECHA DE TERMINO:");

        cbAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnoActionPerformed(evt);
            }
        });

        cbTaller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTaller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTallerActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(204, 255, 0));
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Aceptar.png"))); // NOI18N
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 255, 0));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/DeleteRed.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtFechaInicio.setDateFormatString("dd/MM/yyyy");
        txtFechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaInicioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbAlumno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTaller, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(txtFechaTermino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnAceptar)
                        .addGap(44, 44, 44)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbTaller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
//           if (valida()){
//           asigna();
//           if(cs.ejecutaTx(c, Accion))
//               JOptionPane.showMessageDialog(this,"Se ejecuto la transaccion");
//           else
//               JOptionPane.showMessageDialog(this,"No se ejecuto la transaccion!!");
//           this.dispose();
//       } 
        if (valida()){
            asigna();
            if (Accion == ControlCursa.CONSULTAR){
                new reporteInd(c.getId());
                this.dispose();
                return;
            }
            String cadena="No se ejecuto la transaccion!!!";
            //System.out.println("");
            if (cs.ejecutaTx(c, Accion))
                cadena= "Se ejecuto la Transaccion!!";
                JOptionPane.showMessageDialog(this, cadena);
                int id = 0;
            if (Accion==ControlCursa.AGREGAR)
                id = cs.UltimoCursa();
            else
                id=a.getId();
            if ((Accion != ControlCursa.ELIMINAR) || (Accion != ControlCursa.CONSULTAR))
//                Casa_Cultura.mysql.guardaImagen(id, "alumno", a.getFoto());
            this.dispose();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnoActionPerformed
        // TODO add your handling code here:
//        cbTaller.requestFocus();
            
    }//GEN-LAST:event_cbAlumnoActionPerformed

    private void txtFechaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaInicioKeyTyped

    }//GEN-LAST:event_txtFechaInicioKeyTyped

    private void cbTallerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTallerActionPerformed
        // TODO add your handling code here:
//        txtFechaInicio.requestFocus();
    }//GEN-LAST:event_cbTallerActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbAlumno;
    private javax.swing.JComboBox<String> cbTaller;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTitulo;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private com.toedter.calendar.JDateChooser txtFechaTermino;
    // End of variables declaration//GEN-END:variables
}
