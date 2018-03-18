package st.enterprise.algorithm.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Marcin on 18.02.2018.
 */
@Getter
@AllArgsConstructor
public class Graph {

    private Node root;

    public Optional<Node> findNode(String name) {
        Set<Node> visitedNodes = new HashSet<>();
        Stack<Node> nodesToRemember = new Stack<>();
        nodesToRemember.push(root);

        while(!nodesToRemember.isEmpty()) {
            Node currentNode = nodesToRemember.pop();

            if(visitedNodes.contains(currentNode)) {
                break;
            } else {
                visitedNodes.add(currentNode);
            }

            if(currentNode.getName().equals(name)) {
                return Optional.of(currentNode);
            } else {
                Optional.ofNullable(currentNode.getLeft()).ifPresent(nodesToRemember::push);
                Optional.ofNullable(currentNode.getRight()).ifPresent(nodesToRemember::push);
            }
        }

        return Optional.empty();
    }

    @Getter
    @Setter
    public static class Node {

        private String name;
        private String leftNodeName;
        private String rightNodeName;

        private Method method;

        private Node left;
        private Node right;

        public Node(String name, Method method, Node left, Node right, String leftNodeName, String rightNodeName) {
            this.name = name;
            this.method = method;
            this.leftNodeName = leftNodeName;
            this.rightNodeName = rightNodeName;
            this.left = left;
            this.right = right;
        }
    }
}
