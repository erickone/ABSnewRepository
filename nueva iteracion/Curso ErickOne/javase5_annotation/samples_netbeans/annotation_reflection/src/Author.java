import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Multi-member (normal) annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
  String firstName();
  String lastName();
}
