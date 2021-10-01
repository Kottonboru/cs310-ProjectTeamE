
package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;
import java.sql.Connection;



public class TASDatabase {
    
    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSet resultset = null;
    ResultSetMetaData metadata = null;
    
    boolean hasresults;
    int columnCount = 0;
    
    public TASDatabase(){
        
        try{

            /* Identify the Server */
        
            String server = ("jdbc:mysql://localhost/tas_fa21_v1");
            String username = "tasuser";
            String password = "teame";
            System.out.println("Connecting to " + server + "...");

            /* Load the MySQL JDBC Driver */
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            /* Open Connection */

            conn = DriverManager.getConnection(server, username, password);
        
            /* Test Connection */

            if(conn.isValid(0)){
            System.out.println("Connection Successful");
            }
    
            
        }
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
        // Close Database Objects
        
        finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null;} catch (Exception e ) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null;} catch (Exception e ) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null;} catch (Exception e ) {} }
            
        }
    }
    
    public Badge getBadge(String badgeid){
        try{
            
            pstSelect = conn.prepareStatement("SELECT * FROM badge where id = ?");
            
            pstSelect.setString(1, badgeid);
            
            pstSelect.execute();
            resultset = pstSelect.getResultSet();
            resultset.first();
            
            
            //Results
            String idNum = resultset.getString(1);
            String name = resultset.getString(2);
            
            Badge b = new Badge(idNum, name);
            
            return b;
        }
        
        catch(Exception e){
            System.err.println("** getBadge: " + e.toString());
        }
        
        return null;
    }
    
    
}
