package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

public class BreadthFirstSearchTest {
    @Test
    void searchTest() {
        In in = new In("src/test/resources/tinyCG.txt");
        Graph G = new Graph(in);
        int s = 0;
        BreadthFirstSearch search = new BreadthFirstSearch(G, s);

        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + " (" + search.distTo(v) + "): ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
            }
            StdOut.println();
        }
    }
}
