package st.enterprise.algorithm.structures;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GraphTest {

    @Test
    public void findNode() {
        /** Graph test structure
         *
         *               //== RN.NR.NR
         *       //== RN.NR
         *      //       \\== RN.NR.NL
         *    RN
         *      \\       //== RN.NL.NR
         *       \\== RN.NL
         *               \\== RN.NL.NL
         */

        Graph.Node rnNrNr = new Graph.Node("RN.NR.NR", null, null, null, null, null);
        Graph.Node rnNrNl = new Graph.Node("RN.NR.NL", null, null, null, null, null);

        Graph.Node rnNlNr = new Graph.Node("RN.NL.NR", null, null, null, null, null);
        Graph.Node rnNlNl = new Graph.Node("RN.NL.NL", null, null, null, null, null);

        Graph.Node rnNr = new Graph.Node("RN.NR", null, rnNrNl, rnNrNr, rnNrNl.getName(), rnNrNr.getName());
        Graph.Node rnNl = new Graph.Node("RN.NL", null, rnNlNl, rnNlNr, rnNrNl.getName(), rnNrNr.getName());

        Graph.Node rn = new Graph.Node("RN", null, rnNl, rnNr, rnNl.getName(), rnNr.getName());

        Graph graph = new Graph(rn);

        assertThat(graph.findNode("RN").isPresent()).isTrue();

        assertThat(graph.findNode("RN.NR").isPresent()).isTrue();
        assertThat(graph.findNode("RN.NL").isPresent()).isTrue();

        assertThat(graph.findNode("RN.NL.NR").isPresent()).isTrue();
        assertThat(graph.findNode("RN.NL.NL").isPresent()).isTrue();

        assertThat(graph.findNode("RN.NR.NR").isPresent()).isTrue();
        assertThat(graph.findNode("RN.NR.NL").isPresent()).isTrue();
    }
}