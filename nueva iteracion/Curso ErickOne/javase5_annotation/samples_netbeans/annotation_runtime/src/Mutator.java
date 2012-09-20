import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Mutator {
    String variable();
}