package Chapter2_1_ElementarySorts;
/*
*   Suppose that we use insertion sort on a randomly ordered array where elements have only one of three values.
*   Is the running time linear, quadratic, or something in between?
*
* */
public class Ex2_1_8 {
    /*
    *   A   C   B   B   A   C   B   A   C
    *
    * */

    /*
    *       C compares to A, C not less_exch than A, 1 for comparision, 0 for exchange
    *       A   C   B   B   A   C   B   A   C
    *       B compares to C, B less_exch than C and B not less_exch than A, 2 for comparision, 1 for exchange operation.
    *       A   B   C   B   A   C   B   A   C
    *       B compares to C and B, B less_exch than C but not lee than B, 2 for comparision, 1 for exchange operation.
    *       A   B   B   C   A   C   B   A   C
    *       A compares to C, B, B. A less_exch than C, B, B but not less_exch than A, 4 for comparision, 3 for exchange operations.
    *       A   A   B   B   C   C   B   A   C
    *       C compares to C, 1 for comparision, 0 for exchange operation.
    *       A   A   B   B   C   C   B   A   C
    *       B compares to C, C, B. 3 for comparision, 2 for exchange operations.
    *       A   A   B   B   B   C   C   A   C
    *       A compares to C, C, B, B, B, A. 5 for comparision, 4 for exchange operations.
    *       A   A   A   B   B   B   C   C   C
    *       C compares to C. 1 for comparision. 0 for exchange operation.
    *
    *       Total number of comparision: 19, 11 for exchange operations.
    *       as known, linear time should be as N which over here is 9. Quadratic should be as 9^2 = 81
    *
    *       the running time is between linear and quadratic.
    *
    *
    * */
}
