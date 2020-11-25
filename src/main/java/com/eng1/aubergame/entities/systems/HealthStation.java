package com.eng1.aubergame.entities.systems;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.Entity;
import com.eng1.aubergame.entities.creatures.Player;

import java.awt.*;

public class HealthStation extends Entity {

    private final Player player;
    private Rectangle healRect;
    private final Game game;

    public HealthStation(Game game, Player player, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.player = player;
        this.game = game;

        healRect = new Rectangle(Math.round(x), Math.round(y), 45, 70);
    }

    @Override
    public void update() {
        heal();
    }

    @Override
    public void render(Graphics g) {

    }

    public void heal() {
        Rectangle playerRec = new Rectangle(Math.round(player.getX()), Math.round(player.getY()), player.getWidth(), player.getHeight());
        if (playerRec.intersects(healRect)) {
            if (game.getKeyManager().isActionPressed()) {
                player.setHealth(100);
            }
        }
    }
}
