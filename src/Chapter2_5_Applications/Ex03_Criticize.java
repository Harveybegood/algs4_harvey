package Chapter2_5_Applications;

/*
*   Criticize the following implementation of a class intended to represent account balances. Why is compareTo()
*   a flawed implementation of the Comparable interface?
*   Describe a way to fix this problem.
*
* */
public class Ex3_Criticize {

    public class Balance implements Comparable<Balance>
    {
        private double amount;
        public int compareTo(Balance that){
            if (this.amount < that.amount - 0.005) return -1;
            if (this.amount > that.amount + 0.005) return +1;
            return 0;
        }
    }

    /*
    *   Suppose that this.amount equals to 0.005, that.amount equals to 0.006.
    *   Intuitively, the method should return -1, while we get the value of returning is 0 which means both equals. so
    *   we have got a wrong result.
    *
    *   To fix this problem is that two variable should be compared directly.
    *
    *   CompareTo():
    *       Reflexive, Antisymmetric, Transitive
    * */
}
