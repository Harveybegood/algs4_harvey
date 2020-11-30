package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/*
*   Random iterator. Write an iterator for RandomQueue<Item> from the previous exercise that returns the items
*   in random order.
*
* */
@SuppressWarnings("unchecked")
public class Ex36 {
    public class RandomQueue<Item> implements Iterable<Item>{
        Item[] list = (Item[]) new Object[1];
        private int size = 0;
        public boolean isEmpty(){return size == 0;}
        public int size(){return size;}

        public void enqueue(Item item){
            if (size() == list.length){resize(2 * list.length);}
            list[size] = item;
            size++;
        }
        public Item dequeue(){
            if (isEmpty()){throw new RuntimeException("list underflow");}
            int randomIndex = StdRandom.uniform(0, size());
            Item randomItem = list[randomIndex];
            list[randomIndex] = list[size-1];
            list[size-1] = null;
            size--;
            if (size > 0 && size == list.length/4){resize(list.length/2);}
            return randomItem;
        }
        public Item sample(){
            if (isEmpty()){throw new RuntimeException("list underflow");}
            int randomIndex = StdRandom.uniform(0, size());
            Item randomItem = list[randomIndex];
            return randomItem;
        }

        public void resize(int newSize){
            Item[] newArray = (Item[]) new Object[newSize];
            for (int i = 0; i < list.length; i++){
                newArray[i] = list[i];
            }
            list = newArray;
        }

        public Iterator<Item> iterator(){
            return new iteratorQueue();
        }
        public class iteratorQueue implements Iterator<Item>{
                int index = 0;
                Item[] arrayCopy = (Item[]) new Object[list.length];
                public iteratorQueue(){
                    copyArray();
                    shuffleItems();
                }
                @Override
                public boolean hasNext() {return index < size;}

                @Override
                public Item next() {
                    Item item = arrayCopy[index];
                    index++;
                    return item;
                }
                public void copyArray(){
                    for (int i = 0; i < size; i++){arrayCopy[i] = list[i];}
                }
                public void shuffleItems(){
                    for(int i = 0; i < size; i++){
                        int randomIndex = StdRandom.uniform(0, size);
                        Item temp = arrayCopy[i];
                        arrayCopy[i] = arrayCopy[randomIndex];
                        arrayCopy[randomIndex] = temp;
                    }
                }
            }
    }

    public static void main(String[] args) {
        Ex36 ex36 = new Ex36();
        RandomQueue<Card> randomQueue = ex36.new RandomQueue<>();
        fillQueueWitBridgeHands(randomQueue);
        StdOut.println("Cards:\n");
        for (Card card : randomQueue){
            StdOut.println(card);
        }
    }

    public static void fillQueueWitBridgeHands(RandomQueue randomQueue){
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        for (int i = 0; i < suits.length; i++){
            randomQueue.enqueue(new Card("A", suits[i]));
            randomQueue.enqueue(new Card("2", suits[i]));
            randomQueue.enqueue(new Card("3", suits[i]));
            randomQueue.enqueue(new Card("4", suits[i]));
            randomQueue.enqueue(new Card("5", suits[i]));
            randomQueue.enqueue(new Card("6", suits[i]));
            randomQueue.enqueue(new Card("7", suits[i]));
            randomQueue.enqueue(new Card("8", suits[i]));
            randomQueue.enqueue(new Card("9", suits[i]));
            randomQueue.enqueue(new Card("10", suits[i]));
            randomQueue.enqueue(new Card("J", suits[i]));
            randomQueue.enqueue(new Card("Q", suits[i]));
            randomQueue.enqueue(new Card("K", suits[i]));
        }
    }

    public static class Card{
        String value;
        String suit;
        public Card(String value, String suit){
            this.value = value;
            this.suit = suit;
        }
        public String toString(){return value + " -> " + suit;}
    }
}
