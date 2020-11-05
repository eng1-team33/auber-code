package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.entities.Entity;

public abstract class Creature extends Entity {

    protected int health;
    
    public Creature(float x, float y) {
        super(x,y);
        health = 10;
    }

}
