import javax.swing.*; // Graphical User interface (GUI) widget toolkit for Java
import java.awt.*; // Abstract Window Toolkit (AWT) develops GUI or window-based applications in java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixGUI extends JFrame {
    private JTextArea matrixAInput;  // Text area for inputting matrix A
    private JTextArea matrixBInput;  // Text area for inputting matrix B
    private JTextArea resultOutput;  // Text area for displaying results

    // Constructor to set up the GUI components
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
        addOperationButton(buttonsPanel, "Add", e -> performOperation("add"));
        addOperationButton(buttonsPanel, "Scalar Multiply A", e -> performOperation("scalarMultiplyA"));
        addOperationButton(buttonsPanel, "Scalar Multiply B", e -> performOperation("scalarMultiplyB"));

        // Result Panel
        resultOutput = new JTextArea(10, 50);
        resultOutput.setEditable(false);  // Result area is not editable

        add(inputPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultOutput), BorderLayout.SOUTH);
    }

    // Helper method to add operation buttons to the panel
    private void addOperationButton(JPanel panel, String name, ActionListener action) {
        JButton button = new JButton(name);  // Create a new button with the given name
        button.addActionListener(action);  // Add the action listener to the button
        panel.add(button);  // Add the button to the panel
    }

    // Method to perform the selected matrix operation
    private void performOperation(String operation) {
        int[][] matrixA = parseMatrix(matrixAInput.getText());  // Parse matrix A input
        int[][] matrixB = parseMatrix(matrixBInput.getText());  // Parse matrix B input
        int[][] result = null;  // Resulting matrix
        int scalar = 2; // Example scalar value for scalar multiplication

        // Perform the selected operation
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
        displayResult(result);  // Display the result of the operation
    }

    // Method to parse a matrix from a string
    private int[][] parseMatrix(String text) {
        String[] rows = text.split("\n");  // Split the text into rows
        int[][] matrix = new int[rows.length][];  // Initialize the matrix

        // Parse each row into integers
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split("\\s+");  // Split the row into values
            matrix[i] = new int[values.length];  // Initialize the row in the matrix
            for (int j = 0; j < values.length; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);  // Parse each value as an integer
            }
        }
        return matrix;  // Return the parsed matrix
    }

    // Method to display the result matrix
    private void displayResult(int[][] result) {
        if (result == null) {
            resultOutput.setText("Invalid operation.");  // Display error message if result is null
            return;
        }
        StringBuilder sb = new StringBuilder();  // StringBuilder for the result text
        for (int[] row : result) {
            for (int value : row) {
                sb.append(value).append("\t");  // Append each value followed by a tab
            }
            sb.append("\n");  // Append a newline after each row
        }
        resultOutput.setText(sb.toString());  // Set the result text area with the result string
    }

    // Main method to run the GUI application
    public static void main(String[] args) {
    // Ensure that the GUI creation and updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            MatrixGUI gui = new MatrixGUI();  // Create an instance of the MatrixGUI class
            gui.setVisible(true);  // Make the GUI visible
        });
    }
}
