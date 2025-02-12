package expression;

public interface Expression {
    int evaluate(int x, int y, int z);
    String toString();
    String toMiniString();
    boolean equals(Object obj);
    int priority();
}