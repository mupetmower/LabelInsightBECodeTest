package com.labelinsight.codetest.solutions;

public class Particle {
    private Integer speed = 1;
    private Direction dir;

    public Particle() {}

    public Particle(Integer speed, Direction dir) {
        this.speed = speed;
        this.dir = dir;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}


enum Direction {
    O, // Off - Indicates the space has no particle
    L, // Left
    R, // Right
    X, // Both - Indicates the space has both a Left and Right particle
}