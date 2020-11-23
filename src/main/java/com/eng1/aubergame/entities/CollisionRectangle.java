package com.eng1.aubergame.entities;

import com.eng1.aubergame.Game;

import java.awt.*;

public class CollisionRectangle extends Entity {

    public CollisionRectangle(float x, float y, int width, int height){
        super(x, y, width, height);
    }

    public CollisionRectangle(Game game, float x, float y, int width, int height) {
        super(x, y, width, height);
        game.getCollisionManager().add(this);
    }


    public boolean isColliding(CollisionRectangle other) {
        if (this.y + this.height < other.y || other.y + other.height < this.y) {
            return false;
        }
        if (this.x + this.width < other.x || other.x + other.width < this.x) {
            return false;
        }
        return true;
    }

    public boolean isCollidingUp(CollisionRectangle other) {
        if(this.y > other.y && this.y < other.y + other.height) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollidingDown(CollisionRectangle other) {
        if(this.y + this.height > other.y && this.y + this.height < other.y + other.height) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollidingLeft(CollisionRectangle other) {
        if(this.x > other.x && this.x < other.x + other.width) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollidingRight(CollisionRectangle other) {
        if(this.x + this.width > other.x && this.x + this.width < other.x + other.width) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }



}
