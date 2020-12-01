package Chapter3_1_SymbolTables;
/*
*   Give a trace of the process of inserting the keys E   A   S   Y   Q   U   E   S   T   I   O   N into an initially
*   empty table using BinarySearchST. How many compares are involved?
*
* */
public class Ex11_TraceOfBinarySearchST {
    /*
    *
    *
    *       keys[]                                                  Vals[]
    *       E                                          0            0
    *       A   E                                      2            1   0
    *       A   E   S                                  2            1   0   2
    *       A   E   S   Y                              2            1   0   2   3
    *       A   E   Q   S   Y                          3            1   0   4   2   3
    *       A   E   Q   S   Y   U                      4            1   0   4   2   3   5
    *       A   E   Q   S   Y   U                      4            1   0   4   2   3   5
    *       A   E   Q   S   Y   U                      4            1   0   4   2   3   5
    *       A   E   Q   S   T   Y   U                  4            1   0   4   2   8   3   5
    *       A   E   I   Q   S   T   Y   U              4            1   0   9   4   2   8   3   5
    *       A   E   I   O   Q   S   T   Y   U          4            1   0   9   10  4   2   8   3   5
    *       A   E   I   N   O   Q   S   T   Y   U      5            1   0   9   11  10  4   2   8   3   5
    *
    *
    *       Total compares: 38
    *
    *
    * */
}
