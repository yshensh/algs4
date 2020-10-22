package fundamentals;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    void indexOfTest() {
        int[] arr = {19, 7, 59, 32, 5, 5};
        Arrays.sort(arr);
        assertEquals(-1, BinarySearch.indexOf(arr, 20));
        assertEquals(0, BinarySearch.indexOf(arr, 5));
    }

}