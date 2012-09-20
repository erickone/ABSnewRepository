
@BetaVersion
@Copyright("2011 JPassion.com")
@Author(firstName = "Sang", lastName = "Shin")
public class MyClass {

    public static void main(String[] args) {

        // Market annotation reflection
        boolean isBeta = MyClass.class.isAnnotationPresent(BetaVersion.class);
        System.out.println("isBeta = " + isBeta);

        // Single value annotation reflection
        String copyright = MyClass.class.getAnnotation(Copyright.class).value();
        System.out.println("copyright = " + copyright);

        // Normal annotation reflection
        String firstName = MyClass.class.getAnnotation(Author.class).firstName();
        String lastName = MyClass.class.getAnnotation(Author.class).lastName();
        System.out.println("firstName = " + firstName + ", lastName = " + lastName);

    }
}
