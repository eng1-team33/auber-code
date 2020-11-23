package com.eng1.aubergame.entities.systems;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;

public class Teleporter extends System {

    private final Game game;
    private final Camera camera;
    public String name;

    public Teleporter(Game game, float x, float y, int width, int height, String name) {
        super(x, y, width, height);
        this.game = game;
        this.camera = game.getCamera();
        this.name = name;
        game.getTeleporterManager().add(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        //this.camera.drawOffsetImage(    , (int) x, (int) y, width, height, null, g);
    }

}
