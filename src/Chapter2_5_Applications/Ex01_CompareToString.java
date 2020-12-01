package Chapter2_5_Applications;
/*
*   Consider the following implementation of the compareTo() method for String. How does the third line help with efficiency?
*
* */
public class Ex1_CompareToString {
    //Ex1_CompareToString ex1_compareToString = new Ex1_CompareToString();
    private String s;
    public int compareTo(String that){
        if (this.s == that){return 0;}
        // if both String object equals, then avoid extra operations such as iterated compares.
        int n = Math.min(this.s.length(), that.length());
        for (int i = 0; i < n; i++){
            if (this.s.charAt(i) > that.charAt(i)) return +1;
            else if (this.s.charAt(i) < that.charAt(i)) return -1;
        }
        return this.s.length() - that.length();
    }

    public static void main(String[] args) {
        Ex1_CompareToString ex1_compareToString = new Ex1_CompareToString();

    }
}
