package src.UI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.logic.CalculatorLogic;

public class CalculatorUI extends JFrame {

    private JTextField display;

    private double num1 = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    private CalculatorLogic logic = new CalculatorLogic();

    public CalculatorUI() {

        // Window setup
        setTitle("Scientific Calculator (Basic MVP)");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // DISPLAY
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // BUTTON PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0",".","=","+",
            "C","","",""
        };

        for (String text : buttons) {
            if (text.equals("")) {
                panel.add(new JLabel()); // empty placeholder
            } else {
                JButton btn = new JButton(text);
                btn.setFont(new Font("Arial", Font.BOLD, 18));
                btn.addActionListener(e -> handleInput(text));
                panel.add(btn);
            }
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    // CORE LOGIC HANDLER
    private void handleInput(String input) {

        // NUMBERS + DOT
        if (input.matches("[0-9.]")) {

            if (startNewNumber) {
                display.setText(input);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + input);
            }
        }

        // OPERATORS
        else if (input.matches("[+\\-*/]")) {

            try {
                num1 = Double.parseDouble(display.getText());
            } catch (Exception e) {
                display.setText("Error");
                return;
            }

            operator = input;
            startNewNumber = true;
        }

        // EQUALS
        else if (input.equals("=")) {

            try {
                double num2 = Double.parseDouble(display.getText());
                double result = logic.calculate(num1, num2, operator);

                display.setText(String.valueOf(result));

            } catch (Exception e) {
                display.setText("Error");
            }

            startNewNumber = true;
        }

        // CLEAR
        else if (input.equals("C")) {
            display.setText("");
            num1 = 0;
            operator = "";
            startNewNumber = true;
        }
    }
}
    

