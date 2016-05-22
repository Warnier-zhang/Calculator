package org.warnier.zhang.calculator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for manipulating user input.
 */
public class Bundle {
    private Map<String, String> dataWrapper;

    public Bundle() {
        dataWrapper = new LinkedHashMap<>();
    }

    public void put(String key, String value) {
        if (dataWrapper.containsKey(key)) {
            dataWrapper.put(key, dataWrapper.get(key) + value);
        } else {
            dataWrapper.put(key, value);
        }
    }

    public String get(String key) {
        return dataWrapper.get(key);
    }

    public void clear() {
        dataWrapper.clear();
    }

    public boolean containsKey(String key) {
        return dataWrapper.containsKey(key);
    }

    public String calculate(String operator) {
        String result = "";
        switch (operator) {
            case "+":
                result = String.valueOf(parseDouble("start") + parseDouble("end"));
                break;
            case "-":
                result = String.valueOf(parseDouble("start") - parseDouble("end"));
                break;
            case "×":
                result = String.valueOf(parseDouble("start") * parseDouble("end"));
                break;
            case "÷":
                result = String.valueOf(parseDouble("start") / parseDouble("end"));
                break;
            default:
                new IllegalArgumentException("Operator is not supported!");
        }
        dataWrapper.clear();
        return result;
    }

    private double parseDouble(String key) {
        return Double.parseDouble(dataWrapper.get(key));
    }

    @Override
    public String toString() {
        String text = "";
        for (Map.Entry<String, String> entry : dataWrapper.entrySet()) {
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

    private boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }
}
