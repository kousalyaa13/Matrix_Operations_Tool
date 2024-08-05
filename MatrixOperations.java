public class MatrixOperations {

    // Matrix multiplication
    // Multiplies two matrices a and b, and returns the resulting matrix.
    // The number of columns in matrix a must match the number of rows in matrix b.
    public static int[][] multiply(int[][] a, int[][] b) {
        int rowsA = a.length;       // Number of rows in matrix a
        int colsA = a[0].length;    // Number of columns in matrix a
        int colsB = b[0].length;    // Number of columns in matrix b
        int[][] result = new int[rowsA][colsB];  // Resulting matrix

        // Iterate through each element to compute the matrix product
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;  // Return the resulting matrix
    }

    // Matrix transposition
    // Transposes the given matrix and returns the transposed matrix.
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;       // Number of rows in the original matrix
        int cols = matrix[0].length;    // Number of columns in the original matrix
        int[][] result = new int[cols][rows];  // Resulting transposed matrix

        // Iterate through each element to compute the transposed matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;  // Return the transposed matrix
    }

    // Determinant calculation (assuming a square matrix)
    // Computes and returns the determinant of the given square matrix.
    public static int determinant(int[][] matrix) {
        int n = matrix.length;  // Size of the square matrix

        // Base case for 1x1 matrix
        if (n == 1) return matrix[0][0];

        // Base case for 2x2 matrix
        if (n == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        int det = 0;  // Variable to store the determinant
        for (int i = 0; i < n; i++) {
            int[][] minor = getMinor(matrix, 0, i);  // Get the minor matrix
            det += Math.pow(-1, i) * matrix[0][i] * determinant(minor);  // Recursively compute the determinant
        }
        return det;  // Return the determinant
    }

    // Helper method to get the minor of a matrix
    // Computes and returns the minor matrix by excluding the specified row and column.
    private static int[][] getMinor(int[][] matrix, int row, int col) {
        int n = matrix.length;  // Size of the square matrix
        int[][] minor = new int[n - 1][n - 1];  // Minor matrix

        // Iterate through each element to compute the minor matrix
        for (int i = 0; i < n; i++) {
            if (i == row) continue;  // Skip the specified row
            for (int j = 0; j < n; j++) {
                if (j == col) continue;  // Skip the specified column
                minor[i < row ? i : i - 1][j < col ? j : j - 1] = matrix[i][j];
            }
        }
        return minor;  // Return the minor matrix
    }

    // Element-wise addition
    // Adds two matrices a and b element-wise and returns the resulting matrix.
    public static int[][] add(int[][] a, int[][] b) {
        int rows = a.length;       // Number of rows in the matrices
        int cols = a[0].length;    // Number of columns in the matrices
        int[][] result = new int[rows][cols];  // Resulting matrix

        // Iterate through each element to compute the element-wise sum
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;  // Return the resulting matrix
    }

    // Scalar multiplication
    // Multiplies each element of the given matrix by a scalar value and returns the resulting matrix.
    public static int[][] scalarMultiply(int[][] matrix, int scalar) {
        int rows = matrix.length;       // Number of rows in the matrix
        int cols = matrix[0].length;    // Number of columns in the matrix
        int[][] result = new int[rows][cols];  // Resulting matrix

        // Iterate through each element to compute the scalar multiplication
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;  // Return the resulting matrix
    }
}
