public class Matrix {
    double[][] matrix;
    int numRows;
    int numColumns;
    public Matrix(int row, int column) {
        matrix = new double[row][column];
        numRows = row;
        numColumns = column;
    }

    public Matrix(double[][] mat) {
        matrix = new double[mat.length][mat[0].length];
        for(int i = 0; i < mat.length; i++) {
            for(int k = 0; k < mat[0].length; k++) {
                matrix[i][k] = mat[i][k];
            }
        }
    }

    public static Matrix add(Matrix fi , Matrix se) {
        double[][] o = fi.getRaw();
        double[][] s = se.getRaw();
        if(o.length != s.length) throw new RuntimeException("Matrices do not have same amount of rows.");
        if(o[0].length != s[0].length) throw new RuntimeException("Matrices do not have same amount of columns.");
        double[][] f = new double[o.length][o[0].length];
        for(int i = 0; i < o.length; i++) {
            for(int k = 0; k < o[0].length; k++) {
                f[i][k] = o[i][k] + s[i][k];
            }
        }
        return new Matrix(f);
    }

    public static Matrix subtract(Matrix fi , Matrix se) {
        double[][] o = fi.getRaw();
        double[][] s = se.getRaw();
        if(o.length != s.length) throw new RuntimeException("Matrices do not have same amount of rows.");
        if(o[0].length != s[0].length) throw new RuntimeException("Matrices do not have same amount of columns.");
        double[][] f = new double[o.length][o[0].length];
        for(int i = 0; i < o.length; i++) {
            for(int k = 0; k < o[0].length; k++) {
                f[i][k] = o[i][k] - s[i][k];
            }
        }
        return new Matrix(f);
    }

    public static Matrix negative(Matrix m) {
        double[][] s = m.getRaw();
        for(int i = 0; i < s.length; i++) {
            for(int k = 0; k < s[0].length; k++) {
                s[i][k] *= -1;
            }
        }
        return new Matrix(s);
    }

    public void negate() {
        for(int i = 0; i < matrix.length; i++) {
            for(int k = 0; k < matrix[0].length; k++) {
                matrix[i][k] *= -1;
            }
        }
    }
    
    public double[][] getRaw() {
        return matrix;
    }

    public static Matrix transpose(Matrix o) {
        double[][] t = o.getRaw();
        double[][] f = new double[t[0].length][t.length];
        for(int r = 0; r < t[0].length; r++) {
            for(int c = 0; c < t.length; c++) {
                f[r][c] = t[c][r];
            }
        }
        return new Matrix(f);
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static Matrix multiply(Matrix o, Matrix t) {
        double[][] m1 = o.getRaw();
        double[][] m2 = t.getRaw();
        int m1ColLength = m1[0].length;
        int m2RowLength = m2.length;
        if(m1ColLength != m2RowLength) return null;
        int mRRowLength = m1.length;
        int mRColLength = m2[0].length;
        double[][] mResult = new double[mRRowLength][mRColLength];
        for(int i = 0; i < mRRowLength; i++) {
            for(int j = 0; j < mRColLength; j++) {
                for(int k = 0; k < m1ColLength; k++) {
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return new Matrix(mResult);
    }

}