package Chapter3_4_HashTables;
/*
*   Which of the following scenarios leads to expected linear running time for a random search hit in a linear-probing hash table?
*
* */
public class Ex13_LinearRunningTime {

    /*
    *   a.  All keys hash to the same index.
    *   key hash    0   1   2   3
    *   A   0       A
    *   B   0           B
    *   C   0               C
    *   D   0                   D
    *   D   0                   (search hit) O(n)
    *
    *   b.  All keys hash to different indices.
    *   key hash    0   1   2   3
    *   A   0       A
    *   B   1           B
    *   C   2               C
    *   D   3                   D
    *   D   3                   (search hit) O(0)
    *
    *   c.  All keys hash to an even-numbered index.
    *   key hash    0   1   2   3   4   5   6   7
    *   A   2               A
    *   B   2                   B
    *   C   2                       C
    *   D   2                           D
    *   D   2                           (search hit) O(n)
    *
    *   d.  All keys hash to different even-numbered indices.
    *   key hash    0   1   2   3   4   5   6   7   8
    *   A   0       A
    *   B   2               B
    *   C   4                       C
    *   D   6                               D
    *   D                                   (search hit) O(0)
    *
    * */

}
