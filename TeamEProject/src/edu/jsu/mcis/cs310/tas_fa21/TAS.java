package edu.jsu.mcis.cs310.tas_fa21;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONValue;



public class TAS {
    public static void main(String[] args) {
        
    }    
        
    @SuppressWarnings("empty-statement")
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        
        int totalTime = 0; 

        try {
            for (int i = 0; i < dailypunchlist.size(); i += 2) {
                
                //Changed "i += 1" to "i += 2"
                Duration dur = Duration.between(dailypunchlist.get(i).getAdjustedTimestamp(), dailypunchlist.get(i + 1).getAdjustedTimestamp());
                int allMins = (int)dur.toMinutes();
                totalTime = totalTime + allMins;
                
            }

            boolean clockout = false;
           
            for (Punch p : dailypunchlist) { 

                if (p.getAdjustedTimestamp().toLocalTime().equals(shift.getLunchStart())) {
                    clockout = true; 
                    break;
                }
            }
            if (!clockout) {
                //Added below code
                totalTime = totalTime - 30;
            }

        }
    catch (IndexOutOfBoundsException e) {}
    System.out.println(totalTime);
    return totalTime;

    }  
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE" + " LL/dd/uuuu HH:mm:ss" );
        /* \"originaltimestamp\":\"TUE 09\\/18\\/2018 11:59:33\",
        \"badgeid\":\"08D01475\",\
        "adjustedtimestamp\":\"TUE 09\\/18\\/2018 12:00:00\",\
        "adjustmenttype\":\"Shift Start\",\
        "terminalid\":\"104\",\
        "id\":\"4943\",\
        "punchtype\":\"CLOCK IN\"
        */
        
        for(Punch punch : dailypunchlist){
            HashMap<String, String> punchData = new HashMap<>();
            
            //Addding data into the PunchData hashmap
            punchData.put("originaltimestamp", String.valueOf(punch.getOriginaltimestamp().format(format).toUpperCase()));
            punchData.put("badgeid", String.valueOf(punch.getBadge().getId()));
            punchData.put("adjustedtimestamp", String.valueOf(punch.getAdjustedTimestamp().format(format).toUpperCase()));
            punchData.put("adjustmenttype", String.valueOf(punch.getAdjustmenttype()));
            punchData.put("terminalid", String.valueOf(punch.getTerminalid()));
            punchData.put("id",String.valueOf(punch.getPunchId())); 
            punchData.put("punchtype", String.valueOf(punch.getPunchtype()));
            
            //Add it into a json arraylist
            jsonData.add(punchData);
        }
        
        String json = JSONValue.toJSONString(jsonData);
        return json;
    }
    
    public static double calculateAbsenteeism(ArrayList<Punch> punchlist, Shift s){
        
    }
    
}
    
    
    
