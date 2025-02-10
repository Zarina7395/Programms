package expression;

import exeption.EvaluationException;

// Умножение
public class Multiply extends BinaryOperation {
    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        long leftValue = left.evaluate(x, y, z);
        long rightValue = right.evaluate(x, y, z);
        if (leftValue == 0 || rightValue == 0) {
            return 0;
        }
        // Проверка на переполнение
        if (leftValue > Integer.MAX_VALUE / rightValue) {
            throw new EvaluationException("overflow");
        }

        return (int)(leftValue * rightValue);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " * " + right.toString() + ")";
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String toMiniString() {
        if (left.priority() == 1) {
            if (right.priority() == 1) {
                return left.toMiniString() + " * " + right.toMiniString();
            } else {
                return left.toMiniString() + " * (" + right.toMiniString() + ")";
            }
        } else {
            if (right.priority() == 1) {
                return "(" + left.toMiniString() + ") * " + right.toMiniString();
            } else {return "(" + left.toMiniString() + ") * (" + right.toMiniString() + ")";}
        }
    }
}

