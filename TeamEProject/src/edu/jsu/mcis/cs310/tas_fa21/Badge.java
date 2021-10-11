
package edu.jsu.mcis.cs310.tas_fa21;


public class Badge {

    private String identification;
    private String descriptions;
    private final String id;
    private final String description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }
    public String getIdentification() {
        return id;
    }
    public String getDescriptions() {
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
