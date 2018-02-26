package st.enterprise.algorithm;

import java.lang.reflect.Method;

/**
 * Every algorithm need to extends from this class.
 *
 * TODO: Probably there will be need of creating BaseAlgorithm
 *
 * Created by Marcin on 17.02.2018.
 */
public abstract class Algorithm<InputModel, OutputModel> {

    protected Void graph;

    public Algorithm() {
        compileThis();
    }

    /**
     * Compile this object to the graph structure.
     */
    protected void compileThis() {
        graph = createGraph(collectBlocks());
    }

    /**
     * Method collects all blocks/methods for {@code this} object when
     * methods are annotated with @Function @Condition.
     */
    protected Method[] collectBlocks() { return null; }

    /**
     * Create graph or some structure which will store blocks/methods in
     * some order which can be invoked by run easly.
     *
     * I don't know currently what Graph structure can be used.
     */
    protected Void createGraph(Method[] methods) { return null; }

    /**
     * Process input data.
     *
     * @param input input model data.
     *
     * @return result of processing input data.
     */
    public OutputModel process(InputModel input) { return null; };
}
