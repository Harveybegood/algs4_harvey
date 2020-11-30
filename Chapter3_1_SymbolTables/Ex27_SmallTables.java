package Chapter3_1_SymbolTables;
/*
*   Small tables. Suppose that a BinarySearchST client has S search operations and N distinct keys. Give the order of
*   growth of S such that the cost of building the table is the same as the cost of all the searches.
*
* */
public class Ex27_SmallTables {
    public static void main(String[] args) {
        int n = 1000;
        binarySearchST<Integer, String> smallTable = new binarySearchST<>(n);
        /*  smallTable.put();
        *   = rank() + assign value + move from back
        *   => rank() = lgn
        *   => assign value = n
        *   => move from back = 1/2 * n(n-1)/2
        * */

        /*  smallTable.get();
        *   = rank() + return value
        *
        *
        * */

        /*
        *   Actually, the action of "move from back" is the extra operation for put().
        *
        *
        * */
    }

}
