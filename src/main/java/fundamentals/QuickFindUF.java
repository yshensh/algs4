package fundamentals;

import java.util.Arrays;

/**
 * The {@code UF} class represents a union–find data type
 * (also known as the disjoint-sets data type).
 * <p>
 * This implementation uses quick-find
 */
public class QuickFindUF {
    private int[] id;   // access to component id (site indexed)
    private int count;  // number of components

    /**
     * Initializes an empty union-find data structure with {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each elements is in its own set.
     *
     * @param n the number of elements
     */
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * Merges the set containing element {@code p} with the set containing element {@code q}.
     * worst case: O(n)
     *
     * @param p one element
     * @param q the other element
     */
    public void union(int p, int q) {
        // put p and q into the same component.
        int pID = find(p);
        int qID = find(q);

        // nothing to do if p and q are already in the same component.
        if (pID == qID) return;

        // change values from id[p] to id[q].
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
        count--;

    }

    /**
     * Returns the canonical element of the set containing element {@code p}.
     * worst case: O(1)
     *
     * @param p an element
     * @return the canonical element of the set containing {@code p}
     */
    public int find(int p) {
        return id[p];
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param p one element
     * @param q q the other element
     * @return {@code true} if {@code p} and {@code 1} are in the same set;
     * {@code false} otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    @Override
    public String toString() {
        return "QuickFindUF{" +
                "id=" + Arrays.toString(id) +
                ", count=" + count +
                '}';
    }
}
