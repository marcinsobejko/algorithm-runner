package st.enterprise.algorithm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Every algorithm should end with block/method annotated with this annotation.
 *
 * Validation for method annotated with this annotation:
 *
 * * need to return OutpuModel
 * * need to be invoked and the end of the algorithm processing
 * * no other block/method can used name = 'end'
 * * scope of block/method annotated with this annotation can't be public or package
 *
 * Created by Marcin on 18.02.2018.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface End {
    String name() default "end";
}
