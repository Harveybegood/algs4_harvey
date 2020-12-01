package Chapter3_3_BalancedSearchTrees;

import Chapter3_2_BinarySearchTrees.Ex10_TestBST;
import Chapter3_2_BinarySearchTrees.Ex32_Certification;
import edu.princeton.cs.algs4.StdOut;

/*
*   Certification. Add to RedBlackBST a method is23() to check that no node is connected to two red links and that there are no
*   right-leaning red links and a method isBalanced() to check that all paths from the root to a null link have the same number of
*   black links. Combine these methods with code from isBST() in Ex3.2.32 to create a method isRedBlackBST() that checks that the tree
*   is a red-black BST.
*
* */
@SuppressWarnings("unchecked")
public class Ex33_Certification {
    RedBlackBST<String, Integer> redBlackBST = new RedBlackBST<>();
    // is23()
    public boolean is23(){
        return is23(redBlackBST.root);
    }
    private boolean is23(RedBlackBST.Node node){
        if (node == null){
            return true;
        }
        if (redBlackBST.isRed(node.left) && redBlackBST.isRed(node.left.left)){
            return false;
        }
        if (redBlackBST.isRed(node.left) && redBlackBST.isRed(node.right)){
            return false;
        }
        if (redBlackBST.isRed(node.right)){
            return false;
        }
       /* is23(node.left);
        is23(node.right);
        return true*/;
        return is23(node.left) && is23(node.right);
    }
    // isBalanced(), all paths from the root to a null link have the same number of black links
    private boolean isBalanced(){
        int black = 0;
        RedBlackBST.Node x = redBlackBST.root;
        while (x != null){
            if (!redBlackBST.isRed(x)){
                black++;
            }
            x = x.left;
        }
        return isBalanced(redBlackBST.root, black);
    }
    private boolean isBalanced(RedBlackBST.Node node, int black){
        if (node == null){
            return black == 0;
        }
        if (!redBlackBST.isRed(node)){
            black--;
        }
        return isBalanced(node.left, black) && isBalanced(node.right, black);
    }
    // isBST()

    // isRedBlackBST()
    private boolean isRedBlackBST(){
        if (isBalanced() && is23() && new Ex32_Certification<>().isBST()) {
            return true;
        }

        return false;
    }
    public static void main(String[] args) {
        Ex33_Certification certification = new Ex33_Certification();
        certification.redBlackBST.put("S", 12);
        certification.redBlackBST.put("E", 4);
        certification.redBlackBST.put("B", 11);
        certification.redBlackBST.put("R", 10);
        certification.redBlackBST.put("T", 15);
        certification.redBlackBST.put("H", 8);
        StdOut.println(certification.isRedBlackBST());
    }
}
