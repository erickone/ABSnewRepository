import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GenericsSubtyping {
   
    public static void main(String[] args) {
       
        ArrayList<Integer> ai = new ArrayList<Integer>();
        ArrayList<String> as = new ArrayList<String>();
        ArrayList<Object> ao1 = new ArrayList<Object>();
        ArrayList<Object> ao2 = new ArrayList<String>();
        ArrayList<Object> ao3 = new ArrayList<Integer>();
       
        List<String> ls = new ArrayList<String>();
        List<Object> lo = new ArrayList<String>();
       
        List<Number> ln1 = new Vector<Number>();
        List<Number> ln2 = new Vector<Integer>();
        List<Number> ln3 = new ArrayList<Long>();
       
    }
   
}