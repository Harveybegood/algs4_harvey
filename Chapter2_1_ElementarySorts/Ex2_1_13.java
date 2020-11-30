package Chapter2_1_ElementarySorts;
/*
*   Deck sort. Explain how you would put a deck of cards in orders by suit(in the order spades, hearts, clubs,diamonds)
*   and by rank within each suit, with the restriction that the cards must be laid out face down in a row, and the only
*   allowed operations are to check the values of two cards and to exchange two cards(keeping them face down).
*
* */
public class Ex2_1_13 {
    public static void deckSort(Comparable[] deck){
        int N = deck.length;
        int h = 1;
        while (h < N / 3){
            h = h * 3 + 1;
        }
        while (h >= 1){
            for (int i = h; i < N; i++){

            }
        }
    }
}
