package Chapter3_5_Applications;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/*
*   Sparse matrices. Develop an API and an implementation for sparse 2D matrices. Support matrix addition and
*   multiplication. Include constructors for row and column vectors.
*       reference to https://algs4.cs.princeton.edu/35applications/
*
* */
public class Ex23_SparseMatrices {
    private interface SparseMatricesAPI{
        SparseMatrix sum(SparseMatrix sparseMatrices);
        SparseMatrix dot(SparseMatrix sparseMatrices);
        void put(int row, int col, double value);
        double get(int row, int col);
        void delete(int row, int col);
    }
    public class SparseVector {
        private int d;                   // dimension
        private ST<Integer, Double> st;  // the vector, represented by index-value pairs

        public SparseVector(int d) {
            this.d = d;
            this.st = new ST<>();
        }

        public void put(int i, double value) {
            if (i < 0 || i >= d) throw new IllegalArgumentException("Illegal index");
            if (value == 0.0) st.delete(i);
            else st.put(i, value);
        }

        public double get(int i) {
            if (i < 0 || i >= d) throw new IllegalArgumentException("Illegal index");
            if (st.contains(i)) return st.get(i);
            else return 0.0;
        }

        public void delete(int key){
            st.delete(key);
        }

        public int nnz() {
            return st.size();
        }

        public double dot(SparseVector that) {
            if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
            double sum = 0.0;

            // iterate over the vector with the fewest nonzeros
            if (this.st.size() <= that.st.size()) {
                for (int i : this.st.keys())
                    if (that.st.contains(i)) sum += this.get(i) * that.get(i);
            } else {
                for (int i : that.st.keys())
                    if (this.st.contains(i)) sum += this.get(i) * that.get(i);
            }
            return sum;
        }

        public double dot(double[] that) {
            if (this.d != that.length){
                throw new IllegalArgumentException("The length between vector and array are not equal");
            }
            double sum = 0.0;
            for (int i : st.keys())
                sum += that[i] * this.get(i);
            return sum;
        }

        public SparseVector scale(double alpha) {
            SparseVector c = new SparseVector(d);
            for (int i : this.st.keys()) c.put(i, alpha * this.get(i));
            return c;
        }

        public SparseVector plus(SparseVector that) {
            if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
            SparseVector c = new SparseVector(d);
            for (int i : this.st.keys()) c.put(i, this.get(i));                // c = this
            for (int i : that.st.keys()) c.put(i, that.get(i) + c.get(i));     // c = c + that
            return c;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            for (int i : st.keys()) {
                s.append("(" + i + ", " + st.get(i) + ") ");
            }
            return s.toString();
        }

    }

    public class SparseMatrix implements SparseMatricesAPI{
        private int n;                 // n-by-n matrix
        private SparseVector[] rows;   // the rows, each row is a sparse vector
        private SparseVector[] cols;   // the cols, each col is a sparse vector

        // initialize an n-by-n matrix of all 0s
        public SparseMatrix(int n) {
            this.n = n;
            rows = new SparseVector[n];
            cols = new SparseVector[n];
            for (int i = 0; i < n; i++){
                rows[i] = new SparseVector(n);
                cols[i] = new SparseVector(n);
            }
        }

        // put A[i][j] = value
        public void put(int i, int j, double value) {
            if (i < 0 || i >= n) throw new IllegalArgumentException("Illegal index");
            if (j < 0 || j >= n) throw new IllegalArgumentException("Illegal index");
            if (value == 0){
                delete(i, j);
                return;
            }
            rows[i].put(j, value);
            cols[j].put(i, value);
        }

        // return A[i][j]
        public double get(int i, int j) {
            if (i < 0 || i >= n) throw new IllegalArgumentException("Illegal index");
            if (j < 0 || j >= n) throw new IllegalArgumentException("Illegal index");
            return rows[i].get(j);
        }

        // return the number of nonzero entries (not the most efficient implementation)
        public int nnz() {
            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += rows[i].nnz();
            return sum;
        }

        // return this + that
        public SparseMatrix plus(SparseMatrix that) {
            if (this.n != that.n) throw new RuntimeException("Dimensions disagree");
            SparseMatrix result = new SparseMatrix(n);
            for (int i = 0; i < n; i++)
                result.rows[i] = this.rows[i].plus(that.rows[i]);
            return result;
        }

        @Override
        public void delete(int row, int col) {
            if (row < 0 || row >= n){
                throw new IllegalArgumentException("Dimension disagree");
            }
            if (col < 0 || col >= n){
                throw new IllegalArgumentException("Dimension disagree");
            }
            rows[row].delete(col);
            cols[col].delete(row);
        }

        @Override
        public SparseMatrix sum(SparseMatrix sparseMatrices) {
            return null;
        }

        @Override
        public SparseMatrix dot(SparseMatrix sparseMatrices) {
            if (n != sparseMatrices.n){
                throw new IllegalArgumentException("Both cols and rows of Matrix must be equal, respectively");
            }
            SparseMatrix result = new SparseMatrix(n);
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    double dot = rows[i].dot(sparseMatrices.cols[j]);
                    if (dot != 0){
                        result.put(i, j, dot);
                    }
                }
            }
            return result;
        }

        // return a string representation
        public String toString() {
            String s = "n = " + n + ", nonzeros = " + nnz() + "\n";
            for (int i = 0; i < n; i++) {
                s += i + ": " + rows[i] + "\n";
            }
            return s;
        }
    }
    public static void main(String[] args) {
        Ex23_SparseMatrices sparseMatrices = new Ex23_SparseMatrices();
        SparseMatrix sparseMatrix1 = sparseMatrices.new SparseMatrix(2);
        SparseMatrix sparseMatrix2 = sparseMatrices.new SparseMatrix(2);
        sparseMatrix1.put(0, 0, 2.0);
        sparseMatrix1.put(0, 1, 3.0);
        sparseMatrix1.put(1, 0, 4.0);
        sparseMatrix1.put(1, 1, 5.0);
        StdOut.println("Print out matrix1");
        StdOut.println(sparseMatrix1.toString());
        sparseMatrix2.put(0, 0, 6.0);
        sparseMatrix2.put(0, 1, 7.0);
        sparseMatrix2.put(1, 0, 8.0);
        sparseMatrix2.put(1, 1, 9.0);
        StdOut.println("Print out matrix2");
        StdOut.println(sparseMatrix2.toString());
        StdOut.println("test client for matrix1 + matrix2");
        SparseMatrix resultSum = sparseMatrix1.plus(sparseMatrix2);
        StdOut.println(resultSum.toString());
        StdOut.println("test client for matrix1 * matrix2");
        SparseMatrix resultMultiplication = sparseMatrix1.dot(sparseMatrix2);
        StdOut.println(resultMultiplication.toString());
        StdOut.println("test client for deletion");
        sparseMatrix1.delete(0, 0);
        sparseMatrix2.delete(1, 1);
        StdOut.println(sparseMatrix1.toString());
        StdOut.println(sparseMatrix2.toString());
    }
}

/*
*   Print out matrix1
    n = 2, nonzeros = 4
    0: (0, 2.0) (1, 3.0)
    1: (0, 4.0) (1, 5.0)

    Print out matrix2
    n = 2, nonzeros = 4
    0: (0, 6.0) (1, 7.0)
    1: (0, 8.0) (1, 9.0)

    test client for matrix1 + matrix2
    n = 2, nonzeros = 4
    0: (0, 8.0) (1, 10.0)
    1: (0, 12.0) (1, 14.0)

    test client for matrix1 * matrix2
    n = 2, nonzeros = 4
    0: (0, 36.0) (1, 41.0)
    1: (0, 64.0) (1, 73.0)

    test client for deletion
    n = 2, nonzeros = 3
    0: (1, 3.0)
    1: (0, 4.0) (1, 5.0)

    n = 2, nonzeros = 3
    0: (0, 6.0) (1, 7.0)
    1: (0, 8.0)

*
*
* */