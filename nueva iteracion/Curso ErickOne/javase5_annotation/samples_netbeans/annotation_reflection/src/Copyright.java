
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Single value annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Copyright {
    String value();
}

