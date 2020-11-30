package Chapter3_1_SymbolTables;
/*
*   Memory usage. Compare the memory usage of BinarySearchST with that of SequentialSearchST for N key-value pairs,
*   under the assumption described in SECTION1.4. Do not count the memory for the keys and values themselves, but do
*   count references to them. For BinarySearchST, assume that array resizing is used, so that the array is between
*   25 percent and 100 percent full.
*
* */
@SuppressWarnings("unchecked")
public class Ex21_MemoryUsage {
    public class BinarySearchST<Key extends Comparable<Key>, Value>{
        /*
        *   16(class) + 4(n) +
        *   8(key[]) + 16(object) + 4(int) + 4(padding)
        *   8(value[]) + 16(object) + 4(int) + 4(padding)
        *
        *
        *
        * */
        private Key[] keys;
        private Value[] values;
        private int n;
        public BinarySearchST(int cap){
            keys = (Key[]) new Comparable[cap];
            values = (Value[]) new Object[cap];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public int rank(Key key){
            int low = 0;
            int high = n - 1;
            while (low <= high){
                int mid = low + (high - low)/2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) high = mid - 1;
                else if (cmp > 0) low = mid + 1;
                else return mid;
            }
            return low;
        }
        public void resize(int newSize){
            Key[] keyTemp = (Key[]) new Comparable[newSize];
            Value[] valueTemp = (Value[]) new Object[newSize];
            for (int i = 0; i < n; i++){
                keyTemp[i] = keys[i];
                valueTemp[i] = values[i];
            }
            keys = keyTemp;
            values = valueTemp;
        }
        public void delete(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for delete() is null");
            if (isEmpty()) return;
            int i = rank(key);
            if (key.compareTo(keys[i]) == 0)
                values[i] = null;
            n--;
            if (n > 0 && n == keys.length / 4)
                resize(n/2);
        }
        public Value get(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for get() is null");
            if (isEmpty()) return null;
            int i = rank(key);
            if (key.compareTo(keys[i]) == 0)
                return values[i];
            else
                return null;
        }
        public void put(Key key, Value value){
            if (key == null || value == null) throw new IllegalArgumentException("Argument for put() is null");
            int i = rank(key);
            if (key.compareTo(keys[i]) == 0)
                values[i] = value;
            if (n == keys.length / 2)
                resize(n * 2);
            for (int j = n; j > i; j--){
                keys[j] = keys[j-1];
                values[j] = values[j-1];
            }
            keys[i] = key;
            values[i] = value;
            n++;
        }
    }
    public class SequentialSearchST<Key, Value>{

        /*
        *   16(class) + 8(first node)+ 8(inner class extra overhead) + 8(next) + 8(key) + 8(value) + 4(int) + 4(padding);
        *
        *
        *
        * */
        public class Node{
            Node next;
            Key key;
            Value value;
            public Node(Node next, Key key, Value value){
                this.next = next;
                this.key = key;
                this.value = value;
            }
        }
        private Node first;
        private int n;
        public SequentialSearchST(Node first){
            this.first = first;
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public Value get(Key key){
            if (key == null) throw new IllegalArgumentException("Argument for get() is null");
            if (isEmpty()) return null;
            for (Node node = first; node != null; node = node.next){
                if (node.key == key)
                    return node.value;
            }
            return null;
        }
        public void put(Key key, Value value){
            if (key == null || value == null) throw new IllegalArgumentException("Argument for put() is null");
            if (isEmpty()){
                first = new Node(first, key, value);
                first.next = null;
                n++;
                return;
            }
            for (Node node = first; node != null; node = node.next){
                if (first.key == key){
                    node.value = value;
                    return;
                }
            }
            first = new Node(first, key, value);
            first = first.next;
            n++;
        }
    }
}
