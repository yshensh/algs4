package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

public class DirectedCycleTest {
    @Test
    void CycleTest() {
        In in = new In("src/test/resources/tinyDG.txt");
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }
    }
}
