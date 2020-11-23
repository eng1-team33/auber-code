package com.eng1.aubergame.handlers;

import com.eng1.aubergame.entities.CollisionRectangle;

import java.util.ArrayList;

public class CollisionManager {

    public ArrayList<CollisionRectangle> collisionRectangles = new ArrayList<>();

    public CollisionManager(){

    }

    public void add(CollisionRectangle collisionRectangle) {
        this.collisionRectangles.add(collisionRectangle);
    }

    public int canMove(CollisionRectangle hitbox){
        for(CollisionRectangle object : this.collisionRectangles){
            if(object.isCollidingUp(hitbox)){
                return 1;
            }
            if(object.isCollidingRight(hitbox)){
                return 2;
            }
            if(object.isCollidingDown(hitbox)){
                return 3;
            }
            if(object.isCollidingLeft(hitbox)){
                return 4;
            }
        }
        return 0;
    }
}
