

public class MyOwnGenericClass {
    
    public static void main(String[] args) {
        
        
        //Ejemplo para el Ejecicio de Tarea
        //Erick Benjamín Leija Cardenas
        Integer Primero = new Integer(1000);
        Long Segundo = new Long(2000L);
        Object Tercero = "Hola";
        Boolean Cuarto = true;
        PairExtendedAgain<Integer, Long, Object, Boolean> pe6
                = new PairExtendedAgain<Integer, Long, Object, Boolean>(Primero,Segundo, Tercero,Cuarto);
        System.out.println("Primero del PairExtendedAgain = " + pe6.getFirst());
        System.out.println("Segundo del PairExtendedAgain = " + pe6.getSecond());
        System.out.println("tercero del PairExtendedAgain = " + pe6.getThird());
        System.out.println("Cuarto del PairExtendedAgain = " + pe6.getFourt());
        
    }
    
}
