package fundamentals;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeSumFastTest {
    @Test
    void countTest() {
        In in = new In("src/test/resources/1Kints.txt");
        int[] arr = in.readAllInts();
        assertEquals(70, ThreeSumFast.count(arr));
    }
}