package cyberhub;

import java.sql.Connection;
import cyberhub.Conexiones;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author duran
 */
public class modificar extends javax.swing.JFrame {

    /**
     * Creates new form añadir
     */
    public modificar() {
        initComponents();
    }
    public void setValues(String titulo, String lanzamiento, String desarrolladora, String formato,
                          String pl1, String pl2, String pl3, String pl4, String pl5, String pl6,
                          String id, String desc) {
        tituloTexto.setText(titulo);
        lanzamientoTexto.setText(lanzamiento);
        desarrolladoraTexto.setText(desarrolladora);
        formatos.setSelectedItem(formato);
        plataforma1.setSelectedItem(pl1);
        plataforma2.setSelectedItem(pl2);
        plataforma3.setSelectedItem(pl3);
        plataforma4.setSelectedItem(pl4);
        plataforma5.setSelectedItem(pl5);
        plataforma6.setSelectedItem(pl6);
        idTexto.setText(id);
        descrip.setText(desc);
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
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        plataforma2 = new javax.swing.JComboBox<>();
        lanzamientoTexto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descrip = new javax.swing.JTextArea();
        desarrolladoraTexto = new javax.swing.JTextField();
        idTexto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tituloTexto = new javax.swing.JTextField();
        plataforma3 = new javax.swing.JComboBox<>();
        plataforma4 = new javax.swing.JComboBox<>();
        plataforma5 = new javax.swing.JComboBox<>();
        plataforma6 = new javax.swing.JComboBox<>();
        plataforma1 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        formatos = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel22.setText("Título");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel21.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel21.setText("Lanzamiento");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel20.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel20.setText("Desarrolladora");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel19.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel19.setText("ID");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, -1, -1));

        jLabel23.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel23.setText("Formato");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        plataforma2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jPanel1.add(plataforma2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 289, 29));

        lanzamientoTexto.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lanzamientoTexto.setBorder(null);
        jPanel1.add(lanzamientoTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 289, 29));

        descrip.setColumns(20);
        descrip.setRows(5);
        jScrollPane1.setViewportView(descrip);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 620, 280));

        desarrolladoraTexto.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        desarrolladoraTexto.setBorder(null);
        jPanel1.add(desarrolladoraTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 289, 29));

        idTexto.setEditable(false);
        idTexto.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        idTexto.setBorder(null);
        jPanel1.add(idTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 240, 29));

        jLabel24.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel24.setText("Plataformas");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, -1, -1));

        tituloTexto.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        tituloTexto.setBorder(null);
        jPanel1.add(tituloTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 289, 29));

        plataforma3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jPanel1.add(plataforma3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 289, 29));

        plataforma4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jPanel1.add(plataforma4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 289, 29));

        plataforma5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jPanel1.add(plataforma5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 289, 29));

        plataforma6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jPanel1.add(plataforma6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 270, 289, 29));

        plataforma1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jPanel1.add(plataforma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 289, 29));

        jLabel25.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel25.setText("Descripción");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        formatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Físico", "Digital", "Físico/Digital" }));
        jPanel1.add(formatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 289, 29));

        jPanel2.setBackground(new java.awt.Color(255, 102, 255));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MODIFICAR");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 470, 110, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        String id = idTexto.getText();
        String pl1Value = (String) plataforma1.getSelectedItem();
        String pl2Value = (String) plataforma2.getSelectedItem();
        String pl3Value = (String) plataforma3.getSelectedItem();
        String pl4Value = (String) plataforma4.getSelectedItem();
        String pl5Value = (String) plataforma5.getSelectedItem();
        String pl6Value = (String) plataforma6.getSelectedItem();
        String titulo = tituloTexto.getText();
        int anio = Integer.parseInt(lanzamientoTexto.getText());
        String desarrolladora = desarrolladoraTexto.getText();
        String descripcion = descrip.getText();

        try (Connection con = Conexiones.crear()) {
            String updateInfoQuery = "UPDATE informacion SET P1 = ?, P2 = ?, P3 = ?, P4 = ?, P5 = ?, P6 = ?, descripcion = ? WHERE id_juego = ?";
            PreparedStatement pstmtInfo = con.prepareStatement(updateInfoQuery);
            pstmtInfo.setString(1, pl1Value);
            pstmtInfo.setString(2, pl2Value);
            pstmtInfo.setString(3, pl3Value);
            pstmtInfo.setString(4, pl4Value);
            pstmtInfo.setString(5, pl5Value);
            pstmtInfo.setString(6, pl6Value);
            pstmtInfo.setString(7, descripcion);
            pstmtInfo.setString(8, id);
            pstmtInfo.executeUpdate();

            String updateVideojuegosQuery = "UPDATE videojuegos SET nombre = ?, anio = ?, desarrolladora = ? WHERE id = ?";
            PreparedStatement pstmtVideojuegos = con.prepareStatement(updateVideojuegosQuery);
            pstmtVideojuegos.setString(1, titulo);
            pstmtVideojuegos.setInt(2, anio);
            pstmtVideojuegos.setString(3, desarrolladora);
            pstmtVideojuegos.setString(4, id);
            pstmtVideojuegos.executeUpdate();

            javax.swing.JOptionPane.showMessageDialog(this, "Registro actualizado.");
        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el registro");
        }
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField desarrolladoraTexto;
    private javax.swing.JTextArea descrip;
    private javax.swing.JComboBox<String> formatos;
    private javax.swing.JTextField idTexto;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lanzamientoTexto;
    private javax.swing.JComboBox<String> plataforma1;
    private javax.swing.JComboBox<String> plataforma2;
    private javax.swing.JComboBox<String> plataforma3;
    private javax.swing.JComboBox<String> plataforma4;
    private javax.swing.JComboBox<String> plataforma5;
    private javax.swing.JComboBox<String> plataforma6;
    private javax.swing.JTextField tituloTexto;
    // End of variables declaration//GEN-END:variables
}