
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class BillingDB {
    
    public static void insertIntoBillingDB(String username, int price, String date){
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/billingDB.db");
         
            PreparedStatement ps = con.prepareStatement("INSERT INTO billing(uname, bill, date) VALUES(?,?,?)");
            
            ps.setString(1, username);
            ps.setInt(2, price);
            ps.setString(3, date);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public static ArrayList<BillObject> billlings(){
        ArrayList<BillObject> customers = new ArrayList<>();
      
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/billingDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id, uname, bill, date FROM billing");
            
            BillObject pl;
            
            while(rs.next()){
                pl = new BillObject(rs.getInt("id"),rs.getString("uname"),
                        rs.getInt("bill"),rs.getString("date"));
                customers.add(pl);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    public static void deleteBillings(){
         try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/billingDB.db");
            PreparedStatement ps = con.prepareStatement("DELETE FROM billing");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "All entries have been deleted!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
