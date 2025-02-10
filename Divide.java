package expression;

import exeption.EvaluationException;

public class Divide extends BinaryOperation {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int rightValue = right.evaluate(x, y, z);
        if (rightValue == 0) {
            throw new EvaluationException("division by zero");
        }
        return left.evaluate(x, y, z) / rightValue;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
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