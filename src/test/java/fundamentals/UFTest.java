package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UFTest {
    @Test
    public void TestUF(){
        In in = new In("src/test/resources/tinyUF.txt");
        int n = in.readInt();
        UF uf = new UF(n);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            StdOut.println("--------------------");
            StdOut.println("Reading from file: " + p + " " + q);
            if (uf.connected(p, q)) {
                StdOut.println(p + " " + q + " connected already");
                continue;
            } else {
                uf.union(p, q);
                StdOut.println("Connecting " + p + " " + q);
            }
            StdOut.println(uf.toString());
        }
    }
}