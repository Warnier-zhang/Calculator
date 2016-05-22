package org.warnier.zhang.calculator.ui;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The UI for Calculator.
 *
 * @author Warnier-zhang
 */
public class CalculatorFrame extends JFrame implements ActionListener {
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
        layout.row().grid().add(makeButton("C", ""), 2).add(makeButton("\u00F7", "")).add(makeButton("\u00D7", ""));
        layout.row().grid().add(makeButton("7", "7")).add(makeButton("8", "8")).add(makeButton("9", "9")).add(makeButton("-", ""));
        layout.row().grid().add(makeButton("4", "4")).add(makeButton("5", "5")).add(makeButton("6", "6")).add(makeButton("+", ""));
        layout.row().grid().add(makeButton("1", "1")).add(makeButton("2", "2")).add(makeButton("3", "3")).add(makeButton("=", ""));
        layout.row().grid().add(makeButton("0", "0"), 2).add(makeButton(".", "")).spanRow();
        return container;
    }

    private JButton makeButton(String name, String command) {
        JButton button = new JButton(name);
        button.setActionCommand(command);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        textField.setText(action);
    }

    private JButton makeCommandButton(String name, int operator){
        return null;
    }
}
