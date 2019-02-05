package Chapter2_4_PriorityQueues;
/*
*   Suppose that your application will have a huge number of insert operations, but only a few remove the maximum operation
*   Which priority-queue implementation do you think would be most effective: heap, unordered array, or ordered array?
*
* */
public class Ex2_4_11 {
    /*
    *       advantage of compare:
    *       Key points here are two that huge number of insert operation and a few remove the maximum operation.
    *
    *       unordered array: the order of algorithm growth for insert will be O(1);
    *                        then for remove the maximum will be O(N) which is linear
    *
    *
    *       ordered array: the order of algorithm growth for insert will be O(lgN)
    *                      then for remove the maximum will be O(1)
    *
    *
    *       heap: the order of algorithm growth for insert will be O(lgN)
    *             then for remove the maximum will be O(lgN)
    *
    *
    *
    *
    *       Due to the key point of huge number of insert operation, the most effective implementation - unordered array
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */
}
