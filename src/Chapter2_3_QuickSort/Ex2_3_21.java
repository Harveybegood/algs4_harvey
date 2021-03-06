package Chapter2_3_QuickSort;
/*
*   Lower bound for sorting with equal keys. Complete the first part of the proof of Proposition M by following the logic
*   in the proof of Proposition I and using the observation that there are N!/f1!f2! . . . fk! different ways to arrange
*   keys with k different values, where the ith value appears with frequency fi(=Npi, in the notation of Proposition M),
*   with f1+...fk = N.
*
* */
public class Ex2_3_21 {

    /*
    *
    *
        Proposition M says that no compare-based sorting algorithm can guarantee to sort N items with fewer than NH - N
        compares, where H is the Shannon entropy, defined from the frequencies of key values.

        First part of the proof: There are N! / f1! f2! ... fk! different ways to arrange keys with k different values,
        where the ith value appears with frequency fi, with f1 + ... + fk = N.

        Using a ternary tree we can describe the sequence of compares. Each node in the tree is either
        a leaf i0 i1 i2 ... in-1 that indicates that the sort is complete and has discovered that
        the original inputs were in the order a[i0], a[i1], ... a[in-1], or an internal node i:j that
        corresponds to a compare operation between a[i] and a[j], with a left subtree corresponding to
        the sequence of compares in the case that a[i] is less than a[j], a central subtree corresponding to
        the sequence of compares in the case that a[i] is equal to a[j] and a right subtree corresponding to
        what happens if a[i] is greater than a[j]. Each path from the root to a leaf corresponds to
        the sequence of compares that the algorithm uses to establish the ordering given in the leaf.

        The first key observation in the proof is that the tree must have at least N! / f1! f2! ... fk! leaves because
        there are N! / f1! f2! ... fk! different permutations of the keys.
        If there are fewer than N! / f1! f2! ... fk! leaves, then some permutation is missing from the leaves,
        and the algorithm would fail for that permutation.

        The number of internal nodes on a path from the root to a leaf in the tree is the number of compares used by
        the algorithm for some input.
        We are interested in the length of the longest such path in the tree (known as the tree height) since
        it measures the worst-case number of compares used by the algorithm.
        It is a basic combinatorial property of ternary trees that a tree of height h has no more than
        3^h leaves - the tree of height h with the maximum number of leaves is perfectly balanced, or complete.

        Combining the previous two observations, we have shown that any compare-based sorting algorithm
        corresponds to a compare tree of height h with N! / f1! f2! ... fk! <= number of leaves <= 3^h

        The value of h is precisely the worst-case number of compares, so we can take the logarithm (base 2) of
        both sides of this equation and conclude that the number of compares used by any algorithm must be
        at least lg N! / f1! f2! ... fk!
        The approximation lg N! ~ N lg N follows immediately from Stirling's approximation to the factorial function,
        which means that the number of compares used by any algorithm must be at least N lg N / f1! f2! ... fk!
        when there are k different values.
*/
}
