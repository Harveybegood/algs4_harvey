package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Sort by reverse domain. Write a data type Domain that represents domain names, including an appropriate compareTo()
*   method where the natural order is in order of the reverse domain name. For example, the reverse domain of cs.princeton
*   .edu is edu.princeton.cs. This is useful for web log analysis. Hint: Use s.split("\\.") to split the string s into
*   tokens, delimited by dots. Write a client that reads domain names from standard input and prints the reverse domains
*   in sorted order.
*
* */
public class Ex14_SortByReverseDomain {
    private static class Domain implements Comparable<Domain>{
        private String[] domainName;
        private int n;
        public Domain(String name){
            this.domainName = name.split("\\.");
            n = domainName.length;
        }
        // store domainName in reverse order
        // return string representation - domainName, delimited by .
        public String toString(){
            if (n == 0) return "";
            String s = domainName[0];
            for (int i = 1; i < n; i++){
                s = domainName[i] + "." + s;
            }
            return s;
        }
        //String[] Names = new String[3];

        public int compareTo(Domain that){
            int n = domainName.length;
            for (int i = 0; i < n; i++){
                if (this.domainName[i].compareTo(that.domainName[i]) > 0) return 1;
                else if (this.domainName[i].compareTo(that.domainName[i]) < 0) return -1;
            }
        /*    if (this.domainName[0].compareTo(that.domainName[0]) > 0){return 1;}
            else if (this.domainName[0].compareTo(that.domainName[0]) < 0){return -1;}
            else if(this.domainName[1].compareTo(that.domainName[1]) > 0){return 1;}
            else if (this.domainName[1].compareTo(that.domainName[1]) < 0){return -1;}
            else if (this.domainName[2].compareTo(that.domainName[2]) > 0){return 1;}
            else if (this.domainName[2].compareTo(that.domainName[2]) < 0){return -1;}*/
            return 0;
        }
    }

    public static void main(String[] args) {
        String[] input = StdIn.readAllLines();
        Domain[] domains = new Domain[input.length];
        for (int i = 0; i < domains.length; i++){
            domains[i] = new Domain(input[i]);
        }
        Arrays.sort(domains);
        /*for (Domain domain : domains){
            StdOut.println(domain.domainName);
        }*/
        for (int i = 0; i < domains.length; i++){
            StdOut.println(domains[i]);
        }
    }
}
