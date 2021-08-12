package Chapter3_5_Applications;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.RedBlackBST;

/*
*   Spell checker. With the file dictionary.txt from the booksite as command-line argument, the BlackFilter client described on page 491
*   prints all misspelled words in a text file taken from standard input. Compare the performance of RedBlackBST, SeparateChainingHashST,
*   and LinearProbingHashST for the file WarAndPeace.txt(available on the booksite)with this client and discuss the results.
        dictionary file: algs4-data/WarandPeace.txt
*       standard input: algs4-data/warAndPeace.txt
*
* */
@SuppressWarnings("unchecked")
public class Ex31_SpellChecker {
    private RedBlackBST redBlackBST;
    private SeparateChainingHashST separateChainingHashST;
    private LinearProbingHashST linearProbingHashST;

    public void misspelledWordsForRedBlackBST(String file){
        redBlackBST = new RedBlackBST();
        In in = new In(file);
        while (!in.isEmpty()){
            redBlackBST.put(in.readString(), 1);
        }
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (!redBlackBST.contains(word)){
                StdOut.println(word);
            }
        }
    }
    public void misspelledWordsForSeparateChainingHashST(String file){
        separateChainingHashST = new SeparateChainingHashST();
        In in = new In(file);
        while (!in.isEmpty()){
            separateChainingHashST.put(in.readString(), 1);
        }
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (!separateChainingHashST.contains(word)){
                StdOut.println(word);
            }
        }
    }
    public void misspelledWordForLinearProbingHashST(String file){
        linearProbingHashST = new LinearProbingHashST();
        In in = new In(file);
        while (!in.isEmpty()){
            linearProbingHashST.put(in.readString(), 1);
        }
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (!linearProbingHashST.contains(word)){
                StdOut.println(word);
            }
        }
    }

    public void comparePerformance(String file){
        StdOut.printf("%11s %22s %19s", "RedBlackBST", "SeparateChainingHashST", "LinearProbingHashST");
        Stopwatch timer1 = new Stopwatch();
        misspelledWordsForRedBlackBST(file);
        StdOut.printf("%11.2f",timer1.elapsedTime());
        Stopwatch timer2 = new Stopwatch();
        misspelledWordsForSeparateChainingHashST(file);
        StdOut.printf("%22.2f",timer2.elapsedTime());
        Stopwatch timer3 = new Stopwatch();
        misspelledWordForLinearProbingHashST(file);
        StdOut.printf("%19.2f",timer3.elapsedTime());
        StdOut.println();
    }

    public static void main(String[] args) {
        String file = args[0];
        Ex31_SpellChecker spellChecker = new Ex31_SpellChecker();
        spellChecker.comparePerformance(file);
    }
}

/*
*       RedBlackBST SeparateChainingHashST LinearProbingHashSTBOOK
        dCHAPTER
        "dWefll,e
        afre
        Buonapartes.
        don‘t
        Antichrist
        Antichrist
        ‘faithful
        slave,‘
        fwas
        idn
        180d5,
        fand
        tfhe
        wellknown
        scarletliveried
        "Heavens!
        scented,
        "First
        friend‘s
        ambassador‘s?
        today‘s
        festivities
        woundup
        "Don‘t
        Novosiltsev‘s
        dispatch?
        stale
        enthusiast
        don‘t
        don‘t
        Alexander‘s
        None.
        selfabnegation
        don‘t
        Hardenburg
        Europe!";
               0.75                  0.61               0.43


*
*
*
* */
