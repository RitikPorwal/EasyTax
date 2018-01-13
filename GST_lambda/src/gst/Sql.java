package gst;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ritik
 */
public class Sql{
    Connection con;
    public Sql(Connection x){
        this.con=x;
    }

    public int check_state(String a,String b){
        
        try {
            Statement s = (Statement) con.createStatement();
            String query1="SELECT state FROM cities WHERE city='"+a+"';";
            ResultSet r1= s.executeQuery(query1);
            String state1=new String();
            String state2=new String();
            while(r1.next()) {
                state1 = (String)r1.getString("state");
            }
            String query2="SELECT state FROM cities WHERE city='"+b+"';";
            ResultSet r2= s.executeQuery(query2);
            while(r2.next()) {
                state2 = (String)r2.getString("state");
            }
            if(state1.equals(state2)){ return 0;}
            else{return 1;}
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 2;
    }
    
    public void truncate_table(String TableName){
        try{
            Connection con =gst.GST.init_connection();
            Statement s1 = (Statement) con.createStatement();
            String query1="TRUNCATE "+TableName+";";
            s1.executeUpdate(query1);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void Insert_in_Service(int id, String name, float cgst, float sgst, float igst){
        try {
            Statement s = (Statement) con.createStatement();
            String query="INSERT INTO services VALUES ("+ id +", '"+name+"', "+cgst+", "+sgst+", "+igst+");";
            s.executeUpdate(query);
        } 
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public List<obj> getAllServices(){
        List<obj> services = new ArrayList<obj>();
        try{
            String query="SELECT * FROM services;";
            Statement s = (Statement) con.createStatement();
            ResultSet r= s.executeQuery(query);
            
            obj service=null;
            
            while(r.next()) {
                service=new obj();
                service.setId(r.getString("id"));
                service.setName(r.getString("name"));
                service.setCgst(r.getString("cgst"));
                service.setSgst(r.getString("sgst"));
                service.setIgst(r.getString("igst"));
                services.add(service);
            }
            return services;
        } 
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }     

    public ArrayList<String> combo_names(String type){
        String table_name,column;
        if(type.equals("service")){
            table_name="services";
            column="name";
        }
        else{
            table_name="cities";
            column="city";
        }
        ArrayList<String> values = new ArrayList<String>();
        int i=0;
        try{
            String query="SELECT * FROM "+table_name+";";
            Statement s = (Statement) con.createStatement();
            ResultSet r= s.executeQuery(query);
            while(r.next()){
                String name=r.getString(column);
                values.add(name);
                
            }
            return values;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
    return null;
    }
}