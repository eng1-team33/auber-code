package com.eng1.aubergame.entities.systems;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.Entity;
import com.eng1.aubergame.entities.Room;

import java.awt.*;

public class System extends Entity {

    protected boolean sabotaged = false;
    private Room room;
    private final Game game;

    public System(Game game, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void sabotage(){
        this.sabotaged = true;
        this.room.removeSystem(this);
        game.setSystemsRemaining(game.getSystemsRemaining() - 1);
    }

}
