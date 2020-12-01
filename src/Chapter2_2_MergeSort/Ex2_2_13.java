package Chapter2_2_MergeSort;
/*
*   Lower bound for average case. Prove that the expected number of compares used by any compare-based sorting algorithm
*   must be at least ~NlgN(assuming that all possible ordering of the inputs are equally likely). Hint: The expected
*   number of compares is at least the external path length of the compare tree(the sum of the lengths of the paths from
*   the root to all leaves), which is minimized when it is balanced.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_13 {
    private static void merge(Comparable[] a, int low, int mid, int high){
        Comparable[] aux = new Comparable[high];
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++)
            aux[k] = a[k];
        for (int k = low; k <= high; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    /*
    *   Based on the Stirling's approximation - log2(n!) ~ n log2(n)
    *   Proof: Let s be the set of all n! possible ordering of n distinct element.
    *   These each require a different permutation to be produced as output.
    *   Let's now build out the entire decision tree for algorithm A on S: the tree we get by looking at all the
    *   different question / answer paths we get by running algorithm A on the inputs in S.
    *   This tree has n! leaves, where the depth of a leaf is the number of comparision performed by the sorting
    *   algorithm on that input. Our goal is to show that the average depth of the leaves must be at least log2(n!)
    *   rounded up (for the worst-case, we only care about the maximum depth).
    *   If the tree is completely balanced, then each leaf is at depth log2(n!)(round up or round down) and we are done.
    *   To prove the theorem, we just need to show that out of all binary trees on a given number of leaves, the one
    *   that minimize their average depth is a completely balanced tree.
    *   This is not too hard to see: Given some unbalanced tree, we take two sibling leaves at largest depth and move
    *   them to be children of the leaf of smallest average depth of the leaves.
    *   Specifically, if the smaller depth is "d" and the larger depth is "D", we have removed two leaves of depth "D"
    *   and one of depth "d", and we have added two leaves of "d+1" and one od depth "D+1".
    *   Since any unbalanced tree can be modified to have a smaller average depth, such a tree cannot be one that
    *   minimize average depth, and therefore the tree of smallest average depth must in fact be balanced.
    *
    *
    *
    * */
}
