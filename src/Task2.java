import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Task2 extends JFrame {
    private JTextField filePath;
    private JPanel panel;
    private JPanel panel1;
    private JButton buttonForPath;
    private JButton buttonForMatrix;
    private JTable table;
    private DefaultTableModel tableModel;

    public Task2() {
        super("Matrix");

        filePath = new JTextField(40);
        buttonForPath = new JButton("Download");
        buttonForMatrix = new JButton("Change the matrix");
        panel = new JPanel(new BorderLayout());
        panel1 = new JPanel(new BorderLayout());
        JLabel max = new JLabel("Max element: ");

        panel.add(new JLabel("File path:"), BorderLayout.WEST);
        panel.add(filePath, BorderLayout.CENTER);
        panel.add(buttonForPath, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);

        panel1.add(max, BorderLayout.WEST);
        panel1.add(buttonForMatrix, BorderLayout.EAST);
        add(panel1, BorderLayout.SOUTH);

        buttonForPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[][] matrix = Matrix.readMatrixFromFile(new File(filePath.getText()));
                    updateTable(matrix);
                    int maxElem = Matrix.maxElement(matrix);
                    String text = "Max element: " + maxElem;
                    max.setText(text);
                } catch (IOException | NumberFormatException | Matrix.MatrixFormatException ex) {
                    ex.printStackTrace();
                } catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        buttonForMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[][] matrix = Matrix.readMatrixFromFile(new File(filePath.getText()));
                    matrix = Matrix.swapMatrix(matrix);
                    updateTable(matrix);
                    int maxElem = Matrix.maxElement(matrix);
                    String text = "Max element: " + maxElem;
                    max.setText(text);
                } catch (IOException | NumberFormatException | Matrix.MatrixFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateTable(int[][] matrix){
        tableModel.setRowCount(matrix.length);
        tableModel.setColumnCount(matrix[0].length);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                tableModel.setValueAt(matrix[i][j], i, j);
            }
        }
    }

    public static void main(String[] args) {
        new Task2();
    }
}