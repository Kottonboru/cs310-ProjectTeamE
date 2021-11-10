package edu.jsu.mcis.cs310.tas_fa21;

//import java.math.BigDecimal;


public class TAS {
    public static void main(String[] args) {
        //igDecimal d = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        //System.out.println(d);
        
        public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        int totalTime = 0; 
        
        
        try{
            for(int i = 0; i < dailypunchlist.size(); i+= 1){
                Duration = Duration.between(dailypunchlist.get(i)
                int allMins = (int)duration.toMinutes();
                totalTime = totalTime + allMin;
        
            }
            
               boolean clockout = false;
               for(Punch p : dailypunchlist){ 
                     
                    if (p.getAdjustedtimestamp().toLocalTime().equals(shift.getLunchstart())){
                        clockout = true; 
                        break;
                     }
               }
               if(!clockout){
               // finish if they do not clockout
               }
               
        }
        catch(IndexOutOfBoundsException e)
        catch(Exception e){e.printStackTrace();}
        return totalTime;
        
        
        
        
    }
    
    
    
}