
public class Main {

    private String name;
    private int id;

    public Main() {
        name = "Java Passion!";
    }

    @Accessor(variableName = "name", variableType = "int")
    public int getId() {
        return id;
    }

    // The "variableType" can be omitted since there is
    // a default value.  If the default has not been set,
    // then you have to explicitly specify it.
    @Accessor(variableName = "xyz")
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println("No compiler error");
    }
}