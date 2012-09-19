import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TypeErasure {
    
    static void printCollection(Collection<? extends Number> c){
        for (Object o: c)
        {
            System.out.println(o);
    }
    }
    
    public static void main(String[] args) {
        
        // Display class information of the various ArrayList instances
        ArrayList<Integer> ai = new ArrayList<Integer>();
        System.out.println("Class of ArrayList<Integer> = " + ai.getClass());
        List<Integer> li = new ArrayList<Integer>();
        System.out.println("Class of List<Integer> = " + li.getClass());
        ArrayList<String> as = new ArrayList<String>();
        System.out.println("Class of ArrayList<String> = " + as.getClass());
        ArrayList ar = new ArrayList();
        System.out.println("Class of ArrayList  = " + ar.getClass());
        
        // Check if two ArrayList instances with different type parameters
        // (one with Integer and the other with String) share the same class (bytecode).
        //
        Boolean b1 = (ai.getClass() == as.getClass());
        System.out.println("Do ArrayList<Integer> and ArrayList<String> share same class? " + b1);
        
        // Check if two ArrayList instances with different type parameters
        // (one with Integer and the other with raw type) share the same class (bytecode).
        //
        Boolean b2 = (ai.getClass() == ar.getClass());
        System.out.println("Do ArrayList<Integer> and ArrayList (raw type) share same class? " + b2);
        
    }
    
}