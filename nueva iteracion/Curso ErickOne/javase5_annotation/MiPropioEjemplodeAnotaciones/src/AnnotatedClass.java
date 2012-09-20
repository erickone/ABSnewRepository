@RequestForEnhancement(id=123,synopsis="Nuevo",engineer="Erick",date="16/09/1983")
public class AnnotatedClass {
    

     public void myMethod() {
      
        String myBooleanREsp = (AnnotatedClass.class.isAnnotationPresent(RequestForEnhancement.class))?"Si":"No";  
        int id = AnnotatedClass.class.getAnnotation(RequestForEnhancement.class).id();
        String synopsys = AnnotatedClass.class.getAnnotation(RequestForEnhancement.class).synopsis();
        String ingeniero = AnnotatedClass.class.getAnnotation(RequestForEnhancement.class).engineer();
        String fecha = AnnotatedClass.class.getAnnotation(RequestForEnhancement.class).date();
        System.out.println("la Anotacion RequestForEnhancement, esta Presente? "  + myBooleanREsp);
         System.out.println("Estos son los datos de la Anotacion: " );
         System.out.println("1.-Id: " + id );
         System.out.println("2.-Sinopsys: " + synopsys );
         System.out.println("3.-Ingeniero: " + ingeniero );
         System.out.println("4.-Fecha: " + fecha );
        
    }
   
}