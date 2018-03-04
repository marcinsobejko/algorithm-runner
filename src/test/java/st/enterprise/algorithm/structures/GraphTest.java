package st.enterprise.algorithm.structures;

import org.assertj.core.internal.ErrorMessages;
import org.junit.Test;

import static org.assertj.core.matcher.AssertionMatcher.*;
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

        Graph.Node rnNrNr = new Graph.Node("RN.NR.NR", null, null, null);
        Graph.Node rnNrNl = new Graph.Node("RN.NR.NL", null, null, null);

        Graph.Node rnNlNr = new Graph.Node("RN.NL.NR", null, null, null);
        Graph.Node rnNlNl = new Graph.Node("RN.NL.NL", null, null, null);

        Graph.Node rnNr = new Graph.Node("RN.NR", null, rnNrNl, rnNrNr);
        Graph.Node rnNl = new Graph.Node("RN.NL", null, rnNlNl, rnNlNr);

        Graph.Node rn = new Graph.Node("RN", null, rnNl, rnNr);

        Graph graph = new Graph(rn);

        assertThat(graph.findNode("RN")).isNotNull();

        assertThat(graph.findNode("RN.NR")).isNotNull();
        assertThat(graph.findNode("RN.NL")).isNotNull();

        assertThat(graph.findNode("RN.NL.NR")).isNotNull();
        assertThat(graph.findNode("RN.NL.NL")).isNotNull();

        assertThat(graph.findNode("RN.NR.NR")).isNotNull();
        assertThat(graph.findNode("RN.NR.NL")).isNotNull();
    }
}