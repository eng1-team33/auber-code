package com.eng1.aubergame.entities.systems;

import com.eng1.aubergame.entities.Entity;

public abstract class System extends Entity {

    protected boolean destroyed = false;

    protected System(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void destroy(){
        this.destroyed = true;
    }

}
