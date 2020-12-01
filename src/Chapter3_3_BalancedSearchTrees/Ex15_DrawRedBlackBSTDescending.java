package Chapter3_3_BalancedSearchTrees;
/*
*   Answer the previous two questions for the case when the keys are inserted in descending order.
*
* */
public class Ex15_DrawRedBlackBSTDescending {
    /*
    *
    *       -K         K(b)
    *
    *
    *       -J          K(b)
    *
    *           J(r)
    *
    *
    *       -I                K(b)      ->      J(b)
    *
    *                  J(r)             I(b)            K(b)
    *
    *          I(r)
    *
    *
    *       -H                  J(b)
    *
    *                   I(b)            K(b)
    *
    *               H(r)
    *
    *
    *       -G                  J(b)            ->          J(b)
    *
    *                   I(b)            K(b)           H(r)        K(b)
    *
    *               H(r)                            G(b)    I(b)
    *
    *           G(r)
    *
    *
    *       -F                  J(b)
    *
    *                   H(r)            K(b)
    *
    *               G(b)    I(b)
    *
    *           F(r)
    *
    *       -E                  J(b)            ->          J(b)            ->          H(b)
    *
    *                   H(r)            K(b)        H(r)            K(b)        F(b)            J(b)
    *
    *              G(b)       I(b)              F(r)     I(b)              E(b)      G(b)   I(b)       K(b)
    *
    *          F(r)                         E(b)     G(b)
    *
    *       E(r)
    *
    *
    *       -D                  H(b)
    *
    *                   F(b)            J(b)
    *
    *               E(b)    G(b)     I(b)   K(b)
    *
    *            D(r)
    *
    *       -C                  H(b)                ->              H(b)
    *
    *                   F(b)            J(b)                F(b)            J(b)
    *
    *              E(b)     G(b)    I(b)    K(b)        D(r)    G(b)    I(b)    K(b)
    *
    *           D(r)                                C(b)    E(b)
    *
    *        C(r)
    *
    *
    *       -B                  H(b)
    *
    *                   F(b)            J(b)
    *
    *               D(r)    G(b)    I(b)    K(b)
    *
    *            C(b)   E(b)
    *
    *          B(r)
    *
    *
    *       -A                   H(b)               ->                   H(b)                       ->                      H(b)
    *
    *                   F(b)            J(b)                    F(b)              J(b)                          D(r)                    J(b)
    *
    *               D(r)    G(b)    I(b)    K(b)        D(r)            G(b)    I(b)    K(b)            B(b)            F(b)        I(b)     K(b)
    *
    *            C(b)   E(b)                        B(r)    E(b)                                    A(b)    C(b)    E(b)    G(b)
    *
    *          B(r)                             A(b)    C(b)
    *
    *        A(r)
    *
    *
    *
    * */

}
