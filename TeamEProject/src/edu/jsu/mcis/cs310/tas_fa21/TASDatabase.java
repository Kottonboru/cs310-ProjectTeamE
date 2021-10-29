package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;
import java.sql.Connection;
import java.time.*;
import java.time.format.DateTimeFormatter;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.*;



public class TASDatabase {
    
    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSetMetaData metadata = null;
    
    private boolean hasresults;
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
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM-dd-yyyy HH:mm:ss");
        
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
                
                LocalDateTime originaltimestamp = resultset.getTimestamp("originaltimestamp").toLocalDateTime();

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
    
    
    public Shift getShift(int id) {
        
        Shift s = null; 
    

        
        try {
            pstSelect = conn.prepareStatement("SELECT * from shift where id = ?");
            pstSelect.setInt(1, id);
            
            boolean hasresult = pstSelect.execute();
             
            if(hasresult) {
                
                System.err.println("Getting shift data ...");
                 
                ResultSet resultset = pstSelect.getResultSet();
                
                resultset.first();

                //Results
                String description = resultset.getString("description");
                LocalTime shiftStart = LocalTime.parse(resultset.getString("start"));
                LocalTime shiftStop = LocalTime.parse(resultset.getString("stop"));
                int interval = resultset.getInt("interval");
                int gracePeriod = resultset.getInt("graceperiod");
                int dock = resultset.getInt("dock");
                LocalTime lunchStart = LocalTime.parse(resultset.getString("lunchstart"));
                LocalTime lunchStop = LocalTime.parse(resultset.getString("lunchstop"));
                int lunchDeduct = resultset.getInt("lunchdeduct");


                s = new Shift(id, description, shiftStart, shiftStop, interval, 
                              gracePeriod, dock, lunchStart, lunchStop, lunchDeduct);
            }
        }
        
        catch(Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    public Shift getShift(Badge b) {
        
        Shift s = null;
        
        try {
            pstSelect = conn.prepareStatement("SELECT employee.shiftid, shift.* FROM employee, shift WHERE employee.shiftid = shift.id AND employee.badgeid = ?");
            pstSelect.setString(1, b.getId());

            boolean hasresult = pstSelect.execute();
             
            if (hasresult) {
                
                ResultSet resultset = pstSelect.getResultSet();
                resultset.first();

                //Results
                int shiftid = resultset.getInt("shiftid");
                
                String description = resultset.getString("description");
                LocalTime shiftStart = LocalTime.parse(resultset.getString("start"));
                LocalTime shiftStop = LocalTime.parse(resultset.getString("stop"));
                int interval = resultset.getInt("interval");
                int gracePeriod = resultset.getInt("graceperiod");
                int dock = resultset.getInt("dock");
                LocalTime lunchStart = LocalTime.parse(resultset.getString("lunchstart"));
                LocalTime lunchStop = LocalTime.parse(resultset.getString("lunchstop"));
                int lunchDeduct = resultset.getInt("lunchdeduct");

                s = new Shift(shiftid, description, shiftStart, shiftStop, interval, 
                          gracePeriod, dock, lunchStart, lunchStop, lunchDeduct);
                        
                }
              
        }

        catch(Exception e) {
            System.err.println("** getShift: " + e.toString());
        }
        
        
        return s;
    }
    
    public int insertPunch(Punch p) {
        
        String query;
        int updateCount;

    // get punch data from punch object
        int results = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime originalTime = p.getOriginaltimestamp();
        //String otsString = originalTime.format(dtf);
        //System.err.println("New Punch Timestamp (from insertPunch(): " + otsString);
        String badgeid = p.getBadge().getId(); 
        int terminalid = p.getTerminalid(); 
        PunchType punchtypeid = p.getPunchtype();

        try {

            // Prepare Update Query

           query = "INSERT INTO punch (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)"; 

            pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstUpdate.setInt(1, terminalid);
            pstUpdate.setString(2, badgeid);
            pstUpdate.setString(3, dtf.format(originalTime));
            pstUpdate.setInt(4, punchtypeid.ordinal());

            // Execute Update Query
            updateCount = pstUpdate.executeUpdate();

            if(updateCount > 0){

                ResultSet resultset = pstUpdate.getGeneratedKeys(); 

                if (resultset.next()){
                    results = resultset.getInt(1);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.err.println("New Punch ID: " + results);
        return results;              
    }
        
        
    public ArrayList<Punch> getDailyPunchList(Badge badge, LocalDate date) {
        
        String query;
     
        String badgeid = badge.getId();
        ArrayList<Punch> alist = null;
        
        try {
               
            query =  "SELECT * FROM punch WHERE badgeid = ? AND DATE(originaltimestamp) = ?";
            pstSelect = conn.prepareStatement(query);
            pstSelect.setString(1, badgeid);
            pstSelect.setDate(2, java.sql.Date.valueOf(date));


            hasresults = pstSelect.execute();
            
            if(hasresults){
                
                alist = new ArrayList<>();
                
                ResultSet resultset = pstSelect.getResultSet();
                
                while (resultset.next()) {
                    
                    int id = resultset.getInt("id");
                    int terminalid = resultset.getInt("terminalid");
                    LocalDateTime originaltimestamp = resultset.getTimestamp("originaltimestamp").toLocalDateTime();
                    int punchtypeid = resultset.getInt("punchtypeid");
                    
                    Punch p = new Punch(id, terminalid, badge, PunchType.values()[punchtypeid] , originaltimestamp);
                
                    alist.add(p);
                    
                }

                
            }
            
        }
        catch(Exception e){ e.printStackTrace(); }
        
        return alist;
                           
    }
    
}
