package Chapter3_4_HashTables;
/*
*   How many compares could it take, in the worst case, to insert N keys into an initially empty table, using linear probing
*   with array resizing?
*
* */
public class Ex15_HowManyCompares {
    /*
            before array resizing
    *       key     hash        0   1   2   3   4 ... n/2 - 2  n/2 - 1 ...  n                  Compares
    *       A       0           A                                                               0
    *       B       0               B                                                           1
    *       C       0                   C                                                       2
    *       D       0                       D                                                   3
    *
    *       ..................................................................
    *
    *       P       0                                   P                                (n/2)-2
    *       the number of keys reach to half full of the hash table
    *       Q       0                                          Q                         (n/2)-1
    *                                                                            (n/2 * (0 + (n/2)-1))/2 ~ n^2/8
    *
    *       after array resizing
    *                                                                            and then calculating the compares for resizing
    *                                                                            which requires rehashing table n/2: n^2/8
    *
    *
    *       key     hash        0   1   2   3   4 ... n/2 - 2  n/2 - 1 n/2 ... n   n + 1   n + 2  ...  2n
    *       X       0                                                   X
    *
    *       Z       0                                                          Z
    *
    *                                                                   (n * (n/2 + n))/2 ~ n^2/2
    *
    *                                                                   Total compares: 5n^2/8
    *
    *
    * */
}
