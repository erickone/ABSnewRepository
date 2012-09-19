@MyAnnotation_RuntimeRetention(someName="JPassion")
@MyAnnotation_ClassRetention(someName="Sang Shin")
@Reviewer_RuntimeRetention(@Name(first = "James", last = "Gosling"))
public class AnnotatedClass {
    
    private String name;
    private int id;
    
    public AnnotatedClass() {
        name = "Java Passion!";
    }
    
    @Accessor(variableName = "name")
    public String getName() {
        return name;
    }
    
    @Mutator(variable ="name")
    public void setName(String name) {
        this.name = name;
    }
    
    @Accessor(variableName = "name", variableType = "int")
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean equals(String otherName) {
        int comparisson = name.compareTo(otherName);
        
        return (comparisson == 0);
    }
}
