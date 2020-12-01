package Chapter3_3_BalancedSearchTrees;
/*
*   Draw the red-black BST that results after each transformation (color flip or rotation) during the insertion of P for
*   our standard indexing client.
*
* */
public class Ex12_DrawRedBlackBST2 {
    /*
    *
    *                          R(b)
    *
    *               E(r)                    X(b)
    *
    *          C(b)        M(b)            S(r)
    *
    *       A(r)        H(r)
    *
    *
    *
    *       -P
    *                              R(b)
    *
    *                E(r)                        X(b)
    *
    *          C(b)        M(b)             S(r)
    *
    *       A(r)        H(r)   P(r)
    *
    *
    *       ->  color flip
    *                               R(b)
    *
    *               E(r)                            X(b)
    *
    *          C(b)        M(r)               S(r)
    *
    *      A(r)        H(b)    P(b)
    *
    *
    *       -> rotate right(M)
    *
    *                                 R(b)
    *
    *                 M(r)                            X(b)
    *
    *           E(r)        P(b)               S(r)
    *
    *       C(b)   H(b)
    *
    *    A(r)
    *
    *       ->  rotate left(M)
    *
    *                                 M(b)
    *
    *                  E(r)                            R(r)
    *
    *            C(b)        H(b)               P(b)            X(b)
    *
    *        A(r)                                           S(r)
    *
    *
    *       ->  color flip
    *
    *                                   M(b)
    *
    *                  E(b)                            R(b)
    *
    *            C(b)        H(b)               P(b)            X(b)
    *
    *        A(r)                                           S(r)
    *
    *
    *
    * */

}
