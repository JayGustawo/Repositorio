/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NN_jframes;
    
import BusinessObjects.Employee;

/**
 *
 * @author Diego Armando
 */
public class NN_JframeAdmin {
    
    public Employee loggeduser;
    public NN_mainmenu managerMainMenu;
    public NN_login managerLogin;
    public NN_citaMain managerCitaMain;
    public NN_clienteMain managerClienteMain;
    public NN_empleadoMain managerEmpleadoMain;
    public NN_agregarCliente managerAgregarCliente;
    public NN_ClienteVerificacion managerClienteVer;
    public NN_editarCliente managerEditarCliente;
    public NN_agregarEmpleado managerAgregarEmpleado;
    public NN_EmpleadoVerificacion managerEmpleadoVer;
    public NN_EmpleadoAdminVer managerEmpleadoAdminVer;
    public NN_editarEmpleado managerEditarEmpleado;
    public NN_agregarCita managerAgregarCita;
    public NN_clienteMainAppointment managerAgregarCitaCliente;
    public NN_editarClienteAppointment managerAgregarCitaEditCliente;
    public NN_agregarClienteAppointment managerAgregarCitaAgregarCliente;
    public NN_agregarCitaClienteExiste managerAgregarCitaClienteExiste;
    public NN_CitaVerificacion managerCitaVer;
    public NN_EditarCita managerCitaEdit;
    public NN_pagoMain managerPagoMain;
    public NN_agregarPago managerAgregarPago;
    public NN_agregarClientePago managerAgregarPagoAgregarCliente;
    public NN_clienteMainPago managerAgregarPagoCliente;
    public NN_editarClientePago managerAgregarPagoEditCliente;
    public NN_EditarPago managerPagoEdit;
    public NN_agregarPagoClienteExiste managerAgregarPagoClienteExiste;
    public NN_PagoVerificacion managerPagoVer;
    //public NN_ExitVer managerExitVer;
    //public NN_DiscardVer managerDiscardVer;
    public NN_InventarioMain managerInventoryMain;
    public NN_agregarProducto managerAgregarProducto;
    public NN_ProductoVerificacion managerProductoVer;
    public NN_editarProducto managerProductoEdit;
    public NN_Estadistica managerEstadistica;
    public NN_PagoAdminVer managerPagoAdminVer;
    
    public void openPayAdminVer(){
        managerPagoAdminVer = new NN_PagoAdminVer();
        managerPagoAdminVer.setVisible(true);
    }
    
    public void openEstadistica(){
        managerEstadistica = new NN_Estadistica();
        managerEstadistica.setVisible(true);
    }
    
    public void openProductoEdit(){
        managerProductoEdit = new NN_editarProducto();
        managerProductoEdit.setVisible(true);
    }
    
    public void openProductoVer(){
        managerProductoVer = new NN_ProductoVerificacion();
        managerProductoVer.setVisible(true);
    }
    
    public void openAddProduct(){
        managerAgregarProducto = new NN_agregarProducto();
        managerAgregarProducto.setVisible(true);
    }
    
    public void openInventoryMain(){
        managerInventoryMain = new NN_InventarioMain();
        managerInventoryMain.setVisible(true);
    }
    
    /*public void openDiscardVer(){
        managerDiscardVer = new NN_DiscardVer();
        managerDiscardVer.setVisible(true);
    }*/
    
    /*public void openExitVer(){
        managerExitVer = new NN_ExitVer();
        managerExitVer.setVisible(true);
    }*/
    
    public void openPagoVer(){
        managerPagoVer = new NN_PagoVerificacion();
        managerPagoVer.setVisible(true);
    }
    
    public void openAddPayClientExist(){
        managerAgregarPagoClienteExiste = new NN_agregarPagoClienteExiste();
        managerAgregarPagoClienteExiste.setVisible(true);
    }
    
    public void openPagoEdit(){
        managerPagoEdit = new NN_EditarPago();
        managerPagoEdit.setVisible(true);
    }
    
    public void openEditPayClient(){
        managerAgregarPagoEditCliente = new NN_editarClientePago();
        managerAgregarPagoEditCliente.setVisible(true);
    }
    
    public void openAddPayClient(){
        managerAgregarPagoCliente = new NN_clienteMainPago();
        managerAgregarPagoCliente.setVisible(true);
    }    
    public void openAddPayAddClient(){
        managerAgregarPagoAgregarCliente = new NN_agregarClientePago();
        managerAgregarPagoAgregarCliente.setVisible(true);
    }
    
    public void openAddPay(){
        managerAgregarPago = new NN_agregarPago();
        managerAgregarPago.setVisible(true);
    }
    
    public void openPagoMain(){
        managerPagoMain = new NN_pagoMain();
        managerPagoMain.setVisible(true);
    }
    public void openCitaEdit(){
        managerCitaEdit = new NN_EditarCita();
        managerCitaEdit.setVisible(true);
    }
    
    public void openCitaVer(){
        managerCitaVer = new NN_CitaVerificacion();
        managerCitaVer.setVisible(true);
    }
    
    public void openAddAppClientExist(){
        managerAgregarCitaClienteExiste = new NN_agregarCitaClienteExiste();
        managerAgregarCitaClienteExiste.setVisible(true);
    }
        
    public void openAddAppAddClient(){
        managerAgregarCitaAgregarCliente = new NN_agregarClienteAppointment();
        managerAgregarCitaAgregarCliente.setVisible(true);
    }
    
    public void openEditAppClient(){
        managerAgregarCitaEditCliente = new NN_editarClienteAppointment();
        managerAgregarCitaEditCliente.setVisible(true);
    }
    
    public void openAddAppClient(){
        managerAgregarCitaCliente = new NN_clienteMainAppointment();
        managerAgregarCitaCliente.setVisible(true);
    }
    public void openAddAppointment(){
        managerAgregarCita = new NN_agregarCita();
        managerAgregarCita.setVisible(true);
    }
    
    public void openMainMenu(){
        managerMainMenu = new NN_mainmenu();
        managerMainMenu.setVisible(true);
    }
    
    public void openLogin(){
        managerLogin = new NN_login();
        managerLogin.setVisible(true);
    }
    
    public void openCita(){
        managerCitaMain = new NN_citaMain();
        managerCitaMain.setVisible(true);
    }
    
    public void openCliente(){
        managerClienteMain = new NN_clienteMain();
        managerClienteMain.setVisible(true);
    }

    public void openEmpleado(){
        managerEmpleadoMain = new NN_empleadoMain();
        managerEmpleadoMain.setVisible(true);
    }

    public void openAddClient(){
        managerAgregarCliente = new NN_agregarCliente();
        managerAgregarCliente.setVisible(true);
    }
    
    public void openCleintVer(){
        managerClienteVer = new NN_ClienteVerificacion();
        managerClienteVer.setVisible(true);
    }
    
    public void openClienteedit(){
        managerEditarCliente = new NN_editarCliente();
        managerEditarCliente.setVisible(true);
    }
    
    public void openAddEmpleado(){
        managerAgregarEmpleado = new NN_agregarEmpleado();
        managerAgregarEmpleado.setVisible(true);
    }
    
    public void openEmpleadoVer(){
        managerEmpleadoVer = new NN_EmpleadoVerificacion();
        managerEmpleadoVer.setVisible(true);
    }
    
    public void openEmpleadoEdit(){
        managerEditarEmpleado = new NN_editarEmpleado();
        managerEditarEmpleado.setVisible(true);
    }
    
    public void openEmpleadoAdminVer(){
        managerEmpleadoAdminVer = new NN_EmpleadoAdminVer();
        managerEmpleadoAdminVer.setVisible(true);
    }
    
    public Employee getLoggeduser() {
        return loggeduser;
    }
  
    public void setLoggeduser(Employee loggeduser) {
        this.loggeduser = loggeduser;
    }
    
}
