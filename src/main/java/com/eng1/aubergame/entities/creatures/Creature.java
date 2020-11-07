package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.entities.Entity;

import static java.lang.Math.sqrt;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
    public static final double DIAGONAL_MOVE_ADJUSTMENT = sqrt(0.5);

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    protected Creature(float x, float y, int width, int height) {
        super(x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (xMove == 0 || yMove == 0) {
            x += xMove;
            y += yMove;
        } else {
            x += DIAGONAL_MOVE_ADJUSTMENT * xMove;
            y += DIAGONAL_MOVE_ADJUSTMENT * yMove;
        }
    }

    //GETTERS AND SETTERS

    public float getXMove() {
        return xMove;
    }

    public void setXMove(float xMove) {
        this.xMove = xMove;
    }

    public float getYMove() {
        return yMove;
    }

    public void setYMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
