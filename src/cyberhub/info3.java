package cyberhub;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import cyberhub.Conexiones;
import static cyberhub.Conexiones.con;


public class info3 extends javax.swing.JFrame {
     private String idJuego;
     
         


    public info3(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id, String nombreUsu) {
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
        usuario.setText(nombreUsu);

         try {
                Image imagenOriginal = ImageIO.read(getClass().getResource("\\imagenes\\" + id + ".jpg"));
                Image imagenEscalada = imagenOriginal.getScaledInstance(350, 450, Image.SCALE_SMOOTH);
                ImageIcon imagenIcono = new ImageIcon(imagenEscalada);
                jLabelImagen.setIcon(imagenIcono);
                jLabelImagen.revalidate();
                jLabelImagen.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
         Conexiones.crear();
         megusta();
    }
    
    public void setNombreUsuario(String nombreUsu) {
        usuario.setText(nombreUsu);
    }
    
    public void meGustaNoMeGusta() { //este CAMBIA el estado de true a false
        String nombreUsuario = usuario.getText();
        String idJuego = jLabelID.getText();
        boolean aniadido = false;

        try {
            Conexiones.con = DriverManager.getConnection(Conexiones.BASE, "SA", "SA");

            String sqlSelect = "SELECT aniadido FROM comentario WHERE id_del_juego = ? AND nombre_usuario = ?";
            PreparedStatement selectStatement = con.prepareStatement(sqlSelect);
            selectStatement.setString(1, idJuego);
            selectStatement.setString(2, nombreUsuario);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                boolean registroAniadido = resultSet.getBoolean("aniadido");
                aniadido = !registroAniadido;

                String updateSql = "UPDATE comentario SET aniadido = ? WHERE id_del_juego = ? AND nombre_usuario = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateSql);
                updateStatement.setBoolean(1, aniadido);
                updateStatement.setString(2, idJuego);
                updateStatement.setString(3, nombreUsuario);
                updateStatement.executeUpdate();

                System.out.println("Se ha actualizado el registro a 'aniadido' = " + aniadido);
                updateStatement.close();
            } else {
                aniadido = true;
                String insertSql = "INSERT INTO comentario (id_del_juego, nombre_usuario, aniadido) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertSql);
                insertStatement.setString(1, idJuego);
                insertStatement.setString(2, nombreUsuario);
                insertStatement.setBoolean(3, aniadido);
                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Nuevo registro agregado.");
                } else {
                    System.out.println("No se pudo agregar.");
                }

                insertStatement.close();
            }
            resultSet.close();
            selectStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String imagenPath = aniadido ? "\\imagenes\\cora.png" : "\\imagenes\\coravacia.png";
        try {
            Image imagenOriginal = ImageIO.read(getClass().getResource(imagenPath));
            if (imagenOriginal == null) {
                throw new IllegalArgumentException("La imagen no se encontró: " + imagenPath);
            }
            Image imagenEscalada = imagenOriginal.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imagenIcono = new ImageIcon(imagenEscalada);
            jLabel4.setIcon(imagenIcono);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jLabel4.revalidate();
        jLabel4.repaint();
    }

    public void megusta() { //este SOLO comprueba el estado, NO CAMBIA NADA
        String nombreUsuario = usuario.getText();
        String idJuego = jLabelID.getText();
        boolean aniadido = false;

        try {
            Conexiones.con = DriverManager.getConnection(Conexiones.BASE, "SA", "SA");

            String sqlSelect = "SELECT aniadido FROM comentario WHERE id_del_juego = ? AND nombre_usuario = ?";
            PreparedStatement selectStatement = Conexiones.con.prepareStatement(sqlSelect);
            selectStatement.setString(1, idJuego);
            selectStatement.setString(2, nombreUsuario);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                aniadido = resultSet.getBoolean("aniadido");
            } else {
                System.out.println("No existe el registro para la ID = " + idJuego + " y el nombre de usuario = " + nombreUsuario);
            }

            resultSet.close();
            selectStatement.close();
            Conexiones.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String imagenPath = aniadido ? "\\imagenes\\cora.png" : "\\imagenes\\coravacia.png";
        try {
            Image imagenOriginal = ImageIO.read(getClass().getResource(imagenPath));
            if (imagenOriginal == null) {
                throw new IllegalArgumentException("La imagen no se encontró: " + imagenPath);
            }
            Image imagenEscalada = imagenOriginal.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imagenIcono = new ImageIcon(imagenEscalada);
            jLabel4.setIcon(imagenIcono);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jLabel4.revalidate();
        jLabel4.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelID = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelLanzamiento = new javax.swing.JLabel();
        jLabelDesarrolladora = new javax.swing.JLabel();
        jLabelPlataformas = new javax.swing.JLabel();
        jLabelFormato = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDescripcion = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelImagen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        jLabelID.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 20));

        jLabelNombre.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        jLabelNombre.setText("jLabelNombre");
        jPanel2.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 594, -1));

        jLabelLanzamiento.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelLanzamiento.setText("jLabelLanzamiento");
        jPanel2.add(jLabelLanzamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 490, -1));

        jLabelDesarrolladora.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelDesarrolladora.setText("jLabelDesarrolladora");
        jPanel2.add(jLabelDesarrolladora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 540, -1));

        jLabelPlataformas.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelPlataformas.setText("jLabelPlataformas");
        jPanel2.add(jLabelPlataformas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 540, -1));

        jLabelFormato.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        jLabelFormato.setText("jLabel1");
        jPanel2.add(jLabelFormato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 490, -1));

        jDescripcion.setColumns(20);
        jDescripcion.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jDescripcion.setRows(5);
        jScrollPane1.setViewportView(jDescripcion);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 500, 193));

        jPanel1.setBackground(new java.awt.Color(204, 51, 255));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("COMENTARIOS");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 120, -1));
        jPanel2.add(jLabelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 392, 491));

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 70, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        String nombreUsuario = jLabel1.getText();
        String idJuego = jLabelID.getText();
        String nombre = jLabelNombre.getText();
        comentarios c = new comentarios(nombre, nombreUsuario, idJuego);
        c.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        meGustaNoMeGusta();
    }//GEN-LAST:event_jLabel4MouseClicked
    
   

    
    
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
            java.util.logging.Logger.getLogger(info3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            String nombreUsu = null;
            new info3(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu).setVisible(true);
        }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea jDescripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelDesarrolladora;
    private javax.swing.JLabel jLabelFormato;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelLanzamiento;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPlataformas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
