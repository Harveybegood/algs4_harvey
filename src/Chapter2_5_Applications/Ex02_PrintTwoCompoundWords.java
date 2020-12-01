package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdIn;

/*
*   Write a program that reads a list of words from standard input and prints all two-word compound words in the list.
*   For example, if after, thought, and afterthought are in the list, then afterthought is a compound word.
*
* */
public class Ex2_PrintTwoCompoundWords {
    public static void main(String[] args) {
        String[] s = StdIn.readString().split("\\s+");

    }
  /*  public List<String> getCompoundWords(ArrayList<String> wordList){
        Collections.sort(wordList);
        Set<String> wordsSet = new HashSet<>(wordList);
        List<String> compoundWords = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++){
            for (int j = i + 1; j < wordList.size(); j++){
                if (wordList.get(j).startsWith(wordList.get(i))){
                    String restOfWord = wordList.get(j).substring(wordList.get(i).length());
                    if (wordsSet.contains(restOfWord)){
                        compoundWords.add(wordList.get(j));
                    }
                }else {
                    break;
                }
            }
        }
        return compoundWords;
    }*/

}
