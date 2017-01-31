package de.tu_chemnitz.sse.and2015.completealarm;

import android.view.View;

import java.util.Random;


public class GenerateRandomEquation {

    private int number_1;
    private int number_2;
    private int number_3;
    private String operator_1;
    private String operator_2;
    private final int MIN = 1;
    private final int MAX = 10;
    private final int MIN_OPERATOR_IDENTIFIER = 0;
    private final int MAX_OPERATOR_IDENTIFIER = 3;



    public String GenerateRandomOperator(int operatorIdentifier)

    {
        String operator = "+";

        switch (operatorIdentifier){
            case(0):
                    operator = "+";
                    break;

            case(1):
                operator = "-";
                break;

            case(2):
                operator = "/";
                break;

            case(3):
                operator = "*";
                break;

        }

        return operator;

    }


    public int GenerateRandomNumber()

    {

        Random randomInteger = new Random();
        int number = randomInteger.nextInt(MAX - MIN) + MIN;
        if (number %2 !=0)
        {
            number = number +1;
        }
        return number;

    }

    public int GenerateRandomOperatorIdentifier()

    {

        Random randomOperatorIdentifier = new Random();
        int operatorIdentifier = randomOperatorIdentifier.nextInt(MAX_OPERATOR_IDENTIFIER - MIN_OPERATOR_IDENTIFIER) + MIN_OPERATOR_IDENTIFIER;

        return operatorIdentifier;

    }


    public String GenerateEquation(){

        this.number_1 = this.GenerateRandomNumber();
        this.number_2 = this.GenerateRandomNumber();
        this.number_3 = this.GenerateRandomNumber();

        int randomIndentifier_1 = this.GenerateRandomOperatorIdentifier();
        this.operator_1 = this.GenerateRandomOperator(randomIndentifier_1);

        int randomIndentifier_2 = this.GenerateRandomOperatorIdentifier();
        this.operator_2 = this.GenerateRandomOperator(randomIndentifier_2);

        String equation = "( "+Integer.toString(this.number_1)+" "+this.operator_1+" "+Integer.toString(this.number_2)+" )"+" "+this.operator_2+" "+Integer.toString(this.number_3);

        return equation;
    }


    public int GetOperatorOutput(int number_1, int number_2, String operator){

        int result = number_1 + number_2;

        switch (operator) {
            case "+" :
                result = number_1 + number_2;
                break;
            case "-":
                result = number_1 - number_2;
                break;
            case "/":
                result = number_1 / number_2;
                break;
            case "*":
                result = number_1 * number_2;
                break;

        }

        return result;

    }



    public String GetEquationResult(){

        int intermediateResult = GetOperatorOutput(this.number_1,this.number_2,this.operator_1);
        int result = GetOperatorOutput(intermediateResult,this.number_3,this.operator_2);

        return Integer.toString(result);

    }

    public String CheckEquationResult(String userInput)
    {
        String result = GetEquationResult();

        if (userInput.equals(result))
        {
            return "Correct";
        }
        else {
            return "TryAgain";
        }
    }


}
