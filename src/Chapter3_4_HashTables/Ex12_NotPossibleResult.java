package Chapter3_4_HashTables;
/*
*   Suppose that the keys A through G, with the hash values given below, are inserted in some order into an initially empty table
*   of size 7 using a linear-probing table(with no resizing for this problem). Which of the following could not possibly result from
*   inserting these keys?
*   Give the minimum and the maximum number of probes that could be required to build a table of size 7 with these keys, and an
*   insertion order that justifies your answer.
*
*       Keys    A   B   C   D   E   F   G
*       Hash    2   0   0   4   4   4   2
*
* */
public class Ex12_NotPossibleResult {

    /*                          0   1   2   3   4   5   6
    *
                a.              E   F   G   A   C   B   D
                key     hash
                G        2              G
                A        2                  A


                b.              C   E   B   G   F   D   A
                key     hash
                C        0      C
                F        4                      F
                D        4                          D



                c.              B   D   F   A   C   E   G
                key     hash
                B        0      B

                d.              C   G   B   A   D   E   F
                key     hash
                C        0      C
                D        4                      D
                E        4                          E
                F        4                              F



                e.              F   G   B   D   A   C   E
                key     hash


                f.              G   E   C   A   D   B   F
                key     hash
                D                               D

                Result: None of them are hold

                Minimum number of probes are required:
                key     hash                                        probes
                B        0      B                                       1
                C        0          C                                   2
                A        2              A                               1
                G        2                  G                           2
                D        4                      D                       1
                E        4                          E                   2
                F        4                              F               3
                                                                        = 12

                Maximum number of probes are required:
                key     hash                                         probes
                Would be same as Minimum number of probes.
    *
    * */

}
