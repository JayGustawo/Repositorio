/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import BusinessObjects.Appointment;
import BusinessObjects.Client;
import BusinessObjects.Employee;
import BusinessObjects.Payment;
import BusinessObjects.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego Armando
 */
public class DatabaseManager {

    Connection conn = null;

    public DatabaseManager() { //Realiza la conexion con la base de datos
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        try {

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "proyectouser", "proyecto");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output consolez");
            e.printStackTrace();
            return;

        }
    }
    
    public void openDB() { //Realiza la conexion con la base de datos
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        try {

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "proyectouser", "proyecto");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output consolez");
            e.printStackTrace();
            return;

        }
    }
    
    public String CheckAppointmentHour(Appointment appmnt){
        String hora ="libre";
        String adminStatement = "SELECT ID,ID_C,ID_E,DAY,MONTH,YEAR,HOUR,COST,REASON,HOURVALUE FROM APPOINTMENT WHERE DAY = ? AND MONTH = ? AND YEAR = ? AND HOURVALUE BETWEEN ? AND ?";
        double min = appmnt.getHourValue() - 0.3;
        double max = appmnt.getHourValue() + 0.3;
        if(min < 0){
            min = 0;
        }
        if(max > 24.59){
            max = 24.59;
        }
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setInt(1,appmnt.getDay());
            preparedStatement.setInt(2,appmnt.getMonth());
            preparedStatement.setInt(3,appmnt.getYear());
            preparedStatement.setDouble(4, min);
            preparedStatement.setDouble(5, max);
            rs = preparedStatement.executeQuery();
            
            while(rs.next() ){
                hora = rs.getString("HOUR");
            } 
            try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(SQLException sqlex ){
            System.out.println(sqlex.getMessage());   
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        
        return hora;
    }
    
    public ResultSet getAppointmentByYearGrouped(int year){
        String adminStatement = "SELECT ID_C,COUNT(*) FROM APPOINTMENT WHERE YEAR = ? GROUP BY ID_C ORDER BY ID_C ASC";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setInt(1,year);
            rs = preparedStatement.executeQuery();
            //preparedStatement.close();
        }catch(SQLException sqlex ){
            System.out.println(sqlex.getMessage());   
        }
        return rs;
    }
    
    public ResultSet getAppointmentByYear(int year){
        String adminStatement = "SELECT ID,ID_C,ID_E,DAY,MONTH,YEAR,HOUR,COST,REASON,HOURVALUE FROM APPOINTMENT WHERE YEAR = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setInt(1,year);
            rs = preparedStatement.executeQuery();
        }catch(SQLException sqlex ){
            System.out.println(sqlex.getMessage());   
        }/*finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }*/
        return rs;
    }
        
    public ResultSet getPaymentByYear(int year){
        String adminStatement = "SELECT ID,ID_A,AMOUNT,DAY,MONTH,YEAR,ID_E FROM PAYMENT WHERE YEAR = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setInt(1,year);
            rs = preparedStatement.executeQuery();
        }catch(SQLException sqlex ){
            System.out.println(sqlex.getMessage());   
        }/*finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
        }*/
        return rs;
    }
    
    public int CheckAppointmentByTime (int newDay, int newMonth, int newYear, double newHValue){
        int count = 0;
        String selectStatement = "Select ID, ID_C, ID_E,DAY,MONTH,YEAR,HOUR,COST,REASON,HOURVALUE from APPOINTMENT where DAY = ? AND MONTH = ?  AND YEAR = ? AND HOURVALUE = ? order by ID";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(selectStatement);
            preparedStatement.setInt(1, newDay);
            preparedStatement.setInt(2, newMonth);
            preparedStatement.setInt(3, newYear);
            preparedStatement.setDouble(4, newHValue);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                count++;
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return count;
    }
    
    public Appointment getAppointmentById(int id) { 
        String selectStatement = "Select ID, ID_C, ID_E, DAY, MONTH, YEAR, HOUR, COST, REASON, HOURVALUE from APPOINTMENT where ID = ? order by ID";
        PreparedStatement preparedStatement = null;
        Appointment appointment = null;
        try {
            preparedStatement = conn.prepareStatement(selectStatement);
            preparedStatement.setInt(1, id);
            
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Appointment appmnt = new Appointment();
                appmnt.setId(rs.getInt("ID"));
                appmnt.setId_c(rs.getInt("ID_C"));
                appmnt.setId_e(rs.getInt("ID_E"));
                appmnt.setDay(rs.getInt("DAY"));
                appmnt.setMonth(rs.getInt("MONTH"));
                appmnt.setYear(rs.getInt("YEAR"));
                appmnt.setHour(rs.getString("HOUR"));
                appmnt.setCost(rs.getFloat("COST"));
                appmnt.setReason(rs.getString("REASON"));
                appmnt.setHourValue(rs.getDouble("HOURVALUE"));
                appointment=appmnt;
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        return appointment;
    }
    
    public int getMax(String tableName){
        int maxVal = 0;
        String maxStatement = "Select VALUE from MAX where ID_NAME = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(maxStatement);
            preparedStatement.setString(1, tableName);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                maxVal = rs.getInt("VALUE");
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally{
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
            } catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
        }
        return maxVal;
    }
    
    public int getMaxAppointment(){
        String maxStatement = "SELECT MAX(ID) AS ID FROM APPOINTMENT";
        PreparedStatement preparedStatement = null;
        int maxid = 68;
        try{
            preparedStatement = conn.prepareStatement(maxStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                maxid = rs.getInt("ID");
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return maxid;
    }
    
    public void updatePayment(Payment pay) { //Actualiza la cita con el ID correspondiente de la base de datos.
        String updateStatement = "UPDATE PAYMENT SET ID_A = ?, AMOUNT = ?, DAY =?, MONTH =?, YEAR =? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(updateStatement);
            preparedStatement.setInt(1,pay.getId_a());
            preparedStatement.setFloat(2,pay.getAmount());
            preparedStatement.setInt(3,pay.getDay());
            preparedStatement.setInt(4,pay.getMonth());
            preparedStatement.setInt(5, pay.getYear());
            preparedStatement.setInt(6,pay.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }

    }
    
    public Payment getPaymentById(int id) { 
        String selectStatement = "Select ID, ID_A, ID_E, AMOUNT, DAY, MONTH, YEAR from PAYMENT where ID = ? order by ID";
        PreparedStatement preparedStatement = null;
        Payment payment = null;
        try {
            preparedStatement = conn.prepareStatement(selectStatement);
            preparedStatement.setInt(1, id);
            
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Payment pay = new Payment();
                pay.setId(rs.getInt("ID"));
                pay.setId_a(rs.getInt("ID_A"));
                pay.setId_e(rs.getInt("ID_E"));
                pay.setAmount(rs.getInt("AMOUNT"));
                pay.setDay(rs.getInt("DAY"));
                pay.setMonth(rs.getInt("MONTH"));
                pay.setYear(rs.getInt("YEAR"));
                payment = pay;
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        return payment;
    }
    
    public boolean removePaymentById(int oldId) {//Actualiza la base de datos removiendo las citas eliminadas.
        boolean succ = true;
        String deleteStatement = "Delete From PAYMENT Where ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, oldId);
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            succ = false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                
            }

        }
        return succ;
    }
    
    public void addPayment(Payment newPayment) { //Actualiza la base de datos con la nueva cita agregada.
        String insertStatement = "insert into PAYMENT(ID,ID_A,ID_E,AMOUNT,DAY,MONTH,YEAR) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(insertStatement);
            preparedStatement.setInt(1, newPayment.getId());
            preparedStatement.setInt(2,newPayment.getId_a());
            preparedStatement.setInt(3,newPayment.getId_e());
            preparedStatement.setFloat(4,newPayment.getAmount());
            preparedStatement.setInt(5,newPayment.getDay());
            preparedStatement.setInt(6,newPayment.getMonth());
            preparedStatement.setInt(7,newPayment.getYear());
            // execute select SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        DatabaseManager db = new DatabaseManager();
        db.IncreaseMax("PAYMENT");
    }
    
    public Product getProductById(int id) { 
        String selectStatement = "Select ID, NAME, QUANTITY, SUPPLIER, COST, UNIT from PRODUCT where ID = ? order by ID";
        PreparedStatement preparedStatement = null;
        Product pro = null;
        try {
            preparedStatement = conn.prepareStatement(selectStatement);
            preparedStatement.setInt(1, id);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("NAME"));
                product.setQuantity(rs.getFloat("QUANTITY"));
                product.setSupplier(rs.getString("SUPPLIER"));
                product.setCost(rs.getFloat("COST"));
                product.setUnit(rs.getString("UNIT"));
                pro = product;
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        return pro;
    }
    
    public boolean removeProductById(int oldId) {//Actualiza la base de datos removiendo las citas eliminadas.
        boolean suc = true;
        String deleteStatement = "Delete From PRODUCT Where ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, oldId);
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            suc = false;

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        return suc;
    }
    
    public void updateProduct(Product product) { //Actualiza la cita con el ID correspondiente de la base de datos.
        String updateStatement = "UPDATE product SET NAME = ?, QUANTITY = ?, SUPPLIER = ?, COST = ?, UNIT = ? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(updateStatement);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setFloat(2,product.getQuantity());
            preparedStatement.setString(3,product.getSupplier());
            preparedStatement.setFloat(4,product.getCost());
            preparedStatement.setString(5,product.getUnit());
            preparedStatement.setInt(6, product.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
    }
        
    public void updateProductQty(Product product) { //Actualiza la cita con el ID correspondiente de la base de datos.
        String updateStatement = "UPDATE product SET QUANTITY = ? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(updateStatement);
            preparedStatement.setFloat(1,product.getQuantity());
            preparedStatement.setInt(2,product.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
    }
    
    public void addProduct(Product newProduct) { //Actualiza la base de datos con la nueva cita agregada.
        String insertStatement = "insert into PRODUCT(ID,NAME,QUANTITY,SUPPLIER,COST,UNIT) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(insertStatement);
            preparedStatement.setInt(1, newProduct.getId());
            preparedStatement.setString(2,newProduct.getName());
            preparedStatement.setFloat(3,newProduct.getQuantity());
            preparedStatement.setString(4,newProduct.getSupplier());
            preparedStatement.setFloat(5,newProduct.getCost());
            preparedStatement.setString(6,newProduct.getUnit());
            // execute select SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        DatabaseManager db = new DatabaseManager();
        db.IncreaseMax("PRODUCT");
    }
    
    public void addAppointment(Appointment newAppointment) { //Actualiza la base de datos con la nueva cita agregada.
        String insertStatement = "insert into appointment(ID,ID_C,ID_E,DAY,MONTH,YEAR,HOUR,COST,REASON,HOURVALUE) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(insertStatement);
            preparedStatement.setInt(1, newAppointment.getId());
            preparedStatement.setInt(2,newAppointment.getId_c());
            preparedStatement.setInt(3, newAppointment.getId_e());
            preparedStatement.setInt(4,newAppointment.getDay());
            preparedStatement.setInt(5,newAppointment.getMonth());
            preparedStatement.setInt(6,newAppointment.getYear());
            preparedStatement.setString(7,newAppointment.getHour());
            preparedStatement.setFloat(8,newAppointment.getCost());
            preparedStatement.setString(9,newAppointment.getReason());
            preparedStatement.setDouble(10, newAppointment.getHourValue());
            // execute select SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        DatabaseManager db = new DatabaseManager();
        db.IncreaseMax("APPOINTMENT");
    }

    public void removeAppointmentById(int oldId) {//Actualiza la base de datos removiendo las citas eliminadas.
        String deleteStatement = "Delete From APPOINTMENT Where ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, oldId);
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
    }

        public void RemoveAll(Appointment oldAppmnt) {
        String deleteStatement = "Delete From APPOINTMENT";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, oldAppmnt.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
    }
        
    public void IncreaseMax (String name){
        DatabaseManager db = new DatabaseManager();
        int newMax = db.getMax(name);
        newMax++;
        String IncreaseStatement = "UPDATE max SET VALUE = ? WHERE ID_NAME = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(IncreaseStatement);
            preparedStatement.setInt(1,newMax);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }  
    }
    
    public void updateAppointment(Appointment appmnt) { //Actualiza la cita con el ID correspondiente de la base de datos.
        String updateStatement = "UPDATE appointment SET ID_C = ?, ID_E = ?,DAY =?,MONTH =?,YEAR =? ,HOUR = ?,COST = ?,REASON = ?,HOURVALUE = ? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(updateStatement);
            preparedStatement.setInt(1,appmnt.getId_c());
            preparedStatement.setInt(2,appmnt.getId_e());
            preparedStatement.setInt(3,appmnt.getDay());
            preparedStatement.setInt(4,appmnt.getMonth());
            preparedStatement.setInt(5,appmnt.getYear());
            preparedStatement.setString(6, appmnt.getHour());
            preparedStatement.setFloat(7,appmnt.getCost());
            preparedStatement.setString(8, appmnt.getReason());
            preparedStatement.setDouble(9,appmnt.getHourValue());
            preparedStatement.setInt(10, appmnt.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
    }
    
    
     public int getMaxClient(){
        String maxStatement = "SELECT MAX(ID) AS ID FROM CLIENT";
        PreparedStatement preparedStatement = null;
        int maxid = 68;
        try{
            preparedStatement = conn.prepareStatement(maxStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next()){
                maxid = rs.getInt("ID");
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return maxid;
    }
    
    
    public int getMaxEmployee(){
        String maxStatement = "SELECT MAX(ID) AS ID FROM EMPLOYEE";
        PreparedStatement preparedStatement = null;
        int maxid = 68;
        try{
            preparedStatement = conn.prepareStatement(maxStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                maxid = rs.getInt("ID");
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return maxid;
    }
    
    public Employee getEmployeeById(int EmployeeID){
        Employee selEmploy = new Employee();
        String adminStatement = "SELECT ID,FIRSTNAME,LASTNAME,ADDRESS,PASS,USERNAME,PHONE,ROLE FROM EMPLOYEE WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setInt(1,EmployeeID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                selEmploy.setId(rs.getInt("ID"));
                selEmploy.setFirstName(rs.getString("FIRSTNAME"));
                selEmploy.setLastName(rs.getString("LASTNAME"));
                selEmploy.setAddress(rs.getString("ADDRESS"));
                selEmploy.setPass(rs.getString("PASS"));
                selEmploy.setUserName(rs.getString("USERNAME"));
                selEmploy.setPhone(rs.getLong("PHONE"));
                selEmploy.setRole(rs.getString("ROLE"));
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(SQLException sqlex ){
            System.out.println(sqlex.getMessage());   
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        //PENDIENTE COMPA//
        return selEmploy;
    }
    
        public boolean editClient(Client changeClient){
        boolean success=true;
        String updateStatement = " UPDATE CLIENT SET FIRSTNAME = ?,LASTNAME =?,PHONE =? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(updateStatement);
            preparedStatement.setString(1,changeClient.getFirstName());
            preparedStatement.setString(2,changeClient.getLastName());
            preparedStatement.setLong(3,changeClient.getPhone());
            preparedStatement.setInt(4,changeClient.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            success = false;
            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                success = false;
                System.out.println(sqlex.getMessage());

            }

        }
        return success;
    }
        
    public boolean editEmployee(Employee changeEmploy){
        boolean success=true;
        String updateStatement = " UPDATE EMPLOYEE SET FIRSTNAME = ?,LASTNAME = ?,ADDRESS = ?,PASS = ?,USERNAME = ?,PHONE = ?,ROLE = ? WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(updateStatement);
            preparedStatement.setString(1,changeEmploy.getFirstName());
            preparedStatement.setString(2,changeEmploy.getLastName());
            preparedStatement.setString(3,changeEmploy.getAddress());
            preparedStatement.setString(4,changeEmploy.getPass());
            preparedStatement.setString(5, changeEmploy.getUserName());
            preparedStatement.setLong(6, changeEmploy.getPhone());
            preparedStatement.setString(7, changeEmploy.getRole());
            preparedStatement.setInt(8,changeEmploy.getId());
            // execute select SQL stetement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            success = false;
            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                success = false;
                System.out.println(sqlex.getMessage());

            }

        }
        return success;
    }
    

    
    public boolean addEmployee(Employee newEmployee) { //Actualiza la base de datos con la nueva cita agregada.
        boolean success = true;
        String insertStatement = "insert into EMPLOYEE(ID,FIRSTNAME,LASTNAME,ADDRESS,PASS,USERNAME,PHONE,ROLE) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(insertStatement);
            preparedStatement.setInt(1, newEmployee.getId());
            preparedStatement.setString(2, newEmployee.getFirstName());
            preparedStatement.setString(3, newEmployee.getLastName());
            preparedStatement.setString(4,newEmployee.getAddress());
            preparedStatement.setString(5, newEmployee.getPass());
            preparedStatement.setString(6, newEmployee.getUserName());
            preparedStatement.setLong(7, newEmployee.getPhone());
            preparedStatement.setString(8,newEmployee.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                success = false;
            }
        }
        DatabaseManager db = new DatabaseManager();
        db.IncreaseMax("EMPLOYEE");
        return success;
    }
    
    public boolean removeAppointment(int idDelete) {//Actualiza la base de datos removiendo las citas eliminadas.
        boolean success=true;
        String deleteStatement = "Delete From APPOINTMENT Where ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, idDelete);
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            success=false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                success=false;
            }
        }
        return success;
    }
    
    public boolean removeEmployee(int idDelete) {//Actualiza la base de datos removiendo las citas eliminadas.
        boolean success=true;
        String deleteStatement = "Delete From EMPLOYEE Where ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, idDelete);
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            success=false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                success=false;
            }
        }
        return success;
    }
    
    public boolean addClient(Client newClient) { //Actualiza la base de datos con la nueva cita agregada.
        boolean success = true;
        String insertStatement = "insert into CLIENT(ID,FIRSTNAME,LASTNAME,PHONE) values (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(insertStatement);
            preparedStatement.setInt(1, newClient.getId());
            preparedStatement.setString(2, newClient.getFirstName());
            preparedStatement.setString(3, newClient.getLastName());
            preparedStatement.setLong(4, newClient.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                success = false;
            }
        }
        DatabaseManager db = new DatabaseManager();
        db.IncreaseMax("CLIENT");
        return success;
    }
        
    public boolean removeClient(int idDelete) {//Actualiza la base de datos removiendo las citas eliminadas.
        boolean success=true;
        String deleteStatement = "Delete From CLIENT Where ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(deleteStatement);
            preparedStatement.setInt(1, idDelete);
            // execute select SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            success=false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                success=false;
            }
        }
        return success;
    }
    
    
    public Client getClientbyName(String SFirstName, String SLastName){
        Client selClient = new Client();
        String adminStatement = "SELECT ID,FIRSTNAME,LASTNAME,PHONE FROM CLIENT WHERE FIRSTNAME = ? AND LASTNAME = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setString(1, SFirstName);
            preparedStatement.setString(2, SLastName);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                selClient.setId(rs.getInt("ID"));
                selClient.setFirstName(rs.getString("FIRSTNAME"));
                selClient.setLastName(rs.getString("LASTNAME"));
                selClient.setPhone(rs.getLong("PHONE"));
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(SQLException sqlex){
            System.out.println(sqlex.getMessage());
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return selClient;
    }
    
    public Client getClientebyId(int clientID){
        Client selClient = new Client();
        String adminStatement = "SELECT ID,FIRSTNAME,LASTNAME,PHONE FROM CLIENT WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setInt(1,clientID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                selClient.setId(rs.getInt("ID"));
                selClient.setFirstName(rs.getString("FIRSTNAME"));
                selClient.setLastName(rs.getString("LASTNAME"));
                selClient.setPhone(rs.getLong("PHONE"));
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }catch(SQLException sqlex ){
            System.out.println(sqlex.getMessage());   
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return selClient;
    }
    
    public boolean checkAdmin(String adminPass){
        boolean success=false;
        String adminStatement = "SELECT ID FROM EMPLOYEE WHERE PASS = ? AND ROLE='Admin'";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement(adminStatement);
            preparedStatement.setString(1,adminPass);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                success = true;
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e){
            success = false;
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
                success=false;
            }
        }
        return success;
    }
    
    public Employee getEmployeeByUsername(String username) { //Este metodo se encarga de obtener todos los datos que contiene la base de datos.
        Employee employ = new Employee();
        String passwordStatement = "SELECT ID,FIRSTNAME,LASTNAME,ADDRESS,PASS,USERNAME,PHONE,ROLE from EMPLOYEE WHERE USERNAME =?";
        PreparedStatement preparedStatement = null;
        Appointment appointment = null;
        try {
            preparedStatement = conn.prepareStatement(passwordStatement);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
             while (rs.next()) {
                employ.setId(rs.getInt("ID"));
                employ.setFirstName(rs.getString("FIRSTNAME"));
                employ.setLastName(rs.getString("LASTNAME"));
                employ.setAddress(rs.getString("ADDRESS"));
                employ.setPass(rs.getString("PASS"));
                employ.setUserName(rs.getString("USERNAME"));
                employ.setPhone(Long.valueOf(rs.getString("PHONE")));
                employ.setRole(rs.getString("ROLE"));
            }try{
                rs.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }
        }
        return employ;
    }
    
    /*public List<Appointment> loadSource() { //Este metodo se encarga de obtener todos los datos que contiene la base de datos.
        String selectStatement = "Select ID_appmnt,PATIENT_NAME,APPOINTMENTDATE,HOUR,REASON,PHONE from appointment order By ID_appmnt";
        PreparedStatement preparedStatement = null;
        List<Appointment> appointments = new ArrayList<Appointment>();
        try {
            preparedStatement = conn.prepareStatement(selectStatement);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Appointment appmnt = new Appointment();
                appmnt.setId(rs.getInt("ID_appmnt"));
                appmnt.setPatient_name(rs.getString("PATIENT_NAME"));
                appmnt.setHour(rs.getString("HOUR"));
                appmnt.setAppointmentdate(new java.util.Date(rs.getDate("APPOINTMENTDATE").getTime()));
                appmnt.setReason(rs.getString("REASON"));
                appmnt.setPhone(Long.valueOf(rs.getString("PHONE")));
                appointments.add(appmnt);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());

            }

        }
        return appointments;
    }*/
    
        

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }
    }

}
