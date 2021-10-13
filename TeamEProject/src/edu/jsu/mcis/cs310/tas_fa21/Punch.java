
package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;

/**
 *
 * @author keeskothemagician
 */
public class Punch {
    //private string adjustmenttype;
    private int punchId;
    private int terminalid;
    private Badge badgeid;
    private Timestamp originaltimestamp;
    PunchType punchtype;
// old punch
    public Punch(int punchId, int terminalid, Badge badgeid, int punchtypeid, Timestamp originaltimestamp) {
        //this.adjustmenttype = adjustmenttype;
        this.punchId = punchId;
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

    public int getPunchId() {
        return punchId;
    }

    public void setPunchId(int id) {
        this.punchId = punchId;
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
        return badgeid;
    }

    public void setBadgeId(Badge badge) {
        this.badgeid = badgeid;
    }

    public PunchType getPunchtype() {
        return punchtype;
    }
    
    public String printOriginal(){
        return "#" + badgeid + " " + punchtype + ": " + originaltimestamp;
    }

}
