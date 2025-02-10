package expression;

public class Add extends BinaryOperation {
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) + right.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }

    public int priority() {
        return 2;
    }

    @Override
    public String toMiniString() {
        return left.toMiniString() + " + " + right.toMiniString();
    }

}
