package st.enterprise.algorithm.annotations;

/**
 * Block/method which will drive algorithm flow based on implmented conditions
 *
 * Validation for method annotated with this annotation:
 *
 * * name field is required
 * * nextBlockOnTrue field is required
 * * nextBlockOnFalse field is required
 * * block/method annotated with this annotation need to return boolean
 *
 * Created by Marcin on 16.02.2018.
 */
public @interface Condition {

    String name();
    String nextBlockOnTrue();
    String nextBlockOnFalse();
}
