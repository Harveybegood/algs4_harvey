package Chapter2_4_PriorityQueues;
/*
*   Criticize the following idea: To implement find the maximum in constant time, why not use a stack or a queue, but keep
*   track of the maximum value inserted so far, then return that value for find the maximum.
*
* */
public class Ex2_4_2 {
    /*
    *   This idea would not work because it only keeps track of the current maximum. After a remove-the-maximum operation
     *  it would not be possible to know which is the next maximum in constant time, requiring a linear operation to
     *  find the new maximum.
    *
    * */
}
