package Chapter3_3_BalancedSearchTrees;
/*
*   Draw the red-black BST that results when you insert items with the keys Y L P M X H C R A E Sin that order into
*   an initially empty tree.
*
* */
public class Ex11_DrawRedBlackBST1 {
    /*
    *
    *       Y           Y(b)
    *
    *
    *       L           Y(b)
    *
    *               L(r)
    *
    *
    *       P           Y(b)        ->      Y(b)        ->      P(r)            ->          P(b)
    *
    *               L(r)                 P(r)               L(b)      Y(b)              L(b)        Y(b)
    *
    *                   P(r)          L(r)
    *
    *
    *       M           P(b)            ->       P(b)
    *
    *              L(b)      Y(b)           M(b)        Y(b)
    *
    *                   M(r)            L(r)
    *
    *       X              P(b)                 ->      P(b)
    *
    *               M(b)          Y(b)                M(b)        X(b)
    *
    *           L(r)                    X(r)      L(r)        Y(r)
    *
    *
    *       H               P(b)                        ->              P(b)
    *
    *                 M(b)          X(b)                    L(r)                    X(b)
    *
    *            L(r)           Y(r)                H(b)            M(b)        Y(r)
    *
    *       H(r)
    *
    *
    *       C               P(b)
    *
    *               L(r)            X(b)
    *
    *           H(b)    M(b)      Y(r)
    *
    *        C(r)
    *
    *
    *       R               P(b)                    ->          P(b)                                ->                  Y(b)                            ->           P(r)
    *
    *               L(r)             X(b)           L(r)                    Y(r)                            P(r)                    X(b)                    L(b)                Y(b)
    *
    *           H(b)     M(b)      Y(r)         H(b)    M(b)            R(b)        X(b)            L(r)            R(b)                                H(b)        M(b)    R(b)    X(b)
    *
    *        C(r)               R(r)        C(r)                                                H(b)    M(b)                                         C(r)
    *
    *                                                                                       C(r)
    *
    *       A                P(b)                       ->                     P(b)
    *
    *               L(b)             Y(b)                           L(b)                   Y(b)
    *
    *           H(b)    M(b)      R(b)      X(b)            C(r)            M(b)        R(b)    X(b)
    *
    *        C(r)                                       A(b)     H(b)
    *
    *     A(r)
    *
    *
    *      E                P(b)
    *
    *               L(b)            Y(b)
    *
    *          C(r)      M(b)    R(b)      X(b)
    *
    *       A(b)    H(b)
    *
    *            E(r)
    *
    *
    *
    * */
}
