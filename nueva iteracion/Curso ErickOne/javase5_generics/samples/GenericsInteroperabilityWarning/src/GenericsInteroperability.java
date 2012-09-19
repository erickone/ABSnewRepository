import java.util.LinkedList;
import java.util.List;

public class GenericsInteroperability {
    
    public static void main(String[] args) {
        
        List<String> ls = new LinkedList<String>();
        List lraw = ls;
        lraw.add(new Integer(4));
        String s = ls.iterator().next();
    }
    
}