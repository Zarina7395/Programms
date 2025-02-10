package expression;

import java.util.Objects;

public class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (Objects.equals(name, "x")) {
            return x;
        }
        if (Objects.equals(name, "y")) {
            return y;
        }
        if (Objects.equals(name, "z")) {
            return z;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    public int priority() { return 1;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Variable)) return false;
        Variable other = (Variable) obj;
        return name.equals(other.name);
    }
}

