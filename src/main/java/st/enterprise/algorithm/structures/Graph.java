package st.enterprise.algorithm.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Stack;

/**
 * Created by Marcin on 18.02.2018.
 */
@AllArgsConstructor
public class Graph {

    private Node root;

    public Node findNode(String name) {
        Stack<Node> nodesToRemember = new Stack<>();
        nodesToRemember.push(root);

        while(!nodesToRemember.isEmpty()) {
            Node currentNode = nodesToRemember.pop();

            if(currentNode.getName().equals(name)) {
                return currentNode;
            } else {
                Optional.ofNullable(currentNode.getLeft()).ifPresent(nodesToRemember::push);
                Optional.ofNullable(currentNode.getRight()).ifPresent(nodesToRemember::push);
            }
        }

        return null;
    }

    @Getter
    @AllArgsConstructor
    public static class Node {

        private String name;

        private Method method;

        private Node left;
        private Node right;
    }
}
