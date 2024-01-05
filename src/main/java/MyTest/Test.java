package MyTest;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        String content = "10+5-5+10+5-10+10*10/5";
        ArrayList<String> expressionList = new ArrayList<>();

        char[] charArray = content.toCharArray();

        String text = "";
        for (char c : charArray) {
            if (Character.isDigit(c)) {
                text += c;
            } else {
                expressionList.add(text);
                text = "";
                expressionList.add(String.valueOf(c));
            }
        }
        expressionList.add(text);

        Expression expression = new TerminalExpression(Integer.parseInt(expressionList.get(0)));
        for (int i = 0; i < expressionList.size(); i++) {
            if(expressionList.get(i).equals("+"))
                expression = new NonTerminalExpressionPlus(expression,new TerminalExpression(Integer.parseInt(expressionList.get(i+1))));
            else if (expressionList.get(i).equals("-")) {
                expression = new NonTerminalExpressionMinus(expression,new TerminalExpression(Integer.parseInt(expressionList.get(i+1))));
            }
            else if (expressionList.get(i).equals("/")) {
                expression = new NonTerminalExpressionDivision(expression,new TerminalExpression(Integer.parseInt(expressionList.get(i+1))));
            }
            else if (expressionList.get(i).equals("*")) {
                expression = new NonTerminalExpressionMultiplication(expression,new TerminalExpression(Integer.parseInt(expressionList.get(i+1))));
            }
        }

        System.out.println(expression.interpret());
    }
}

interface Expression {
    int interpret();
}

class TerminalExpression implements Expression {
    int value;

    public TerminalExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return this.value;
    }
}

class NonTerminalExpressionPlus implements Expression {
    Expression expression1;
    Expression expression2;

    public NonTerminalExpressionPlus(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public int interpret() {
        return expression1.interpret() + expression2.interpret();
    }
}

class NonTerminalExpressionMinus implements Expression {
    Expression expression1;
    Expression expression2;

    public NonTerminalExpressionMinus(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public int interpret() {
        return expression1.interpret() - expression2.interpret();
    }
}
class NonTerminalExpressionMultiplication implements Expression {
    Expression expression1;
    Expression expression2;

    public NonTerminalExpressionMultiplication(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public int interpret() {
        return expression1.interpret() * expression2.interpret();
    }
}

class NonTerminalExpressionDivision implements Expression {
    Expression expression1;
    Expression expression2;

    public NonTerminalExpressionDivision(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public int interpret() {
        return expression1.interpret() / expression2.interpret();
    }
}