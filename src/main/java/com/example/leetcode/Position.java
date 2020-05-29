package com.example.leetcode;

import java.util.Objects;

public class Position {
    int x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
