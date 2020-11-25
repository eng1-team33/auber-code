package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.Room;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.systems.System;
import com.eng1.aubergame.gfx.Animation;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Infiltrator extends Creature {

    private final Game game;
    private final World world;
    private final Player player;
    private final Camera camera;

    private boolean idle = true;
    private boolean abilityUsed = false;
    private boolean invisible = false;

    private int currentState = 0;
    //States {0 = wander, 1 = sabotage}

    private int sabotageState = 0;
    //States {0 = not found a target, 1 = target in current room, 2 = no active systems in current room, 3 = in corridor, 4 = at target system}

    private Room previousRoom = null;
    private final Random random = new Random();
    private int randomSign = 0;
    private System targetSystem = null;
    private long sabotageTimer, lastTime;
    private long abilityTimer, lastTimeAbility;
    private long randomAbilityTime = ThreadLocalRandom.current().nextLong(3000, 10000);

    private long abilityInUseTimer, abilityInUseLastTime;

    int chosenAbility = ThreadLocalRandom.current().nextInt(0,3);
    boolean abilityInProgress = false;

    private Animation animWalking;

    private int alienImage;
    private BufferedImage idleAlien;

    public Infiltrator(Game game, World world, Player player, float x, float y) {
        super(game, world, x, y, 49, 78);
        this.game = game;
        this.world = world;
        this.camera = game.getCamera();
        this.player = player;
        lastTimeAbility = java.lang.System.currentTimeMillis();
        alienImage = ThreadLocalRandom.current().nextInt(1,3);
        switch(alienImage) {
            case 1:
                idleAlien = Assets.alien1;
                animWalking = new Animation(250, Assets.alien1Walking);
                break;
            case 2:
                idleAlien = Assets.alien2;
                animWalking = new Animation(250, Assets.alien2Walking);
                break;
        }
    }

    public void arrest() {
        Rectangle playerRec = new Rectangle(Math.round(player.getX()), Math.round(player.getY()), player.getWidth(), player.getHeight());
        Rectangle infilRec = new Rectangle(Math.round(this.getX()), Math.round(this.getY()), this.getWidth(), this.getHeight());
        if (playerRec.intersects(infilRec)) {
            if (game.getKeyManager().isArrestPressed()) {
                arrested = true;
                this.setX(ThreadLocalRandom.current().nextInt(4506, 4893));
                this.setY(ThreadLocalRandom.current().nextInt(1603,2238));
                game.setInfiltratorsArrested(game.getInfiltratorsArrested() + 1);
            }
        }
    }

    @Override
    public void update() {
        if(!this.arrested) {
            this.arrest();
            if (idle) {
                this.setCurrentRoom();
                switch (currentState) {
                    case 0:
                        this.wander();
                        break;
                    case 1:
                        if (!abilityUsed) {
                            this.checkAbility();
                        }
                        this.sabotage();
                        break;
                }
            }

            this.updateMovement();
            this.move();
            this.clearMovement();
        }
    }

    @Override
    public void render(Graphics g) {
        if(!invisible) {
            this.camera.drawOffsetImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null, g);
        }
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
                break;
            case 1:
                if(currentRoom == null) {
                    sabotageState = 3;
                } else {
                    if (targetSystem == null) {
                        targetSystem = currentRoom.getRandomSystem();
                    }
                    if (this.getX() == targetSystem.getX() && this.getY() == targetSystem.getY()) {
                        sabotageState = 4;
                        game.setSabotageInProgress(true);
                        game.setSystemBeingSabotaged(targetSystem);
                        sabotageTimer = 0;
                        lastTime = java.lang.System.currentTimeMillis();
                    }
                    destinationX = targetSystem.getX();
                    destinationY = targetSystem.getY();
                    idle = false;
                }
                break;
            case 2:
                if(currentRoom == null) {
                    sabotageState = 3;
                } else {
                    if (previousRoom != null) {
                        destinationRoom = currentRoom.getRandomRoomExceptPrevious(previousRoom);
                    } else {
                        destinationRoom = currentRoom.getRandomAdjacentRoom();
                        previousRoom = currentRoom;
                    }
                    destinationX = destinationRoom.getX() + (destinationRoom.getWidth() / 2);
                    destinationY = destinationRoom.getY() + (destinationRoom.getHeight() / 2);
                    idle = true;
                    sabotageState = 0;
                }
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
                    targetSystem.sabotage();
                    sabotageTimer = 0;
                    this.sabotageState = 0;
                    this.currentState = 0;
                    game.setSabotageInProgress(false);
                    idle = true;
                    previousRoom = null;
                    targetSystem.sabotage();
                    currentRoom.removeSystem(targetSystem);
                }
                break;
        }
    }

    public void checkAbility() {
        abilityTimer += java.lang.System.currentTimeMillis() - lastTimeAbility;
        lastTimeAbility = java.lang.System.currentTimeMillis();

        if(currentState == 1 && abilityTimer > randomAbilityTime && abilityUsed == false) {
            abilityInProgress = true;
            useAbility(chosenAbility);
        }

        if(abilityInProgress) {
            stopAbility(chosenAbility);
        }
    }

    public void useAbility(int ability) {
        switch(ability) {
            //Damage
            case 0:
                if(this.getX() < player.getX() + 300 && this.getX() > player.getX() - 300 && this.getY() < player.getY() + 300 && this.getY() > player.getY() - 300) {
                    player.setHealth(player.getHealth() - 30);
                    abilityUsed = true;
                    abilityInProgress = false;
                }
                break;
            //Speed
            case 1:
                abilityInUseLastTime = java.lang.System.currentTimeMillis();
                this.setSpeed(8.0f);
                break;
            //Invisibility
            case 2:
                abilityInUseLastTime = java.lang.System.currentTimeMillis();
                invisible = true;
                break;
        }
    }

    public void stopAbility(int ability) {
        abilityInUseTimer += java.lang.System.currentTimeMillis() - abilityInUseLastTime;
        abilityInUseLastTime = java.lang.System.currentTimeMillis();

        switch(ability) {
            case 1:
                if(abilityInUseTimer > 7000) {
                    this.setSpeed(4.0f);
                    abilityUsed = true;
                    abilityInProgress = false;
                }
                break;
            case 2:
                if(abilityInUseTimer > 4000){
                    invisible = false;
                    abilityUsed = true;
                    abilityInProgress = false;
                }
                break;
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove != 0 || yMove != 0){
            return animWalking.getCurrentFrame();
        }
        return idleAlien;
    }

    public boolean isArrested() {
        return this.arrested;
    }
}
