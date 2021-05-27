package Chapter3_4_HashTables;
/*
*   Double probing. Modify SeparateChainingHashST to use a second hash function and pick the shorter of the two lists. Give
*   a trace of the process of inserting the keys E A S Y Q U T I O N in that order into an initially empty table of size M = 3
*   using the function 11 K % M (for the kth letter) as the first hash function and the function 17 K % M (for the kth letter)
*   as the second hash function. Give the average number of probes for random search hit and search miss in this table.
*
* */
public class Ex27_DoubleProbing {
    /*
    *   Key   Value   hash (11 K % M(3))     st[]
    *
    *   E       0       11 0 % 3 = 3        st[0] -> Y|3 -> T|6 -> N|9
    *   A       1       11 1 % 3 = 2        st[1] -> S|2 -> U|5 -> O|8
    *   S       2       11 2 % 3 = 1        st[2] -> A|1 -> Q|4 -> I|7
    *   Y       3       11 3 % 3 = 0        st[3] -> E|0
    *   Q       4       11 4 % 3 = 2
    *   U       5       11 5 % 3 = 1
    *   T       6       11 6 % 3 = 0
    *   I       7       11 7 % 3 = 2
    *   O       8       11 8 % 3 = 1
    *   N       9       11 9 % 3 = 0
    *
    * */

    // cannot understand what difference btw 11 k % 3 and 17 k % 3


    /*
    *    E       0       17 0 % 3 = 3
    *    A       1       17 1 % 3 = 2
    *    S       2       17 2 % 3 = 1
    *    Y       3       17 3 % 3 = 0
    *    Q       4       17 4 % 3 = 2
    *    U       5       17 5 % 3 = 1
    *    T       6       17 6 % 3 = 0
    *    I       7       17 7 % 3 = 2
    *    O       8       17 8 % 3 = 1
    *    N       9       17 9 % 3 = 0
    *
    * */
}
