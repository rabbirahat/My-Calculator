public class CalculatorLogic {
    private double currentResult; // Stores the current result
    private String lastOperator; // Stores the last operator
    private StringBuilder expression; // Stores the expression for display
    private boolean clearOnNextInput; // Flag to clear input field on next input

    public CalculatorLogic() {
        currentResult = 0;
        lastOperator = "=";
        clearOnNextInput = true;
        expression = new StringBuilder();
    }

    public String processCommand(String command, String currentText) {
        try {
            // If command is a number or decimal, append to the input
            if (command.matches("[0-9.]")) {
                if (clearOnNextInput) {
                    clearOnNextInput = false;
                    return command;
                } else {
                    return currentText + command;
                }
            }

            // Handle special commands
            switch (command) {
                case "C":
                    reset();
                    return "";
                case "CE":
                    return "";
                case "Backspc":
                    if (!currentText.isEmpty()) {
                        return currentText.substring(0, currentText.length() - 1);
                    }
                    return "";
                case "+/-":
                    if (!currentText.isEmpty()) {
                        double value = Double.parseDouble(currentText) * -1;
                        return formatResult(value);
                    }
                    return currentText;
                case "1/x":
                    if (!currentText.isEmpty()) {
                        double value = Double.parseDouble(currentText);
                        if (value != 0) {
                            return formatResult(1 / value);
                        }
                        return "Error"; // Handle division by zero
                    }
                    return currentText;
                case "x^2":
                    if (!currentText.isEmpty()) {
                        double value = Double.parseDouble(currentText);
                        return formatResult(value * value);
                    }
                    return currentText;
                case "root(x)":
                    if (!currentText.isEmpty()) {
                        double value = Double.parseDouble(currentText);
                        if (value >= 0) {
                            return formatResult(Math.sqrt(value));
                        }
                        return "Error"; // Handle square root of negative number
                    }
                    return currentText;
                case "=":
                    calculate(Double.parseDouble(currentText));
                    lastOperator = "=";
                    clearOnNextInput = true;

                    // Reset the expression and return the result
                    expression.append(currentText).append(" = ");
                    return formatResult(currentResult);
                default:
                    // Update current result and prepare for next input
                    calculate(Double.parseDouble(currentText));
                    lastOperator = command;
                    expression.append(currentText).append(" ").append(command).append(" ");
                    clearOnNextInput = true;
                    return formatResult(currentResult);
            }
        } catch (NumberFormatException ex) {
            return "Error";
        }
    }


    private void reset() {
        currentResult = 0;
        lastOperator = "=";
        clearOnNextInput = true;
        expression = new StringBuilder();
    }

    private void calculate(double input) {
        switch (lastOperator) {
            case "+":
                currentResult += input;
                break;
            case "-":
                currentResult -= input;
                break;
            case "x":
                currentResult *= input;
                break;
            case "/":
                currentResult = (input != 0) ? currentResult / input : 0;
                break;
            case "%":
                currentResult %= input;
                break;
            case "=":
                currentResult = input;
                break;
        }
    }

    public String getExpression() {
        return expression.toString();
    }

    private String formatResult(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value); // Show as integer if no decimal part
        } else {
            return String.valueOf(value); // Show as float if there's a decimal part
        }
    }
}
