package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Is the following implementation of hashCode() legal?
*    public int hashCode(){
*       return 17;
*    }
*   If so, describe the effect of using it. If not, explain why.
*
* */
public class Ex05_IsHashFunctionLegal {
    public int hashCode(){
        return 17;
    }
    public int hash(int s){
        return ((Integer)s).hashCode();
    }

    public static void main(String[] args) {
        Ex05_IsHashFunctionLegal isHashFunctionLegal = new Ex05_IsHashFunctionLegal();
        StdOut.println(isHashFunctionLegal.hash(10));
    }

    // with reference to the definition of hashCode() method under Class Object, which is typically implemented by converting the
    // internal address of the object into an integer, then also known as that objects that are equals will have the same
    // hash value calculated.
    // the return for 17, however, will not reach a expectation as hashCode() that disperse the keys uniformly among the
    // possible 32-bits result values. if so, a performance reduction will not be avoided for methods get(), put()...etc.

}
