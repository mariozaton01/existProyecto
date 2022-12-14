/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Controlador.Controlador;
import creacionFicheros.AlumnosRelated;
import creacionFicheros.XMLrelated;

/**
 *
 * @author mario
 */
public class VentanaAlumnos extends javax.swing.JFrame {

    /**
     * Creates new form VentanaAlumnos
     */
    public VentanaAlumnos() {
        initComponents();
        try{
            XMLrelated.createXML();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bVerListaAlum = new javax.swing.JButton();
        bAlumPorAsignatura = new javax.swing.JButton();
        bAsignaturasDeAlum = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        bAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¿Que quieres hacer?");

        bVerListaAlum.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        bVerListaAlum.setText("Ver Lista Alumnos");
        bVerListaAlum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVerListaAlumActionPerformed(evt);
            }
        });

        bAlumPorAsignatura.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        bAlumPorAsignatura.setText("Ver Alumnos por Asignatura");
        bAlumPorAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlumPorAsignaturaActionPerformed(evt);
            }
        });

        bAsignaturasDeAlum.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        bAsignaturasDeAlum.setText("Ver Asignaturas de Alumno");
        bAsignaturasDeAlum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAsignaturasDeAlumActionPerformed(evt);
            }
        });

        bCancelar.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        bAyuda.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        bAyuda.setText("Ayuda");
        bAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(bVerListaAlum, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bAlumPorAsignatura)
                .addGap(18, 18, 18)
                .addComponent(bAsignaturasDeAlum)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(bAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(114, 114, 114)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bVerListaAlum, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAsignaturasDeAlum, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAlumPorAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAyuda))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        // TODO add your handling code here:
        Controlador.bCancelar(this);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bVerListaAlumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVerListaAlumActionPerformed
        // TODO add your handling code here:
        
        String texto="";
        try {
            texto= AlumnosRelated.verListaAlumnos();
        } catch (Exception ex) {
            Logger.getLogger(VentanaAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, texto);
    }//GEN-LAST:event_bVerListaAlumActionPerformed

    private void bAlumPorAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlumPorAsignaturaActionPerformed
        // TODO add your handling code here:
        Controlador.bAlumPorAsig();
    }//GEN-LAST:event_bAlumPorAsignaturaActionPerformed

    private void bAsignaturasDeAlumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAsignaturasDeAlumActionPerformed
        // TODO add your handling code here:
        Controlador.bAsigDeAlum();
    }//GEN-LAST:event_bAsignaturasDeAlumActionPerformed

    private void bAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAyudaActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "·Ver Lista Alumnos: se desplegará un texto con los alumnos que esten en el curso\n"
                + "·Alumnos por Asignatura: se abrirá una ventana en la que podrás ver los alumnos que tiene cada asignatura.\n"
                + "·Asignaturas de Alumno: se abrirá una ventana en la que se podrá ver las asignaturas que cursa un alumno.");
    }//GEN-LAST:event_bAyudaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAlumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlumPorAsignatura;
    private javax.swing.JButton bAsignaturasDeAlum;
    private javax.swing.JButton bAyuda;
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bVerListaAlum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
