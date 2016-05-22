package org.warnier.zhang.calculator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for manipulating user input.
 */
public class Bundle {
    private Map<String, String> data;

    public Bundle() {
        data = new LinkedHashMap<>();
    }

    public void putEntry(String key, String value) {
        if (data.containsKey(key)) {
            data.put(key, data.get(key) + value);
        } else {
            data.put(key, value);
        }
    }

    public void clear() {
        data.clear();
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    /**
     * Return itself when entering one operand.
     * Return NULL and clear operator when there is no operand.
     * Calculate the result according to operator when there are two operands.
     */
    public String calculate() {
        String s = data.get(BundleKey.S);
        if (isEmpty(s)) {
            data.clear();
            return "";
        }
        String e = data.get(BundleKey.E);
        if (isEmpty(e)) {
            return s;
        }
        return String.valueOf(calculate(Double.parseDouble(s), Double.parseDouble(e)));
    }

    private double calculate(double s, double e) {
        double result;
        switch (data.get(BundleKey.O)) {
            case "+":
                result = s + e;
                break;
            case "-":
                result = s - e;
                break;
            case "×":
                result = s * e;
                break;
            case "÷":
                result = s / e;
                break;
            default:
                result = 0;
        }
        data.clear();
        return result;
    }

    @Override
    public String toString() {
        String text = "";
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String value = entry.getValue();
            if (isEmpty(value)) {
                value = "";
            }
            if (value.length() > 1) {
                if (value.startsWith("0")) {
                    value = value.substring(1);
                }
            }
            text += value;
        }
        return text;
    }

    public void log() {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }
}
