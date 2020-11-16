package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.gfx.Animation;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Creature {

    private Animation animWalking;

    private final Game game;
    private final Camera camera;

    public Player(Game game, float x, float y) {
        super(x, y, 39, 62);
        this.game = game;
        this.camera = game.getCamera();

        animWalking = new Animation(250, Assets.playerWalking);
    }

    @Override
    public void update() {
        animWalking.update();
        getInput();
        move();
        camera.move(xMove, yMove);
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

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove > 0 || yMove > 0){
            return animWalking.getCurrentFrame();
        }
        return Assets.player;
    }

}
