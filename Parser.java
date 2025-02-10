package parser;




import exeption.ParsingException;
import expression.Variable;


import expression.*;
import java.util.*;

public class Parser {
    private final String input;
    private int pos = 0;

    public Parser(String input) {
        this.input = input.replaceAll("\\s+", ""); // Удаляем пробелы
    }

    private char currentChar() {
        return (pos < input.length()) ? input.charAt(pos) : '\0';
    }

    private void advance() {
        pos++;
    }

    private int parseNumber() {
        int start = pos;
        while (Character.isDigit(currentChar())) {
            advance();
        }
        return Integer.parseInt(input.substring(start, pos));
    }

    private Expression parseVariable() {
        char ch = currentChar();
        advance();
        return new Variable(String.valueOf(ch));
    }

private Expression parsePrimary() {
    if (Character.isDigit(currentChar())) {
        return new Const(parseNumber());
    } else if (currentChar() == 'x' || currentChar() == 'y' || currentChar() == 'z') {
        return parseVariable();
    } else if (currentChar() == '(') {
        advance(); // Пропустить '('
        Expression expression = parseExpression();
        advance(); // Пропустить ')'
        return expression;
    } else if (currentChar() == '-') {
        advance(); // Пропустить '-'
        return new Subtract(new Const(0), parsePrimary()); // Унарный минус
    }

    throw new ParsingException("Unexpected character: " + currentChar());
}

    private Expression parseTerm() {
        Expression left = parsePrimary();
        while (currentChar() == '*' || currentChar() == '/') {
            char op = currentChar();
            advance();
            Expression right = parsePrimary();
            if (op == '*') {
                left = new Multiply(left, right);
            } else {
                left = new Divide(left, right);
            }
        }
        return left;
    }

    private Expression parseExpression() {
        Expression left = parseTerm();
        while (currentChar() == '+' || currentChar() == '-') {
            char op = currentChar();
            advance();
            Expression right = parseTerm();
            if (op == '+') {
                left = new Add(left, right);
            } else {
                left = new Subtract(left, right);
            }
        }
        return left;
    }

    public Expression parse() {
        return parseExpression();
    }
}