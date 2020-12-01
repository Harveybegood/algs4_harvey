package Chapter3_1_SymbolTables;
/*
*   Which of the symbol-table implementations in this section would you use for an application that does 10^6 put()
*   and 10^3 get() operations, randomly intermixed? Justify your answer.
*
* */
public class Ex14_WhichOptimal {
    /*
    *
    *
    *
    *
    *             algorithm               worst-case cost       average-case cost                 efficiently
    *            (data structure)        (after N inserts)     (after N random inserts)          support ordered
    *                                                                                               operations?
    *                                   search       insert       searchhit        insert
    *           sequential search
    *          (unordered linked list)     N            N           N/2              N                  no
    *
    *            binary search
    *            (ordered array)           lgN          2N          lgN              N                  yes
    *
    *
    * */
}
