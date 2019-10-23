package com.kamilnowosad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorListener implements ActionListener {

    private JTextField textFieldTop;
    private JTextField getTextFieldBottom;
    private String buttonText;

    public CalculatorListener(JTextField textFieldTop, JTextField getTextFieldBottom, String buttonText) {
        this.textFieldTop = textFieldTop;
        this.getTextFieldBottom = getTextFieldBottom;
        this.buttonText = buttonText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
