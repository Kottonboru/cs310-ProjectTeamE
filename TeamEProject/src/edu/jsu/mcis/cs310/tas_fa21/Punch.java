package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Punch {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    
    private int id;
    private int terminalid;
    private Badge badge;
    private LocalDateTime originaltimestamp;
    private PunchType punchtype;
    

    public Punch(int id, int terminalid, Badge badge, PunchType punchtypeid, LocalDateTime originaltimestamp) {
 
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtypeid;
        this.originaltimestamp = originaltimestamp;
        
    }

    public Punch(int terminalid, Badge badgeid, int punchtypeid, LocalDateTime originaltimestamp) {
        this.terminalid = terminalid;
        this.badge = badgeid;
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

}
