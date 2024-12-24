import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame {
    private JFrame frame; // Main window
    private JTextField textField; // Display for the current input/output
    private JLabel expressionLabel; // Label for the previous expression
    private CalculatorLogic logic; // Reference to the logic handler
    private JPanel buttonPanel;

    public CalculatorGUI() {
        // Initialize the CalculatorLogic
        logic = new CalculatorLogic();

        // Create the main window
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(1300,50,430,630);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        // Set calculator icon
        ImageIcon icon = new ImageIcon(getClass().getResource("Windows_Calculator_icon.png"));
        frame.setIconImage(icon.getImage());

        // Expression label (top)
        expressionLabel = new JLabel("", SwingConstants.RIGHT);
        expressionLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        expressionLabel.setPreferredSize(new Dimension(frame.getWidth(), 40));
        expressionLabel.setForeground(Color.WHITE);
        frame.add(expressionLabel, BorderLayout.NORTH);

        // Input/output text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setPreferredSize(new Dimension(frame.getWidth(), 50));
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        frame.add(textField, BorderLayout.CENTER);

        // Create the panel for buttons
        buttonPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                // Calculate a square button size based on the frame dimensions
                int rows = 6;
                int cols = 4;
                int size = Math.min(frame.getWidth() / cols, (frame.getHeight() - 150) / rows); // Adjust for label and textField height
                return new Dimension(size * cols, size * rows);
            }
        };
        buttonPanel.setLayout(new GridLayout(6, 4, 2, 2)); // 6 rows and 4 columns with 2px gaps

        // Define button labels
        String[] buttons = {
                "%", "CE", "C", "Backspc",
                "1/x", "x^2", "root(x)", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "=",
        };

        // Add buttons to the panel
        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 22));
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            button.addActionListener(e -> processInput(label));

            if (label.equals("=")) {
                // Set the "=" button color as blue
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            } else {
                // Set all other buttons to dark gray with white text
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
            }

            // Set button to be square
            button.setPreferredSize(new Dimension(80, 80)); // Fixed size for buttons
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        // Add keyboard input listener
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if (Character.isDigit(key) || "+-*/.=C%".indexOf(key) != -1) {
                    processInput(String.valueOf(key));
                } else if (key == '\n') { // Enter key for "="
                    processInput("=");
                }
            }
        });

        frame.setFocusable(true);
        frame.setVisible(true);
    }

    // Process input from buttons or keyboard
    private void processInput(String input) {
        try {
            String result = logic.processCommand(input, textField.getText());
            textField.setText(result);

            // Update expression label to show the full expression
            expressionLabel.setText(logic.getExpression());
        } catch (Exception e) {
            textField.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
