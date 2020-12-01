package Chapter3_3_BalancedSearchTrees;
/*
*   Find a sequence of keys to insert into a BST and into a red-black BST such that the height of the BST is less than
*   the red-black BST, or prove that no such sequence is possible.
*
* */
public class Ex22_HeightBSTRedBlackBST {

    /*

    *       Assume a sequence of keys as: 10 2 13 15 12 0
    *
    *       BST:        10                10
    *
    *
    *                   2                 10
    *
    *                               2
    *
    *
    *                   13                 10
    *
    *                               2               13
    *
    *
    *
    *                   15                 10
    *
    *                               2               13
    *
    *                                                       15
    *
    *
    *                   12                  10
    *
    *                               2                13
    *
    *                                             12    15
    *
    *
    *                   0                    10
    *
    *                               2                 13
    *
    *                           0                 12      15
    *
    *                   Height = 2
    *
    *
    *       Red-Black:
    *
    *                  10                   10(b)
    *
    *
    *                  2                    10(b)
    *
    *                                   2(r)
    *
    *
    *                  13                    10(b)                  10(r)                  10(b)
    *
    *                                   2(r)        13(r)       2(b)      13(b)         2(b)    13(b)
    *
    *
    *                  15                    10(b)                              10(b)
    *
    *                                   2(b)        13(b)               2(b)             15(b)
    *
    *                                                       15(r)                   13(r)
    *
    *
    *                  12                    10(b)                          10(b)                           10(b)                               13(b)
    *
    *                                2(b)             15(b)         2(b)             13(b)          2(b)             13(r)                 10(r)         15(b)
    *
    *                                            13(r)                           12(r)      15(r)               12(b)       15(b)       2(b)    12(b)
    *
    *                                          12(r)
    *
    *
    *                  0                        13(b)
    *
    *                                   10(r)           15(b)
    *
    *                               2(b)        12(b)
    *
    *                            0(r)
    *
    *                    Height = 3
    *
    *
    * */


}
