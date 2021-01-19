package Chapter3_2_BinarySearchTrees;
/*
*   Array representation. Develop a BST implementation that represents the BST with three arrays(pre-allocated to the
*   maximum size given in the constructor): one with the keys, one with array indices corresponding to left links,
*   and one with array indices corresponding to right links. Compare the performance of your program with that of the
*   standard implementation.
*
* */
@SuppressWarnings("unchecked")
public class Ex41_ArrayRepresentation<Key extends Comparable<Key>, Value> {
    private class BSTWithThreeArrays{
        int n = 10;
        Key[] keys;
        Value[] values;
        int[] left, right;
        int numberOfNodeOfTree;
        int index;
        public BSTWithThreeArrays(int numberOfNodeOfTree){
            keys = (Key[]) new Comparable[n];
            values = (Value[]) new Object[n];
            left = new int[n];
            right = new int[n];
            this.index = 10;
            this.numberOfNodeOfTree = numberOfNodeOfTree;
        }
        // one with array indices corresponding to left links, one with array indices corresponding to right links
        public void put(Key key, Value value){
            put(0, key, value);
        }
        private void put(int i, Key key, Value value){
            if (keys[i] == null){
                keys[i] = key;
                values[i] = value;
            }
            int cmp = key.compareTo(keys[i]);
            if (cmp < 0){
                i = 2 * i + 1;
                put(i, key, value);
            }
            else if (cmp > 0){
                i = 2 * i + 2;
                put(i, key, value);
            }
            else {
                values[i] = value;
            }
        }
    }
}
