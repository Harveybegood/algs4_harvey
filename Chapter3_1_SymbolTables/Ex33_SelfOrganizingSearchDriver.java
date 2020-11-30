package Chapter3_1_SymbolTables;
/*
*   Driver for self-organizing search. Write a driver program for self-organizing search implementation (see ex3.1.22)
*   that uses put() to fill a symbol table with N keys, then does 10N successful searches according to a predefined
*   probability distribution. Use this driver to compare the running time of your implementation from ex3.1.22 with
*   BinarySearchST for N = 10^3, 10^4, 10^5, and 10^6 using the probability distribution where search hits the ith
*   smallest key with probability 1/2^i.
*
* */
public class Ex33_SelfOrganizingSearchDriver {
    static Ex22_SelfOrganizingSearch<Integer, String> selfOrganizingSearch = new Ex22_SelfOrganizingSearch<>(10);
    static binarySearchST<Integer, String> binarySearchST = new binarySearchST<>(10);

    public static void main(String[] args) {

    }
    static int[] N = {1000, 10000, 100000, 1000000};
    // fill a symbol table with N keys
    public static void fillSymbolTable(int[] N){
        int j = 0;
        for (int i = 0; j < 4 && i < N[j]; i++){
            selfOrganizingSearch.put(i, "Value" + i);
            binarySearchST.put(i, "Value" + i);
            j++;
        }
    }
}
