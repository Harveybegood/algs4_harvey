package Chapter2_4_PriorityQueues;
/*
*   Draw all of the different heaps that can be made from the five keys A B C D E, then draw all of the different heaps
*   that can be made from the five keys A A A B B.
*
* */
public class Ex2_4_9{
   /*
   *        A B C D E
    *
    *       Idea: E that must be the position 1 and being at level 1
    *             C D both can be at left child of level 2 and four of them(A B C D) can be at right child of level 2
    *             each one of them (A B) can be either at left child or right child of level 3
    *
    *               E                               E                           E
    *       D                C              D               B           D               A
    *   A       B                       A       C                   B       C
    *
    *
    *       A A A B B
    *
    *       Idea: There is one of B must be at position 1, and meanwhile the other B can be either left or right child
    *             of level 2,
    *             Rest of all A will be randomly at other position.
    *
    *                   B                                           B
    *           B               A                            A              B
    *
    *       A       A       A                           A          A    A
    *
   *
   *
   *
   * */
}
