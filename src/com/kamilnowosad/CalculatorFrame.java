package com.kamilnowosad;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private final int WIDTH = 320;
    private final int HEIGHT = 400;

    private JTextField textFieldBottom, textFieldTop;
    private JButton[] controlButtons = new JButton[20];
    private String[] buttonSymbols = {"(",")","CE","C","7","8","9","*",
            "4","5","6","+","1","2","3","-","0",".","/","="};

    public CalculatorFrame(){
        setLayout(null);
        setSize(WIDTH,HEIGHT);
        setAlwaysOnTop(true);
        createPanelTop();
        createPanelBottom();
        setBackground(Color.BLACK );

        setTitle("CalculatorListener");
        setResizable(false);
    }

    public void createPanelTop(){
        JPanel panelTop = new JPanel();
        panelTop.setBounds(2,2,WIDTH-4,HEIGHT/5 -20);
        panelTop.setLayout(null);

        textFieldBottom = new JTextField();
        textFieldBottom.setHorizontalAlignment(JTextField.LEFT);
        textFieldBottom.setBounds(0,0,WIDTH-8,HEIGHT/5);

        panelTop.add(textFieldBottom);
        add(panelTop);
    }

    public void createPanelBottom(){
        JPanel panelBottom = new JPanel();
        panelBottom.setBounds(2,2+HEIGHT/5 -20,WIDTH-18,4*HEIGHT/5 - 24);
        panelBottom.setLayout(new GridLayout(5,4));
        JButton b;

        for (int i = 0; i < controlButtons.length ; i++) {
            b = new JButton();
            b.setFont(new Font("Arial",Font.PLAIN,20));
            b.setText(buttonSymbols[i]);
            b.addActionListener(new CalculatorListener(textFieldTop,textFieldBottom,b.getText()));
            controlButtons[i] = b;
            panelBottom.add(b);
        }

        add(panelBottom);
    }


}
