package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;


public class DijkstraSPTest {
    @Test
    void edgeWeightedDigraphTest() {
        In in = new In("src/test/resources/tinyEWD.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int s = 0;
        DijkstraSP sp = new DijkstraSP(G, s);
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d: no path\n", s, t);
            }
        }
    }
}
