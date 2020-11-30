package Chapter1_3_Bags_Queues_Stacks;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*

*   Random queue. A random queue stores a collection of items and supports the following API:
*
*   Write a class RandomQueue that implements this API. Hints: Use an array representation(with resizing).
*   To remove an item, swap one at a random position (indexed 0 through  N-1) with the one at the last
*   position(index N-1). Then delete and return the last object, as in ResizingArrayStack. Write a client
*   that deals bridge hands(13 cards each) using RandomQueue<Card>.
*
* */
public class Ex35 {
    @SuppressWarnings("unchecked")
    public class RandomQueue<Item> {
        private Item[] items = (Item[]) new Object[1];
        private int N = 0;

        //public void RandomQueue(){}
        boolean isEmpty() {
            return N == 0;
        }         // is the queue empty

        public int size() {
            return N;
        }

        void enqueue(Item item) {// add an item
            //t i = 0;
            if (N == items.length) {
                resize(2 * N);
            }
            items[N++] = item;
        }

        @SuppressWarnings("unchecked")
        Item dequeue() {             // remove and return a random item(sample without replacement)
            if (isEmpty()) {
                throw new RuntimeException("Queue underflow");
            }
            int randomIndex = StdRandom.uniform(0, N);
            Item randomItem = items[randomIndex];
            items[randomIndex] = items[N - 1];
            items[N - 1] = null;
            N--;
            if (N > 0 && N == items.length / 4) {
                resize(items.length / 2);
            }
            return randomItem;

        }

        Item sample() {              // return a random item, but do not remove,(sample with replacement)
            if (isEmpty()) {
                throw new RuntimeException("Queue underflow");
            }
            int randomIndex = StdRandom.uniform(0, N);
            return items[randomIndex];
            //return randomItem;
        }

        @SuppressWarnings("unchecked")
        public void resize(int newSize) {
            Item[] newArray = (Item[]) new Object[newSize];
            for (int i = 0; i < size(); i++) {
                newArray[i] = items[i];
            }
            items = newArray;
        }
    }
    public static void main(String[] args) {
        Ex35 ex35 = new Ex35();
      // fillQueueWithBridgeHandsCard()
        RandomQueue<Card> randomQueue = ex35.new RandomQueue<>();
        fillQueueWithBridgeHandsCards(randomQueue);
        for (int i = 0; i < 2; i ++){
            int count = 0;
            StdOut.println("Hand " + (i + 1));
            while (count < 13){
                StdOut.println(randomQueue.dequeue());
                count++;
            }
            StdOut.println();
        }
        Card sample = randomQueue.sample();
        StdOut.println("Size before sample: " + randomQueue.size() + " Expected: 26");
        StdOut.println("Random item: " + sample);
        StdOut.println("Size after sample: " + randomQueue.size() + " Expected: 26");
    }
    @SuppressWarnings("unchecked")
    private static void fillQueueWithBridgeHandsCards(RandomQueue randomQueue){
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
    private static class Card{
        String value;
        String suit;
        public Card(String value, String suit){
            this.value = value;
            this.suit = suit;
        }
        public String toString(){
            return value + " - " + suit;
        }
    }
}
