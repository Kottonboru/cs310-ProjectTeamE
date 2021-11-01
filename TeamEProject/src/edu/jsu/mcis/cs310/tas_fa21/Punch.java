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
       
        LocalDateTime shiftStartInterval = shiftStart.minusMinutes(s.getInterval());
        LocalDateTime shiftStartGrace = shiftStart.plusMinutes(s.getGracePeriod());
        LocalDateTime shiftStartDock = shiftStart.plusMinutes(s.getDock());
        
        LocalDateTime shiftStopInterval = shiftStop.plusMinutes(s.getInterval());
        LocalDateTime shiftStopGrace = shiftStop.minusMinutes(s.getGracePeriod());
        LocalDateTime shiftStopDock = shiftStop.minusMinutes(s.getDock());
        
        
        
        if (dayofweek != Calendar.SATURDAY && dayofweek != Calendar.SUNDAY) {
              
           
            if (punchtype == PunchType.CLOCK_IN) {
                //Interval
                if (originaltimestamp.isAfter(shiftStartInterval) && originaltimestamp.isBefore(shiftStart)){
                    
                }
                //Dock
                else if (originaltimestamp.isBefore(shiftStartDock) && originaltimestamp.isAfter(shiftStart)){
                
                }
                //Grace
                else if (originaltimestamp.isBefore(shiftStartGrace) && originaltimestamp.isAfter(shiftStart)){
                    
                }
                //Lunch
                else if (originaltimestamp.isBefore(lunchStop) && originaltimestamp.isAfter(lunchStart)){
                    
                }
            
            
            }
            
            else if (punchtype == PunchType.CLOCK_OUT) {
                //Interval
                if (originaltimestamp.isBefore(shiftStopInterval) && originaltimestamp.isAfter(shiftStop)){
                    
                }
                //Dock
                else if (originaltimestamp.isAfter(shiftStopDock) && originaltimestamp.isBefore(shiftStop)){
                
                }
                //Grace
                else if (originaltimestamp.isAfter(shiftStopGrace) && originaltimestamp.isBefore(shiftStop)){
                    
                }
                //Lunch
                else if (originaltimestamp.isBefore(lunchStop) && originaltimestamp.isAfter(lunchStart)){
                    
                }
                
            }
        }
        
        //adjustments that apply on weekends
    
    
    
    }

}
