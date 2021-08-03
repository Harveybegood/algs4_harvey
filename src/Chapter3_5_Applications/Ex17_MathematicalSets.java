package Chapter3_5_Applications;


import java.util.HashSet;

/*
*   Mathematical sets. Your goal is to develop an implementation of the following API MathSET for processing(mutable)
*   mathematical sets:
*   Use a symbol table. Extra credit: Represent sets with arrays of boolean values.
* */
@SuppressWarnings("unchecked")
public class Ex17_MathematicalSets<Key> {
    private Key[] universe;
    private HashSet<Key> hashSet;

    // create a set
    public Ex17_MathematicalSets(Key[] universe){
        this.universe = universe;
        hashSet = new HashSet<>();
        for (Key key : universe){
            hashSet.add(key);
        }
    }
    // put key into the set
    public void add(Key key){
        hashSet.add(key);
    }

    // set of keys in the universe that are not in this set
    public Ex17_MathematicalSets<Key> complement(){
        // this set which is the initial set initializing the constructor, that is my comprehension
        // so, keys that added later via add() will be counted as complement to it.
        Ex17_MathematicalSets<Key> that = new Ex17_MathematicalSets<>(universe);
        for (Key key : this.universe){
            if (!contains(key)){
                that.add(key);
            }
        }
        return that;
    }
    // put any keys from a into the set that are not already there
    public void union(Ex17_MathematicalSets<Key> a){
        for (Key key : a.hashSet){
            if (!this.contains(key)){
                this.add(key);
            }
        }
    }
    // remove any keys from this set that are not in a
    public void intersection(Ex17_MathematicalSets<Key> a){
        for (Key key : this.hashSet){
            if (!a.contains(key)){
                this.delete(key);
            }
        }
    }
    // remove key from the set
    public void delete(Key key){hashSet.remove(key);}
    // is key in the set
    public boolean contains(Key key){return hashSet.contains(key);}
    // is the set empty
    public boolean isEmpty(){return hashSet.isEmpty();}
    // number of keys in the set
    public int size(){return hashSet.size();}

    public static void main(String[] args) {

    }
}
