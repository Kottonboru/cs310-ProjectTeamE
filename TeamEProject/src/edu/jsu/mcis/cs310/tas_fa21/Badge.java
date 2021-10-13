
package edu.jsu.mcis.cs310.tas_fa21;


public class Badge {

    private String id;
    private String description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    public Badge(String id){
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("#").append(this.id).append(" ");
        output.append("(").append(this.description).append(")");
        
        return output.toString();
    }

    public static void main(String[] args) {
       
        
        
        
    }
    
}
