import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixGUI extends JFrame {
    private JTextArea matrixAInput;
    private JTextArea matrixBInput;
    private JTextArea resultOutput;

    public MatrixGUI() {
        setTitle("Matrix Operations Tool");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        matrixAInput = new JTextArea(5, 20);
        matrixBInput = new JTextArea(5, 20);
        inputPanel.add(new JScrollPane(matrixAInput));
        inputPanel.add(new JScrollPane(matrixBInput));

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5));
        addOperationButton(buttonsPanel, "Multiply", e -> performOperation("multiply"));
        addOperationButton(buttonsPanel, "Transpose A", e -> performOperation("transposeA"));
        addOperationButton(buttonsPanel, "Transpose B", e -> performOperation("transposeB"));
        addOperationButton(buttonsPanel, "Determinant A", e -> performOperation("determinantA"));
        addOperationButton(buttonsPanel, "Determinant B", e -> performOperation("determinantB"));
        addOperationButton(buttonsPanel, "Add", e -> performOperation("add"));
        addOperationButton(buttonsPanel, "Scalar Multiply A", e -> performOperation("scalarMultiplyA"));
        addOperationButton(buttonsPanel, "Scalar Multiply B", e -> performOperation("scalarMultiplyB"));

        // Result Panel
        resultOutput = new JTextArea(10, 50);
        resultOutput.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultOutput), BorderLayout.SOUTH);
    }

    private void addOperationButton(JPanel panel, String name, ActionListener action) {
        JButton button = new JButton(name);
        button.addActionListener(action);
        panel.add(button);
    }

    private void performOperation(String operation) {
        int[][] matrixA = parseMatrix(matrixAInput.getText());
        int[][] matrixB = parseMatrix(matrixBInput.getText());
        int[][] result = null;
        int scalar = 2; // Example scalar value for scalar multiplication
        switch (operation) {
            case "multiply":
                result = MatrixOperations.multiply(matrixA, matrixB);
                break;
            case "transposeA":
                result = MatrixOperations.transpose(matrixA);
                break;
            case "transposeB":
                result = MatrixOperations.transpose(matrixB);
                break;
            case "determinantA":
                resultOutput.setText("Determinant of Matrix A: " + MatrixOperations.determinant(matrixA));
                return;
            case "determinantB":
                resultOutput.setText("Determinant of Matrix B: " + MatrixOperations.determinant(matrixB));
                return;
            case "add":
                result = MatrixOperations.add(matrixA, matrixB);
                break;
            case "scalarMultiplyA":
                result = MatrixOperations.scalarMultiply(matrixA, scalar);
                break;
            case "scalarMultiplyB":
                result = MatrixOperations.scalarMultiply(matrixB, scalar);
                break;
        }
        displayResult(result);
    }

    private int[][] parseMatrix(String text) {
        String[] rows = text.split("\n");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split("\\s+");
            matrix[i] = new int[values.length];
            for (int j = 0; j < values.length; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        return matrix;
    }

    private void displayResult(int[][] result) {
        if (result == null) {
            resultOutput.setText("Invalid operation.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int value : row) {
                sb.append(value).append("\t");
            }
            sb.append("\n");
        }
        resultOutput.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatrixGUI gui = new MatrixGUI();
            gui.setVisible(true);
        });
    }
}
