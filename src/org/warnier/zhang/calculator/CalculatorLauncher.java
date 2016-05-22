package org.warnier.zhang.calculator;

import org.warnier.zhang.calculator.ui.CalculatorFrame;

import javax.swing.*;

/**
 * The main launcher for Calculator.
 *
 * @author Warnier-zhang
 * @version 1.0
 */
public class CalculatorLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorFrame().pack();
            }
        });
    }
}
