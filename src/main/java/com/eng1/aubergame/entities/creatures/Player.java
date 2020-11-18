package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.CollisionManager;
import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.CollisionRectangle;
import com.eng1.aubergame.gfx.Animation;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Creature {

    private Animation animWalking;

    private final Game game;
    private final Camera camera;
    private final CollisionManager collisionManager;
    private CollisionRectangle hitbox;

    public Player(Game game, float x, float y) {
        super(x, y, 39, 62);
        this.game = game;
        this.camera = game.getCamera();
        this.collisionManager = game.getCollisionManager();
        this.hitbox = new CollisionRectangle(game, this.getX(), this.getY(), this.getWidth(),this.getHeight());

        animWalking = new Animation(250, Assets.playerWalking);
    }

    @Override
    public void update() {
        animWalking.update();
        getInput();
        checkCollisions();
        move();
        camera.move(xMove, yMove);
        hitbox.setX(this.getX());
        hitbox.setY(this.getY());
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (game.getKeyManager().isUpPressed()) {
            yMove = -speed;
        }
        if (game.getKeyManager().isDownPressed()) {
            yMove = speed;
        }
        if (game.getKeyManager().isLeftPressed()) {
            xMove = -speed;
        }
        if (game.getKeyManager().isRightPressed()) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        this.camera.drawOffsetImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null, g);
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

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove > 0 || yMove > 0){
            return animWalking.getCurrentFrame();
        }
        return Assets.player;
    }

}
