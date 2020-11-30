package Chapter2_4_PriorityQueues;
/*
*   In MaxPQ, suppose that a client calls insert() with an item that is larger than all items in the queue,
*   and then immediately calls delMax(). Assume that there are no duplicate keys. Is the resulting heap
*   identical to the heap as it was before these operations? Answer the same question for two insert()
*   operations(the first with a key larger than all keys in the queue and the second for a key larger than
*   that one) followed by two delMax() operations.
*
* */
public class Ex2_4_18 {
    /*
    *
    *          calls insert() with an item then calls delMax();
    *          Ans: Identical to the original heap.
    *
    *          calls twice insert() and twice delMax();
    *          Ans: Identical to the original heap.
    *
    *
    *
    *
    * */
}
