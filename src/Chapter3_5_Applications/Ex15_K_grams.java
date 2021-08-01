package Chapter3_5_Applications;
/*
*   Write a program that takes a string on standard input and an integer k as command-line argument and puts on standard
*   output a sorted list of the k-grams found in the string, each followed by its index in the string.
*
* */
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex15_K_grams {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String s = StdIn.readString();
        ST<String, Integer> k_gram = findK_Grams(s, k);
        for (String kGrams : k_gram.keys()){
            StdOut.println(kGrams + " " + k_gram.get(kGrams));
        }
    }
    public static ST<String, Integer> findK_Grams(String s, int k){
        ST<String, Integer> kGram = new ST<>();
        for (int i = 0; i < s.length() - k; i++){
            kGram.put(s.substring(i, i + k), i);
        }
        return kGram;
    }
}

/*
*
*           searchingapplications
            app 9
            arc 2
            ati 15
            cat 14
            chi 4
            ear 1
            gap 8
            hin 5
            ica 13
            ing 6
            ion 17
            lic 12
            nga 7
            pli 11
            ppl 10
            rch 3
            sea 0
            tio 16
*
* */