package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.CollisionRectangle;
import com.eng1.aubergame.entities.Entity;
import com.eng1.aubergame.entities.Room;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.systems.System;
import com.eng1.aubergame.handlers.CollisionManager;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.sqrt;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
    public static final double DIAGONAL_MOVE_ADJUSTMENT = sqrt(0.5);

    private CollisionRectangle hitbox;
    private final CollisionManager collisionManager;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    protected boolean arrested = false;
    private boolean idle = true;

    private int wanderState = 0;
    //States {0 = walking to given point, 1 = waiting for random time at point}

    public float destinationX, destinationY;
    public Room destinationRoom = null;
    public Room currentRoom;
    private long waitTimer, lastTimeWander, randomTimeToWait;

    private boolean atXCoord = false;
    private boolean atYCoord = false;

    protected final World world;

    protected Creature(Game game, World world, float x, float y, int width, int height) {
        super(x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        this.hitbox = new CollisionRectangle(game, this.getX(), this.getY(), this.getWidth(),this.getHeight());
        this.collisionManager = game.getCollisionManager();
        this.world = world;
    }

    public void move() {
        this.checkCollisions();
        if (xMove == 0 || yMove == 0) {
            x += xMove;
            y += yMove;
        } else {
            x += DIAGONAL_MOVE_ADJUSTMENT * xMove;
            y += DIAGONAL_MOVE_ADJUSTMENT * yMove;
        }
    }

    public void checkCollisions(){
        int collisions = collisionManager.canMove(this.hitbox);
        switch(collisions) {
            case 1:
                if(yMove < 0){
                    yMove = 0;
                }
                break;
            case 2:
                if(xMove > 0){
                    xMove = 0;
                }
                break;
            case 3:
                if(yMove > 0){
                    yMove = 0;
                }
                break;
            case 4:
                if(xMove < 0){
                    xMove = 0;
                }
                break;
        }
    }

    public void clearMovement() {
        this.setXMove(0);
        this.setYMove(0);
    }

    public void updateMovement() {
        if(this.getXMove() != 0 || this.getYMove() != 0){
            if(this.getX() < destinationX - 10) {
                this.setXMove(speed);
            } else if(this.getX() > destinationX + 10) {
                this.setXMove(-speed);
            } else {
                this.setXMove(0);
                atXCoord = true;
            }
            if(this.getY() < destinationY - 10) {
                this.setYMove(speed);
            } else if(this.getY() > destinationY + 10) {
                this.setYMove(-speed);
            } else {
                this.setYMove(0);
                atYCoord = true;
            }
            if(atXCoord && atYCoord) {
                idle = true;
            }
        }
    }

    public void setCurrentRoom() {
        currentRoom = world.getCurrentRoom(this.getX(), this.getY());
    }

    public void wander() {
        switch (wanderState) {
            case 0:
                if(destinationRoom == null){
                    destinationRoom = currentRoom.getRandomAdjacentRoom();
                    destinationX = destinationRoom.getX() + (destinationRoom.getWidth() / 2);
                    destinationY = destinationRoom.getY() + (destinationRoom.getHeight() / 2);
                    idle = false;
                } else if(destinationRoom == currentRoom){
                    if(atXCoord && atYCoord) {
                        wanderState = 1;
                        waitTimer = 0;
                        lastTimeWander = java.lang.System.currentTimeMillis();
                        randomTimeToWait = ThreadLocalRandom.current().nextInt(500, 3000);
                    } else {
                        destinationX = ThreadLocalRandom.current().nextInt((int) destinationRoom.getX() + 20, (int) destinationRoom.getX() + destinationRoom.getWidth() - 20);
                        destinationY = ThreadLocalRandom.current().nextInt((int) destinationRoom.getY() + 20, (int) destinationRoom.getY() + destinationRoom.getHeight() - 20);
                        idle = false;
                    }
                } else {
                    destinationX = destinationRoom.getX() + (destinationRoom.getWidth() / 2);
                    destinationY = destinationRoom.getY() + (destinationRoom.getHeight() / 2);
                    idle = true;
                }
            case 1:
                waitTimer += java.lang.System.currentTimeMillis() - lastTimeWander;
                lastTimeWander = java.lang.System.currentTimeMillis();

                if(waitTimer > randomTimeToWait){
                    waitTimer = 0;
                    wanderState = 0;
                }
                break;
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
