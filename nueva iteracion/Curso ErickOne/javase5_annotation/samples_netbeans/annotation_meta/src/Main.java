
public class Main {
    
    @Exposed("name")
    private String name;
    
    //@Exposed("name")   // Compile error 
    public void setName(String name) {
        this.name = name;
    }
    public static void main(String[] args) {
        System.out.println("No compiler error");
    }
    
}