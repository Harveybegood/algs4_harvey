package Chapter1_3_BagsQueuesStacks;
/*
*   Suppose that a client performs an intermixed sequence of (stack) push and pop operations. The push operations
*   put the integers 0 through 9 in order onto the stack; the pop operations print out the return values.
*   Which of the following sequence(s) could not occur?
*
*
* */
public class Ex03_intermixedSequenceStack {
    /*
    *
    *
    *
    *           a.   4 3 2 1 0 9 8 7 6 5    -> 0 1 2 3 4, 4 3 2 1 0/  5 6 7 8 9, 9 8 7 6 5 Yes
                b.   4 6 8 7 5 3 2 9 0 1    -> Not
                c.   2 5 6 7 4 8 9 3 1 0    -> 0 1 2, 2. / 3 4 5, 5/ 6, 6/ 7, 7/ 4/ 8, 8/ 9, 9/ 3, 1, 0 Yes
                d.   4 3 2 1 0 5 6 7 8 9    -> 0 1 2 3 4, 4 3 2 1 0/ 5, 5/ 6, 6/ ...... Yes
                e.   1 2 3 4 5 6 9 8 7 0    -> Yes
                f.   0 4 6 5 3 8 1 7 2 9    -> Yes
                g.   1 4 7 9 8 6 5 3 0 2    -> No
                h    2 1 4 3 6 5 8 7 9 0    -> Yes
    *
    *
    *
    *
    * */
}
