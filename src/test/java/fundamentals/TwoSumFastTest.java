package fundamentals;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumFastTest {
    @Test
    void countTest() {
        In in = new In("src/test/resources/1Kints.txt");
        int[] arr = in.readAllInts();
        assertEquals(1, TwoSumFast.count(arr));
    }
}