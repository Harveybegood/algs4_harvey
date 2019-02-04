package Chapter2_4_PriorityQueues;
/*
*   Suppose that we wish to avoid wasting one position in a heap-ordered array pq[], putting the largest value
*   in pq[0], its children in pq[1] and pq[2], and so forth, proceeding in level order. where are the parents and
*   children of pq[k].
*
* */
public class Ex2_4_10 {
    /*
    *
    *           Induction procedure:
    *
    *                           pq[0]
    *                                                               2*0 + 1, 2*0 + 2
    *                pq[1]                      pq[2]
    *                                                               2*1 + 1, 2*1 + 2
    *        pq[3]            pq[4]        pq[5]        pq[6]
    *                                                                2*2 + 1, 2*2 + 2
    *   pq[7]    pq[8]     pq[9]  pq[10] pq[11] pq[12] pq[13] pq[14]
    *
    *
    *           Children of pk[k]: left -> pk[2*k+1], right -> pk[2*k+2]
    *           Parent of pk[k]: pk[k/2 - 1] or pk[k/2 - 2];
    *                            or  pk[(k-1) / 2]
    *
    * */
}
