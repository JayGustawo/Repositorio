package Managers;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego Armando
 */
public class FileManager { //Clase que se encargaba del manejo de archivos en la entrega intermedia.
    
    private String fileName = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public  void appendfile(String content){
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        try{
            fw = new FileWriter(this.getFileName(), true);
            bw = new BufferedWriter(fw);
            bw.write(System.lineSeparator() + content);
                        
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bw != null){
                    bw.flush();
                    bw.close();
                }
                if(fw != null){
                    fw.close();
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public List<String> readfile() {
        BufferedReader br = null;
        FileReader fr = null;
        List<String> fileLines = new ArrayList<String>();
        try {
            fr = new FileReader(this.getFileName());
            br = new BufferedReader (fr);
            String sCurrentLine;
            while((sCurrentLine = br.readLine()) != null ){
                fileLines.add(sCurrentLine);
            }
            
            
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(br != null)
                    br.close();
                if(fr != null)
                    fr.close();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return fileLines;
    }
    
    public  void writeFile(List<String> content){
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        try{
            fw = new FileWriter(this.getFileName());
            bw = new BufferedWriter(fw);
            for(String line: content){
            	bw.write(line + System.lineSeparator());
            }
                        
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bw != null){
                    bw.flush();
                    bw.close();
                }
                if(fw != null){
                    fw.close();
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
