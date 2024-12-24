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
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        // Set calculator icon
        ImageIcon icon = new ImageIcon(getClass().getResource("Windows_Calculator_icon.png"));
        frame.setIconImage(icon.getImage());

        // Expression label (top)
        expressionLabel = new JLabel("", SwingConstants.RIGHT);
        expressionLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        expressionLabel.setPreferredSize(new Dimension(frame.getWidth(), 40));
        expressionLabel.setForeground(Color.BLACK);
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

        // Add keyboard input listener using KeyBindings
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        // Define supported keys
        String keys = "0123456789+-*/.=C%";
        for (char key : keys.toCharArray()) {
            inputMap.put(KeyStroke.getKeyStroke(key), String.valueOf(key));
            actionMap.put(String.valueOf(key), new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processInput(String.valueOf(key));
                }
            });
        }

        // Handle Enter key for "="
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "=");
        actionMap.put("=", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput("=");
            }
        });

        // Handle Backspace key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Backspc");
        actionMap.put("Backspc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput("Backspc");
            }
        });

        // Handle Clear (C) key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "C");
        actionMap.put("C", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput("C");
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
