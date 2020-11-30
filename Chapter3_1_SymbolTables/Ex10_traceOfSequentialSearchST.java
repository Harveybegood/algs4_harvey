package Chapter3_1_SymbolTables;
/*
*   Give a trace of the process of inserting the keys E A S Y Q U E S T I O N into an initially empty table using
*   SequentialSearchST. How many compares are involved?
*
* */
public class Ex10_traceOfSequentialSearchST {
    /*
    *
    *
    *   0    E
    *   1    A   E
    *   2    S   A   E
    *   3    Y   S   A   E
    *   4    Q   Y   S   A   E
    *   5    U   Q   Y   S   A   E
    *   5    U   Q   Y   S   A   E(change value)
    *   5    U   Q   Y   S() A   E
    *   6    T   U   Q   Y   S   A   E
    *   7    I   T   U   Q   Y   S   A   E
    *   8    O   I   T   U   Q   Y   S   A   E
    *   9   N   O   I   T   U   Q   Y   S   A   E
    *
    *  Total compares: 45
    *
    *
    *
    * */
}
