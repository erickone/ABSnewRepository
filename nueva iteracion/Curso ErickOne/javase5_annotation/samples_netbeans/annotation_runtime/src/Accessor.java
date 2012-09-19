
/**
 *  Annotation definition for an accessor method.  This shows the use of
 *  multiple members.
 **/
public @interface Accessor {
    String variableName();
    String variableType() default "String";
}