package st.enterprise.algorithm;

import st.enterprise.algorithm.annotations.Condition;
import st.enterprise.algorithm.annotations.End;
import st.enterprise.algorithm.annotations.Function;
import st.enterprise.algorithm.annotations.Start;
import st.enterprise.algorithm.structures.Graph;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Every algorithm need to extends from this class.
 *
 * TODO: Probably there will be need of creating BaseAlgorithm
 *
 * Created by Marcin on 17.02.2018.
 */
public abstract class Algorithm<InputModel, OutputModel> {

    protected Graph graph;

    protected InputModel inputModel;
    protected OutputModel outputModel;

    public Algorithm() {
        compileThis();
    }

    /**
     * Compile this object to the graph structure.
     *
     * TODO: I shoudl add validation after collectBlocks to esure that
     * every blocks/methods don't have any other annotation or that
     * they have only one annotation which is @Start, @End, @Function
     * @Condition
     */
    protected void compileThis() {
        graph = createGraph(collectBlocks());
    }

    /**
     * Method collects all blocks/methods for {@code this} object when
     * methods are annotated with @Function @Condition.
     *
     * TODO: Probably I can do it better with java 8 stram api
     */
    protected Method[] collectBlocks() {
        List<Method> methods = new ArrayList<>();
        for(Method method: this.getClass().getDeclaredMethods()) {
            boolean isStart = method.getAnnotation(Start.class) != null;
            boolean isEnd = method.getAnnotation(End.class) != null;
            boolean isCondition = method.getAnnotation(Condition.class) != null;
            boolean isFunction = method.getAnnotation(Function.class) != null;

            if(isStart || isEnd || isCondition || isFunction) {
                methods.add(method);
            }
        }

        return methods.toArray(new Method[methods.size()]);
    }

    /**
     * Create graph or some structure which will store blocks/methods in
     * some order which can be invoked by run easly.
     *
     * I don't know currently what Graph structure can be used.
     */
    protected Graph createGraph(Method[] methods) {
        Graph.Node startNode = findStart(methods);
        return createGraph(startNode, methods);
    }

    /**
     * Find start node.
     *
     * @param methods array of methods to process by algorithm
     */
    private Graph.Node findStart(Method[] methods) {
        for(Method method: methods) {
            Start start = method.getAnnotation(Start.class);
            if(start != null) {
                return new Graph.Node(start.name(), method, null, null, start.nextBlock(), null);
            }
        }

        return null;
    }

    /**
     * Find method by name and remove method from list.
     */
    private Graph.Node findMethodByNameAndRemove(String nodeName, List<Method> methods) {
        for(Method m: methods) {
            End end = m.getAnnotation(End.class);
            if(end != null && end.name().equals(nodeName)) {
                methods.remove(m);
                return new Graph.Node(end.name(), m, null, null, null, null);
            }

            Function function = m.getAnnotation(Function.class);
            if(function != null && function.name().equals(nodeName)) {
                methods.remove(m);
                return new Graph.Node(function.name(), m, null, null, function.nextBlock(), null);
            }

            Condition condition = m.getAnnotation(Condition.class);
            if(condition != null && condition.name().equals(nodeName)) {
                methods.remove(m);
                return new Graph.Node(condition.name(), m, null, null, condition.nextBlockOnFalse(), condition.nextBlockOnTrue());
            }
        }

        return null;
    }

    /**
     * Create graph with based on delivered methods.
     *
     * @param startNode node which point to start block/method
     * @param methods array with methods which will by processed in graph
     *
     * @return create algorithm graph
     */
    private Graph createGraph(Graph.Node startNode, Method[] methods) {
        Graph graph = new Graph(startNode);

        List<Method> methodsList = new LinkedList<>();
        methodsList.addAll(Arrays.asList(methods));

        Stack<Graph.Node> nodesToRemember = new Stack<>();
        nodesToRemember.push(startNode);

        do {
            Graph.Node currentNode = nodesToRemember.pop();

            Graph.Node leftNode = findMethodByNameAndRemove(currentNode.getLeftNodeName(), methodsList);
            Graph.Node rightNode = findMethodByNameAndRemove(currentNode.getRightNodeName(), methodsList);

            if (leftNode == null) {
                Optional<Graph.Node> optional = graph.findNode(currentNode.getLeftNodeName());
                if(optional.isPresent()) {
                    leftNode = graph.findNode(currentNode.getLeftNodeName()).get();
                    currentNode.setLeft(leftNode);
                }
            } else {
                currentNode.setLeft(leftNode);
                nodesToRemember.push(leftNode);
            }

            if(rightNode == null) {
                Optional<Graph.Node> optional = graph.findNode(currentNode.getRightNodeName());
                if(optional.isPresent()) {
                    rightNode = optional.get();
                    currentNode.setRight(rightNode);
                }
            } else {
                currentNode.setRight(rightNode);
                nodesToRemember.push(rightNode);
            }
        } while (!nodesToRemember.empty());

        return graph;
    }

    /**
     * Process algorithm based on graph.
     */
    protected void process() {
        renderGraph(graph);
    }

    private void renderGraph(Graph graph) {
        Stack<Graph.Node> nodesToRemember = new Stack<>();
        nodesToRemember.push(graph.getRoot());

        do {
            Graph.Node currentNode = nodesToRemember.pop();

            String rightNodeName = currentNode.getRight() != null ? currentNode.getRight().getName() : "empty";
            String currentNodeName = currentNode.getName() != null ? currentNode.getName() : "empty";
            String leftNodeName = currentNode.getLeft() != null ? currentNode.getLeft().getName() : "empty";

            System.out.println("== \t\t" + rightNodeName);
            System.out.println("== " + currentNodeName);
            System.out.println("== \t\t" + leftNodeName);

            System.out.println("====================");

            if(currentNode.getName().equals("end")) {
                break;
            }

            if(currentNode.getLeft() != null) {
                nodesToRemember.push(currentNode.getLeft());
            }

            if(currentNode.getRight() != null) {
                nodesToRemember.push(currentNode.getRight());
            }
        } while (!nodesToRemember.empty());
    }

    /**
     * Process input data. This method should setup data for processing by algorithm
     * and then it should invoke process method.
     *
     * @param inputModel input model data.
     *
     * @return result of processing input data.
     */
    public OutputModel process(InputModel inputModel) {
        this.inputModel = inputModel;
        process();
        return outputModel;
    };
}
