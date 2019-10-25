package com.kamilnowosad;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private final int WIDTH = 320;
    private final int HEIGHT = 480;

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
        setTitle("CalculatorListener");
        setResizable(false);
    }

    public void createPanelTop(){
        JPanel panelTop = new JPanel();
        panelTop.setBounds(5,10,WIDTH-22,HEIGHT/6 +15);
        panelTop.setLayout(new BorderLayout());

        textFieldTop = new JTextField();
        textFieldTop.setEditable(false);
        textFieldTop.setFont(new Font("Arial",Font.PLAIN,25));
        textFieldTop.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldTop.setEditable(false);
        textFieldTop.setText("");

        textFieldBottom = new JTextField();
        textFieldBottom.setEditable(false);
        textFieldBottom.setBackground(Color.WHITE);
        textFieldBottom.setFont(new Font("Arial",Font.PLAIN,40));
        textFieldBottom.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldBottom.setText("0");

        panelTop.add(textFieldTop,BorderLayout.PAGE_START);
        panelTop.add(textFieldBottom, BorderLayout.PAGE_END);
        add(panelTop);
    }

    public void createPanelBottom(){
        JPanel panelBottom = new JPanel();
        panelBottom.setBounds(5,5+HEIGHT/6 +30,WIDTH-22,4*HEIGHT/6);
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
