package Chapter3_2_BinarySearchTrees;


/*
*   Refined analysis. Refine the mathematical model to better explain the experimental results in the table given in the text.
*   Specifically, show that the average number of compares for a successful search in a tree built from random keys approaches
*   the limit 2lnN + 2ϒ - 3 ≈ 1.39lgN - 1.85 as N increases, where ϒ = .57721... is Euler's constant. Hint: Referring to the
*   quick sort analysis in Section 2.3, use the fact that the integral of 1/x approaches lnN + ϒ.
*
* */
public class Ex35_RefinedAnalysis {
    /*
    *
    *   The number of compares used for a search hit ending at a given node is 1 plus the depth. Adding the depths of all nodes,
    *   we get a quantity known as the internal path length of the tree. Thus, the desired quantity is 1 plus the average internal
    *   path length of the BST, which we can analyze with the same argument that we used for Proposition K in section2.3:
    *   Let Cn be the total internal path length of a BST built from inserting N randomly ordered distinct keys, so that the average
    *   cost of a search hit is 1 + Cn/N. We have C0 = C1 = 0 and for N > 1 we can write a recursive relationship that directly
    *   mirrors the recursive BST structure:
    *
    *       Cn = N - 1 + (C0 + Cn-1)/N + ... (Cn-1 + C0)/N
    *       Cn = N - 1 + 2(C0 + ... + Cn-1)/N
    *   Multiply N by both sides:
    *
    *       NCn = N(N-1) + 2(C0 + ... + Cn-1)
    *
    *   Subtract by both sides for N - 1:
    *
    *       NCn - (N-1)Cn-1 = N(N-1) + 2(C0 + ... + Cn-1) - (N-1)(N-2) - 2(C0 + ... + Cn-2)
    *       NCn - (N-1)Cn-1 = 2(N-1) + 2Cn-1
    *       NCn = 2(N-1) + (N+1)Cn-1
    *
    *   Divide by both side for N(N+1):
    *
    *       Cn/(N+1) = (2(N-1) + (N+1)Cn-1)/N(N+1)
    *       Cn/(N+1) = 2(N-1)/N(N+1) + Cn-1/N
    *                = 2/(N+1) - 2/N(N+1) + Cn-1/N
    *
    *
    *   Finally:
    *       ?
    *       Cn = 2 - 2/N + Cn-1(N+1)/N
    *       Cn = 2(N+1) * (1/3 + 1/4 + ... + 1/(N+1) - 2)
    * */

}

