package Chapter3_4_HashTables;
/*
*   Show the result of using the delete() method on page 471 to delete C from the table resulting from using LinearProbingHashST
*   with our standard indexing client(shown on page 469).
*
* */
public class Ex17_ShowResult {
    /*
    *   The standard indexing client:
    *   key hash    0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15
    *               P   M           A   C   S   H   L       E               R   X
    *               10  9           8   5   0   5   11      12              3   7
    *
    *   The alert when we delete a key is to rehash table only related all of the keys in the cluster to the right of the deleted keys.
    *
    *   First, set the key-value pair C-5 to be null.
    *               P   M           A   null S  H   L       E               R   X
    *               10  9           8   null 0  5   11      12              3   7
    *   Second, rehash table, which there are 3 key-value pair in the cluster, so we shall see the result after 3 times of rehashing
    *               P   M           A   S null  H   L       E               R   X
    *               10  9           8   0 null  5   11      12              3   7
    *
    *               P   M           A   S   H  null L       E               R   X
    *               10  9           8   0   5  null 11      12              3   7
    *
    *   Result:     P   M           A   S   H   L   null    E               R   X
    *               10  9           8   0   5   11  null    12              3   7
    *
    * */
}
