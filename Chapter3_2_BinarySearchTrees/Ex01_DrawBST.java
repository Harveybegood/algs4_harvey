package Chapter3_2_BinarySearchTrees;
/*
*   Draw the BST that results when you insert the keys E A S Y Q U E S T I O N, in that order(associating the value i
*   with the ith key, as per the convention in the text) into an initially empty tree. How many compares are needed
*   to build the tree?
*
* */
public class Ex01_DrawBST {
    /*
    *                                                    E(1)
    *                                      ...............................
    *                                                    E(2)
    *                                          A(1)                                                     1
    *                                      ...............................
    *                                                    E(3)                                           1
    *                                          A(1)                   S(1)
    *                                      ...............................
    *                                                    E(4)                                           2
    *                                          A(1)                   S(2)
    *                                                                          Y(1)
    *
    *                                      ....................................
    *                                                     E(5)                                          2
     *                                          A(1)                   S(3)
     *                                                           Q(1)         Y(1)
     *
    *                                      .......................................
    *                                                    E(6)
     *                                         A(1)                     S(4)
     *                                                          Q(1)             Y(2)                      3
     *                                                                  U(1)
     *                                     ........................................
     *                                                    E(7)                                          1
     *                                         A(1)                    S(5)
     *                                                      Q(1)              Y(2)
     *                                                                  U(1)
     *                                     .........................................
     *                                                     E(8)                                          2
     *                                         A(1)                    S(6)
     *                                                         Q(1)              Y(2)
     *                                                                  U(1)
     *                                     .........................................
     *                                                    E(9)                                          4
     *                                         A(1)                    S(7)
     *                                                      Q(1)              Y(3)
     *                                                                  U(2)
    *                                                               T(1)
    *                                     ............................................
    *                                                      E(11)                                        2
     *                                         A(2)                      S(7)
     *                                               I(1)        Q(2)              Y(3)
     *                                                      O(1)            U(2)
     *                                                               T(1)
     *                                   ...............................................
    *                                                       E(12)                                        4
     *                                         A(2)                         S(8)
     *                                               I(1)           Q(3)              Y(3)
     *                                                       O(2)               U(2)
     *                                                  N(1)              T(1)
     *                                                                                                  total = 22
     *
    * */
}
