package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class Punch {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    
    private int id;
    private int terminalid;
    private Badge badge;
    private LocalDateTime originaltimestamp;
    private PunchType punchtype;
    
//existing punch
    public Punch(int id, int terminalid, Badge badge, PunchType punchtypeid, LocalDateTime originaltimestamp) {
 
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtypeid;
        this.originaltimestamp = originaltimestamp;
        
    }
    
    public Punch(int terminalid, Badge badge, int punchtypeid) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = PunchType.values()[punchtypeid];
        this.originaltimestamp = LocalDateTime.now();
    }

    //new punch
    public Punch(int terminalid, Badge badgeid, LocalDateTime originaltimestamp, int punchtypeid) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = PunchType.values()[punchtypeid]; 
        this.originaltimestamp = originaltimestamp;
    }

    public int getPunchId() {
        return id;
    }

    public void setPunchId(int id) {
        this.id = id;
    }

    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }

    //public string getAdjustmenttype() {
    //return adjustmenttype;
    //}
    //public void setAdjustmenttype(string adjustmenttype) {
    //this.adjustmenttype = adjustmenttype;
    //}
    public void setOriginaltimestamp(LocalDateTime originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public PunchType getPunchtype() {
        return punchtype;
    }
    
    public String printOriginal(){
        
        // #D2C39273 CLOCK IN: WED 09/05/2018 07:00:07
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badge.getId()).append(' ');
        s.append(punchtype).append(": ").append(originaltimestamp.format(dtf));
        
        return s.toString().toUpperCase();
        
    }
    
    public void adjust(Shift s) {
        
        
        TemporalField usWeekDay = WeekFields.of(Locale.US).dayOfWeek();
        int dayofweek = originaltimestamp.get(usWeekDay);
        
        LocalDateTime shiftStart = s.getStart().atDate(originaltimestamp.toLocalDate());
        LocalDateTime shiftStop = s.getStop().atDate(originaltimestamp.toLocalDate());
        LocalDateTime lunchStart = s.getLunchStart().atDate(originaltimestamp.toLocalDate());
        LocalDateTime lunchStop = s.getLunchStop().atDate(originaltimestamp.toLocalDate());
        
        
        if (dayofweek != Calendar.SATURDAY && dayofweek != Calendar.SUNDAY) {
              
            
        
            if (punchtype == PunchType.CLOCK_IN) {
            
            
            
            }
            
            else if (punchtype == PunchType.CLOCK_OUT) {
                
            }
        }
        
        //adjustments that apply on weekends
    
    
    
    }

}
