package st.enterprise.algorithm.annotations;

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
public @interface Function {

    String name();
    String nextBlock();
}
