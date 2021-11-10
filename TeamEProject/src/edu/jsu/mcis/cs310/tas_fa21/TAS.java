package edu.jsu.mcis.cs310.tas_fa21;

import java.time.Duration;
import java.util.ArrayList;



public class TAS {
    public static void main(String[] args) {
    }    
        
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        
        int totalTime = 0; 

        try{
            for(int i = 0; i < dailypunchlist.size(); i+= 1){
                
                duration = Duration.between(dailypunchlist.get(i));
                int allMins = (int)duration.toMinutes();
                totalTime = totalTime + allMin;
                
            }

           boolean clockout = false;
           
           for(Punch p : dailypunchlist){ 

                if (p.getAdjustedTimestamp().toLocalTime().equals(shift.getLunchStart())){
                    clockout = true; 
                    break;
                 }
           }
           if(!clockout){
           // finish if they do not clockout
           }

    }
    catch(IndexOutOfBoundsException e){};
    return totalTime;

    }  
}
    
    
    
