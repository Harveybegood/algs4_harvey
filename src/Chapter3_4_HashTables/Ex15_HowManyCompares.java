package Chapter3_4_HashTables;
/*
*   How many compares could it take, in the worst case, to insert N keys into an initially empty table, using linear probing
*   with array resizing?
*
* */
public class Ex15_HowManyCompares {
    /*
    *       key     hash        0   1   2   3   4   5   6   7   8   9           Compares
    *       A       0           A                                                   0
    *       B       0               B                                               1
    *       C       0                   C                                           2
    *       D       0                       D                                       3
    *
    *       ..................................................................
    *
    *       X       0                                                       X      n-3
    *       Y       0                                                              n-2
    *       Z       0                                                              n-1
    *                                                                            (n * (0 + n-1))/2 = n^2/2
    *                                                                            and then calculating the compares for resizing
    *                                                                            there is just double previous one
    *                                                                            n ^ 2 / 2 * 2 = n^2
    *
    *
    * */
}
