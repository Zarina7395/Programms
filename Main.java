package parser;

import expression.Expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Если передано значение через аргументы командной строки
        if (args.length == 0) {
            System.out.println("Пожалуйста, введите значение x, y, z в командной строке.");
            return;
        }

        int x, y, z;
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            z = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введенное значение не является целым числом.");
            return;
        }

        String expressionInput = "x * (x - 2) * x + 1";

        Parser parser = new Parser(expressionInput);
        Expression expression;

        try {
            expression = parser.parse();
        } catch (RuntimeException e) {
            System.out.println("Ошибка разбора выражения: " + e.getMessage());
            return;
        }

        // Выводим результаты
        System.out.println("Значение выражения: " + expression.evaluate(x, y, z));
        System.out.println("Строковое представление: " + expression.toString());
        System.out.println("Минимальное строковое представление: " + expression.toMiniString());
    }
}