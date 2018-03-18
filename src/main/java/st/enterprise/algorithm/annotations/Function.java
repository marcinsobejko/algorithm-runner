package st.enterprise.algorithm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Block/method which will be used to process some chunk of logic.
 *
 * Validation for method annotated with this annotation:
 *
 * * name field is required
 * * nextBlock field is required
 *
 * Created by Marcin on 16.02.2018.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Function {

    String name();
    String nextBlock();
}
