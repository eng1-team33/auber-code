package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Alien extends Creature {

    protected Alien(Game game, World world, float x, float y, int width, int height) {
        super(game, world, x, y, width, height);
    }

    @Override
    public void update() {
        this.wander();
        this.updateMovement();
        this.move();
        this.clearMovement();
    }

    @Override
    public void render(Graphics g) {

    }



}
