package Chapter3_3_BalancedSearchTrees;
/*
*   Draw the 2-3 tree that results when you insert the keys Y L P M X H C R A E S in that order into an initially empty tree
*
* */
public class Ex02_Draw2_3Tree02 {
    /*
    *
    *           Y
    *
    *
    *           Y
    *       L
    *
    *       L &  P both are RED
    *
    *           Y    -> rotateLeft()  Y    ->  rotateRight      P        flipColor()        P
    *       L                       P                       L       Y                   L       Y
    *           P               L
    *
    *
    *           P       -> rotateLeft()    ->   p
    *       L       Y                       L       Y
    *           M                       M
    *
    *
    *               P
    *           L       Y
    *       M      X
    *
    *
    *               P       ->    rotateRight()         P               M &  X are RED
    *           L       Y                       M              Y
    *        M      X                       H       L       X
    *    H
    *
    *
    *                 P                 C is RED
    *           M           Y
    *       H       L   X
    *    C
    *
    *
    *                 P       ->   rotateLeft()       ->        p                 M & X are RED -> flipColor()
    *           M           Y                          M             X
    *       H      L     X                         H        L    R       Y
    *    C            R                         C
    *
    *
    *
    *                 P      ->       rotateRight()             P               flipColor() C is RED
    *           M           X                            M              X
    *       H       L   R       Y                   C         L     R       Y
    *    C                                      A       H
    * A
    *
    *                 P
    *           M           X
    *       C       L    R      Y
    *    A      H
    *         E
    *
    *
    *                  P         ->       rotateLeft()          P
     *           M           X                          M               X
     *       C       L    R      Y                  C       L       R       Y
     *    A      H           S                  A       H       S
     *         E                                     E
    *
    * */
}
