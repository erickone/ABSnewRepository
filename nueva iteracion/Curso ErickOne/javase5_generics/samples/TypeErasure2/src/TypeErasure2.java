import java.util.List;
import java.util.Vector;

public class TypeErasure2 {
    
    public static void main(String[] args) {
        //
        // Get class and type information of a collection class
        //
        List<Number> ln5 = new Vector<Number>(20);
        Class c3  = ln5.getClass();                                       // Right-click this line and select Fix Imports first
        System.out.println("Class of List<Number>  =" + c3);
        
        Class [] c4 = c3.getInterfaces();                                // Right-click this line and select Fix Imports first
        for (Class c: c4){
            System.out.println("Interface = " + c);
        }
        
        Class c5 = c3.getSuperclass();
        System.out.println("Superclass = " + c5);
    }
    
}
