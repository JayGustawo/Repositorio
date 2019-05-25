/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NN_jframes;

import Managers.DatabaseManager;

/**
 *
 * @author Diego Armando
 */
public class NN_MainClass {
    
    static NN_JframeAdmin manager = new NN_JframeAdmin();
    static DatabaseManager db = new DatabaseManager();
    
    public static void main(String[] args) {
        manager.openLogin();
    }
    
    public static NN_JframeAdmin getManager(){
        return manager;
    }
    
    public static void refreshDB(){
        db.closeConnection();
        db.openDB();
    }
    
}
