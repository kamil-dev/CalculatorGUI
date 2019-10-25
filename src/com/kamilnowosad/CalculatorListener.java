package com.kamilnowosad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

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
                try {
                double result = calculate(textFieldTop.getText(),0).value;
                textFieldBottom.setText(String.valueOf(result));
                } catch (ArithmeticException exception){
                    System.out.println("You can not divide by 0");
                    textFieldTop.setText("");
                    textFieldBottom.setText("0");
                }

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
            if (lastTopChar == '(' && bottomText.equals("0")){
                textFieldTop.setText(topText + buttonText);
            }
            else if (lastBottomChar != '.' && lastTopChar != ')' && !(bottomText.equals("0") && topText.isEmpty()) ){
                textFieldTop.setText(topText + bottomText + buttonText);
                textFieldBottom.setText("0");
            }
            else if (topText.isEmpty() || lastTopChar == ')' || lastTopChar == '(' ){
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

    private static ValueAndIndex calculate(String txt, int indexStart) {
        Stack<Double> s = new Stack<>();
        double number;
        StringBuilder numInBuild = new StringBuilder();
        char c;
        char operand =' ';
        int indexEnd = txt.length() - 1;

        for (int i = indexStart; i < txt.length() ; i++) {
            c = txt.charAt(i);
            if (c == '-' || c == '+' || c == '*' || c == '/' ){
                if (numInBuild.length() != 0 ) {
                    number = Double.parseDouble(numInBuild.toString());
                    numInBuild.setLength(0);
                    if (operand == '*' || operand == '/'){
                            number = (operand == '*') ? s.pop() * number : s.pop() / number;
                            s.push(number);
                    } else s.push(number);
                }
                operand = c;
                if (c == '-'){
                    numInBuild.append(c);
                }

            } else if ((c >= '0' && c <= '9') || c == '.' ) {
                numInBuild.append(c);
            } else if (c == '(') {
                ValueAndIndex p = calculate(txt, i+1);
                i = p.indexEnd;
                if (numInBuild.toString().equals("-")){
                    number = -p.value;
                    numInBuild.setLength(0);
                } else number = p.value;
                if (operand == '*' || operand == '/'){

                    if (operand == '*'){
                        number = s.pop()*number;
                    } else {
                        if (number == 0) throw new ArithmeticException("Can not divide by 0");
                        number = s.pop()/number;
                    }
                }
                s.push(number);
            } else if (c == ')'){
                indexEnd = i;
                i = txt.length() - 1;
            }
        }
        if (numInBuild.length() != 0 ) {
            number = Double.parseDouble(numInBuild.toString());
            numInBuild.setLength(0);
            if (operand == '*' || operand == '/'){
                if (operand == '*'){
                    number = s.pop()*number;
                } else {
                    if (number == 0) throw new ArithmeticException("Can not divide by 0");
                    number = s.pop()/number;
                }
            }
            s.push(number);
        }
        number = 0;
        for (Double d : s){
            System.out.println(d);
            number+=d;
        }
        return new ValueAndIndex(number,indexEnd);
    }

}
