package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.Room;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.systems.System;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Infiltrator extends Creature {

    private final Game game;
    private final World world;

    protected boolean arrested = false;
    private boolean idle = true;

    private int currentState = 0;
    //States {0 = wander, 1 = sabotage}

    private int sabotageState = 0;
    //States {0 = not found a target, 1 = target in current room, 2 = no active systems in current room, 3 = in corridor, 4 = at target system}

    private int wanderState = 0;
    //States {0 = walking to given point, 1 = waiting for random time at point}

    private Room previousRoom = null;
    private final Random random = new Random();
    private int randomSign = 0;
    private System targetSystem = null;
    private long sabotageTimer, lastTime;

    public Infiltrator(Game game, World world, float x, float y) {
        super(game, world, x, y, 39, 62);
        this.game = game;
        this.world = world;
    }

    public void arrest(){
        arrested = true;
    }

    @Override
    public void update() {
        if(!this.arrested) {
            if(idle){
                this.setCurrentRoom();
                switch (currentState) {
                    case 0:
                        this.wander();
                        break;
                    case 1:
                        this.sabotage();
                        break;
                }
            }
        }
        this.updateMovement();
        this.move();
        this.clearMovement();
    }

    @Override
    public void render(Graphics g) {
        //game.getCamera().drawOffsetImage();
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void sabotage() {
        switch(sabotageState) {
            case 0:
                if(currentRoom == null) {
                    sabotageState = 3;
                } else if(currentRoom.activeSystems() == 0) {
                    sabotageState = 2;
                    randomSign = 0;
                } else {
                    sabotageState = 1;
                    randomSign = 0;
                }
            case 1:
                if(targetSystem == null) {
                    targetSystem = currentRoom.getRandomSystem();
                }
                if(this.getX() == targetSystem.getX() && this.getY() == targetSystem.getY()) {
                    sabotageState = 4;
                    game.setSabotageInProgress(true);
                    game.setSystemBeingSabotaged(targetSystem);
                    sabotageTimer = 0;
                    lastTime = java.lang.System.currentTimeMillis();
                }
                destinationX = targetSystem.getX();
                destinationY = targetSystem.getY();
                idle = false;
                break;
            case 2:
                if(previousRoom != null) {
                    destinationRoom = currentRoom.getRandomRoomExceptPrevious(previousRoom);
                } else {
                    destinationRoom = currentRoom.getRandomAdjacentRoom();
                    previousRoom = currentRoom;
                }
                destinationX = destinationRoom.getX() + (destinationRoom.getWidth() / 2);
                destinationY = destinationRoom.getY() + (destinationRoom.getHeight() / 2);
                idle = true;
                sabotageState = 0;
                break;
            case 3:
                if(randomSign == 0) {
                    if (random.nextBoolean()) {
                        randomSign = 1;
                    } else {
                        randomSign = -1;
                    }
                }
                this.setXMove(speed * randomSign);
                this.setYMove(speed * randomSign);
                break;
            case 4:
                sabotageTimer += java.lang.System.currentTimeMillis() - lastTime;
                lastTime = java.lang.System.currentTimeMillis();

                if(sabotageTimer > 5000){
                    targetSystem.destroy();
                    sabotageTimer = 0;
                    this.sabotageState = 0;
                    this.currentState = 0;
                    game.setSabotageInProgress(false);
                    idle = true;
                    previousRoom = null;
                }
                break;
        }
    }

    public boolean isArrested() {
        return this.arrested;
    }
}
