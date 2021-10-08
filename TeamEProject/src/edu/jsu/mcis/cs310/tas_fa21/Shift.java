
package edu.jsu.mcis.cs310.tas_fa21;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Shift {

    
    
     // created private data for types only in this class
    private int id; //
    private String description;
    private LocalTime start;
    private LocalTime stop;
    private int interval;
    private int gracePeriod;
    private int dockPoints;
    private int lunchDeduction; 
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private int lunchDuration; 
    private int  shiftDuration; 
    
    
    
    
    
    public static void main(String[] args) {
        
    }
    
    // built constructor 
    // used this method to call constructor of class
    //
        public Shift(int id, String description, LocalTime start, LocalTime stop, int interval, int gracePeriod, int dock, LocalTime lunchStart, LocalTime lunchStop, int lunchDeduct) {
        this.id = id;
        this.description = description;
        this.start = start; // included Start time when employee starts shift
        this.stop = stop; // included stop time when employee stop shift
        this.interval = interval; // interval represents minutes before and after employee clocked in
        this.gracePeriod = gracePeriod; 
        this.dockPoints = dockPoints; // points included for forgiveness or punishment
        this.lunchStart = lunchStart; // included when lunch starts
        this.lunchStop = lunchStop; // included when lunch stops
        this.lunchDuration = (int) MINUTES.between(lunchStart, lunchStop);
        this.shiftDuration = (int) MINUTES.between(start, stop);
        this.lunchDeduction = lunchDeduction;
   
        
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public LocalTime getStart() {
        return start;
    }
    public LocalTime getStop() {
        return stop;
    }
    public int getInterval() {
        return interval;
    }
    public int getGracePeriod() {
        return gracePeriod;
    }
    public int getDockPoints() {
        return dockPoints;
    }
    public LocalTime getLunchStart() {
        return lunchStart;
    }
    public LocalTime getLunchStop() {
        return lunchStop;
    }
    public int getLunchDeduction() {
        return lunchDeduction;
    }
    
    public long getLunchDuration(){
        return lunchDuration;
    }
    
    public long getShiftDuration(){
        return shiftDuration;
    }
    
    @Override
    public String toString() {

    
        StringBuilder s = new StringBuilder();

        s.append("Shift ").append(id).append(": ").append(description).append(": ").append(start).append(" - ");
        s.append(stop).append(" (").append(shiftDuration).append(" minutes); Lunch: ").append(lunchStart).append(" - ");
        s.append(lunchStop).append(" (").append(lunchDuration).append(" minutes)");
        s.append(description).append(": ");
        s.append(start).append(" - ").append(stop);
        s.append(" (").append(shiftDuration).append(" minutes);");
        s.append(" Lunch: ").append(lunchStart).append(" - ").append(lunchStop);
        s.append(" (").append(lunchDuration).append(" minutes)");

        return s.toString();
        
    }
    
}
