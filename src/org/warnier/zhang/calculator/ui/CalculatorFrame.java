package org.warnier.zhang.calculator.ui;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.warnier.zhang.calculator.model.Bundle;
import org.warnier.zhang.calculator.model.BundleKey;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UI for Calculator.
 *
 * @author Warnier-zhang
 */
public class CalculatorFrame extends JFrame implements ActionListener {
    private static final String REGEX_OPERATOR = "[\\+\\-×÷]";
    private static final String REGEX_PN = "[\\+\\-]";
    private static final String REGEX_NUMBER = "[\\.0123456789]";
    private Bundle bundle;
    private JTextField textField;

    public CalculatorFrame() {
        this("Calculator");
    }

    public CalculatorFrame(String title) {
        super(title);
        bundle = new Bundle();

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
        textField.setText(doAction(e.getActionCommand()));
    }

    private String doAction(String command) {
        String result;
        if (command.equals("C")) {
            bundle.clear();
            result = "";
        } else if (command.equals("=")) {
            result = bundle.calculate();
        } else if (command.matches(REGEX_OPERATOR)) {
            if (command.matches(REGEX_PN)) {
                if (bundle.containsKey(BundleKey.O)) {
                    if (!bundle.containsKey(BundleKey.E)) {
                        bundle.putEntry(BundleKey.E, command);
                    }
                } else {
                    if (!bundle.containsKey(BundleKey.S)) {
                        bundle.putEntry(BundleKey.S, command);
                    } else {
                        bundle.putEntry(BundleKey.O, command);
                    }
                }
            } else {
                bundle.putEntry(BundleKey.O, command);
            }
            bundle.log();
            result = bundle.toString();
        } else if (command.matches(REGEX_NUMBER)) {
            if (bundle.containsKey(BundleKey.O)) {
                bundle.putEntry(BundleKey.E, command);
            } else {
                bundle.putEntry(BundleKey.S, command);
            }
            bundle.log();
            result = bundle.toString();
        } else {
            result = "Error!";
        }
        return result;
    }
}
