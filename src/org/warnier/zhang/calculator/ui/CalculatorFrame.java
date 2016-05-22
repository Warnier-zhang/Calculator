package org.warnier.zhang.calculator.ui;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.warnier.zhang.calculator.model.Bundle;

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
        String command = e.getActionCommand();
        if (command.equals("C")) {
            bundle.clear();
            showText("");
        } else if (command.equals("=")) {
            showText(bundle.calculate(bundle.get("operator")));
        } else if (command.matches("[\\+\\-×÷]")) {
            bundle.put("operator", command);
            showText(bundle.toString());
        } else if (command.matches("[\\.0123456789]")) {
            if (bundle.containsKey("operator")) {
                bundle.put("end", command);
            } else {
                bundle.put("start", command);
            }
            showText(bundle.toString());
        } else {
            new IllegalArgumentException("NULL Input!");
        }
    }

    private void showText(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textField.setText(text);
            }
        });
    }
}
