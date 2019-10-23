package com.kamilnowosad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorListener implements ActionListener {

    private JTextField textFieldTop;
    private JTextField textFieldBottom;
    private String buttonText;

    public CalculatorListener(JTextField textFieldTop, JTextField textFieldBottom, String buttonText) {
        this.textFieldTop = textFieldTop;
        this.textFieldBottom = textFieldBottom;
        this.buttonText = buttonText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String topText = textFieldTop.getText();
        char lastTopChar = topText.isEmpty() ? ' ' : topText.charAt(topText.length()-1) ;
        String bottomText = textFieldBottom.getText();
        char lastBottomChar = bottomText.charAt(bottomText.length()-1);

        if (buttonText.equals("=")){

            if (calculateNumOfOpenParentheses(topText) == 0) {
                if (lastTopChar == '+' || lastTopChar == '-' || lastTopChar == '*' || lastTopChar == '/'){
                    textFieldTop.setText(topText + bottomText);
                }
//                double result = Math.round(executeCalculations(textFieldTop.getText())*100)/100;
//                textFieldBottom.setText(String.valueOf(result));
            }
        } else if (buttonText.equals("C")){
            textFieldTop.setText("");
            textFieldBottom.setText("0");
        } else if (buttonText.equals("CE")){
            textFieldBottom.setText("0");
        } else if (buttonText.equals("(")){
            if (topText.isEmpty() || lastTopChar == '+' || lastTopChar == '/' || lastTopChar == '-' || lastTopChar == '*' || lastTopChar == '('){
                textFieldTop.setText(topText + "(");
            }
        } else if (buttonText.equals(")")){
            if (lastBottomChar != '.' && calculateNumOfOpenParentheses(topText) > 0){
                if (lastTopChar != ')'){
                textFieldTop.setText(topText + bottomText + buttonText);}
                else textFieldTop.setText(topText + buttonText);
                textFieldBottom.setText("0");
            }

        } else if (buttonText.equals("*") || buttonText.equals("+") || buttonText.equals("/")){
            if (lastBottomChar != '.'){
                textFieldTop.setText(topText + bottomText + buttonText);
                textFieldBottom.setText("0");
            }
            if (lastTopChar == ')'){
                textFieldTop.setText(topText + buttonText);
            }

        } else if (buttonText.equals("-")){
            if (topText.isEmpty() || (lastTopChar >= '0' && lastTopChar <= '9') || lastTopChar == ')' || lastTopChar == '(' ){
                textFieldTop.setText(topText + buttonText);
            }

        } else if (buttonText.equals(".")){
            if (!bottomText.contains(".")){
                textFieldBottom.setText(bottomText+buttonText);
            }
        } else {
            if (bottomText.equals("0")){
                textFieldBottom.setText(buttonText);
            } else textFieldBottom.setText(bottomText + buttonText);
        }
    }

    private static int calculateNumOfOpenParentheses(String text){
        int numOfOpeningParenthesis = 0;
        int numOfClosingParenthesis = 0;
        for (int i = 0; i < text.length() ; i++) {
            if (text.charAt(i)=='(')numOfOpeningParenthesis++;
            if (text.charAt(i)==')')numOfClosingParenthesis++;
        }
        return numOfOpeningParenthesis-numOfClosingParenthesis;
    }



//    private static double executeCalculations(String expression){
//        List<Double> values = new ArrayList<>();
//        List<Character> operands = new ArrayList<>();
//
//        String doubleNumGroupPattern = "(-?\\d+\\.?\\d*)";
//        Pattern compiledGroupPattern = Pattern.compile(doubleNumGroupPattern);
//        Matcher groupMatcher = compiledGroupPattern.matcher(expression);
//        while(groupMatcher.find()){
//            values.add(Double.parseDouble(groupMatcher.group(1)));
//        }
//        System.out.println(Arrays.toString(values.toArray()));
//
//        return 0;
//    }

}
