
import java.lang.annotation.*;

/**
 *
 * @author Erick Leija
 */
public class Main
{
    
     AnnotatedClass ac;
    
    public Main() {
        ac = new AnnotatedClass();
    }
    
    public void printAnnotations() {
       // Get Class object of the AnnotatedClass class
        Class c = ac.getClass(); 
        // Get all annotations applied to the AnnotatedClass class.
        // Only the annotations with RUNTIME retention are retained during runtime.
        Annotation[] annotations = c.getAnnotations();
        int numberOfAnnotations = annotations.length;
        System.out.println("La Clase " + c.getName() + " tiene " +
                numberOfAnnotations + " anotaciones");
        
        for (int i = 0 ; i < numberOfAnnotations; i++) {
            System.out.println("Anotacion " + i + ": " + annotations[i] +
                    ", tipo" + annotations[i].annotationType().getName());
        }
    }
    public static void main(String[] args) {
        Main ar = new Main();
        ar.printAnnotations();
        ar.ac.myMethod();
        
    }
    
}
