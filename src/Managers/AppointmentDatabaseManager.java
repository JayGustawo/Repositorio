package Managers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BusinessObjects.Appointment;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego Armando
 */
public class AppointmentDatabaseManager implements AppointmentManager<DatabaseManager>{

    List<Appointment> appointmentList = new ArrayList<Appointment>();//Se crea una lista la cual contendra los valores incluidos en nuestro formato de cita.
    DatabaseManager dbManager = new DatabaseManager();
    public int retrievedid;
    public String retrievedname;
    public String retrieveddate;
    public String retrievedhour;
    public String retrievedreason;
    public String retrievedphone;

    public boolean addAppointment(Appointment newAppointment) {//Este metodo se manda llamar cuando se desea agregar una cita y valida el contenida de esta.
        if (appointmentList.contains(newAppointment)) {
            return false;
        } else {
            dbManager.addAppointment(newAppointment);
            appointmentList.add(newAppointment);
            return true;
        }
    }

    public boolean removeAppointment(Appointment removedAppointment) {//Este metodo se manda llamar cuando se desea eliminar un cita de la base de datos, y se valida que el id ingresado sea valido, y este asociado a una cita existente
        if (appointmentList.contains(removedAppointment)) { 
            appointmentList.remove(removedAppointment);
            
            return true;
        } else {
            return false;
        }

    }


    
    public boolean updateAppointment(Appointment updatedAppointment) {//Este metodo se encarga de actualizar las citas que se encuentran en la base de datos
        
        if (appointmentList.contains(updatedAppointment)) {
            appointmentList.remove(updatedAppointment);
            appointmentList.add(updatedAppointment);
            dbManager.updateAppointment(updatedAppointment);
            return true;
        } else {
            return false;
        }

    }
    
    public void load() throws ParseException {  //Obtiene la informacion que contiene la base de datos.
        
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
    
    public Appointment getAppointmenById(int id) {
        return dbManager.getAppointmentById(id);
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public DatabaseManager getSourceManager() {
        return dbManager;
    }

    public void setSourceManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }
    
    public void close(){
        this.dbManager.closeConnection();
    }

}
