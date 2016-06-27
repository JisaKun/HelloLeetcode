package SetMatrixZeroes;

/**
 * Created by Zhang Hongchuan on 2016/6/27.
 * https://leetcode.com/problems/set-matrix-zeroes/
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int LINES = matrix.length;
        int ROWS = matrix[0].length;
        boolean row = false;
        boolean line = false;
        for (int i : matrix[0]) {
            if (i == 0) {
                line = true;
                break;
            }
        }
        for (int[] eachLine : matrix) {
            if (eachLine[0] == 0) {
                row = true;
                break;
            }
        }
        // mark
        for (int m = 1; m < LINES; m++) {
            for (int n = 1; n < ROWS; n++) {
                if (matrix[m][n] == 0) {
                    matrix[m][0] = 0;
                    matrix[0][n] = 0;
                }
            }
        }
        // set
        for (int m = 1; m < LINES; m++) {
            for (int n = 1; n < ROWS; n++) {
                if (matrix[m][0] == 0 || matrix[0][n] == 0) {
                    matrix[m][n] = 0;
                }
            }
        }
        if (line) {
            for (int i = 0; i < ROWS; i++) {
                matrix[0][i] = 0;
            }
        }
        if (row) {
            for (int i = 0; i < LINES; i++) {
                matrix[i][0] = 0;
            }
        }


    }
}
