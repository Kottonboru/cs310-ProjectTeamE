
package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;

/**
 *
 * @author keeskothemagician
 */
public class Punch {
    //private string adjustmenttype;
    private int id;
    private int terminalid;
    private Badge badgeid;
    private Timestamp originaltimestamp;
    PunchType punchtype;
// old punch
    public Punch(int id, int terminalid, Badge badgeid, int punchtypeid, Timestamp originaltimestamp) {
        //this.adjustmenttype = adjustmenttype;
        this.id = id;
        this.terminalid = terminalid;
        this.badgeid = badgeid;
        this.punchtype = PunchType.values()[punchtypeid];
        this.originaltimestamp = originaltimestamp;
    }
// new punch
    public Punch(int terminalid, Badge badgeid, int punchtypeid, Timestamp originaltimestamp) {
        this.terminalid = terminalid;
        this.badgeid = badgeid;
        this.punchtype = PunchType.values()[punchtypeid]; 
        this.originaltimestamp = originaltimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOriginaltimestamp() {
        return originaltimestamp;
    }

    //public string getAdjustmenttype() {
    //return adjustmenttype;
    //}
    //public void setAdjustmenttype(string adjustmenttype) {
    //this.adjustmenttype = adjustmenttype;
    //}
    public void setOriginaltimestamp(Timestamp originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public Badge getBadgeId() {
        return badge;
    }

    public void setBadgeId(Badge badge) {
        this.badge = badge;
    }

    public PunchType getPunchtype() {
        return punchtype;
    }
    
    public String printOriginal(){
        return "#" + badgeid + " " + punchtype + ": " + originaltimestamp;
    }
    
}