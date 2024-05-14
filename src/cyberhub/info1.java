package cyberhub;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class info1 extends javax.swing.JFrame {
     private String idJuego;
     String bbdd = "jdbc:hsqldb:hsql://localhost";
     java.sql.Connection con = null;

    public info1(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id) {
        initComponents();
        this.idJuego = id;
        jLabelNombre.setText(nombre);
        jLabelLanzamiento.setText("Lanzamiento: " + lanzamiento);
        jLabelDesarrolladora.setText("Desarrolladora: " + desarrolladora);
        jLabelPlataformas.setText("Plataformas: " + plataformas);
        jLabelFormato.setText("Formato: " + formato);
        jLabelID.setText(id);
        jDescripcion.setText(descripcion);jDescripcion.setLineWrap(true); 
        jDescripcion.setWrapStyleWord(true);
        jDescripcion.setPreferredSize(new Dimension(200, 200));
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jDescripcion.setEditable(false);
        
        try {
            String imagenPath = "C:\\Users\\duran\\OneDrive\\Documentos\\NetBeansProjects\\CyberHub\\src\\cyberhub\\imagenes\\" + id + ".jpg";
            ImageIcon imagenIcon = new ImageIcon(imagenPath);
            Image imagen = imagenIcon.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH);
            ImageIcon imagenEscalada = new ImageIcon(imagen);
            jLabelImagen.setIcon(imagenEscalada);
        } catch (Exception ex) {
            ex.printStackTrace();
        }       
    }
    public void setNombreUsuario(String nombreUsuario) {
        jLabel1.setText(nombreUsuario);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelID = new javax.swing.JLabel();
        jLabelLanzamiento = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelDesarrolladora = new javax.swing.JLabel();
        jLabelPlataformas = new javax.swing.JLabel();
        jLabelFormato = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDescripcion = new javax.swing.JTextArea();
        jLabelImagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabelID.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelLanzamiento.setText("jLabelLanzamiento");

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelNombre.setText("jLabelNombre");

        jLabelDesarrolladora.setText("jLabelDesarrolladora");

        jLabelPlataformas.setText("jLabelPlataformas");

        jLabelFormato.setText("jLabel1");

        jDescripcion.setColumns(20);
        jDescripcion.setRows(5);
        jScrollPane1.setViewportView(jDescripcion);

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Comentarios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Me gusta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelDesarrolladora, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelPlataformas, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNombre)
                        .addGap(69, 69, 69)
                        .addComponent(jLabelLanzamiento)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDesarrolladora)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPlataformas)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFormato)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1))
                    .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nombreUsuario = jLabel1.getText();
boolean aniadido = true;

try {
    Connection con = DriverManager.getConnection(bbdd, "SA", "SA");
    
    String sqlSelect = "SELECT id_del_juego FROM comentario WHERE id_del_juego = ? AND aniadido = false";
    PreparedStatement selectStatement = con.prepareStatement(sqlSelect);
    selectStatement.setString(1, idJuego);
    
    ResultSet resultSet = selectStatement.executeQuery();
    
    if (resultSet.next()) {
        // Ya existe un registro para este juego sin marcar como añadido
        String updateSql = "UPDATE comentario SET aniadido = true WHERE id_del_juego = ?";
        PreparedStatement updateStatement = con.prepareStatement(updateSql);
        updateStatement.setString(1, idJuego);
        
        int rowsUpdated = updateStatement.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("Registro existente actualizado a 'aniadido' = true");
        } else {
            System.out.println("No se pudo actualizar el registro existente");
        }
        
        updateStatement.close();
    } else {
        // No hay registros existentes para este juego sin marcar como añadido
        String insertSql = "INSERT INTO comentario (id_del_juego, nombre_usuario, aniadido) VALUES (?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);
        insertStatement.setString(1, idJuego);
        insertStatement.setString(2, nombreUsuario);
        insertStatement.setBoolean(3, aniadido);
        
        int rowsInserted = insertStatement.executeUpdate();
        
        if (rowsInserted > 0) {
            System.out.println("Nuevo registro agregado correctamente a la base de datos");
        } else {
            System.out.println("No se pudo agregar el nuevo registro a la base de datos");
        }
        
        insertStatement.close();
    }
} catch (SQLException e) {
    // Manejar excepciones de SQL
    e.printStackTrace();
} finally {
    // Cerrar la conexión
    try {
        if (con != null) {
            con.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nombreUsuario = jLabel1.getText();
        String idJuego = jLabelID.getText();
        String nombre = jLabelNombre.getText();
        comentarios c = new comentarios(nombre, nombreUsuario, idJuego);
        c.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(info1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            String nombre = "Nombre del juego";
            String lanzamiento = "Fecha de lanzamiento";
            String desarrolladora = "Desarrolladora";
            String plataformas = "Plataformas";
            String formato = "Formato";
            String descripcion = "Descripción del juego";
            String id = "00552.jpg";
            new info1(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id).setVisible(true);
        }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextArea jDescripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDesarrolladora;
    private javax.swing.JLabel jLabelFormato;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelLanzamiento;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPlataformas;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
