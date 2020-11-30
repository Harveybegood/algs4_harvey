package Chapter3_2_BinarySearchTrees;


import edu.princeton.cs.algs4.StdOut;

/*
*   Certification. Write a method isBST() takes a Node as argument and returns true if the argument node is the root of
*   a binary search tree, false otherwise. Hint: This task is also more difficult than it might seem, because the order
*   in which you call methods in the previous three exercises is important.
*
*       BST has such properties as follows:
*       . The left subtree of a node contains only nodes with keys less than the node's key.
*       . The right subtree of a node contains only nodes with keys greater than the node's key.
*       . Likewise, each of child nodes abide to the same as above mentioned.
*       . And no duplicated keys.
*
*       Solution(text):
*          if(!isBinaryTree(root)) return false;
*          if(isOrdered(root, min(), max())) return false;
*          if(!hasNoDuplicates(root)) return false;
*          return true;
*k
* */
@SuppressWarnings("unchecked")
public class Ex32_Certification<Key extends Comparable<Key>, Value> {
    Ex29_BinaryTreeCheck<String, Integer> binaryTreeCheck = new Ex29_BinaryTreeCheck<>();
    Ex30_OrderCheck<String, Integer> orderCheck = new Ex30_OrderCheck<>();
    Ex31_EqualKeyCheck<String, Integer> equalKeyCheck = new Ex31_EqualKeyCheck<>();
    public static void main(String[] args) {
        Ex32_Certification<String, Integer> certification = new Ex32_Certification<>();
         Ex10_TestBST.Node root = new Ex10_TestBST().new Node("S", 6, 1);
         root.left = new Ex10_TestBST().new Node("E", 2, 1);
         root.right = new Ex10_TestBST().new Node("X", 7, 1);
         root.left = new Ex10_TestBST().new Node("A", 1, 1);
        StdOut.println("Expecting return true: " + certification.isBST());
        Ex10_TestBST<String, Integer>.Node root1 = new Ex10_TestBST<String, Integer>().new Node("S", 6, 1);
        root1.left = new Ex10_TestBST<String, Integer>().new Node("X", 4, 1);
        root1.right = new Ex10_TestBST<String, Integer>().new Node("A", 2, 1);
        StdOut.println("Expecting return false: " + certification.isBST());
    }
    public boolean isBST(){
        return isBST(new Ex10_TestBST<String, Integer>().root, null, null);
    }
    private boolean isBST(Ex10_TestBST<String, Integer>.Node node, String min, String max){
        if (!binaryTreeCheck.isBinaryTree()){
            return false;
        }
        if (!orderCheck.isOrdered(min, max)){
            return false;
        }
        if (!equalKeyCheck.hasNoDuplicate()){
            return false;
        }
        return true;
    }
}
