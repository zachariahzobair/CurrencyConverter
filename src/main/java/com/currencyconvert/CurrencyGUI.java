package com.currencyconvert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CurrencyGUI {

    private static final String[] CURRENCIES = {"USD", "AUD", "CAD", "MXN", "GBP", "PKR", "INR", "KRW", "CNY", "JPY"};

    public static void main(String[] args) {
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(450,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        placeComponents(panel);

        frame.add(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel p) {
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        p.setBackground(new Color(54,54,54));

        JLabel title = new JLabel("Currency Converter");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 36));
        title.setForeground(Color.white);
        p.add(title, gbc);

        JLabel entryLabel = new JLabel("Enter Amount to Convert:");
        entryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        entryLabel.setForeground(Color.white);
        p.add(entryLabel, gbc);

        JTextField entryField = new JTextField(15);
        p.add(entryField, gbc);

        JComboBox firstCurrencyList = new JComboBox(CURRENCIES);
        p.add(firstCurrencyList, gbc);

        JLabel convertToLabel = new JLabel("Convert To");
        convertToLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        convertToLabel.setForeground(Color.white);
        p.add(convertToLabel, gbc);

        JComboBox secondCurrencyList = new JComboBox(CURRENCIES);
        p.add(secondCurrencyList, gbc);

        JButton button = new JButton("Convert!");


        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        resultLabel.setForeground(Color.white);
        p.add(resultLabel, gbc);

        JTextField result = new JTextField(15);
        result.setEditable(false);
        p.add(result, gbc);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double convertValue = 0;
                try {
                    convertValue = Double.parseDouble(entryField.getText());
                } catch (Exception ex) {
                    JOptionPane op = new JOptionPane("Enter a Valid Value!");
                    p.add(op);
                }

                try {
                    double resultValue = CurrencyConverter.convert(convertValue, firstCurrencyList.getSelectedItem().toString(),
                            secondCurrencyList.getSelectedItem().toString());

                    result.setText(String.format("%.2f", resultValue));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        p.add(button, gbc);
    }
}
