# MyCalculator Project
MyCalculator is a GUI-based Java program that emulates the operations of a standard calculator, similar to the Windows Calculator. Built using the Swing framework, it provides a user-friendly interface and supports a wide range of mathematical functions. Whether you need to perform basic arithmetic, advanced operations like square roots and reciprocals, or manage complex calculations with a clear expression display, MyCalculator has you covered. This application features a modern graphical user interface (GUI) with support for basic arithmetic, advanced mathematical operations, and intuitive keyboard shortcuts. It is designed to be simple yet powerful, catering to everyday computational needs.

___

## Features
- **Basic Arithmetic:** Perform addition, subtraction, multiplication, and division.
- **Advanced Functions:**
  - Percentage (%)
  - Reciprocal (1/x)
  - Square (x²)
  - Square Root (√x)
 
- **Input Management:**
  - Backspace to delete the last digit
  - Clear (C) and Clear Entry (CE) buttons
  - Negate (+/-) functionality
 
- **Keyboard Support:** Use your keyboard to enter numbers and operators directly
- **Error Handling:**
  - Handles division by zero gracefully
  - Prevents invalid operations like the square root of negative numbers.
 
- **Expression Display:** Shows the complete expression at the top for clarity
- **Responsive Design:** Buttons automatically adjust their size based on the window dimensions.

## Getting Started
### Prerequisites
- Java JDK 8 or later must be installed on your system.
- A compatible IDE (e.g., IntelliJ IDEA, Eclipse) or command-line environment to run the application.

### Installation
#### 1. Clone the repository:
git clone https://github.com/rabbirahat/My-Calculator.git
#### 2. Navigate to the project directory:
cd MyCalculator
#### 3. Compile and run the application using your preferred IDE or the command line:
javac Calculator.java CalculatorGUI.java CalculatorLogic.java
java Calculator

## Screenshots
![image](https://github.com/user-attachments/assets/3cf5a15c-0f5c-4d51-851e-f3ddd32cebdf)

### How to Use
**1. Starting the Calculator:** Launch the application. The main calculator interface will open.

**2. Performing Calculations:**
- Click on the buttons or use the keyboard to input numbers and operators.
- Press = or Enter to evaluate the expression.

**3. Clearing Input:**
- CE: Clears the current entry.
- C: Clears the entire expression.

**4.Keyboard Shortcuts:**
- Numbers: 0-9
- Decimal Point: .
- Operators: +, -, *, /
- Clear: ESC (C), Backspace (Backspc)
- Evaluate: Enter or =

## Code Overview
### Main Components
**1. Calculator.java:** The entry point of the application. Initializes the GUI.

**2. CalculatorGUI.java:** The entry point of the application. Initializes the GUI.
- Creates and manages the GUI using Swing components.
- Handles button clicks and keyboard inputs.
- Updates the display with the result and expression.
  
**3. CalculatorLogic.java:** The entry point of the application. Initializes the GUI.
- Handles the core mathematical operations and logic.
- Manages the calculation state, including the current result, last operator, and input clearing.

### Key Classes and Methods
- processInput(String input):
   - Handles button and keyboard inputs.
   - Updates the display with results or error messages.

- calculate(double input):
   - Performs arithmetic operations based on the last operator.
 
- reset():
   - Resets the calculator state.



