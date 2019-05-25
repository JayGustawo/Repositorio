/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

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
public interface AppointmentManager<X> { //Interfaz que reemplaza el manejador de archivos por este manejador de base de datos, utilizando sus metodos.
    
      public boolean addAppointment(Appointment newAppointment) ;

    public boolean removeAppointment(Appointment removedAppointment);

    public boolean updateAppointment(Appointment updatedAppointment);

    public void load() throws ParseException ;

    public List<Appointment> getAppointmentList();

    public void setAppointmentList(List<Appointment> appointmentList);

    public X getSourceManager();

    public void setSourceManager(X dbManager);
    
    public void close();
    
    public Appointment getAppointmenById(int id); 

    
}
