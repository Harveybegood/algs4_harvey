package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Certification. Add assert statements to BinarySearchST to check algorithm invariants and data structure integrity
*   after every insertion and deletion. For example, every index i should always be equal to rank(select(i)) and the array
*   should always be in order.
*
* */
@SuppressWarnings("unchecked")
public class Ex30_Certification {
    private static binarySearchST<String, Integer> binarySearchST = new binarySearchST<>(5);
    public static void main(String[] args) {
        for (int i = 0; !StdIn.isEmpty(); i++){
            String s = StdIn.readString();
            binarySearchST.put(s, i);
        }
        for (String s : binarySearchST.keys()){
            StdOut.println(s + "  " + binarySearchST.get(s));
        }
        binarySearchST.put("O", 3);
        assert indexIEqualToRank();
        assert inOrder();
        binarySearchST.delete("O");
        assert indexIEqualToRank();
        assert inOrder();
    }
    public static boolean indexIEqualToRank(){
        for (int i = 0; i < binarySearchST.size(); i++){
            if (binarySearchST.rank(binarySearchST.select(i)) != i){
                return false;
            }
        }
        return true;
    }
    public static boolean inOrder(){
        for (int i = 0; i < binarySearchST.size(); i++){
            if (binarySearchST.get(binarySearchST.select(i)) > binarySearchST.get(binarySearchST.select(i+1))){
                return false;
            }
        }
        return true;
    }
}
