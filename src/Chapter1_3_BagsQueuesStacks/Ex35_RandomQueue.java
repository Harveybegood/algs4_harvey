package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Random queue. A random queue stores a collection of Items and supports the following API:
*
*       Item sample()
*
*   Write a class RandomQueue that implements this API. Hint: Use an array representation(with resizing). To remove an Item,
*   swap one at a random position(indexed 0 through N-1)with the one at the last position(index N-1). Then delete and return the
*   return the last object, as in ResizingArrayStack. Write a client that deals bridge hands(13 Items each)using RandomQueue<Card>.
*
*
* */
@SuppressWarnings("unchecked")
public class Ex35_RandomQueue<Item> {
    private Item[] Items;
    private int n;
    public Ex35_RandomQueue(){
        Items = (Item[]) new Object[2];
        n = 0;
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void enqueue(Item Item){
        if (n == Items.length){
            resize(n * 2);
        }
        Items[n] = Item;
        n++;
    }
    public void resize(int newSize){
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < n; i++){
            temp[i] = Items[i];
        }
        Items = temp;
    }
    // remove and return a random Item(sample without replacement)
    public Item dequeue(){
        int i = StdRandom.uniform(0,n);
        Item randomItem = Items[i];
        Items[i] = Items[n-1];
        Items[n-1] = null;
        if (n > 0 && n == Items.length / 4){
            resize(Items.length / 2);
        }
        n--;
        return randomItem;
    }
    // return a random Item, but do not remove(sample with replacement)
    public Item sample(){
        int i = StdRandom.uniform(n-1);
        return Items[i];
    }
    public static class Card{
        String suits;
        String values;
        public Card(String suits, String values){
            this.suits = suits;
            this.values = values;
        }
        public String toString(){
           return  suits + " " + values;
        }
    }
    public static void generateBridgeCards(Ex35_RandomQueue randomQueue){
        String[] suits = {"Spades", "Diamonds", "Hearts", "Clubs"};
        for (int i = 0; i < suits.length; i++){
            randomQueue.enqueue(new Card(suits[i], "A"));
            randomQueue.enqueue(new Card(suits[i], "2"));
            randomQueue.enqueue(new Card(suits[i], "3"));
            randomQueue.enqueue(new Card(suits[i], "4"));
            randomQueue.enqueue(new Card(suits[i], "5"));
            randomQueue.enqueue(new Card(suits[i], "6"));
            randomQueue.enqueue(new Card(suits[i], "7"));
            randomQueue.enqueue(new Card(suits[i], "8"));
            randomQueue.enqueue(new Card(suits[i], "9"));
            randomQueue.enqueue(new Card(suits[i], "10"));
            randomQueue.enqueue(new Card(suits[i], "J"));
            randomQueue.enqueue(new Card(suits[i], "Q"));
            randomQueue.enqueue(new Card(suits[i], "K"));
        }
    }
    public static void main(String[] args) {
        // how can I deal bridge hands
        Ex35_RandomQueue<Card> randomQueue = new Ex35_RandomQueue<>();
        generateBridgeCards(randomQueue);
        for (int i = 0; i < 13; i++){
            StdOut.print(randomQueue.sample() + " ");
            StdOut.println();
        }
        StdOut.println(".........................");
        for (int i = 0; i < 52;i++){
            StdOut.println(randomQueue.dequeue());
        }
    }
}
