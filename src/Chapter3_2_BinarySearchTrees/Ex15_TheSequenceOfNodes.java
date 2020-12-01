package Chapter3_2_BinarySearchTrees;
/*
*   Give the sequences of nodes examined when the methods in BST are used to compute each of the following quantities
*   for the tree drawn at right.
*
*                           E
*
*                   D               Q
*
*             A              J             T
*
*                                M      S
* */
public class Ex15_TheSequenceOfNodes {
    /*
    *
    *       floor("Q")  Q > E -> Q = Q -> Q
    *
    *       select(5)   5 > E.size(left), traverse to right child nodes -> 5-side(E.left)-1 = 2, 2 == Q.size(left)
    *                   return Q.key
    *
    *       ceiling("Q")    Q > E -> Q == Q -> Q
    *
    *       rank("J")   J > E -> J < Q -> J == J
    *
    *       size("D", "T")  E -> D -> Q -> J -> M -> T -> S
    *
    *       keys("D", "T")  D < E -> D;  T > E -> T > Q -> T == T
    *
    *
    * */
}
