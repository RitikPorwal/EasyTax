package gst;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Ritik
 */
public class UI {
    Connection con;
    public UI(Connection x){
        this.con=x;
    }    
    
    public void deleteAllRows(final DefaultTableModel model) {
        for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
            model.removeRow(i);
        }
    }

    public void fillcombo_services(Sql y,javax.swing.JComboBox x){
        ArrayList<String> names = new ArrayList<String>();
        names=y.combo_names("service");
        for(int i=0;i<names.size();i++){
            x.addItem(names.get(i));
        }
    }
    
    public void fill_service_table(Sql s,javax.swing.JTable x){
        
        DefaultTableModel model = (DefaultTableModel) x.getModel();
        deleteAllRows(model);
        List<obj> lstOfServices=s.getAllServices();
        for(obj service:lstOfServices){
            service.getId();
            service.getName();
            service.getCgst();
            service.getSgst();
            service.getIgst();
            model.addRow(new Object[]{service.getId(),service.getName(),service.getCgst(),service.getSgst(),service.getIgst()});
        }
    }
    
    public void fillcombo_location(Sql y,javax.swing.JComboBox x){
        ArrayList<String> names = new ArrayList<String>();
        names=y.combo_names("location");
        for(int i=0;i<names.size();i++){
            x.addItem(names.get(i));
        }
        
    }

    public void result_table(javax.swing.JTable x, float a,float b,float c,float d,float e){
        DefaultTableModel model = (DefaultTableModel) x.getModel();
        deleteAllRows(model);
        model.addRow(new Object[]{a,b,c,d,e});
    }

    public void rate_table(Sql gst_Sql, javax.swing.JTable x, int a, String b, float c, float d, float e,String f,String g){
        DefaultTableModel model = (DefaultTableModel) x.getModel();
        deleteAllRows(model);
        int q=gst_Sql.check_state(f,g);
        if(q==0){e=0;}
        else{c=0;d=0;}
        model.addRow(new Object[]{a,b,c,d,e});
    }

}
