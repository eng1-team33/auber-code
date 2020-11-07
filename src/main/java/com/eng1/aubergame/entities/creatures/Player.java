package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.gfx.Assets;

import java.awt.*;


public class Player extends Creature {

    private final Game game;

    public Player(Game game, float x, float y) {
        super(x, y, 39, 62);
        this.game = game;
    }

    @Override
    public void update() {
        getInput();
        move();
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
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
    }

}
