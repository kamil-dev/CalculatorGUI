package com.kamilnowosad.test;

import com.kamilnowosad.model.CalculatorListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalculatorListenerTest {
    private String expression;
    private double result;

    @Parameterized.Parameters
    public static Collection createDataTest(){
        Object[][] data = new Object[][]{
                {"3.1-2.4*(-3+11)/22.3*(2+5*(4-3))",-2.92691},
                {"(2-4)+4+(1/2)*5/4-((-2+7)/4+1)",0.375 },
                {"2+2*0-(2+3.5)+5",1.5},
                {"-(-1-(-1-(-1)-(-1)-1-(-1)))+1",3.0},
                {"(-2*(2+3)-0.5)/(2*4+(-5))",-3.5}
        };
        return Arrays.asList(data);
    }

    public CalculatorListenerTest(String expression, Double result){
        this.expression = expression;
        this.result = result;
    }

    @Test
    public void expressionEvaluationShouldBeEqualWithResult(){
        double evaluation = CalculatorListener.calculate(expression,0).getValue();
        assertEquals(result,evaluation,0.0001);
    }


}