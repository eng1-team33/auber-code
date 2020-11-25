package com.eng1.aubergame.entities.systems;

import com.eng1.aubergame.entities.Entity;

import java.awt.*;

public class System extends Entity {

    protected boolean sabotaged = false;

    protected System(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void sabotage(){
        this.sabotaged = true;
    }

}
