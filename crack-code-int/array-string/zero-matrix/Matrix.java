public class Matrix {
    int[][] m;
    int[][] spare;

    public Matrix() {
        this.m = new int[25][25];
    }

    public Matrix(int h) {
        this.m = new int[h][h];
    }

    public Matrix(int h, int w) {
        this.m = new int[h][w];
    }

    public void populateEmptyMatrix() {
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                m[i][j] = j+1;
            }
        }
    }

    // O(n^2) Time - Can I do this better?
    public void rotate90Degrees() {
        int rows = m.length;
        int cols = m[0].length;
        this.spare = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spare[i][cols-j-1] = m[j][i];
            }
        }

        m = spare;
        spare = null;
    }

    // O(mn)?
    public void zeroMatrix(int row, int col) {
        for (int i = 0; i < m[row].length; i++) {
            m[row][i] = 0;
        }

        for (int i = 0; i < m.length; i++) {
            m[i][col] = 0;
        }
    }

    public String toString() {
        String r = "";

        for (int i = 0; i < m.length; i++) {

            for (int j = 0; j < m[i].length; j++) {

                if (j == 0) {
                    r += "[";
                } else {
                    r += ", ";
                }

                r += m[i][j];

                if (j == m[i].length-1) {
                    r += "]\n";
                }

            }

        }

        return r;
    }
    public static void main(String[] args) {
        Matrix test = new Matrix();
        test.populateEmptyMatrix();
        test.zeroMatrix(12, 6);
        System.out.println(test.toString());
    }
}