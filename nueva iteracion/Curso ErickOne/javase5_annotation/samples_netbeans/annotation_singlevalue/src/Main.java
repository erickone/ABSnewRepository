
public class Main {
    
    private String name;
    private int id;
    
    @Mutator1_value("xyz")
    public void setName(String name) {
        this.name = name;
    }
    
    @Mutator2_variable(variable = "xyz")
    public int getId() {
        return id;
    }

    @Mutator3_valuexxx(valuexxx = "abc")
    public void setId(int id) {
        this.id = id;
    }
    
    public static void main(String[] args) {
        System.out.println("Compiler Error");
        
    }
    
}
