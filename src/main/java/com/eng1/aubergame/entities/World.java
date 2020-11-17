package com.eng1.aubergame.entities;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;

public class World extends Entity {

    private final Game game;
    private final Camera camera;

    public World(Game game, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.game = game;
        this.camera = game.getCamera();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        this.camera.drawOffsetImage(Assets.world, (int) x, (int) y, width, height, null, g);
    }
}
