import java.io.*;
import java.util.*;
public class Matrix extends ArithmeticException {
    private static void swapColumns(int[][] A, int i, int j) {
        for (int k = 0; k < A.length; k++) {
            int temp = A[k][i];
            A[k][i] = A[k][j];
            A[k][j] = temp;
        }
    }

    public static class MatrixFormatException extends ArithmeticException {
        public MatrixFormatException(String message) {
            super(message);
        }
    }

    public static int[][] swapMatrix(int[][] A) {

        int max = maxElement(A);
        int maxInd = 0, maxInd2 = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (max < A[i][j]) {
                    max = A[i][j];
                    maxInd = i;
                    maxInd2 = j;
                    break;
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (maxInd == i) {
                    int[] tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp;
                }
                if (maxInd2 == j) {
                    swapColumns(A, i, j);
                }
            }
        }
        return A;
    }

    public static int[][] readMatrixFromFile(File path_to_the_File) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path_to_the_File));
        String line;
        int[][] matrix = null;
        int n = 0;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (matrix == null) {
                n = Integer.parseInt(line);
                matrix = new int[n][n];
            } else {
                String[] tokens = line.split("\\s+");
                if (tokens.length != n) {
                    throw new NumberFormatException("Invalid matrix format in file");
                }
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(tokens[j]);
                }
                i++;
            }
        }
        bufferedReader.close();
        if (matrix == null) {
            throw new NumberFormatException("Empty matrix in file");
        }
        return matrix;
    }

    public static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int maxElement(int[][] A) {
        int max = A[0][0];
        int maxInd = 0, maxInd2 = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (max < A[i][j]) {
                    max = A[i][j];
                    maxInd = i;
                    maxInd2 = j;
                }
            }
        }
        return max;
    }

//    public static void main(String[] args) throws IOException {
//        int[][] matrix = Matrix.readMatrixFromFile2(new File("C:\\Users\\YANA\\IdeaProjects\\VLazorykOOP\\jlab6-vshnvsk\\src\\matrix.txt"));
//        displayMatrix(matrix);
//    }
}