package Chapter3_3_BalancedSearchTrees;
/*
*   Draw the 2-3 tree that results when you insert the keys E A S Y Q U T I O N in that order into an initially empty tree.
*
* */
public class Ex01_Draw2_3Tree01 {
    /*
    *                           E
    *
    *                           E
    *                       A
    *
    *                           flapColors()
    *                           E
    *                       A       S
    *
    *                           The rotation of rotateLeft() occurs
    *                           E                   ->              E                   The color of S is RED
    *                       A       S                           A        Y
    *                                   Y                           S
    *
    *                           The rotation of rotateRight() occurs   and then rotateLeft()   and E is RED
    *                           E                   ->              E                  ->           S
    *                       A       Y                           A       S                       E       Y
    *                           S                                   Q       Y               A       Q
    *                       Q
    *
    *                           rotateRight() and E and Q are RED
    *                           S                   ->              S
    *                       E       Y                           E       Y
    *                    A      Q                           A       U
    *                               U                           Q
    *
    *                               E and Y are RED
    *                           S           ->          S           ->      S
    *                       E       Y               E       Y           E       Y
    *                    A      U               A       U           A       T
    *                       Q                       T                   Q       U
    *                           T               Q
    *
    *                           .......
    *                           .......
    *                           .......
    *
    * */
}
