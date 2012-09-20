import java.util.LinkedList;
import java.util.List;

public class GenericsInteroperability {
    
    public static void main(String[] args) {
        
        List<String> ls = new LinkedList<String>();
        //List lraw = ls;
        //lraw.add(new Integer(4));
        List<String> ls2 = ls;
        ls2.add(new Integer(4));        // Compile error
        String s = ls.iterator().next();
    }
    
}