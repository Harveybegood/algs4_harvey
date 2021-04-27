package Chapter3_4_HashTables;
/*
*   Answer the previous question for search miss, assuming the search key is equally likely to hash to each table position.
*   Search miss:
* */
public class Ex14_RunningTimeSearchMiss {
    /*
            a. All keys hash to the same index.
    *       key hash    0   1   2   3   4   5   6   7   8 ... n
            A   0       A                                   1
            B   0           B                               2
            C   0               C                           3
            D   0                   D                       4
            .................................................
            X   0                                           n
                                                            search miss: O(n)

            b. All keys hash to different indices.
            key hash    0   1   2   3   4   5   6   7   8 ... n
            A   0       A
            B   1           B
            C   2               C
            D   3                   D
                                                            search miss: O(1)

            c. All keys hash to an even-numbered index.
            key hash    0   1   2   3   4   5   6   7   8 ... n
            A   2               A                             1
            B   2                   B                         2
            C   2                       C                     3
            D   2                           D                 4
            .................................................
            X   2                                             n
                                                            search miss: O(n)

            d. All keys hash to different even-numbered indices.
            key hash    0   1   2   3   4   5   6   7   8 ... n
            A   0       A
            B   2               B
            C   4                       C
            D   6                               D
            ..................................................
            X   n                                             n
                                                            search time: O(1)
    *
    *
    * */
}
