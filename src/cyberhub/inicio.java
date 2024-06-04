package cyberhub;

import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import cyberhub.Conexiones;
import java.awt.Image;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.help.JHelp;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class inicio extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    public String nombreUsu; 
    
    
    public inicio() {
        initComponents();
        Conexiones.crear();
        actualizarTable(Conexiones.con);
        ranking(Conexiones.con);
        cargarFoto(Conexiones.con);
        tableModel = (DefaultTableModel) jTable1.getModel();
       
        String AYUDA_HS = "ayuda/conayuda/helpset.hs";
        try {
         ClassLoader cl = getClass().getClassLoader();
         HelpSet helpset = new HelpSet(cl, cl.getResource(AYUDA_HS));
         HelpBroker hb = helpset.createHelpBroker();
         JHelp jhelp = new JHelp(helpset);
         hb.enableHelpOnButton(jMenuItem4, "AcercaDe", helpset);

        } catch (HelpSetException ex) {
            System.err.println("Error al cargar la ayuda: " + ex);
        } 
    }
    
    public void actualizarTable(Connection con) {
        DefaultTableModel tm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        
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
                String sql = "SELECT DISTINCT v.nombre, v.anio, v.desarrolladora, v.plataformas, v.formato, i.P1, i.P2, i.P3, i.P4, i.P5, i.P6, v.id, i.descripcion "
                        + "FROM videojuegos v, informacion i "
                        + "WHERE v.id = i.id_juego";
                System.out.println(sql);
                Statement lee = con.createStatement();
                ResultSet resultado = lee.executeQuery(sql);

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
    
    public void nombreDelUsuario(){
        nombreUsu = jLabel2.getText();
        jTextField2.setText(nombreUsu);
    }
    
    public void cargarFoto(Connection con) {
        try {
            String sql = "SELECT v.id "
                        + "FROM videojuegos v, comentario c "
                        + "WHERE v.id = c.id_del_juego AND c.aniadido = TRUE "
                        + "GROUP BY v.id "
                        + "ORDER BY COUNT(c.aniadido) DESC "
                        + "LIMIT 1";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String idJuegoMasGustado = null;
            if (rs.next()) {
                idJuegoMasGustado = rs.getString("id");
            }

            if (idJuegoMasGustado != null) {
                Image imagenOriginal = ImageIO.read(getClass().getResource("\\imagenes\\" + idJuegoMasGustado + ".jpg"));
                Image imagenEscalada = imagenOriginal.getScaledInstance(140, 200, Image.SCALE_SMOOTH);

                ImageIcon imagenIcono = new ImageIcon(imagenEscalada);
                jLabel10.setIcon(imagenIcono);

                jLabel10.revalidate();
                jLabel10.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ranking(Connection con) {
        try {
            String sql = "SELECT v.nombre, COUNT(c.aniadido) AS me_gustas "
                       + "FROM videojuegos v, comentario c "
                       + "WHERE v.id = c.id_del_juego AND c.aniadido = TRUE "
                       + "GROUP BY v.nombre "
                       + "ORDER BY me_gustas DESC "
                       + "LIMIT 3";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int rank = 1;
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int meGustas = rs.getInt("me_gustas");

                switch (rank) {
                    case 1:
                        jLabel13.setText(nombre);
                        jLabel14.setText(meGustas + " Me gustas");
                        break;
                    case 2:
                        jLabel15.setText(nombre);
                        jLabel16.setText(meGustas + " Me gustas");
                        break;
                    case 3:
                        jLabel17.setText(nombre);
                        jLabel18.setText(meGustas + " Me gustas");
                        break;
                }
                rank++;
            }
            if (rank <= 2) {
                jLabel15.setText("");
                jLabel16.setText("");
            }
            if (rank <= 3) {
                jLabel17.setText("");
                jLabel18.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        jLabel2.setText(nombreUsuario);
        jTextField2.setText(nombreUsuario);
    }
    
    private void mostrarInfo1(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id, String nombreUsu) {
        info1 info1Frame = new info1(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
        String nombreUsuario = jLabel2.getText();
        info1Frame.setNombreUsuario(nombreUsuario);
        info1Frame.setVisible(true);
    }
    private void mostrarInfo2(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id, String nombreUsu) {
        info2 info2Frame = new info2(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
        String nombreUsuario = jLabel2.getText();
        info2Frame.setNombreUsuario(nombreUsuario);
        info2Frame.setVisible(true);
    }
    private void mostrarInfo3(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id, String nombreUsu) {
        info3 info3Frame = new info3(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
        String nombreUsuario = jLabel2.getText();
        info3Frame.setNombreUsuario(nombreUsuario);
        info3Frame.setVisible(true);
    }
    private void mostrarInfo4(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id, String nombreUsu) {
        info4 info4Frame = new info4(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
        String nombreUsuario = jLabel2.getText();
        info4Frame.setNombreUsuario(nombreUsuario);
        info4Frame.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel3.setText("Plataforma");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 625, -1, -1));

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 290, 29));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 30, 215, 31));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 112, 885, 418));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel4.setText("Formato");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 676, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escoge una opción...", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 629, 290, 29));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/lupa.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 625, 70, 69));
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 480, 183, 253));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/reset.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(577, 625, 68, 69));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/info (1).png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 625, 73, 69));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escoge una opción...", "Físico", "Digital", "Físico/Digital" }));
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 676, 289, 29));

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel12.setText("Tendencias");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, -1, -1));

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel11.setText("Juego más gustado");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 450, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/pfp.jpg"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel1.setText("Título");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 575, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/fontbolt (2).png"))); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 230, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 140, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 270, -1, -1));

        jLabel13.setText("jLabel13");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 370, -1, -1));

        jLabel14.setText("jLabel14");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 400, -1, -1));

        jLabel15.setText("jLabel15");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 370, -1, -1));

        jLabel16.setText("jLabel16");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 400, -1, -1));

        jLabel17.setText("jLabel17");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 370, -1, -1));

        jLabel18.setText("jLabel18");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 400, -1, -1));

        jMenu2.setText("Opciones");

        jMenuItem3.setText("Cerrar sesión");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Acerca de");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu6.setText("Ayuda");

        jMenuItem4.setText("Ayuda");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1249, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        String nombreUsuario = jLabel2.getText();
        perfil perfilFrame = new perfil();
        perfilFrame.setNombreUsuario(nombreUsuario);
        perfilFrame.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        Connection con = Conexiones.crear();
        actualizarTable(con);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        String nombre = jTextField1.getText();
        String plataforma = jComboBox1.getSelectedItem().toString();
        String formato = jComboBox2.getSelectedItem().toString(); 

        if (plataforma.equals("Escoge una opción...")) {
            plataforma = "";
        }

        if (formato.equals("Escoge una opción...")) {
            formato = "";
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultado = null;

        try {
        con = Conexiones.crear();

        String sqlFiltrado = "SELECT DISTINCT v.nombre, v.anio, v.desarrolladora, v.plataformas, v.formato, i.P1, i.P2, i.P3, i.P4, i.P5, i.P6, v.id "
                           + "FROM videojuegos v, informacion i "
                           + "WHERE v.id = i.id_juego";

        if (!nombre.isEmpty()) {
            sqlFiltrado += " AND v.nombre LIKE ?";
        }
        if (!plataforma.isEmpty()) {
            sqlFiltrado += " AND (i.P1 = ? OR i.P2 = ? OR i.P3 = ? OR i.P4 = ? OR i.P5 = ? OR i.P6 = ?)";
        }
        if (!formato.isEmpty()) {
            sqlFiltrado += " AND v.formato = ?";
        }

            ps = con.prepareStatement(sqlFiltrado);

            int index = 1;
            if (!nombre.isEmpty()) {
                ps.setString(index++, "%" + nombre + "%");
            }
            if (!plataforma.isEmpty()) {
                for (int i = 0; i < 6; i++) {
                    ps.setString(index++, plataforma);
                }
            }
            if (!formato.isEmpty()) {
                ps.setString(index, formato);
            }

            resultado = ps.executeQuery();

            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);

        while (resultado.next()) {
            Object[] datos = new Object[12];
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
            tm.addRow(datos);   }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try 
            {
                if (resultado != null) resultado.close();
                if (ps != null) ps.close();
                Conexiones.cerrar();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
            
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int rowIndex = jTable1.getSelectedRow();
        String nombreUsu = jTextField2.getText();
        if (rowIndex != -1) {
            String nombre = (String) jTable1.getValueAt(rowIndex, 0);
            String lanzamiento = (String) jTable1.getValueAt(rowIndex, 1);
            String desarrolladora = (String) jTable1.getValueAt(rowIndex, 2);
            String plataformas = (String) jTable1.getValueAt(rowIndex, 3);
            String formato = (String) jTable1.getValueAt(rowIndex, 4);
            String descripcion = (String) jTable1.getValueAt(rowIndex, 12);
            String id = (String) jTable1.getValueAt(rowIndex, 11);

            int idNum = Integer.parseInt(id);

            if ((idNum >= 1 && idNum <= 41) ||
                (idNum >= 108 && idNum <= 215) ||
                (idNum >= 501 && idNum <= 730) ||
                (idNum >= 765 && idNum <= 803)) {
                mostrarInfo1(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
            } else if (idNum >= 42 && idNum <= 107) {
                mostrarInfo4(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
            } else if ((idNum >= 216 && idNum <= 270) ||
                       (idNum >= 731 && idNum <= 764)) {
                mostrarInfo3(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
                
            } else {
                mostrarInfo2(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id, nombreUsu);
            }
            }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       login l = new login();
       l.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        String nombreUsuario = jLabel2.getText();
        perfil perfilFrame = new perfil();
        perfilFrame.setNombreUsuario(nombreUsuario);
        perfilFrame.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        acercade ad = new acercade();
        ad.setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
                FlatDraculaIJTheme.setup();
            } catch( Exception ex ) {
                  System.err.println( "Failed to initialize LaF" );
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
