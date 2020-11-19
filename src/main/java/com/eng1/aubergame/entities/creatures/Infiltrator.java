package com.eng1.aubergame.entities.creatures;

import java.awt.*;

public class Infiltrator extends Creature {

    protected boolean arrested = false;

    protected Infiltrator(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void arrest(){
        arrested = true;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
