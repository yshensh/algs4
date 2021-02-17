package fundamentals;

import java.util.Arrays;

/**
 * The {@code UF} class represents a union–find data type
 * (also known as the disjoint-sets data type).
 * <p>
 * This implementation uses quick-union
 */
public class QuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int count;      // number of components

    /**
     * Initializes an empty union-find data structure with {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each elements is in its own set.
     *
     * @param n the number of elements
     */
    public QuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Merges the set containing element {@code p} with the set containing element {@code q}.
     * worst case: O(tree height)
     *
     * @param p one element
     * @param q the other element
     */
    public void union(int p, int q) {
        // p and q the same root
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        } else {
            parent[pRoot] = qRoot;
        }
        count--;
    }

    /**
     * Rerturns the canonical element of the set containing element {@code p}.
     * worst case: O(tree height)
     *
     * @param p an element
     * @return the canonical element of the set containing {@code p}
     */
    public int find(int p) {
        // find parent of p which is parent[p]
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
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
        return "QuickUnionUF{" +
                "parent=" + Arrays.toString(parent) +
                ", count=" + count +
                '}';
    }
}
