package Chapter3_3_BalancedSearchTrees;
/*
*   With 1 bit per node for color, we can represent 2-, 3-, and 4-nodes. How many bits per node would we need to represent
*   5-, 6-, 7-, and 8-nodes with a binary tree?
*
* */
public class Ex19_BitsPerNode {
    /*
    *
    *       5-nodes:            5
    *
    *                     3           7
    *
    *                 1
    *
    *
    *       6-nodes:            5
    *
    *                     3             7
    *
    *                 1        2
    *
    *       7-nodes:            5
    *
    *                     3             7
    *
    *                 1        2    6
    *
    *
    *        8-nodes:            5
    *
    *                     3             7
    *
    *                 1       2     6        8
    *
    *
    *        2 bits can be transformed as 0 0, 0 1, 1 0, 1 1, which means 4 colors.
    *        3 bits can represent 8 colors.
    *
    *        so, the least bits are 2 bits, the most bits are 3 bits.
    *
    * */
}
