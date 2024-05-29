package cyberhub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;



public class perfil extends javax.swing.JFrame {
    String bbdd = "jdbc:hsqldb:hsql://localhost";
    Connection con = null;
    private String nombreUsuario;
    
    public perfil() {
        initComponents();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection(bbdd, "SA", "SA");
            if (con != null) {
                System.out.println("Conexion creada");
            } else {
                System.out.println("Problema al crear la conexión");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
    jLabel1.setText(nombreUsuario);
    actualizarTable(con);
}
    



    public void actualizarTable(Connection con) {
    DefaultTableModel tm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
String user = nombreUsuario;
    System.out.println(user);

    tm.addColumn("Nombre");
    tm.addColumn("Lanzamiento");
    tm.addColumn("Desarrolladora");
    tm.addColumn("Plataformas");
    tm.addColumn("Formato");
    tm.addColumn("P1");
    tm.addColumn("P2");
    tm.addColumn("P3");
    tm.addColumn("P4");
    tm.addColumn("P5");
    tm.addColumn("P6");
    tm.addColumn("ID");
    tm.addColumn("Desc");

    jTable1.setModel(tm);

    try {
        String sql = "SELECT DISTINCT v.nombre, v.anio, v.desarrolladora, v.plataformas, v.formato, i.P1, i.P2, i.P3, i.P4, i.P5, i.P6, v.id, i.descripcion, c.nombre_usuario "
                + "FROM videojuegos v, informacion i, comentario c "
                + "WHERE v.id = i.id_juego AND v.id = c.id_del_juego AND c.aniadido = true AND c.nombre_usuario = '" + user + "'"; // Concatenar user directamente en la consulta SQL

        Statement statement = con.createStatement();
        ResultSet resultado = statement.executeQuery(sql);

        while (resultado.next()) {
            Object[] datos = new Object[13];
            datos[0] = resultado.getString(1);
            datos[1] = resultado.getString(2);
            datos[2] = resultado.getString(3);
            datos[3] = resultado.getString(4);
            datos[4] = resultado.getString(5);
            datos[5] = resultado.getString(6);
            datos[6] = resultado.getString(7);
            datos[7] = resultado.getString(8);
            datos[8] = resultado.getString(9);
            datos[9] = resultado.getString(10);
            datos[10] = resultado.getString(11);
            datos[11] = resultado.getString(12);
            datos[12] = resultado.getString(13);

            tm.addRow(datos);
        }
        statement.close();
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(5).setWidth(0);
        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(6).setWidth(0);
        jTable1.getColumnModel().getColumn(7).setMinWidth(0);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(7).setWidth(0);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(8).setWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setWidth(0);
        jTable1.getColumnModel().getColumn(10).setMinWidth(0);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(10).setWidth(0);
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(11).setWidth(0);
        jTable1.getColumnModel().getColumn(12).setMinWidth(0);
        jTable1.getColumnModel().getColumn(12).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(12).setWidth(0);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Borrar registro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto Thin", 1, 18)); // NOI18N
        jLabel2.setText("Juegos que te gustan:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(281, 281, 281))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
try {
    int rowIndex = jTable1.getSelectedRow();
    String idJuego = (String) jTable1.getValueAt(rowIndex, 11);
    String nombreUsuario = jLabel1.getText(); // Obtener el nombre de usuario
    
    String comentario = (String) jTable1.getValueAt(rowIndex, 12);
    
    if (comentario == null || comentario.isEmpty()) {
        // Si el comentario es nulo o vacío, elimina el registro
        String sqlDelete = "DELETE FROM comentario WHERE id_del_juego = ? AND nombre_usuario = ?";
        PreparedStatement deleteStatement = con.prepareStatement(sqlDelete);
        deleteStatement.setString(1, idJuego);
        deleteStatement.setString(2, nombreUsuario); // Agregar el nombre de usuario a la condición
        
        int rowsDeleted = deleteStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Se ha eliminado el registro debido a que el comentario era null o vacío");
        } else {
            System.out.println("No se encontraron registros para eliminar");
        }
        
        deleteStatement.close();
    } else {
        // Si el comentario no es nulo ni vacío, actualiza el estado del registro
        String sqlUpdate = "UPDATE comentario SET aniadido = false WHERE id_del_juego = ? AND nombre_usuario = ?";
        PreparedStatement updateStatement = con.prepareStatement(sqlUpdate);
        updateStatement.setString(1, idJuego);
        updateStatement.setString(2, nombreUsuario); // Agregar el nombre de usuario a la condición

        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("El valor de 'aniadido' se ha cambiado a false para el juego con ID: " + idJuego);
        } else {
            System.out.println("No se encontraron registros para actualizar");
        }
        
        updateStatement.close();
    }
    
    // Actualizar la tabla después de realizar la operación
    actualizarTable(con);
} catch (SQLException ex) {
    ex.printStackTrace();
    System.out.println("Error al cambiar el valor de 'aniadido' o al eliminar el registro");
}


    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new perfil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
