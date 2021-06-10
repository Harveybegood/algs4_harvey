package Chapter3_4_HashTables;

import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

/*
*   Deletion: Implement an eager delete() for the methods described in each of the previous two exercises.
*
* */
public class Ex29_Deletion{
    public class DeleteDoubleProbing<Key, Value> extends Ex27_DoubleProbing<Key, Value>{
        public void deleteInDoubleProbing(Key key){
            if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
            if (isEmpty()){return;}
            int indexForFirstHash = hash1(key);
            SequentialSearchST.Node node = st[indexForFirstHash].first;
            while (node != null){
                if (node.key.equals(key)){
                    node.key = null;
                    node.value = null;
                    node = node.next;
                    n--;
                    if (n <= M / 4){
                        resize(M / 2);
                    }
                    return;
                }
                else {
                    SequentialSearchST.Node temp = node;
                    node = temp.next;
                }
            }
            int indexForSecondHash = hash2(key);
            SequentialSearchST.Node node1 = st[indexForSecondHash].first;
            while (node1 != null){
                if (node1.key.equals(key)){
                   /* if (node1.next == null){
                        node1.key = null;
                        node1.value = null;
                        n--;
                    }
                    else {
                        node1 = node1.next;
                        n--;
                    }*/
                    node1.key = null;
                    node1.value = null;
                    node1 = node1.next;
                    n--;
                    if (n <= M / 4){
                        resize(M / 2);
                    }
                }
                else {
                    SequentialSearchST.Node temp = node1;
                    node1 = temp.next;
                }
            }
        }
    }
    public class deleteDoubleHashing<Key, Value> extends Ex28_DoubleHashing<Key, Value>{
        public boolean flag = false;
        public void deleteInDoubleHashing(Key key){
            if (key == null){throw new IllegalArgumentException("Argument key cannot be null");}
            if (isEmpty()){return;}
            int indexForFirstHash = hash1(key);
            if (keys[indexForFirstHash] != null){
                while (!keys[indexForFirstHash].equals(key)){
                    indexForFirstHash = (indexForFirstHash + 1) % M;
                }
                if (keys[indexForFirstHash].equals(key)){
                    flag = true;
                }
                keys[indexForFirstHash] = null;
                values[indexForFirstHash] = null;
                indexForFirstHash = (indexForFirstHash + 1) % M;
                while (keys[indexForFirstHash] != null){
                    Key keyToRedo = keys[indexForFirstHash];
                    Value valueToRedo = values[indexForFirstHash];
                    n--;
                    put(keyToRedo, valueToRedo);
                    indexForFirstHash = (indexForFirstHash + 1) % M;
                }
                if (n < M / 4){
                    resize(M / 2);
                }
                if (flag){
                    return;
                }
            }
            int indexForSecondHash = hash2(key);
            if (keys[indexForFirstHash] != null){
                while (!keys[indexForSecondHash].equals(key)){
                    indexForSecondHash = (indexForSecondHash + 1) % M;
                }
                keys[indexForSecondHash] = null;
                values[indexForSecondHash] = null;
                indexForSecondHash = (indexForSecondHash + 1) % M;
                while (keys[indexForSecondHash] != null){
                    Key keyToRedo = keys[indexForSecondHash];
                    Value valueToRedo = values[indexForSecondHash];
                    n--;
                    put(keyToRedo, valueToRedo);
                    indexForSecondHash = (indexForSecondHash + 1) % M;
                }
                if (n < M / 4){
                    resize(M / 2);
                }
            }
        }
    }

    public static void main(String[] args) {
        Ex29_Deletion deletion1 = new Ex29_Deletion();
        DeleteDoubleProbing<String, Integer> doubleProbing = deletion1.new DeleteDoubleProbing<>();
        doubleProbing.put("E", 0);
        doubleProbing.put("X", 1);
        doubleProbing.put("A", 2);
        doubleProbing.put("M", 3);
        doubleProbing.put("P", 4);
        doubleProbing.put("L", 5);
        StdOut.println("The items stored in ST for the current: ");
        for (String s : doubleProbing.keys()){
            StdOut.println(s + " " + doubleProbing.get(s));
        }
        doubleProbing.deleteInDoubleProbing("E");
        doubleProbing.deleteInDoubleProbing("L");
        doubleProbing.deleteInDoubleProbing("P");
        StdOut.println("The items stored in ST after deleting three items");
        for (String s : doubleProbing.keys()){
            StdOut.println(s + " " + doubleProbing.get(s));
        }
        deleteDoubleHashing<String, Integer> doubleHashing = deletion1.new deleteDoubleHashing<>();
        doubleHashing.put("E", 0);
        doubleHashing.put("X", 1);
        doubleHashing.put("A", 2);
        doubleHashing.put("M", 3);
        doubleHashing.put("P", 4);
        doubleHashing.put("L", 5);
        StdOut.println("The items stored in ST for the current: ");
        for (String s : doubleHashing.keys()){
            StdOut.println(s + " " + doubleHashing.get(s));
        }
        doubleHashing.deleteInDoubleHashing("E");
        doubleHashing.deleteInDoubleHashing("L");
        doubleHashing.deleteInDoubleHashing("P");
        StdOut.println("The items stored in ST after deleting three items");
        for (String s : doubleHashing.keys()){
            StdOut.println(s + " " + doubleHashing.get(s));
        }
    }
}
