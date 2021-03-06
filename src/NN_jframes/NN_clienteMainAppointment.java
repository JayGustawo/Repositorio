/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NN_jframes;

import BusinessObjects.Client;
import Managers.DatabaseManager;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Armando
 */
public class NN_clienteMainAppointment extends javax.swing.JFrame {
    
    DatabaseManager db;
    boolean busy = false;
    public NN_JframeAdmin manager;
    public NN_agregarCita agregarMenu;
    public NN_EditarCita EditarCitaMenu;
    boolean fromEdit;
    int selectedId;
    /**
     * Creates new form NN_clienteMain
     */
    public NN_clienteMainAppointment() {
        initComponents();
        setMinimumSize(new Dimension(991,711).getSize());
        hidebttn();
        //KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
        manager = NN_MainClass.getManager();
        db = NN_MainClass.db;
        fromEdit = false;
        try{
            fromEdit=manager.managerCitaEdit.edit;
        } catch (Exception e){
            
        }
        //System.out.println("fromEdit="+fromEdit);
        if(fromEdit){
            EditarCitaMenu = manager.managerCitaEdit;
        }
        else{
            agregarMenu = manager.managerAgregarCita;
        }       
        DefaultTableModel amodel = (DefaultTableModel)table_clientes.getModel();
        amodel.setRowCount(0);
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "proyectouser", "proyecto");
            String colhead[]={"ID","Primer nombre","Apellidos","Telefono"};
            amodel.setColumnIdentifiers(colhead);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select ID,FIRSTNAME,LASTNAME,PHONE FROM CLIENT ORDER BY ID ASC");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols=rsmd.getColumnCount();
            while(rs.next()){
                Object[] obj=new Object[cols];
                for(int i=0; i<cols; i++){
                    obj[i]=rs.getObject(i+1);
                }
                amodel.addRow(obj);
            }
        }catch(Exception e){
        }
        table_clientes.setDefaultEditor(Object.class, null);
    }

    public void hidebttn(){
                
        B_seleccionar.setOpaque(false);
        B_seleccionar.setContentAreaFilled(false); //to make the content area transparent
        B_seleccionar.setBorderPainted(false); //to make the borders transparent
        
        B_editar.setOpaque(false);
        B_editar.setContentAreaFilled(false); //to make the content area transparent
        B_editar.setBorderPainted(false); //to make the borders transparent
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tab_text = new javax.swing.JLabel();
        TAB = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_clientes = new javax.swing.JTable();
        T_clienteseleccionado = new javax.swing.JLabel();
        B_editar = new javax.swing.JButton();
        TB_textSelect = new javax.swing.JTextField();
        T_message = new javax.swing.JLabel();
        B_seleccionar = new javax.swing.JButton();
        buttonE = new javax.swing.JLabel();
        buttonA = new javax.swing.JLabel();
        Line = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clientes");
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tab_text.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        Tab_text.setForeground(new java.awt.Color(255, 255, 255));
        Tab_text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tab_text.setText("Clientes");
        getContentPane().add(Tab_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 200, 20));

        TAB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Clientmain/clientMain_tab.png"))); // NOI18N
        getContentPane().add(TAB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(115, 214, 192), 3));

        table_clientes.setModel(new javax.swing.table.DefaultTableModel(
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
        table_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_clientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 68, 970, 500));

        T_clienteseleccionado.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        T_clienteseleccionado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T_clienteseleccionado.setText("id de cliente seleccionado");
        getContentPane().add(T_clienteseleccionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 200, 40));

        B_editar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        B_editar.setForeground(new java.awt.Color(255, 255, 255));
        B_editar.setText("Editar");
        B_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_editarActionPerformed(evt);
            }
        });
        getContentPane().add(B_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 645, 90, 30));

        TB_textSelect.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        TB_textSelect.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TB_textSelect.setText("-");
        getContentPane().add(TB_textSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 40, -1));

        T_message.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        T_message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(T_message, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 650, 281, 28));

        B_seleccionar.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N
        B_seleccionar.setForeground(new java.awt.Color(255, 255, 255));
        B_seleccionar.setText("Seleccionar");
        B_seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_seleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(B_seleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 645, 100, 30));

        buttonE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Clientmain/clientMain_button.png"))); // NOI18N
        getContentPane().add(buttonE, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 640, 90, 40));

        buttonA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Clientmain/clientMain_button.png"))); // NOI18N
        getContentPane().add(buttonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 90, 40));

        Line.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Clientmain/clientMain_line.png"))); // NOI18N
        getContentPane().add(Line, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 210, 20));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/NinaNails/Clientmain/clientMain_background.png"))); // NOI18N
        getContentPane().add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_clientesMouseClicked
        // TODO add your handling code here:
        table_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = table_clientes.rowAtPoint(evt.getPoint());
        int col = table_clientes.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            DefaultTableModel amodel = (DefaultTableModel)table_clientes.getModel();
            Object ido = amodel.getValueAt(row, 0);
            try{
                selectedId = Integer.parseInt(ido.toString());
            }catch(Exception e){
                selectedId = 0;
                JOptionPane.showMessageDialog(null, "Id de cliente invalido", "ID invalido", JOptionPane.INFORMATION_MESSAGE);
            }
            TB_textSelect.setText(ido.toString());
        }
    }
});
    }//GEN-LAST:event_table_clientesMouseClicked

    private void B_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_editarActionPerformed
        // TODO add your handling code here:
        boolean ok=true;
        try{
            selectedId = Integer.parseInt(TB_textSelect.getText());
        }catch(Exception e){
            ok = false;
            selectedId = 0;
            JOptionPane.showMessageDialog(null, "Id de cliente invalido", "ID invalido", JOptionPane.INFORMATION_MESSAGE);
        }
        if(ok){
            Client query = db.getClientebyId(selectedId);
            if(query.getId() == selectedId){
                manager.openEditAppClient();
                this.enable(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Id no existe", "ID invalido", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_B_editarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(fromEdit){
            EditarCitaMenu.setEnabled(true);
            fromEdit = false;
        }
        else{
           agregarMenu.setEnabled(true);
           fromEdit = false;
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void B_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_seleccionarActionPerformed
        // TODO add your handling code here:
        confirm(); 
    }//GEN-LAST:event_B_seleccionarActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        busy = false;
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
        busy = true;
    }//GEN-LAST:event_formWindowLostFocus

    public void confirm(){
         boolean ok=true;
        try{
            selectedId = Integer.parseInt(TB_textSelect.getText());
        }catch(Exception e){
            ok = false;
            selectedId = 0;
            JOptionPane.showMessageDialog(null, "Id de cliente invalido", "ID invalido", JOptionPane.INFORMATION_MESSAGE);
        }
       if(ok){
            Client query = db.getClientebyId(selectedId);
            if(query.getId()==selectedId){
                Client selectedClient = db.getClientebyId(selectedId);
                if(fromEdit){
                    if(EditarCitaMenu==null){       //ERASE
                        //System.out.println("MENU IS NULL");
                    }
                    if(selectedClient==null){
                        //System.out.println("CLIENT IS NULL");
                    }
                    EditarCitaMenu.updateAppointmentOldClientData(selectedClient);
                    EditarCitaMenu.enable(true);
                }
                else{
                    if(agregarMenu==null){       //ERASE
                        //System.out.println("MENU IS NULL");
                    }
                    if(selectedClient==null){
                        //System.out.println("CLIENT IS NULL");
                    }
                    agregarMenu.updateAppointmentOldClientData(selectedClient);
                    agregarMenu.enable(true);
                }
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Id no existe", "ID invalido", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    }
    
    public void refreshTable(){
        DefaultTableModel amodel = (DefaultTableModel)table_clientes.getModel();
        amodel.setRowCount(0);
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "proyectouser", "proyecto");
            String colhead[]={"ID","Primer nombre","Apellidos","Telefono"};
            amodel.setColumnIdentifiers(colhead);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select ID,FIRSTNAME,LASTNAME,PHONE FROM CLIENT ORDER BY ID ASC");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols=rsmd.getColumnCount();
            while(rs.next()){
                Object[] obj=new Object[cols];
                for(int i=0; i<cols; i++){
                    obj[i]=rs.getObject(i+1);
                }
                amodel.addRow(obj);
            }
        }catch(Exception e){
        }
        table_clientes.setDefaultEditor(Object.class, null);
    }
    
    public void display(String message){
        T_message.setText(message);
    }
    
    /*KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
        @Override
        public boolean dispatchKeyEvent(final KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_TYPED) {
            String eString = e.toString();
            if(eString.contains("Intro") && !busy){
                confirm();
            }
        }
        // Pass the KeyEvent to the next KeyEventDispatcher in the chain
            return false;
        }
    };*/
    
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
            java.util.logging.Logger.getLogger(NN_clienteMainAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NN_clienteMainAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NN_clienteMainAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NN_clienteMainAppointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NN_clienteMainAppointment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton B_editar;
    private javax.swing.JButton B_seleccionar;
    private javax.swing.JLabel Line;
    private javax.swing.JLabel TAB;
    private javax.swing.JTextField TB_textSelect;
    private javax.swing.JLabel T_clienteseleccionado;
    private javax.swing.JLabel T_message;
    private javax.swing.JLabel Tab_text;
    private javax.swing.JLabel buttonA;
    private javax.swing.JLabel buttonE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_clientes;
    // End of variables declaration//GEN-END:variables
}
