package Chapter2_4_PriorityQueues;
/*
*   What is the minimum number of items that must be exchanged during a remove the maximum operation in a heap of size
*   N with no duplicate keys? Give a heap of size 15 for which the minimum is achieved. Answer the same questions for
*   two and three successive remove the maximum operations.
*
* */
public class Ex2_4_14 {
    /*
    *       Based on couples of  properties that heap has:
    *       binary tree
    *       increased order(or decreased order)
    *       the height of level is lgN
    *
    *
    *       the procedure to remove the maximum operation:
    *       exchange the index 1 with last index(N+1),
    *       remove the top item which has been swap to on the last position - eg: pq[N+1] = null;
    *       sink the last item which has been swap to on the top position by comparing it with it's children
    *
    *
    *       Hence, a heap of size N:
    *
    *       lgN - 1;
    *
    *       a heap of size 15:
    *
    *       3
    *
    *       two and three successive remove the maximum operations:
    *       Two: lgN + lgN = 6
    *       Three: lgN + lgN + lgN = 9
    *
    *
    * */
}
