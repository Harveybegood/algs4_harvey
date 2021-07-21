package Chapter3_5_Applications;

import edu.princeton.cs.algs4.SequentialSearchST;


/*
*   Develop a SET implementation SequentialSearchSET by starting with the code for SequentialSearchST and eliminating all
*   of the code involving values.
*
* */
public class Ex02_SequentialSearchSET {
    private class SET<Key>{
        private SequentialSearchST<Key, Boolean> st;
        public SET(){
            st = new SequentialSearchST<>();
        }
        public boolean isEmpty(){return st.isEmpty();}
        public boolean contains(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return st.contains(key);
        }
        public int size(){return st.size();}
        public void put(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            st.put(key, false);
        }
        public boolean get(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            return st.get(key);
        }
        public void delete(Key key){
            if (key == null){throw new IllegalStateException("Argument cannot be null");}
            st.delete(key);
        }
        public Iterable<Key> keys(){
            return st.keys();
        }
    }
}
