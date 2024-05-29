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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class inicio extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    
    
    public inicio() {
        initComponents();
        Conexiones.crear();
        actualizarTable(Conexiones.con);
        ranking(Conexiones.con);
        cargarFoto(Conexiones.con);
        tableModel = (DefaultTableModel) jTable1.getModel();
        
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
        } else {
            System.out.println("No se encontró el juego más gustado.");
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
        System.out.println(sql);
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
   /* public void tendencias(Connection con) {
    DefaultTableModel tm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    tm.addColumn("Nombre");
    tm.addColumn("ID");
    tm.addColumn("Me Gustas");
    jTable2.setModel(tm);

    try {
        String sql = "SELECT v.nombre, v.id, COUNT(c.aniadido) AS me_gustas "
                    + "FROM videojuegos v, comentario c "
                    + "WHERE v.id = c.id_del_juego AND c.aniadido = TRUE "
                    + "GROUP BY v.nombre, v.id "
                    + "ORDER BY me_gustas DESC "
                    + "LIMIT 10";

        System.out.println(sql);
        Statement lee = con.createStatement();
        ResultSet resultado = lee.executeQuery(sql);

        while (resultado.next()) {
            Object[] datos = new Object[3];
            datos[0] = resultado.getString("nombre");
            datos[1] = resultado.getString("id");
            datos[2] = resultado.getInt("me_gustas");

            tm.addRow(datos);
        }
        jTable2.getColumnModel().getColumn(1).setMinWidth(0);
        jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(1).setWidth(0);
        jTable2.getColumnModel().getColumn(2).setMinWidth(0);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(2).setWidth(0);
    } catch (Exception e) {
        e.printStackTrace();
    }
}*/
    
    public void setNombreUsuario(String nombreUsuario) {
        jLabel2.setText(nombreUsuario);
    }
    private void cargarDatos() {
    try {
        String sqlFiltrado = "SELECT DISTINCT v.nombre, v.anio, v.desarrolladora, v.plataformas, v.formato, i.P1, i.P2, i.P3, i.P4, i.P5, i.P6, v.id "
                + "FROM videojuegos v, informacion i "
                + "WHERE v.id = i.id_juego";

        String nombre = jTextField1.getText();
        String plataforma = jComboBox1.getSelectedItem().toString();
        String formato = jComboBox2.getSelectedItem().toString();

        if (!nombre.isEmpty()) {
            sqlFiltrado += " AND v.nombre LIKE ?";
        }
        if (!plataforma.equals("Escoge una opción...")) {
            sqlFiltrado += " AND (i.P1 = ? OR i.P2 = ? OR i.P3 = ? OR i.P4 = ? OR i.P5 = ? OR i.P6 = ?)";
        }
        if (!formato.equals("Escoge una opción...")) {
            sqlFiltrado += " AND v.formato = ?";
        }

        PreparedStatement ps = Conexiones.con.prepareStatement(sqlFiltrado);

        int index = 1;
        if (!nombre.isEmpty()) {
            ps.setString(index++, "%" + nombre + "%");
        }
        if (!plataforma.equals("Escoge una opción...")) {
            for (int i = index; i <= index + 5; i++) {
                ps.setString(i, plataforma);
            }
            index += 6;
        }
        if (!formato.equals("Escoge una opción...")) {
            ps.setString(index, formato);
        }

        ResultSet resultado = ps.executeQuery();

        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        tm.setRowCount(0);

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
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void mostrarInfo1(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id) {
        info1 info1Frame = new info1(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
        String nombreUsuario = jLabel2.getText();
        info1Frame.setNombreUsuario(nombreUsuario);
        info1Frame.setVisible(true);
    }
    /*private void mostrarInfo2(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id) {
        info2 info2Frame = new info2(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
        String nombreUsuario = jLabel2.getText();
        info2Frame.setNombreUsuario(nombreUsuario);
        info2Frame.setVisible(true);
    }
    private void mostrarInfo3(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id) {
        info3 info3Frame = new info3(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
        String nombreUsuario = jLabel2.getText();
        info3Frame.setNombreUsuario(nombreUsuario);
        info3Frame.setVisible(true);
    }
    private void mostrarInfo4(String nombre, String lanzamiento, String desarrolladora, String plataformas, String formato, String descripcion, String id) {
        info4 info4Frame = new info4(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
        String nombreUsuario = jLabel2.getText();
        info4Frame.setNombreUsuario(nombreUsuario);
        info4Frame.setVisible(true);
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escoge una opción...", "Dreamcast", "Game Boy", "Game Boy Advance", "Game Boy Color", "GameCube", "Master System", "Mega Drive", "NES", "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Switch", "PlayStation", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "PSP", "PS Vita", "Sega Saturn", "SNES", "Wii", "Wii U", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X/S" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel1.setText("Título");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escoge una opción...", "Físico", "Digital", "Físico/Digital" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel3.setText("Plataforma");

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        jLabel4.setText("Formato");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/fontbolt (2).png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/reset.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/lupa.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/info (1).png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logos/pfp.jpg"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel11.setText("Juego más gustado");

        jLabel12.setFont(new java.awt.Font("Roboto Medium", 1, 14)); // NOI18N
        jLabel12.setText("Tendencias");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

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

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        jLabel15.setText("jLabel15");

        jLabel16.setText("jLabel16");

        jLabel17.setText("jLabel17");

        jLabel18.setText("jLabel18");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 21, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel17)))))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jMenu1.setText("Apariencia");

        jMenuItem1.setText("Claro");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Oscuro");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

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
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(84, 84, 84))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
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
        actualizarTable(Conexiones.con);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        String nombre = jTextField1.getText();
    String plataforma = jComboBox1.getSelectedItem().toString();
    String formato = jComboBox2.getSelectedItem().toString(); 
    
    if (plataforma.equals("Escoge una opción...")) {
        plataforma = "";}

    if (formato.equals("Escoge una opción...")) {
        formato = "";}

    try {
        String sqlFiltrado = "SELECT DISTINCT v.nombre, v.anio, v.desarrolladora, v.plataformas, v.formato, i.P1, i.P2, i.P3, i.P4, i.P5, i.P6, v.id "
                + "FROM videojuegos v, informacion i "
                + "WHERE v.id = i.id_juego";

        if (!nombre.isEmpty()) {
            sqlFiltrado += " AND v.nombre LIKE '%" + nombre + "%'";
        }
        if (!plataforma.isEmpty()) {
            sqlFiltrado += " AND (i.P1 = ? OR i.P2 = ? OR i.P3 = ? OR i.P4 = ? OR i.P5 = ? OR i.P6 = ?)";
        }
        if (!formato.isEmpty()) {
            sqlFiltrado += " AND v.formato = ?";
        }

        PreparedStatement ps = Conexiones.con.prepareStatement(sqlFiltrado);

        int index = 1;
        if (!plataforma.isEmpty()) {
            for (int i = index; i <= index + 5; i++) {
                ps.setString(i, plataforma);
            }
            index += 6;
        }
        if (!formato.isEmpty()) {
            ps.setString(index, formato);
        }

        ResultSet resultado = ps.executeQuery();

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
            tm.addRow(datos);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int rowIndex = jTable1.getSelectedRow();
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
                mostrarInfo1(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
            } else if (idNum >= 42 && idNum <= 107) {
                mostrarInfo4(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
            } else if ((idNum >= 216 && idNum <= 270) ||
                       (idNum >= 731 && idNum <= 764)) {
                mostrarInfo3(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
                
            } else {
                mostrarInfo2(nombre, lanzamiento, desarrolladora, plataformas, formato, descripcion, id);
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
