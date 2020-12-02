package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

import static Chapter1_3_BagsQueuesStacks.Ex35_RandomQueue.generateBridgeCards;

/*
*   Random iterator. Write an iterator for RandomQueue<Item> from the previous exercise that returns the items in random order.
*
* */
@SuppressWarnings("unchecked")
public class Ex36_RandomIterator<Item> implements Iterable<Item>{
    static Ex35_RandomQueue randomQueue = new Ex35_RandomQueue();
    public Iterator<Item> iterator(){
        return new randomQueueIterator();
    }
    private class randomQueueIterator implements Iterator<Item>{

        public boolean hasNext(){return randomQueue.size() != 0;}
        public Item next(){
            return (Item) randomQueue.dequeue();
        }

    }
    public static void main(String[] args) {
        Ex36_RandomIterator<Ex35_RandomQueue.Card> randomIterator = new Ex36_RandomIterator();
        generateBridgeCards(randomQueue);
        for (Ex35_RandomQueue.Card card : randomIterator){
            StdOut.println(card);
        }
    }
}
