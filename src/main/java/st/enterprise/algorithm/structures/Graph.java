package st.enterprise.algorithm.structures;

import java.util.LinkedList;

/**
 * Created by Marcin on 18.02.2018.
 */
public class Graph {



    private static class Node<E> {
        E item;
        Node<E>[] nexts;

        Node(E element, Node<E>... nexts) {
            this.item = element;
            this.nexts = nexts;
        }
    }
}
