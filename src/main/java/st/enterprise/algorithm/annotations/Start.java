package st.enterprise.algorithm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Every algorithm need start at block/method annotated with this annotation.
 * Method annotated ith this block/method need initialize data for the algorithm.
 *
 * You shouldn't override name field of this annotation.
 * name of this annotation cannot be reused in others blocks/methods.
 *
 * Validation for method annotated with this annotation:
 *
 * * need to have argument InputModel
 * * need to be invoked at the start of the algorithm processing
 * * no other block/method can used name = 'start'
 * * scope of block/method annotated with this annotation can't be public or package
 *
 * Created by Marcin on 18.02.2018.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Start {

    String name() default "start";
    String nextBlock();
}
