package Chapter3_5_Applications;

import Chapter1_4AnalysisOfAlgorithms.Ex13_AmountOfMemory;
import Chapter3_3_BalancedSearchTrees.RedBlackBST;
import Chapter3_4_HashTables.LinearProbingHashST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.LinkedList;

/*
*   List. Develop an implementation of the following API:
*
*   Hint: Use two symbol table, one to find the ith item in the list efficiently, and the other to efficiently search by item.
*   (Java's java.util.List interface contains methods like these but does not supply any implementation that efficiently supports
*   all operations.)
*
* */
public class Ex27_List<Item> implements Iterable<Item> {
    private RedBlackBST<Double, Item> findItemByPositionST;
    private LinearProbingHashST<Item, Double> searchItemST;
    public Ex27_List(){
        findItemByPositionST = new RedBlackBST<>();
        searchItemST = new LinearProbingHashST<>();
    }

    // add item to the front
    public void addFront(Item item){
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (contains(item)){return;}
        double minKey;
        if (isEmpty()){
            minKey = 0;
        }
        else {
            minKey = findItemByPositionST.min() - 1;
        }
        findItemByPositionST.put(minKey, item);
        searchItemST.put(item, minKey);
    }

    // add item to the back
    public void addBack(Item item){
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (contains(item)){return;}
        double maxKey;
        if (isEmpty()){
            maxKey = 0;
        }
        else {
            maxKey = findItemByPositionST.max() + 1;
        }
        findItemByPositionST.put(maxKey, item);
        searchItemST.put(item, maxKey);
    }

    // remove from the front
    public Item deleteFront(){
        if (isEmpty()){return null;}
        Item frontItem = findItemByPositionST.get(findItemByPositionST.min());
        findItemByPositionST.delMin();
        searchItemST.delete(frontItem);
        return frontItem;
    }

    // remove from the back
    public Item deleteBack(){
        if (isEmpty()){return null;}
        Item backItem = findItemByPositionST.get(findItemByPositionST.max());
        findItemByPositionST.delMax();
        searchItemST.delete(backItem);
        return backItem;
    }

    // remove item from the list
    public void delete(Item item){
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        double index = searchItemST.get(item);
        findItemByPositionST.delete(index);
        searchItemST.delete(item);
    }

    // add item as the ith in the list
    public void add(int i, Item item){
        if (i < 0 || i >= size()){
            throw new IllegalArgumentException("Invalid argument");
        }
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (contains(item)){
            return;
        }
        double key = findItemByPositionST.select(i);
        findItemByPositionST.put(key, item);
        searchItemST.put(item, key);
    }

    // remove the ith item from the list
    public Item delete(int i){
        if (i < 0 || i > size()){throw new IllegalArgumentException("Invalid index");}
        Item item = findItemByPositionST.get(findItemByPositionST.select(i));
        delete(item);
        return item;
    }

    // is key in the list?
    public boolean contains(Item item){
        if (item == null){throw new IllegalArgumentException("Argument cannot be null");}
        return searchItemST.contains(item);
    }

    // is the list empty?
    public boolean isEmpty(){
        return size() == 0;
    }

    // number of items in the list
    public int size(){
        return findItemByPositionST.size();
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        //LinkedList<Double> linkedList;
        Queue<Double> queue;
        public ListIterator(){
            queue = new Queue<>();
            for(Double key : findItemByPositionST.keys()){
                queue.enqueue(key);
            }
        }
        public boolean hasNext(){
            return queue.size() > 0;
        }
        public Item next(){
            return findItemByPositionST.get(queue.dequeue());
        }
    }

    public static void main(String[] args) {
        Ex27_List<Integer> list = new Ex27_List<>();
        list.addFront(10);
        list.addFront(11);
        list.addBack(20);
        list.addBack(21);
        for (Integer i : list){
            StdOut.print(i + "  ");
        }
        StdOut.println("\nAdd item 15 to index 2");
        list.add(2, 15);
        for (Integer i : list){
            StdOut.print(i + "  ");
        }
        StdOut.print("\nDelete the front item of List: ");
        StdOut.println(list.deleteFront());
        for (Integer i : list){
            StdOut.print(i + "  ");
        }
        StdOut.print("\nDelete the back item of List: ");
        StdOut.println(list.deleteBack());
        for (Integer i : list){
            StdOut.print(i + "  ");
        }
        StdOut.println("\nDelete the item 20 of List: ");
        list.delete(new Integer(20));
        for (Integer i : list){
            StdOut.print(i);
        }
        StdOut.println("\nDelete item on index 0th");
        list.delete(0);
        for (Integer i : list){
            StdOut.print(i + "  ");
        }
    }

}
