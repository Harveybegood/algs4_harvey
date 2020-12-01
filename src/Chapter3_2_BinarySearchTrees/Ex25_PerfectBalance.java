package Chapter3_2_BinarySearchTrees;

import java.util.Arrays;

/*
*   Perfect balance. Write a program that inserts a set of keys into an initially empty BST such that the tree produced
*   is equivalent to binary search, in the sense that the sequence of compares done in the search for any keys in the BST
*   is the same as the sequence of compares used by binary search for the same set of keys.
*
* */
public class Ex25_PerfectBalance {
    // Perfect balance, the depth of each leaf node has the same depth, we need to put the inserted items to be ordered
   /* private class Node{
        private Node left, right;
        private Key key;
        private Value value;
        private int n;
        public Node(Key key, Value value, int n){
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        return node.n;
    }*/
  /*  public void put(Key key, Value value){
        root = put(root, key, value);
    }
    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }*/
  private void insertValueAsBS(int[] values, Ex10_TestBST<Integer, Integer> testBST, int low, int high){
      if (low > high){
          return;
      }
      int middle = low + (high - low) / 2;
      testBST.put(values[middle], values[middle]);
      insertValueAsBS(values, testBST, low, middle - 1);
      insertValueAsBS(values, testBST, middle + 1, high);
  }

    public static void main(String[] args) {
        int[] values = new int[10];
        Arrays.sort(values);
        Ex25_PerfectBalance perfectBalance = new Ex25_PerfectBalance();
        Ex10_TestBST<Integer, Integer> testBST = new Ex10_TestBST<>();
        perfectBalance.insertValueAsBS(values, testBST, 0, values.length - 1);
    }
}
