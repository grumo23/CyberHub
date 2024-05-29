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
import javax.swing.JOptionPane;
import cyberhub.Conexiones;


public class info2 extends javax.swing.JFrame {
     private String idJuego;
     String bbdd = "jdbc:hsqldb:hsql://localhost";
     java.sql.Connection con = null;

    public info2(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id) {
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabelID.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelLanzamiento.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelLanzamiento.setText("jLabelLanzamiento");

        jLabelNombre.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabelNombre.setText("jLabelNombre");

        jLabelDesarrolladora.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelDesarrolladora.setText("jLabelDesarrolladora");

        jLabelPlataformas.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelPlataformas.setText("jLabelPlataformas");

        jLabelFormato.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelFormato.setText("jLabel1");

        jDescripcion.setColumns(20);
        jDescripcion.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jDescripcion.setRows(5);
        jScrollPane1.setViewportView(jDescripcion);

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
                                .addGap(130, 130, 130)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addGap(0, 94, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabelNombre)
                        .addGap(48, 48, 48)
                        .addComponent(jLabelLanzamiento)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDesarrolladora)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPlataformas)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFormato)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nombreUsuario = jLabel1.getText();
boolean aniadido = true;

try {
    Connection con = DriverManager.getConnection(bbdd, "SA", "SA");

    String sqlSelect = "SELECT id_del_juego, aniadido FROM comentario WHERE id_del_juego = ? AND nombre_usuario = ?";
    PreparedStatement selectStatement = con.prepareStatement(sqlSelect);
    selectStatement.setString(1, idJuego);
    selectStatement.setString(2, nombreUsuario);

    ResultSet resultSet = selectStatement.executeQuery();

    if (resultSet.next()) {
        // Ya existe un registro para este juego y usuario
        boolean registroAniadido = resultSet.getBoolean("aniadido");
        if (registroAniadido) {
            // El registro ya está marcado como añadido
            System.out.println("Este juego ya te gusta.");
            // Muestra un mensaje al usuario
            JOptionPane.showMessageDialog(null, "Este juego ya te gusta.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // El registro existe pero no está marcado como añadido, por lo que lo actualizamos a true
            String updateSql = "UPDATE comentario SET aniadido = true WHERE id_del_juego = ? AND nombre_usuario = ?";
            PreparedStatement updateStatement = con.prepareStatement(updateSql);
            updateStatement.setString(1, idJuego);
            updateStatement.setString(2, nombreUsuario);
            updateStatement.executeUpdate();
            System.out.println("Se ha actualizado el registro a 'aniadido' = true.");
            updateStatement.close();
        }
    } else {
        // No hay registros existentes para este juego y usuario, así que lo creamos y lo marcamos como añadido
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

    resultSet.close();
    selectStatement.close();
    con.close();
} catch (SQLException e) {
    // Manejar excepciones de SQL
    e.printStackTrace();




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
            java.util.logging.Logger.getLogger(info2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
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
            new info2(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id).setVisible(true);
        }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
