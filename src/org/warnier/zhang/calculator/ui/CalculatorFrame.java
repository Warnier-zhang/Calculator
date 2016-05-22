package org.warnier.zhang.calculator.ui;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The UI for Calculator.
 *
 * @author Warnier-zhang
 */
public class CalculatorFrame extends JFrame implements ActionListener {
    private Map<String, String> input = new HashMap<>();
    private JTextField textField;

    public CalculatorFrame() {
        this("Calculator");
    }

    public CalculatorFrame(String title) {
        super(title);
        add(makeCalculatorUI());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel makeCalculatorUI() {
        JPanel container = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(container);
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        layout.row().grid().add(textField);
        layout.row().grid().add(makeButton("C"), 2).add(makeButton("÷")).add(makeButton("×"));
        layout.row().grid().add(makeButton("7")).add(makeButton("8")).add(makeButton("9")).add(makeButton("-"));
        layout.row().grid().add(makeButton("4")).add(makeButton("5")).add(makeButton("6")).add(makeButton("+"));
        layout.row().grid().add(makeButton("1")).add(makeButton("2")).add(makeButton("3")).add(makeButton("="));
        layout.row().grid().add(makeButton("0"), 2).add(makeButton(".")).spanRow();
        return container;
    }

    private JButton makeButton(String name) {
        JButton button = new JButton(name);
        button.setActionCommand(name);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("C")) {
            textField.setText("");
            input.clear();
        } else if (command.equals("=")) {
            if (input.containsKey("operator")) {
                calculate();
            }
        } else if (command.matches("[\\+\\-×÷]")) {
            input.put("operator", command);
            showText();
        } else if (command.matches("[\\.0123456789]")) {
            if (input.containsKey("operator")) {
                putArg("end", command);
            } else {
                putArg("start", command);
            }
            showText();
        } else {
            new IllegalArgumentException("NULL Input!");
        }
    }

    private void putArg(String key, String value) {
        String origin = input.get(key);
        if (isEmpty(origin)) {
            input.put(key, value);
        } else {
            input.put(key, origin + value);
        }
    }

    private void showText() {
        String text = "";
        String start = input.get("start");
        String operator = input.get("operator");
        String end = input.get("end");
        if (!isEmpty(start)) {
            text += start;
        }
        if (!isEmpty(operator)) {
            text += operator;
        }
        if (!isEmpty(end)) {
            text += end;
        }
        textField.setText(text);
    }

    private void calculate() {
        String operator = input.get("operator");
        double start = parseText(input.get("start"));
        double end = parseText(input.get("end"));
        switch (operator) {
            case "+":
                textField.setText(String.valueOf(start + end));
                break;
            case "-":
                textField.setText(String.valueOf(start - end));
                break;
            case "×":
                textField.setText(String.valueOf(start * end));
                break;
            case "÷":
                if (end != 0) {
                    textField.setText(String.valueOf(start / end));
                } else {
                    new IllegalArgumentException("Divisor is Zero!");
                }
                break;
            default:
                new IllegalArgumentException("Operator is not supported!");
        }
        input.clear();
    }

    private double parseText(String text) {
        return Double.parseDouble(text);
    }

    private boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    private void makeLog() {
        System.out.println(input);
    }
}
