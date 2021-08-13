package Chapter3_5_Applications;

import Chapter3_4_HashTables.LinearProbingHashST;

/*
*   Develop classes HashSTint and HashSTdouble for maintaining sets of keys of primitive int and double types, respectively.
*   (Convert generic to primitive types in the code of LinearProbingHashST)
*
* */
@SuppressWarnings("unchecked")
public class Ex04_ClassesHashSTintHashSTdouble {
    // I do not write the LinearProbingHashST accordingly, just apply for it with primitive date type
    public LinearProbingHashST<Integer, Integer> stint;
    public LinearProbingHashST<Double, Double> stdouble;
    public class HashSTint{
        public HashSTint(){
            stint = new LinearProbingHashST<>();
        }
        public boolean isEmpty(){return stint.N == 0;}
        public int size(){return stint.N;}
        public boolean contains(int key){
            return stint.get(key) != null;
        }
        public int get(int key){
            return stint.get(key);
        }
        public void put(int key, int value){
            stint.put(key, value);
        }
        public void delete(int key){
            stint.delete(key);
        }
        public Iterable<Integer> keys(){
            return stint.keys();
        }
    }
    public class HashSTdouble{
        public HashSTdouble(){
            stdouble = new LinearProbingHashST<>();
        }
        public boolean isEmpty(){return stdouble.N == 0;}
        public int size(){return stdouble.N;}
        public boolean contains(double key){return stdouble.contains(key);}
        public double get(double key){
            return stdouble.get(key);
        }
        public void put(double key, double value){
            stdouble.put(key, value);
        }
        public void delete(double key){
            stdouble.delete(key);
        }
        public Iterable<Double> keys(){
            return stdouble.keys();
        }
    }
}
