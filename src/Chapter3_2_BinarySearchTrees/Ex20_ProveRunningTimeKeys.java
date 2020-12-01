package Chapter3_2_BinarySearchTrees;
/*
*   Prove that the running time of the two-argument keys() in a BST with N nodes is at most proportional to the tree
*   height plus the number of keys in the range.
*
* */
public class Ex20_ProveRunningTimeKeys {
    /*
    *
    *
    *       We know that all keys reside at their own nodes respectively.
    *       each of nodes has its now height. We start from the root to each of targeted nodes which would be met less than
    *       or equals to its height in this tree.
    *
    *       Once nodes be found, then we put it into queue.
    *
    *       Considering worst case, for example, keys not found, then each search has to reach at the cost of height of tree
    *
    *       hence, at the worst case, running time equals to the number of nodes * heights + the number of nodes.
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
