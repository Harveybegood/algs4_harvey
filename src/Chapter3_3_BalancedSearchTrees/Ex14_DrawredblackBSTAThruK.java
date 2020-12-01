package Chapter3_3_BalancedSearchTrees;
/*
*   Draw the red-black BST that results when you insert letters A through K in order into an initially empty tree,
*   then describe what happens in general when trees are built by insertion of keys in ascending order(see also the figure in the text)
*
*
* */
public class Ex14_DrawredblackBSTAThruK {

    /*
    *
    *       -A          A(b)
    *
    *
    *
    *       -B          A(b)                 ->         B(b)                                                              rotate right
    *
    *                       B(r)                    A(r)
    *
    *
    *       -C          B(b)                 ->         B(b)                                                              color flip
    *
    *               A(r)    C(r)                    A(b)    C(b)
    *
    *
    *       -D          B(b)                ->          B(b)                                                               rotate right
    *
    *               A(b)    C(b)                    A(b)    D(b)
    *
    *                           D(r)                     C(r)
    *
    *
    *       -E          B(b)                ->           B(b)               ->              D(b)                            color flip -> rotate right
    *
    *               A(b)    D(b)                    A(b)        D(r)                  B(r)          E(b)
    *
    *                   C(r)    E(r)                        C(b)    E(b)            A(b)    C(b)
    *
    *
    *       -F            D(b)              ->              D(b)                                                            rotate right
    *
    *               B(r)        E(b)                B(r)            F(b)
    *
    *           A(b)    C(b)        F(r)        A(b)    C(b)      E(r)
    *
    *
    *       -G              D(b)                    ->         D(b)                 ->              D(b)                    color flip  ->  color clip
    *
    *               B(r)            F(b)                B(r)            F(r)                B(b)            F(b)
    *
    *           A(b)    C(b)      E(r)  G(r)        A(b)    C(b)     E(b)   G(b)        A(b)    C(b)    E(b)    G(b)
    *
    *
    *       -H              D(b)                        ->              D(b)                                                rotate right
    *
    *               B(b)            F(b)                        B(b)            F(b)
    *
    *           A(b)    C(b)    E(b)    G(b)            A(b)        C(b)    E(b)     H(b)
    *
    *                                       H(r)                                    G(r)
    *
    *
    *       -I                 D(b)                                 ->                      D(b)                                             ->               D(b)                          color flip -> rotate right
    *
    *                B(b)                 F(b)                              B(b)                            F(b)                               B(b)                           H(b)
    *
    *           A(b)       C(b)      E(b)      H(b)                 A(b)            C(b)            E(b)            H(r)                A(b)            C(b)         F(r)              I(b)
    *
    *                                       G(r)    I(r)                                                        G(b)    I(b)                                    E(b)        G(b)
    *
    *
    *       -J                  D(b)                                                    ->                      D(b)                                                                        rotate right ->
    *
    *             B(b)                          H(b)                                            B(b)                            H(b)
    *
    *       A(b)        C(b)            F(r)            I(b)                            A(b)            C(b)            F(r)            J(b)
    *
    *                               E(b)    G(b)                J(r)                                                E(b)    G(b)      I(r)
    *
    *
    *
    *       -K                      D(b)                                            ->                  D(b)                                    ->                      D(b)
    *
    *
    *                    B(b)                     H(b)                                      B(b)                      H(b)                                  B(b)                      H(r)
    *
    *             A(b)        C(b)        F(r)              J(b)                      A(b)          C(b)        F(r)         J(r)                       A(b)     C(b)         F(b)             J(b)
    *
    *                                 E(b)      G(b)    I(r)    K(r)                                       E(b)      G(b)  I(b)    K(b)                                    E(b)  G(b)       I(b)    K(b)
    *
    *
    *
    *                                    H(b)                                                                                                                                   color flip -> color flip -> rotate right
    *
    *                        D(r)                    J(b)
    *
    *                B(b)           F(b)        I(b)        K(b)
    *
    *          A(b)       C(b)   E(b)   G(b)
    *
    *
    *
    *
    * */


}
