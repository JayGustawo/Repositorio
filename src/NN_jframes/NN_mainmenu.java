/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NN_jframes;

import BusinessObjects.Client;
import BusinessObjects.Employee;
import Managers.DatabaseManager;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Armando
 */
public class NN_mainmenu extends javax.swing.JFrame {

    DatabaseManager db;
    public NN_JframeAdmin manager;
    /**
     * Creates new form NN_mainmenu
     */
    public NN_mainmenu() {
        initComponents();
        hidebttn();
        setMinimumSize(new Dimension(100,560).getSize());
        db = NN_MainClass.db;
        manager = NN_MainClass.getManager();
        db = NN_MainClass.db;
        DefaultTableModel amodel = (DefaultTableModel)table_cita.getModel();
        LocalDate rightnow = java.time.LocalDate.now();
        int tDay = rightnow.getDayOfMonth();
        int tMonth = rightnow.getMonthValue()-1;
        int tYear = rightnow.getYear();
        amodel.setRowCount(0);
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "proyectouser", "proyecto");
            String colhead[]={"ID","Cliente","Empleado","Dia","Mes","Año","Hora","Razon"};
            amodel.setColumnIdentifiers(colhead);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID,ID_C,ID_E,DAY,MONTH,YEAR,HOUR,REASON,HOURVALUE FROM APPOINTMENT WHERE DAY = " + tDay + " AND MONTH = " +tMonth+ " AND YEAR = " + tYear + " ORDER BY HOURVALUE");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols=rsmd.getColumnCount();
            while(rs.next()){
                Object[] obj=new Object[cols];
                for(int i=0; i<cols; i++){
                    if(i==1){
                        obj[i]= idToClientName(rs.getInt("ID_C"));
                    }
                    else if(i==2){
                        obj[i]= idToEmployeeName(rs.getInt("ID_E"));
                    }
                    else if(i==4){
                       switch (rs.getInt("MONTH")){
                           case 0:  obj[i]= "Enero";
                           break;
                           case 1:  obj[i]= "Febrero";
                           break;
                           case 2:  obj[i]= "Marzo";
                           break;
                           case 3:  obj[i]= "Abril";
                           break;
                           case 4:  obj[i]= "Mayo";
                           break;
                           case 5:  obj[i]= "Junio";
                           break;
                           case 6:  obj[i]= "Julio";
                           break;
                           case 7:  obj[i]= "Agosto";
                           break;
                           case 8:  obj[i]= "Septiembre";
                           break;
                           case 9:  obj[i]= "Octubre";
                           break;
                           case 10:  obj[i]= "Noviembre";
                           break;
                           case 11:  obj[i]= "Diciembre";
                           break;
                       }
                    }
                    else{
                        obj[i]=rs.getObject(i+1);
                    }
                    
                }
                amodel.addRow(obj);
            }
            conn.close();
            stmt.close();
        }catch(Exception e){
        }
        
        table_cita.setDefaultEditor(Object.class, null);
        T_username.setText(manager.loggeduser.getUserName());
    }

    public void hidebttn(){
                
        bttn_cita.setOpaque(false);
        bttn_cita.setContentAreaFilled(false); //to make the content area transparent
        bttn_cita.setBorderPainted(false); //to make the borders transparent
        
        bttn_clientes.setOpaque(false);
        bttn_clientes.setContentAreaFilled(false); //to make the content area transparent
        bttn_clientes.setBorderPainted(false); //to make the borders transparent
        
        bttn_empleado.setOpaque(false);
        bttn_empleado.setContentAreaFilled(false); //to make the content area transparent
        bttn_empleado.setBorderPainted(false); //to make the borders transparent
        
        bttn_pago.setOpaque(false);
        bttn_pago.setContentAreaFilled(false); //to make the content area transparent
        bttn_pago.setBorderPainted(false); //to make the borders transparent
        
        bttn_inventario.setOpaque(false);
        bttn_inventario.setContentAreaFilled(false); //to make the content area transparent
        bttn_inventario.setBorderPainted(false); //to make the borders transparent}
        
        bttn_salir.setOpaque(false);
        bttn_salir.setContentAreaFilled(false); //to make the content area transparent
        bttn_salir.setBorderPainted(false); //to make the borders transparent
        
        bttn_stats.setOpaque(false);
        bttn_stats.setContentAreaFilled(false); //to make the content area transparent
        bttn_stats.setBorderPainted(false); //to make the borders transparent
        
        bttn_log.setOpaque(false);
        bttn_log.setContentAreaFilled(false); //to make the content area transparent
        bttn_log.setBorderPainted(false); //to make the borders transparent
    }
    
    public String idToEmployeeName(int id){
        String name = null;
        Employee newEmployee = db.getEmployeeById(id);
        if(newEmployee.getId()<=0){
            name = "Inexistente";
        }
        else{
            String fName = newEmployee.getFirstName();
            String lName = newEmployee.getLastName();
            name = fName + " " + lName;
        }
        
        return name;
    }
    
    public String idToClientName(int id){
        String name = null;
        Client newClient = db.getClientebyId(id);
        if(newClient.getId()<=0){
            name = "Inexistente";
        }
        else{
            String fName = newClient.getFirstName();
            String lName = newClient.getLastName();
            name = fName + " " + lName;
        }
        
        return name;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Line = new javax.swing.JLabel();
        bttn_log = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        t_message = new javax.swing.JLabel();
        TAB = new javax.swing.JLabel();
        bttn_cita = new javax.swing.JButton();
        bttn_empleado = new javax.swing.JButton();
        bttn_pago = new javax.swing.JButton();
        bttn_inventario = new javax.swing.JButton();
        bttn_salir = new javax.swing.JButton();
        bttn_clientes = new javax.swing.JButton();
        bttn_stats = new javax.swing.JButton();
        T_username = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_cita = new javax.swing.JTable();
        background = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu Principal");
        setLocation(new java.awt.Point(300, 230));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Line.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/LogIn/Login_Inputline.png"))); // NOI18N
        getContentPane().add(Line, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, 20));

        bttn_log.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_logActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 50, 40, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Main/Main_exitbutton.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, -1));

        t_message.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        t_message.setForeground(new java.awt.Color(255, 255, 255));
        t_message.setText("Las citas para el dia de hoy son:");
        getContentPane().add(t_message, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 190, 20));

        TAB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TAB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Main/Main_tab.png"))); // NOI18N
        getContentPane().add(TAB, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, 40));

        bttn_cita.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_cita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_citaActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_cita, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 80));

        bttn_empleado.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_empleadoActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 70, 80));

        bttn_pago.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_pagoActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 70, 80));

        bttn_inventario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_inventarioActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 70, 80));

        bttn_salir.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 70, 80));

        bttn_clientes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_clientesActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, 80));

        bttn_stats.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        bttn_stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_statsActionPerformed(evt);
            }
        });
        getContentPane().add(bttn_stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 70, 80));

        T_username.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        T_username.setForeground(new java.awt.Color(186, 93, 93));
        T_username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T_username.setText("-");
        getContentPane().add(T_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(564, 14, 210, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Main/Main_Colorbuttons.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, -1));

        jScrollPane1.setBackground(new java.awt.Color(251, 243, 243));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 107, 97), 3));

        table_cita.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_cita);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 660, 450));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Main/Main_background.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 560));

        jRadioButton1.setText("jRadioButton1");
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttn_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_pagoActionPerformed
        // TODO add your handling code here:
        manager.openPagoMain();
        this.dispose();
    }//GEN-LAST:event_bttn_pagoActionPerformed

    private void bttn_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_inventarioActionPerformed
        // TODO add your handling code here:
        if(manager.loggeduser.getRole().equals("Admin")){
            manager.openInventoryMain();
        this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Usted no cuenta con permisos de administrador", "No autorizado", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_bttn_inventarioActionPerformed

    private void bttn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_salirActionPerformed
        // TODO add your handling code here:
        manager.openLogin();
        this.dispose();
    }//GEN-LAST:event_bttn_salirActionPerformed

    private void bttn_citaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_citaActionPerformed
        // TODO add your handling code here:
        manager.openCita();
        this.dispose();
    }//GEN-LAST:event_bttn_citaActionPerformed

    private void bttn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_clientesActionPerformed
        // TODO add your handling code here:
        manager.openCliente();
        this.dispose();
    }//GEN-LAST:event_bttn_clientesActionPerformed

    private void bttn_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_empleadoActionPerformed
        // TODO add your handling code here:
        if(manager.loggeduser.getRole().equals("Admin")){
            manager.openEmpleado();
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Usted no cuenta con permisos de administrador", "no autorizado", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bttn_empleadoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir del sistema?", "WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes option
            System.exit(0);
            } else {
            // no option
            }
    }//GEN-LAST:event_formWindowClosing

    private void bttn_statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_statsActionPerformed
        // TODO add your handling code here:
        manager.openEstadistica();
        this.dispose();
    }//GEN-LAST:event_bttn_statsActionPerformed

    private void bttn_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_logActionPerformed
        // TODO add your handling code here:
        manager.openLogin();
        this.dispose();
    }//GEN-LAST:event_bttn_logActionPerformed

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
            java.util.logging.Logger.getLogger(NN_mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NN_mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NN_mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NN_mainmenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NN_mainmenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Line;
    private javax.swing.JLabel TAB;
    private javax.swing.JLabel T_username;
    private javax.swing.JLabel background;
    private javax.swing.JButton bttn_cita;
    private javax.swing.JButton bttn_clientes;
    private javax.swing.JButton bttn_empleado;
    private javax.swing.JButton bttn_inventario;
    private javax.swing.JButton bttn_log;
    private javax.swing.JButton bttn_pago;
    private javax.swing.JButton bttn_salir;
    private javax.swing.JButton bttn_stats;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel t_message;
    private javax.swing.JTable table_cita;
    // End of variables declaration//GEN-END:variables
}
