package edu.jsu.mcis.cs310.tas_fa21;

import java.time.*;

public class Shift {
    
    private int iD;
    private String description;
    private LocalTime shiftStart;
    private LocalTime shiftStop;
    private int interval;
    private int gracePeriod;
    private int dock;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private int lunchDeduct;
    private int lunchDuration;
    private int shiftDuration;

    public Shift(int id, String description, LocalTime start, LocalTime stop, int interval, 
            int gracePeriod, int dock, LocalTime lunchStart, LocalTime lunchStop, int lunchDeduct, 
            int lunchDuration, int shiftDuration) {
        this.iD = iD;
        this.description = description;
        this.shiftStart = start;
        this.shiftStop = stop;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchDeduct = lunchDeduct;
        this.lunchDuration = Duration.between(lunchStart, lunchStop).toMinutesPart();
        this.shiftDuration = Duration.between(start, stop).toMinutesPart();
    }
    public Shift(int id){
        this.iD=iD;
    }
    
    public int getId() {
        return iD;
    }

    public void setId(int id) {
        this.iD = iD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStart() {
        return shiftStart;
    }

    public void setStart(LocalTime start) {
        this.shiftStart = start;
    }

    public LocalTime getStop() {
        return shiftStop;
    }

    public void setStop(LocalTime stop) {
        this.shiftStop = stop;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(LocalTime lunchStart) {
        this.lunchStart = lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(LocalTime lunchStop) {
        this.lunchStop = lunchStop;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(int lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public int getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(int shiftDuration) {
        this.shiftDuration = shiftDuration;
    }
    
    @Override
    public String toString()
    {
        return description + ": " + shiftStart + " - " + " (" + shiftDuration + " minutes); Lunch: " + 
               lunchStart + " - " + lunchStop + " (" + lunchDuration + " minutes)";
    }
    
    
    
    
    
}