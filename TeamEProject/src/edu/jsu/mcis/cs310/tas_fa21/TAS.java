package edu.jsu.mcis.cs310.tas_fa21;

import java.time.LocalDateTime;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class TAS {
    public static void main(String[] args) {
    }
    
    public static int calculateTotalMinutes (ArrayList<Punch>dailypunchlist, Shift shift){
        
        int total = 0;
        
        for (Punch punch : dailypunchlist) {
            
            LocalDateTime begin;
            LocalDateTime end;
            
            if (punch.getPunchtype() == PunchType.CLOCK_IN){ 
                begin = punch.getAdjustedTimeStamp();
                
                
                total = (int)(ChronoUnit.MINUTES.between(begin,end));
            }
            
        } 
        return total;
    }
}