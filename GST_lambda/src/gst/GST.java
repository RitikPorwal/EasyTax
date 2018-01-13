/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gst;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ritik
 */
public class GST {
    public static Connection init_connection(){
        try{
            Class.forName("java.sql.DriverManager");
            Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/gst","root","password");
            return con;
        }
        catch(Exception e) {
            System.out.println("Error");
        }
        return null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int amount,id;
        String source,destination;
        
        // TODO code application logic here
    }
    
}
