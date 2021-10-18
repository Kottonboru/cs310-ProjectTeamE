package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;
import java.sql.Connection;
import java.time.*;



public class TASDatabase {
    
    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSetMetaData metadata = null;
    
    boolean hasresults;
    int columnCount = 0;
    
    public TASDatabase(){
        
        try{

            /* Identify the Server */
        
            String server = ("jdbc:mysql://localhost/tas_fa21_v1?serverTimezone=America/Chicago");
            String username = "tasuser";
            String password = "teame";
            System.out.println("Connecting to " + server + "...");

            /* Load the MySQL JDBC Driver */
        
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
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
        
    }
    
    public Badge getBadge(String badgeid){
        
        Badge b = null;
        
        try{
            
            pstSelect = conn.prepareStatement("SELECT * FROM badge where id = ?");
            
            pstSelect.setString(1, badgeid);
            
            boolean hasresults = pstSelect.execute();
            
            if (hasresults) {
            
                ResultSet resultset = pstSelect.getResultSet();
                resultset.first();

                String id = resultset.getString("id");
                String description = resultset.getString("description");

                b = new Badge(id, description);

                
            }

        }
        
        catch(Exception e){
            System.err.println("** getBadge: " + e.toString());
        }
        
       return b;
        
    }
    
    public Punch getPunch(int punchid) {
        
        Punch p = null;
        
        try {
            pstSelect = conn.prepareStatement("select * from punch where id=?");
            pstSelect.setInt(1, punchid);
            
            boolean hasresult = pstSelect.execute();
            
            if (hasresult) {
                
                System.err.println("Getting punch data ...");

                ResultSet resultset = pstSelect.getResultSet();
                resultset.first();
                
                int id = resultset.getInt("id");
                int terminalid = resultset.getInt("terminalid");
                String badgeid = resultset.getString("badgeid");
                Badge badge = getBadge(badgeid);
                
                java.time.LocalDateTime originaltimestamp = resultset.getTimestamp("originaltimestamp").toLocalDateTime();

                PunchType punchtype = PunchType.values()[resultset.getInt("punchtypeid")];

                //Punch(int id, int terminalid, Badge badge, int punchtypeid, Timestamp originaltimestamp
                p = new Punch(id, terminalid, badge, punchtype, originaltimestamp);
                
            }
            
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return p;
    }
    
    
    public Shift getShift(int shiftId) {
        
        Shift s= null; 
        
        try {

            pstSelect = conn.prepareStatement("select * from employee where id=?");
            
            pstSelect.setInt(1, shiftId);
            
            boolean hasresult = pstSelect.execute();
             
             if(hasresult) {
                 
            ResultSet resultset = pstSelect.getResultSet();
            resultset.first();
            
            //Results
            String description = resultset.getString("description");
                        LocalTime shiftStart = LocalTime.parse(resultset.getString("tart"));
                        LocalTime shiftStop = LocalTime.parse(resultset.getString("stop"));
                        int interval = resultset.getInt("interval");
                        int gracePeriod = resultset.getInt("graceperiod");
                        int dock = resultset.getInt("dock");
                        LocalTime lunchStart = LocalTime.parse(resultset.getString("lunchstart"));
                        LocalTime lunchStop = LocalTime.parse(resultset.getString("lunchstop"));
                        int lunchDeduct = resultset.getInt("lunchdeduct");
                        int lunchDuration = resultset.getInt("lunchduration");
                        int shiftDuration = resultset.getInt("shiftduration");
                        
                       s = new Shift( shiftId, description, shiftStart, shiftStop, interval, 
                               gracePeriod, dock, lunchStart, lunchStop, lunchDeduct, lunchDuration, shiftDuration);

            }
        }
        
        catch(Exception e) {
            System.err.println("** getShift: " + e.toString());
        }
        return s;
    }
    
    public Shift getShift(Badge b) {
        
        Shift s = null;
        
        try {
            pstSelect = conn.prepareStatement("select * from employee where id=1");
            
            //pstSelect.setString(1, b);
            
             boolean hasresult = pstSelect.execute();
             
              if (hasresult) {
            ResultSet resultset = pstSelect.getResultSet();
            resultset.first();
            
            //Results
            String badge = resultset.getString(1);
            
            String badgeid = resultset.getString("badgeid");
            
            b =new Badge (badgeid);
            
             
            //b = new Badge(idNum);
            //int badgeId = b.getId();
            //return ;
      
            }
              
        }
        
        catch(Exception e) {
            System.err.println("** getShift: " + e.toString());
        }
        
        return s;
        
    }
        
}