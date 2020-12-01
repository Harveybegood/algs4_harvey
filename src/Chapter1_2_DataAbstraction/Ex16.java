package Chapter1_2_DataAbstraction;


/*
*   Rational numbers. Implement an immutable data type Rational for rational numbers that supports addition,
*   subtraction, multiplication, and division.
*
*   You do not have to worry about testing for overflow(see exercise 1.2.17), but use as instance variables two long
*   values that represent the numerator and denominator to limit the possibility of overflow. Use Euclid's algorithm
*   (see page4)to ensure that the numerator and denominator never have any common factors. Include a test client
*   that exercise all of your methods.
*
* */
public class Ex16 {
    public class Rational{
        private long num;
        private long denom;
        public Rational(long numerator, long denominator){
            long g = gcd(numerator, denominator);
            this.num = numerator / g;
            this.denom = denominator / g;
        }
     /*   public Rational plus(Rational b){
            // sum of this number and b

        }
        public Rational minus(Rational b){
            // difference of this number and b

        }*/
        public Rational times(Rational b){
            // product of this number and b
            Rational c = new Rational(this.num, b.denom);
            Rational d = new Rational(b.num, this.denom);
            return new Rational(c.num * d.num, c.denom * d.denom);
        }
     /*   public Rational divides(Rational b){
            // quotient of this number and b

        }*/

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
        public String toString(){
            return num + "/" + denom;
        }
    }

    public static long gcd(long a, long b){
        if (b == 0) return a;
        long r = a % b;
        return gcd(b, r);
    }

    public static void main(String[] args) {

    }
}
