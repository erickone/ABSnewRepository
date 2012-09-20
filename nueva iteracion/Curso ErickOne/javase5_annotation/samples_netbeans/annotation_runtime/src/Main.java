import java.lang.annotation.*;

public class Main {
    
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
        System.out.println("Class " + c.getName() + " has " +
                numberOfAnnotations + " annotations");
        
        for (int i = 0 ; i < numberOfAnnotations; i++) {
            System.out.println("Annotation " + i + ": " + annotations[i] +
                    ", type" + annotations[i].annotationType().getName());
        }
    }
    
    public static void main(String[] args) {
        Main ar = new Main();
        ar.printAnnotations();
        
    }
}